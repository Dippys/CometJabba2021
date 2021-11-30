package com.cometproject.server.game.commands.vip;

import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.misc.ChatEmotion;
import com.cometproject.server.network.messages.outgoing.room.avatar.TalkMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class ToggleTradeCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        PlayerEntity entity = client.getPlayer().getEntity();
        if (params.length < 1) {
            entity.getPlayer().getSession().send(new TalkMessageComposer(entity.getId(), "Para desactivar los tradeos escribe ;trades on y para desactivar ;trades off", ChatEmotion.NONE, 34));
            return;
        }

        switch (params[0]) {
            default:
            case "on":
                entity.getPlayer().getSession().send(new TalkMessageComposer(entity.getId(), "Tradeos activados con éxito", ChatEmotion.NONE, 34));
                client.getPlayer().getSettings().setAllowTrade(true);
                break;

            case "off":
                entity.getPlayer().getSession().send(new TalkMessageComposer(entity.getId(), "Tradeos desactivados con éxito", ChatEmotion.NONE, 34));
                client.getPlayer().getSettings().setAllowTrade(false);
                break;
        }
    }

    @Override
    public String getPermission() {
        return "toggletrade_command";
    }

    @Override
    public String getParameter() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Toggle trade, evita que te tradeen teniendo el tradeo apagado.";
    }
}
