package com.cometproject.server.game.commands.user.room;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.highscore.HighscoreClassicFloorItem;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.highscore.HighscoreFloorItem;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;

import java.util.List;

public class ClearHighscoreCommand extends ChatCommand {

    @Override
    public void execute(Session client, String message[]) {
        if(client == null)
            return;

        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId()) &&
                !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            sendNotif(Locale.getOrDefault("command.need.rights", "You need rights to use this command in this room!"), client);
            return;
        }

        final List<HighscoreFloorItem> scoreboards = client.getPlayer().getEntity().getRoom().getItems().getByClass(HighscoreFloorItem.class);

        if (scoreboards.size() != 0) {
            for (HighscoreFloorItem scoreboard : scoreboards) {
                scoreboard.resetScoreboard();
            }
        }

        client.send(new NotificationMessageComposer("highscore", "Has reiniciado correctamente la clasificación de la sala."));
    }

    @Override
    public String getPermission() {
        return "superwired_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.highscore.description");
    }
}
