package com.cometproject.server.network.multirevision.modifiers.outgoing.room.permissions;

import com.cometproject.server.network.multirevision.Attributes;
import com.cometproject.server.network.multirevision.modifiers.Modifier;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class YouAreControllerModifier implements Modifier {
    @Override
    public ByteBuf modify(ByteBuf packet, String revision, ChannelHandlerContext ctx) {
        int flatId = ctx.channel().attr(Attributes.LAST_FLAT_ID).get();
        int oldLength = packet.getInt(0);
        int access = packet.getInt(6);// length (4) + header (2)

        packet.setInt(6, flatId);//flat id
        packet.writeInt(access);//access

        packet.setInt(0, oldLength + 4); // account for new appended int
        return packet;
    }
}

