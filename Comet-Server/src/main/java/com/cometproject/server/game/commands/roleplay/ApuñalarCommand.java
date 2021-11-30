package com.cometproject.server.game.commands.roleplay;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class ApuñalarCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        String target = params[0];

        Session targetPlayer = NetworkManager.getInstance().getSessions().getByPlayerUsername(target);

        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(client.getPlayer().getEntity().getId(), Locale.getOrDefault("apuñalar.text.command", "*Saco mi cuchillo y se lo entierro a " + targetPlayer), ChatEmotion.ANGRY, 1));
        client.getPlayer().getEntity().applyEffect(new PlayerEffect(162, 10));
        targetPlayer.getPlayer().getEntity().applyEffect(new PlayerEffect(89, 10));
    }

    @Override
    public String getPermission() {
        return null;
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
