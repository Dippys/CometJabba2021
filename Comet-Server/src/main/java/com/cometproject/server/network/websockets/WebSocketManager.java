package com.cometproject.server.network.websockets;

import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.network.websockets.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class WebSocketManager
        implements Initialisable {
    private static WebSocketManager instance;

    public void initialize() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = (new ServerBootstrap().group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)).childHandler(new ChannelInitializer<SocketChannel>(){

                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ChannelHandler[]{new HttpServerCodec()}).addLast(new ChannelHandler[]{new WebSocketHandler()});
                }
            }).option(ChannelOption.SO_BACKLOG, 1024);
            serverBootstrap.bind(2053);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static WebSocketManager getInstance() {
        if (instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }
}