package com.cometproject.server.game.commands.roleplay;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.effects.PlayerEffect;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class EmergencyCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new TalkMessageComposer(client.getPlayer().getEntity().getId(), Locale.getOrDefault("text.911.command", "<b>*Llamo a emergencias*</b>"), ChatEmotion.SHOCKED, 0));
        client.getPlayer().getEntity().applyEffect(new PlayerEffect(65, 10));
    }

    @Override
    public String getPermission() {
        return "roleplay_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Llama al 911";
    }
}
