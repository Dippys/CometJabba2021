package com.cometproject.server.game.commands.staff.rewards;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.sessions.Session;


public class PayGameCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        String username = params[0];

        Session session = NetworkManager.getInstance().getSessions().getByPlayerUsername(username);
        Session user = NetworkManager.getInstance().getSessions().getByPlayerUsername(params[0]);
        String targetUsername = user.getPlayer().getData().getUsername();

        if (targetUsername == client.getPlayer().getData().getUsername()) {
            sendNotif(Locale.getOrDefault("command.seasonal.himself", "¡No puedes darte premio a ti mismo!"), client);
            return;
        }

        if (session == null) {
            return;
        }

        int g = Integer.parseInt(Locale.getOrDefault("amount.paygame", "3"));

        session.getPlayer().getData().increaseActivityPoints(g);

        session.getPlayer().getData().save();

        session.send(new NotificationMessageComposer(
                Locale.getOrDefault("command.seasonal.image", "globos"),
                Locale.getOrDefault("command.seasonal.successmessage", "Has recibido %amount% " + Locale.getOrDefault("currency_name", "Globos") + ".").replace("%amount%", String.valueOf(g))
        ));

        session.send(session.getPlayer().composeCurrenciesBalance());
        session.getPlayer().sendBalance();

        client.send(new NotificationMessageComposer(
                Locale.getOrDefault("command.seasonal.image", "globos"),
                Locale.getOrDefault("command.seasonal.giver.successmessage", "Le has dado %amount% globos a %username%.").replace("%amount%", String.valueOf(g)).replace("%username%", session.getPlayer().getData().getUsername())));

        if(!client.getPlayer().getData().getUsername().equals(username)){
            NetworkManager.getInstance().getSessions().broadcast(new NotificationMessageComposer("ganador" , Locale.getOrDefault("event_winner_notification", "%winner% ha ganado un evento!").replace("%winner%", username)));
        }
    }

    @Override
    public String getPermission() {
        return "paygamedificil_command";
    }

    @Override
    public String getParameter() {
        return Locale.get("command.parameter.username");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.seasonal.description");
    }
}
