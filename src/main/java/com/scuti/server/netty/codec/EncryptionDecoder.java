package com.scuti.server.netty.codec;

import com.scuti.server.encryption.RC4;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import io.netty.buffer.Unpooled;

import java.util.List;

public class EncryptionDecoder extends ByteToMessageDecoder {

    private final RC4 rc4;

    public EncryptionDecoder(RC4 rc4) {
        this.rc4 = rc4;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {

        ByteBuf result = Unpooled.buffer();

        while (buffer.readableBytes() > 0) {
            result.writeByte((byte) (buffer.readByte() ^ this.rc4.next()));
        }

        out.add(result);
    }
}
