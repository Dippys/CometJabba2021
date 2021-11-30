package com.cometproject.server.game.commands.staff;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomForwardMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class SendUserCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length < 2) {
            return;
        }

        int roomId = Integer.parseInt(params[1]);
        String player = params[0];
        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(player);

        if(session == null)
            return;

        session.getPlayer().bypassRoomAuth(true);
        session.send(new RoomForwardMessageComposer(roomId));
    }

    @Override
    public String getPermission() {
        return "senduser_command";
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
