package com.cometproject.server.game.commands.user;

import com.cometproject.api.stats.CometStats;
import com.cometproject.server.api.rooms.RoomStats;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class OnlineCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        CometStats cometStats = Comet.getStats();
        int OnlineCount = cometStats.getPlayers() + client.getPlayer().getEntity().getRoom().getEntities().getBotEntities().size();
        int OnlineRoomsCount = cometStats.getRooms();
        if(params.length < 1) {
            client.send(new WhisperMessageComposer(client.getPlayer().getId(), "En estos momentos somos " + OnlineCount + " usuarios conectados con " + OnlineRoomsCount + " salas activas" , 34));
        }

    }

    @Override
    public String getPermission() {
        return "online_command";
    }

    @Override
    public String getParameter() { return  Locale.getOrDefault("command.parameter.message", "Ver número de jugadores en línea"); }

    @Override
    public String getDescription() {
        { return Locale.get("command.about.description"); }
    }
}
