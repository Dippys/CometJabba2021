package com.cometproject.server.game.commands.staff.alerts;

import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.api.networking.sessions.ISession;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.players.data.PlayerData;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.AdvancedAlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;


public class DjAlertCommand extends ChatCommand {

    private String logDesc = "";

    @Override
    public void execute(Session client, String[] params) {
        if (params.length == 0) {
            return;
        }

        final IMessageComposer msg = new AdvancedAlertMessageComposer(
                Locale.getOrDefault("command.djalert.alerttitle", "Un DJ ha comenzado a transmitir."),
                Locale.getOrDefault("command.djalert.message", "El DJ %username% ha comenzado a transmitir, también a creado un evento en curso en el cual puedes ganar muchos premios, asiste y disfruta.")
                        .replace("%message%", this.merge(params))
                        .replace("%username%", client.getPlayer().getData().getUsername()) + "<br><br><b> " + client.getPlayer().getData().getUsername() + "</b>",
                Locale.getOrDefault("command.djalert.buttontitle", "¡Ir ahora!"), "event:navigator/goto/" + client.getPlayer().getEntity().getRoom().getId(), "djalert");

        final Room room = client.getPlayer().getEntity().getRoom();
        final PlayerData playerData = client.getPlayer().getData();

        final IMessageComposer whisper = new TalkMessageComposer(
                -1, Locale.getOrDefault("none.ishere", "[FRANK] El DJ %username% ha comenzado a transmitir, haz click <a href=\'event:navigator/goto/" + client.getPlayer().getEntity().getRoom().getId() + "'><b>aquí</b></a> para ir a su evento.").replace("%message%", this.merge(params)) .replace("%username%", client.getPlayer().getData().getUsername()) + "<br><br><i> " + client.getPlayer().getData().getUsername() + "</i>", ChatEmotion.NONE, 34);

        for (ISession session : NetworkManager.getInstance().getSessions().getSessions().values()) {
            if (session.getPlayer() != null && !session.getPlayer().getSettings().ignoreEvents())
                session.send(whisper);
        }

        this.logDesc = "Evento de DJ creado por %s en '%b'"
                .replace("%s", client.getPlayer().getData().getUsername())
                .replace("%b", client.getPlayer().getEntity().getRoom().getData().getName());
    }

    @Override
    public String getPermission() {
        return "dj_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.djalert.description", "[DJ] Crea un evento exclusivo!");
    }

    @Override
    public String getLoggableDescription() {
        return this.logDesc;
    }

    @Override
    public boolean isLoggable() {
        return true;
    }
}
