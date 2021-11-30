package com.cometproject.server.game.commands.user.ws;

import com.cometproject.api.utilities.JsonUtil;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.websockets.WebSocketSessionManager;
import com.cometproject.server.network.websockets.packets.outgoing.YoutubeWebPacket;
import com.google.common.collect.Maps;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

public class RoomVideoCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length != 1) {
            return;
        }
        String url = params[0];
        for (PlayerEntity playerEntity : client.getPlayer().getEntity().getRoom().getEntities().getPlayerEntities()) {
            if (playerEntity.getPlayer().getSession().getWsChannel() == null) continue;
            WebSocketSessionManager.getInstance().sendMessage(playerEntity.getPlayer().getSession().getWsChannel(), new YoutubeWebPacket("youtube_send", url.replace("watch?v=", "embed/") + "?autoplay=1"));
        }
    }

    @Override
    public String getPermission() {
        return "roomvideo_command";
    }

    @Override
    public String getParameter() {
        return "%video%";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.roomvideo.description");
    }
}
