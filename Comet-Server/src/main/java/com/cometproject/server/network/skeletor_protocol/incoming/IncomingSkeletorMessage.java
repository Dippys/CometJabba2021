package com.cometproject.server.network.skeletor_protocol.incoming;

import com.cometproject.server.network.sessions.Session;
import com.google.gson.JsonObject;

public abstract class IncomingSkeletorMessage<T> {
    public final Class<T> type;

    public IncomingSkeletorMessage(Class<T> type) {
        this.type = type;
    }

    public abstract void handle(Session client, T message);

    public static class JSONIncomingEvent {
        public String header;
        public JsonObject data;
    }
}

