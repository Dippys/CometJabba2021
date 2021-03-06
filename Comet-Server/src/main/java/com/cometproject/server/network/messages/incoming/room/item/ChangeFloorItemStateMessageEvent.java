package com.cometproject.server.network.messages.incoming.room.item;

import com.cometproject.api.game.catalog.types.ICatalogItem;
import com.cometproject.api.game.catalog.types.ICatalogPage;
import com.cometproject.api.game.furniture.types.FurnitureDefinition;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.pathfinding.AffectedTile;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.WiredFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.data.WiredItemData;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.WiredTriggerStateChanged;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers.custom.WiredTriggerCustomStateChanged;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.UpdateStackMapMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ChangeFloorItemStateMessageEvent implements Event {
    public void handle(Session client, MessageEvent msg) {

        int virtualId = msg.readInt();

        Long itemId = ItemManager.getInstance().getItemIdByVirtualId(virtualId);
        final Map<Long, RoomItemFloor> floorItems = new ConcurrentHashMap<>();

        if (itemId == null) {
            return;
        }

        if (client.getPlayer().getEntity() == null || client.getPlayer().getEntity().getRoom() == null) {
            return;
        }

        if (!client.getPlayer().getEntity().isVisible()) {
            return;
        }

        if(client.getPlayer().getEntity().isFreeze() == true) {
            return;
        }

        Room room = client.getPlayer().getEntity().getRoom();

        RoomItemFloor item = room.getItems().getFloorItem(itemId);

        if (item == null) {
            return;
        }

        if(client.getPlayer().isSearchFurni()){
            FurnitureDefinition itemData = item.getDefinition();
            ICatalogItem catalogItem = CatalogManager.getInstance().getCatalogItemByItemId(itemData.getId());
            ICatalogPage page = CatalogManager.getInstance().getPage(catalogItem == null ? 0 : catalogItem.getPageId());


            final String devInfo = "<b>Informaci??n del furni que est??s buscando.</b>\r\r" +
                    //"<b>ID Base:</b> " + itemData.getId() + "\r" + "<b>Nombre:</b>" + itemData.getItemName() + "\r" +
                    //"<b>Sprite ID:</b> " + itemData.getSpriteId() + "\r" +
                    //"<b>Interacci??n:</b> " + itemData.getInteraction() + "\r" +
                    //"<b>Cycle count:</b> " + itemData.getInteractionCycleCount() + "\r\r" +
                    "<b>Informaci??n del producto en cat??logo:</b>\r\r" +
                    //"<b>ID Cat??logo:</b> " + (catalogItem == null ? "ID no encontrada." : catalogItem.getId()) + "\r" +
                    "<b>Nombre:</b> " + (catalogItem == null ? "Nombre no encontrado." : catalogItem.getDisplayName()) + "\r" +
                    //"<b>ID P??gina:</b> " + (page == null ? "P??gina no encontrada." : page.getId()) +
                    "<b>Nombre de p??gina:</b> " + (page == null ? "P??gina no encontrada." : page.getCaption());

            client.send(new AdvancedAlertMessageComposer("Informaci??n de " + itemData.getItemName(), devInfo, "Abrir p??gina del cat??logo", "event:catalog/open/" + (page == null ? "" : page.getLinkName()), ""));
            return;
        }

        client.getPlayer().getQuests().progressQuest(QuestType.EXPLORE_FIND_ITEM, item.getDefinition().getSpriteId());

        WiredTriggerStateChanged.executeTriggers(client.getPlayer().getEntity(), item);
        WiredTriggerCustomStateChanged.executeTriggers(client.getPlayer().getEntity(), item);

        if (item.onInteract(client.getPlayer().getEntity(), msg.readInt(), false)) {
            List<Position> tilesToUpdate = new ArrayList<>();
            tilesToUpdate.add(new Position(item.getPosition().getX(), item.getPosition().getY(), 0d));

            for (AffectedTile tile : AffectedTile.getAffectedTilesAt(item.getDefinition().getLength(), item.getDefinition().getWidth(), item.getPosition().getX(), item.getPosition().getY(), item.getRotation())) {
                if (room.getEntities().getEntitiesAt(new Position(tile.x, tile.y)).size() >= 0)
                    tilesToUpdate.add(new Position(tile.x, tile.y, 0d));
            }

            for (Position tileToUpdate : tilesToUpdate) {
                RoomTile tile = room.getMapping().getTile(tileToUpdate);

                if (tile != null) {
                    tile.reload();

                    room.getEntities().broadcastMessage(new UpdateStackMapMessageComposer(tile));
                }
            }
        }
    }
}
