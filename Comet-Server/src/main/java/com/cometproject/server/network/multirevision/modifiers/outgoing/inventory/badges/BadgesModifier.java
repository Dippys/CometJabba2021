package com.cometproject.server.network.multirevision.modifiers.outgoing.inventory.badges;

import com.cometproject.server.network.multirevision.modifiers.Modifier;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BadgesModifier implements Modifier {
    @Override
    public ByteBuf modify(ByteBuf packet, String revision, ChannelHandlerContext ctx) {
        int oldLength = packet.readInt();
        short header = packet.readShort();

        ArrayList<Map.Entry<Integer,String>> ownedBadges = new ArrayList<>();

        int badgeCount = packet.readInt();
        for(int i = 0; i < badgeCount; i++) {
            int badgeId = packet.readInt();
            int stringLength = packet.readShort();
            byte[] data = new byte[stringLength];
            packet.readBytes(data);
            String badgeCode = new String(data, CharsetUtil.UTF_8);
            ownedBadges.add(new AbstractMap.SimpleEntry<>(badgeId, badgeCode));
        }

        /*
        // trashed
        int equippedCount = packet.readInt();
        for(int i = 0; i < equippedCount; i++) {
            packet.readInt();//badge slot
            int stringLength = packet.readShort();
            packet.skipBytes(stringLength);//badge code
        }
         */
        packet.skipBytes(packet.readableBytes());
        packet.discardReadBytes();

        packet.writeInt(-1); // we dont know length yet
        packet.writeShort(header);
        packet.writeInt(0);//page
        packet.writeInt(1);//out
        packet.writeInt(ownedBadges.size());//badgecount
        for(Map.Entry<Integer,String> entry : ownedBadges) {
            packet.writeInt(entry.getKey());
            byte[] data = entry.getValue().getBytes(CharsetUtil.UTF_8);
            packet.writeShort(data.length);
            packet.writeBytes(data);
        }
        packet.setInt(0, packet.writerIndex() - 4);// set new length
        return packet;
    }
}

