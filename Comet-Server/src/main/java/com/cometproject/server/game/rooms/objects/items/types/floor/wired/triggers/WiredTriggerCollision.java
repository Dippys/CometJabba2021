package com.cometproject.server.game.rooms.objects.items.types.floor.wired.triggers;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredActionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredTriggerItem;
import com.cometproject.server.game.rooms.types.Room;

import java.util.Optional;


public class WiredTriggerCollision extends WiredTriggerItem {

    public WiredTriggerCollision(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    public static boolean executeTriggers(RoomEntity entity, RoomItemFloor collidingItem, WiredActionItem wiredTrigerer) {
        boolean wasExecuted = false;

        // if we have a trigger: collision and an action that triggered it in same stack, only activate this trigger
        if(wiredTrigerer != null) {
            Optional<RoomItemFloor> flooritem = wiredTrigerer.getTile().getItems().stream().filter(item -> item instanceof WiredTriggerCollision).findFirst();
            if(flooritem.isPresent()) {
                WiredTriggerCollision trigger = ((WiredTriggerCollision) flooritem.get());

                return trigger.evaluate(entity, collidingItem);
            }
        }

        for (RoomItemFloor floorItem : getTriggers(entity.getRoom(), WiredTriggerCollision.class)) {
            WiredTriggerCollision trigger = ((WiredTriggerCollision) floorItem);

            wasExecuted = trigger.evaluate(entity, collidingItem);
        }

        return wasExecuted;
    }

    @Override
    public boolean suppliesPlayer() {
        return true;
    }

    @Override
    public int getInterface() {
        return 9;
    }

}
