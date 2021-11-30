package com.cometproject.server.protocol.codec.websockets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.CharsetUtil;

import java.util.List;

public class MessageInterceptorHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.toString(CharsetUtil.UTF_8).startsWith("GET")) {
            // this is a websocket upgrade request, so add the appropriate decoders/encoders
            ctx.pipeline().addAfter("messageInterceptor", "websocketHandler", new WebSocketFrameCodec());
            ctx.pipeline().addAfter("messageInterceptor", "protocolHandler", new WebSocketServerProtocolHandler("/", true));
            ctx.pipeline().addAfter("messageInterceptor", "customHttpHandler", new HttpCustomHandler());
            ctx.pipeline().addAfter("messageInterceptor", "objectAggregator", new HttpObjectAggregator(65536));
            ctx.pipeline().addAfter("messageInterceptor", "httpCodec", new HttpServerCodec());
        }
        // Remove ourselves
        ctx.pipeline().remove(this);
    }
}

