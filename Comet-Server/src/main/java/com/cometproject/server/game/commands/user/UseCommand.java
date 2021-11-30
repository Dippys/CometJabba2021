package com.cometproject.server.game.commands.user;

import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.objects.items.RoomItemFloor;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.google.common.collect.Lists;

import java.util.List;

public class UseCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        final PlayerEntity playerEntity = client.getPlayer().getEntity();
        RoomItemFloor floorItem = playerEntity.getTile().getTopItemInstance();
        List<Position> tilesToUpdate = Lists.newArrayList();

        if (floorItem == null) {
            client.getPlayer().getSession().send(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "Pon un furni bajo de ti", ChatEmotion.NONE, 34));
            return;
        }

        int useMode = Integer.parseInt(params[0]);

        if(floorItem.getItemData().getData().equals("0")) {
            floorItem.getItemData().setData("" + (floorItem.getDefinition().getInteractionCycleCount() + useMode));
        } else {
            floorItem.getItemData().setData("" + (Integer.parseInt(floorItem.getItemData().getData()) + useMode));
        }
        floorItem.sendUpdate();
        tilesToUpdate.add(new Position(floorItem.getPosition().getX(), floorItem.getPosition().getY()));
    }

    @Override
    public String getPermission() {
        return "use_command";
    }

    @Override
    public String getParameter() {
        return "%numero%";
    }

    @Override
    public String getDescription() {
        return "Modifica el estado de un furni usando este comando pero estando encima.";
    }
}
