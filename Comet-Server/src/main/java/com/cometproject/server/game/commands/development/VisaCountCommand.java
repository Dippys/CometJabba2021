package com.cometproject.server.game.commands.development;

import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.sessions.Session;

public class VisaCountCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if(params.length > 1) {
            return;
        }

        String option = params[0];

        switch (option) {
            default:
            case "on":
                client.getPlayer().getEntity().getRoom().getData().setVisaCount(true);
                client.getPlayer().sendBubble("furni_placement_error", "Conteo de bots en sala activado");
                break;

            case "off":
                client.getPlayer().getEntity().getRoom().getData().setVisaCount(false);
                client.getPlayer().sendBubble("furni_placement_error", "Conteo de bots en sala desactivado");
                break;
        }
    }

    @Override
    public String getPermission() {
        return "visacount_command";
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
