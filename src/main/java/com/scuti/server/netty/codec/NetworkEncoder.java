package com.scuti.server.netty.codec;

import com.scuti.api.messages.MessageComposer;
import com.scuti.server.netty.streams.NettyResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class NetworkEncoder extends MessageToMessageEncoder<MessageComposer> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageComposer msg, List<Object> out) throws Exception {

        ByteBuf buffer = Unpooled.buffer();

        NettyResponse response = new NettyResponse(msg.getHeader(), buffer);
        msg.compose(response);

        if (!response.hasLength()) {
            buffer.setInt(0, buffer.writerIndex() - 4);
        }

        out.add(buffer);
    }
}
