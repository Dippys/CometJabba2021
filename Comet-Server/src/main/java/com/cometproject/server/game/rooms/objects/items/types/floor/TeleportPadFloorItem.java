package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.types.Room;

public class TeleportPadFloorItem extends TeleporterFloorItem {
    public TeleportPadFloorItem(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public void onEntityStepOn(RoomEntity entity) {
        if (this.inUse || !(entity instanceof PlayerEntity)) {
            return;
        }

        this.inUse = true;

        if (entity.isOverriden()) return;

        final TeleporterItemEvent event = new TeleporterItemEvent(RoomItemFactory.getProcessTime(0.01));
        event.outgoingEntity = (PlayerEntity) entity;

        entity.setOverriden(false);
        event.outgoingEntity.setOverriden(true);

        event.state = 1;

        try {
            this.queueEvent(event);
        } catch (Exception e) {
            Comet.getServer().getLogger().error("Failed to queue teleporter item event", e);
        }
    }
}
