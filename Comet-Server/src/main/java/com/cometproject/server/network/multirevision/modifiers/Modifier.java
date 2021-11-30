package com.cometproject.server.network.multirevision.modifiers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public interface Modifier {
    ByteBuf modify(ByteBuf packet, String revision, ChannelHandlerContext ctx);
}

