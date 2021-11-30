package com.cometproject.server.game.commands.vip;

import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;

public class TagColourCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(params.length < 0) {
            return;
        }

        String colour = params[0];
        client.getPlayer().getData().setTagColour(colour);
        client.getPlayer().getData().save();
    }

    @Override
    public String getPermission() {
        return "fastwalk_command";
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
