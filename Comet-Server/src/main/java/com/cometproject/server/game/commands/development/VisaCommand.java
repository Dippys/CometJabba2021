package com.cometproject.server.game.commands.development;

import com.cometproject.api.game.bots.BotMode;
import com.cometproject.api.game.bots.BotType;
import com.cometproject.api.game.utilities.Position;
import com.cometproject.server.game.bots.BotData;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.RoomEntity;
import com.cometproject.server.game.rooms.objects.entities.types.BotEntity;
import com.cometproject.server.game.rooms.objects.entities.types.data.PlayerBotData;
import com.cometproject.server.network.messages.outgoing.room.avatar.AvatarsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.google.common.collect.Lists;

import java.util.List;

public class VisaCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(params.length > 1 ) {
            return;
        }

        int count = Integer.parseInt(params[0]);
        final Position entityPosition = client.getPlayer().getEntity().getPosition();

        if (count > 1000) {
            count = 1000;
        } else if (count < 0) {
            count = 1;
        }

        List<RoomEntity> addedEntities = Lists.newArrayList();

        for (int i = 0; i < count; i++) {
            final int id = -(i + 1);
            final String username = client.getPlayer().getData().getUsername() + "Minion" + i;
            final String motto = "";

            BotData botData = new PlayerBotData(
                    id,
                    username,
                    motto,
                    client.getPlayer().getData().getFigure(),
                    client.getPlayer().getData().getGender(),
                    client.getPlayer().getData().getUsername(),
                    client.getPlayer().getId(),
                    "[]",
                    false,
                    7,
                    BotType.MIMIC,
                    BotMode.DEFAULT, "");

            final BotEntity botEntity = client.getPlayer().getEntity().getRoom().getBots().addBot(botData,
                    entityPosition.getX(), entityPosition.getY(), entityPosition.getZ());

            if (botEntity != null) {
                addedEntities.add(botEntity);
            }
        }

        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new AvatarsMessageComposer(addedEntities));
    }

    @Override
    public String getPermission() {
        return "visa_command";
    }

    @Override
    public String getParameter() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
