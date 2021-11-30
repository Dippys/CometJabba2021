package com.cometproject.server.network.multirevision.modifiers.outgoing.room.session;

import com.cometproject.server.network.multirevision.Attributes;
import com.cometproject.server.network.multirevision.modifiers.Modifier;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class FlatAccessibleModifier implements Modifier {
    @Override
    public ByteBuf modify(ByteBuf packet, String revision, ChannelHandlerContext ctx) {
        int flatId = ctx.channel().attr(Attributes.LAST_FLAT_ID).get();

        int oldLength = packet.readInt();
        short header = packet.readShort();
        int stringLength = packet.readShort();
        byte[] data = new byte[stringLength];
        packet.readBytes(data);

        packet.writeInt(oldLength + 4); // account for new appended int
        packet.writeShort(header);
        packet.writeInt(flatId);//write flat id
        packet.writeShort(stringLength);
        packet.writeBytes(data);
        return packet;
    }
}

