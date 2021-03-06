package com.cometproject.server.network.messages.incoming.room.item.stickies;

import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.items.RoomItemWall;
import com.cometproject.server.game.rooms.objects.items.types.wall.PostItWallItem;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;


public class SavePostItMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int virtualId = msg.readInt();

        long itemId = ItemManager.getInstance().getItemIdByVirtualId(virtualId);

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) {
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();

        String colour = msg.readString();
        String message = msg.readString();

        RoomItemWall wallItem = room.getItems().getWallItem(itemId);

        if (!(wallItem instanceof PostItWallItem)) return;

        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId()) && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            return;
        }

        final PostItWallItem item = (PostItWallItem) wallItem;

        item.setExtraData(colour + " " + message);
        item.sendUpdate();

        item.saveData();
    }
}
