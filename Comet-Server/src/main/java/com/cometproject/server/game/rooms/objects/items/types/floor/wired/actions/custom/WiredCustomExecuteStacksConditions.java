package com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.custom;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.actions.WiredActionExecuteStacks;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredActionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.base.WiredConditionItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.events.WiredItemEvent;
import com.cometproject.server.game.rooms.types.Room;
import com.google.common.collect.Lists;

import java.util.List;

public class WiredCustomExecuteStacksConditions extends WiredActionItem {

    public WiredCustomExecuteStacksConditions(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public void onEventComplete(WiredItemEvent event) {
        List<Position> tilesToExecute = Lists.newArrayList();

        for (long itemId : this.getWiredData().getSelectedIds()) {
            final RoomItemFloor floorItem = this.getRoom().getItems().getFloorItem(itemId);

            if (floorItem == null || (floorItem.getPosition().getX() == this.getPosition().getX() && floorItem.getPosition().getY() == this.getPosition().getY()))
                continue;

            tilesToExecute.add(new Position(floorItem.getPosition().getX(), floorItem.getPosition().getY()));
        }

        List<WiredConditionItem> conditions = Lists.newArrayList();
        List<WiredActionItem> actions = Lists.newArrayList();

        for (Position tileToUpdate : tilesToExecute) {
            for (RoomItemFloor roomItemFloor : this.getRoom().getMapping().getTile(tileToUpdate).getItems()) {
                if (roomItemFloor instanceof WiredConditionItem) conditions.add((WiredConditionItem) roomItemFloor);
                if (roomItemFloor instanceof WiredActionItem && !(roomItemFloor instanceof WiredCustomExecuteStacksConditions)) {
                    actions.add((WiredActionItem) roomItemFloor);
                }
            }
        }

        boolean canExecute = conditions.stream().allMatch(item -> item.evaluate(event.entity, event.data));

        if (canExecute) {
            final int max = 30;
            int limiter = 0;

            for (WiredActionItem actionItem : actions) {
                if (limiter >= max) {
                    break;
                }

                limiter++;
                actionItem.evaluate(event.entity, event.data);
            }
        }

        tilesToExecute.clear();
    }


    @Override
    public boolean requiresPlayer() {
        return false;
    }

    @Override
    public int getInterface() {
        return 18;
    }
}
