package com.cometproject.server.game.rooms.objects.items.types.floor.wired.addons;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.types.floor.RollableFloorItem;
import com.cometproject.server.game.rooms.types.Room;

public class WiredAddonPuzzleBox extends RollableFloorItem {
    public WiredAddonPuzzleBox(RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public void onEntityStepOn(RoomEntity entity) {
        // do nothing with puzzle box
    }

    @Override
    public void onEntityStepOff(RoomEntity entity) {
        //nothing
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTriggered) {
        if (isWiredTriggered || entity == null) return false;
        if(!entity.getPosition().touching(this.getPosition())) return false;

        if(! (entity.getPosition().squareInFront(entity.getBodyRotation()) == this.getPosition())) {
            int rotation = Position.calculateRotation(entity.getPosition().getX(), entity.getPosition().getY(), this.getPosition().getX(), this.getPosition().getY(), false);
            entity.setBodyRotation(rotation);
            entity.setHeadRotation(rotation);
        }
        this.setRotation(entity.getBodyRotation());
        Position oldPos = this.getPosition();
        boolean moved = this.rollSingle(entity);
        if (entity instanceof PlayerEntity && moved) {
            entity.setOverriden(true);
            entity.moveTo(oldPos);
            entity.setOverriden(false);
        }
        return true;
    }

    @Override
    protected boolean rollSingle(RoomEntity entity) {
        if (this.isRolling || !entity.getPosition().touching(this.getPosition())) {
            return false;
        }

        if (entity instanceof PlayerEntity) {
            this.kickerEntity = entity;
            if (kickerEntity.getBodyRotation() % 2 != 0) {
                return false;
            }
        }
        
        Position newPosition = this.getPosition().squareInFront(this.getRotation());

        if (!this.isValidRoll(newPosition)) {
            return false;
        }
        this.isRolling = true;
        this.getItemData().setData("11");
        this.moveTo(newPosition, entity.getBodyRotation());
        this.isRolling = false;
        return true;
    }
}
