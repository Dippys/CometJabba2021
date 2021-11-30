package com.cometproject.server.network.skeletor_protocol.outgoing;

import com.google.gson.JsonObject;

public abstract class OutgoingSkeletorMessage {
    public String header;
    public JsonObject data;

    public OutgoingSkeletorMessage(String name) {
        this.header = name;
        this.data = new JsonObject();
    }
}
