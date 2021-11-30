package com.cometproject.server.game.commands.staff.alerts;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class HotelAlertCommand extends ChatCommand {
    public void execute(Session client, String[] message) {
        if (message.length == 0)
            return;

        NetworkManager.getInstance().getSessions().broadcast(new AlertMessageComposer(String.format("Alerta del Equipo Administrativo:<br><br>%s<br><br>- %s", new Object[] { merge(message), client.getPlayer().getData().getUsername() })));

    }

    public boolean isAsync() {
        return true;
    }

    public String getPermission() {
        return "hotelalert_command";
    }

    @Override
    public String getParameter() {
        return null;
    }

    public String getDescription() {
        return Locale.get("command.hotelalert.description");
    }
}
