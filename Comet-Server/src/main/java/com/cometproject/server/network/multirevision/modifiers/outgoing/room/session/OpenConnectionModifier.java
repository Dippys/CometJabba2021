package com.cometproject.server.network.multirevision.modifiers.outgoing.room.session;

import com.cometproject.server.network.multirevision.Attributes;
import com.cometproject.server.network.multirevision.modifiers.Modifier;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class OpenConnectionModifier implements Modifier {
    @Override
    public ByteBuf modify(ByteBuf packet, String revision, ChannelHandlerContext ctx) {
        int flatId = ctx.channel().attr(Attributes.LAST_FLAT_ID).get();
        packet.writeInt(flatId);//write flat id
        int oldLength = packet.getInt(0);
        packet.setInt(0, oldLength + 4); // account for new appended int
        return packet;
    }
}

