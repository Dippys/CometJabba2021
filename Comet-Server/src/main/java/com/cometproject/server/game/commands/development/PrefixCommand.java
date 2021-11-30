package com.cometproject.server.game.commands.development;

import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;

public class PrefixCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {

        String prefix = params[0];

        client.getPlayer().getData().setTag(prefix);
        client.getPlayer().getData().save();
        isExecuted(client);
    }

    @Override
    public String getPermission() {
        return "prefix_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
