package com.cometproject.server.game.rooms.objects.items.types.floor;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFactory;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.utilities.RandomUtil;

import java.util.Timer;
import java.util.TimerTask;

public class TrashFloorItem  extends RoomItemFloor {
    private boolean isInUse = false;
    private Timer timer = new Timer();

    public TrashFloorItem (RoomItemData itemData, Room room) {
        super(itemData, room);
    }

    @Override
    public boolean onInteract(RoomEntity entity, int requestData, boolean isWiredTrigger) {
        if (!isWiredTrigger) {
            if (!this.getPosition().touching(entity.getPosition())) {
                entity.moveTo(this.getPosition().squareInFront(this.getRotation()).getX(), this.getPosition().squareBehind(this.getRotation()).getY());
                return false;
            }
        }

        entity.cancelWalk();
        entity.lookTo(this.getPosition().squareInFront(this.getRotation()).getX() - 1, this.getPosition().squareBehind(this.getRotation()).getY() - 1);

        if (this.getItemData().getData().equals("1")) {
            ((PlayerEntity) entity).getPlayer().getSession().send(new WhisperMessageComposer(((PlayerEntity) entity).getId(), "Este tacho de basura ya fue registrado, revisa más tarde.", 0));
            return false;
        }

        ((PlayerEntity) entity).getRoom().getEntities().broadcastMessage(new TalkMessageComposer(((PlayerEntity) entity).getId(), "<b>*Comienzo a buscar en el tacho de la basura*</b>", ChatEmotion.NONE, 0));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
        int random = RandomUtil.getRandomInt(1,3);

            switch (random) {
                case 1:
                    ((PlayerEntity) entity).getRoom().getEntities().broadcastMessage(new TalkMessageComposer(((PlayerEntity) entity).getId(), "He encontrado 1 crédito", ChatEmotion.NONE, 0));
                    ((PlayerEntity) entity).getPlayer().getData().increaseCredits(1);
                    ((PlayerEntity) entity).getPlayer().getData().save();
                    break;

                case 2:
                    ((PlayerEntity) entity).getRoom().getEntities().broadcastMessage(new TalkMessageComposer(((PlayerEntity) entity).getId(), "He encontrado 2 créditos", ChatEmotion.NONE, 0));
                    ((PlayerEntity) entity).getPlayer().getData().increaseCredits(2);
                    ((PlayerEntity) entity).getPlayer().getData().save();
                    break;

                default:
                case 3:
                    ((PlayerEntity) entity).getRoom().getEntities().broadcastMessage(new TalkMessageComposer(((PlayerEntity) entity).getId(), "Lamentablemente no he podido encontrar nada", ChatEmotion.NONE, 0));
                    break;
                }
            }
        }, 2000);

        this.setTicks(RoomItemFactory.getProcessTime(180));

        this.getItemData().setData("1");
        this.sendUpdate();

        this.saveData();
        return true;
    }

    @Override
    public void onPlaced() {
        if (!"0".equals(this.getItemData().getData())) {
            this.getItemData().setData("0");
        }
    }

    @Override
    public void onPickup() {
        this.cancelTicks();
    }

    @Override
    public void onTickComplete() {
        this.isInUse = false;

        this.getItemData().setData("0");
        this.sendUpdate();

        this.saveData();
    }
}