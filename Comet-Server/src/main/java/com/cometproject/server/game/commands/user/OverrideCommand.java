package com.cometproject.server.game.commands.user;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;

public class OverrideCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params)
    {
        boolean ok = true;
        if(client.getPlayer().getEntity().isOverriden())
            ok = false;


        client.getPlayer().getEntity().setOverrideA(ok);
        client.getPlayer().getEntity().setOverriden(ok);

        if(ok)
            sendNotif(Locale.getOrDefault("command.override.enable", "El Override está activado !"), client);
        else
            sendNotif(Locale.getOrDefault("command.override.disable", "El Override está desactivado !"), client);
    }

    @Override
    public String getPermission() {
        return "override_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.override.description");
    }

    @Override
    public boolean canDisable() {
        return true;
    }
}
