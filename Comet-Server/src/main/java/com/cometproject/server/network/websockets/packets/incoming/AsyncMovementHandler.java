package com.cometproject.server.network.websockets.packets.incoming;

import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.sessions.Session;
import io.netty.channel.ChannelHandlerContext;

public class AsyncMovementHandler extends AbstractWebSocketHandler<AsyncMovementHandler.ASMData> {

    public AsyncMovementHandler() {
        super(ASMData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ASMData eventData) {
        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(Integer.parseInt(eventData.session));

        if(s != null && s.getPlayer() != null && s.getPlayer().getEntity() != null){
            PlayerEntity player = s.getPlayer().getEntity();

            switch (eventData.direction){
                case 37:
                    player.moveTo(player.getPosition().getX() - 1, player.getPosition().getY());
                    break;
                case 38:
                    player.moveTo(player.getPosition().getX(), player.getPosition().getY() - 1);
                    break;
                case 39:
                    player.moveTo(player.getPosition().getX() + 1, player.getPosition().getY());
                    break;
                case 40:
                    player.moveTo(player.getPosition().getX(), player.getPosition().getY() + 1);
                    break;
            }
        }

    }


    class ASMData {
        private String session;
        private Integer direction;
    }
}
