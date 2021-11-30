package com.cometproject.server.game.commands.gimmicks;

import com.cometproject.api.game.rooms.entities.RoomEntityStatus;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.tasks.CometThreadManager;

import java.util.concurrent.TimeUnit;

public class SexCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) {
            sendNotif(Locale.getOrDefault("command.sex.none", "Who do you want to rape?"), client);
            return;
        }

        if (client.getPlayer().getEntity().isRoomMuted() || client.getPlayer().getEntity().getRoom().getRights().hasMute(client.getPlayer().getId())) {
                sendNotif(Locale.getOrDefault("command.user.muted", "Estás muteado!"), client);
            return;
        }

        String kissedPlayer = params[0];
        Session kissedSession = NetworkManager.getInstance().getSessions().getByPlayerUsername(kissedPlayer);

        if (kissedSession == null) {
            sendNotif(Locale.getOrDefault("command.user.offline", "El usuario está desconectado!"), client);
            return;
        }

        if (kissedSession.getPlayer().getEntity() == null) {
            sendNotif(Locale.getOrDefault("command.user.notinroom", "El usuario no está en la sala."), client);
            return;
        }

        if (kissedSession.getPlayer().getData().getUsername().equals(client.getPlayer().getData().getUsername())) {
            sendNotif(Locale.getOrDefault("command.sex.himself", "No puedes tener sexo contigo mismo!"), client);
            return;
        }

        int posX = kissedSession.getPlayer().getEntity().getPosition().getX();
        int posY = kissedSession.getPlayer().getEntity().getPosition().getY();
        int playerX = client.getPlayer().getEntity().getPosition().getX();
        int playerY = client.getPlayer().getEntity().getPosition().getY();

        if (!((Math.abs((posX - playerX)) >= 2) || (Math.abs(posY - playerY) >= 2))) {

            client.getPlayer().getEntity().applyEffect(new PlayerEffect(544));

            if (client.getPlayer().getEntity().hasStatus(RoomEntityStatus.SIT)) {
                client.getPlayer().getEntity().removeStatus(RoomEntityStatus.SIT);
                client.getPlayer().getEntity().addStatus(RoomEntityStatus.LAY, "0.5");
                client.getPlayer().getEntity().markNeedsUpdate();
                isExecuted(client);
            } else {
                client.getPlayer().getEntity().addStatus(RoomEntityStatus.LAY, "0.5");
                client.getPlayer().getEntity().markNeedsUpdate();
                isExecuted(client);
            }

            kissedSession.getPlayer().getEntity().applyEffect(new PlayerEffect(502));

            if (kissedSession.getPlayer().getEntity().hasStatus(RoomEntityStatus.LAY)) {
                kissedSession.getPlayer().getEntity().removeStatus(RoomEntityStatus.LAY);
                kissedSession.getPlayer().getEntity().addStatus(RoomEntityStatus.SIT, "0.5");
                kissedSession.getPlayer().getEntity().markNeedsUpdate();
            } else {
                kissedSession.getPlayer().getEntity().addStatus(RoomEntityStatus.SIT, "0.5");
                kissedSession.getPlayer().getEntity().markNeedsUpdate();
            }

            Room room = client.getPlayer().getEntity().getRoom();
            room.getEntities().broadcastMessage(new TalkMessageComposer(client.getPlayer().getEntity().getId(), "* " + Locale.getOrDefault("command.sex.word.1", " Gets horny and starts humping %username%").replace("%username%", kissedSession.getPlayer().getData().getUsername()) + " *", ChatEmotion.NONE, 16));
            room.getEntities().broadcastMessage(new TalkMessageComposer(kissedSession.getPlayer().getEntity().getId(), " * " + Locale.getOrDefault("command.sex.word.2", " Give it to me harder baby").replace("%username%", client.getPlayer().getData().getUsername()) + " *", ChatEmotion.NONE, 16));
        } else {
            client.getPlayer().getSession().send(new WhisperMessageComposer(client.getPlayer().getEntity().getId(), Locale.getOrDefault("command.notaround", "Oops! %playername% is not near, walk to this player.").replace("%playername%", kissedSession.getPlayer().getData().getUsername()), 16));
        }
    }

    @Override
    public String getPermission() {
        return "sex_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.username", "%username%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.sex.description");
    }
}
