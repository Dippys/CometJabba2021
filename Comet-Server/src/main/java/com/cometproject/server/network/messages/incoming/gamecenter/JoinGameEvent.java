
package com.cometproject.server.network.messages.incoming.gamecenter;

import com.cometproject.server.composers.gamecenter.GameStatusMessageComposer;
import com.cometproject.server.game.fastfood.FastFoodGame;
import com.cometproject.server.game.gamecenter.GameCenterInfo;
import com.cometproject.server.game.gamecenter.GameCenterManager;
import com.cometproject.server.game.snowwar.SnowPlayerQueue;
import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.protocol.messages.MessageEvent;

public class JoinGameEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        int gameId = msg.readInt();
        client.getPlayer().sendBubble("", "GameID: " + gameId);
        switch (gameId) {
            case 1: {
                FastFoodGame.onPlayButton(client.getPlayer());
                break;
            }
            case 2: {
                SnowPlayerQueue.addPlayerInQueue(client);
                break;
            }
        }
        client.send(new GameStatusMessageComposer(gameId, 0));
    }
}