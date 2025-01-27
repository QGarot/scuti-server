package server.codec;

import server.codec.encoding.Base64Encoding;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import logger.Logger;
import server.streams.IRequest;
import server.streams.Request;

import java.util.List;

public class Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buffer, List<Object> list) throws Exception {
        buffer.markReaderIndex();

        if (buffer.readableBytes() < 5) {
            return;
        }

        byte delimiter = buffer.readByte();
        buffer.resetReaderIndex();

        if (delimiter == 60) {
            String policy = "<?xml version=\"1.0\"?>" +
                    "<cross-domain-policy>" +
                    "<allow-access-from domain=\"*\" to-ports=\"*\" />" +
                    "</cross-domain-policy>\0";

            ChannelFuture future = channelHandlerContext.channel().writeAndFlush(policy.getBytes());
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

            IRequest request = new Request(messageHeader, message);
            list.add(request);
        }
    }
}
