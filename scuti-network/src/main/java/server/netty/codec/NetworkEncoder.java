package server.netty.codec;

import com.scuti.util.logger.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class NetworkEncoder extends MessageToMessageEncoder<byte[]> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, byte[] bytes, List<Object> list) throws Exception {
        try {
            ByteBuf buffer = Unpooled.buffer();
            buffer.writeBytes(bytes);
            list.add(buffer);
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
    }
}
