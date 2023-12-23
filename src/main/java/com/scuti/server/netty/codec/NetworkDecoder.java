package com.scuti.server.netty.codec;

import com.scuti.server.encoding.Base64Encoding;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.util.logger.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NetworkDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {

        buffer.markReaderIndex();

        if (buffer.readableBytes() < 6) {
            return;
        }

        byte delimiter = buffer.readByte();
        buffer.resetReaderIndex();

        if (delimiter == 60) {
            String policy = "<?xml version=\"1.0\"?>\r\n"
                    + "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n"
                    + "<cross-domain-policy>\r\n"
                    + "<allow-access-from domain=\"*\" to-ports=\"*\" />\r\n"
                    + "</cross-domain-policy>\0)";

            ChannelFuture future = ctx.channel().writeAndFlush(Unpooled.copiedBuffer(policy.getBytes()));
            future.addListener(ChannelFutureListener.CLOSE);
            Logger.logInfo("Policy sent");
        } else {

            buffer.markReaderIndex();
            int length = Base64Encoding.decodeInt32(new byte[] {buffer.readByte(), buffer.readByte(), buffer.readByte()});

            if (buffer.readableBytes() < length) {
                buffer.resetReaderIndex();
                return;
            }

            if (length < 0) {
                return;
            }

            int messageHeader = Base64Encoding.decodeInt32(new byte[] {buffer.readByte(), buffer.readByte()});
            byte[] message = new byte[length - 2];

            buffer.readBytes(message, 0, message.length);

            NettyRequest request = new NettyRequest(messageHeader, message);
            out.add(request);
        }
    }
}
