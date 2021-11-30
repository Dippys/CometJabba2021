package com.cometproject.server.network.multirevision.modifiers.incoming.room.session;

import com.cometproject.server.network.multirevision.Attributes;
import com.cometproject.server.network.multirevision.modifiers.Modifier;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class OpenFlatConnectionModifier implements Modifier {
    @Override
    public ByteBuf modify(ByteBuf packet, String revision, ChannelHandlerContext ctx) {
        //save flat id to session
        int roomId = packet.getInt(2);//2 (header)
        ctx.channel().attr(Attributes.LAST_FLAT_ID).set(roomId);
        return packet;
    }
}

