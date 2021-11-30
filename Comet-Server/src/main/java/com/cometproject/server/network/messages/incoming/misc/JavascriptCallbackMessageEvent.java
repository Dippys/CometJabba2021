package com.cometproject.server.network.messages.incoming.misc;

import com.cometproject.server.network.messages.incoming.Event;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.skeletor_protocol.SkeletorProtocolManager;
import com.cometproject.server.protocol.messages.MessageEvent;

public class JavascriptCallbackMessageEvent implements Event {
    @Override
    public void handle(Session client, MessageEvent msg) throws Exception {
        String payload = msg.readString();
        SkeletorProtocolManager.getInstance().OnMessage(payload, client);
    }
}
