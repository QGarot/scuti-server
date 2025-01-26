package server;

import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import messages.IGameHandler;
import messages.MessageHandler;
import server.connections.ConnectionHandler;
import game.IGameController;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import logger.Logger;
import server.codec.Decoder;
import server.codec.Encoder;
import server.streams.IRequest;

import java.net.InetSocketAddress;

public class ScutiServer implements IServer {

    private final String HOST;
    private final int PORT;

    private final IGameHandler messageHandler;  // handles game events
    private final SimpleChannelInboundHandler<IRequest> connectionHandler; // handles any received data from the client
    private final MessageToMessageEncoder<byte[]> encoder;
    private final ByteToMessageDecoder decoder;

    private final IGameController game; // the server needs to know the game logic to treats incoming data

    public ScutiServer(String host, int port, IGameController gameController) {
        this.HOST = host;
        this.PORT = port;

        // game
        this.game = gameController;

        // Handlers (IoC)
        this.messageHandler = new MessageHandler(this.getGame());
        this.connectionHandler = new ConnectionHandler(this.getMessageHandler());

        // encoder and decoder
        this.encoder = new Encoder();
        this.decoder = new Decoder();
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // Get needed object to set the child handler below
        MessageToMessageEncoder<byte[]> encoder = this.getEncoder();
        ByteToMessageDecoder decoder = this.getDecoder();
        SimpleChannelInboundHandler<IRequest> connectionHandler = this.getConnectionHandler();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("encoder", encoder);
                            socketChannel.pipeline().addLast("decoder", decoder);
                            socketChannel.pipeline().addLast("handler", connectionHandler);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_RCVBUF, 2048)
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(2048))
                    .childOption(ChannelOption.ALLOCATOR, new UnpooledByteBufAllocator(false));

            // Bind and start to accept incoming connections.
            b.bind(new InetSocketAddress(this.HOST, this.PORT)).addListener(objectFuture -> {
                if (objectFuture.isSuccess()) {
                    Logger.logInfo("Scuti server is listening on " + this.HOST + ":" + this.PORT);
                } else {
                    Logger.logError("Scuti server failed to start!");
                }
            });
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public IGameController getGame() {
        return this.game;
    }

    @Override
    public SimpleChannelInboundHandler<IRequest> getConnectionHandler() {
        return this.connectionHandler;
    }

    @Override
    public IGameHandler getMessageHandler() {
        return this.messageHandler;
    }

    @Override
    public MessageToMessageEncoder<byte[]> getEncoder() {
        return this.encoder;
    }

    @Override
    public ByteToMessageDecoder getDecoder() {
        return this.decoder;
    }
}
