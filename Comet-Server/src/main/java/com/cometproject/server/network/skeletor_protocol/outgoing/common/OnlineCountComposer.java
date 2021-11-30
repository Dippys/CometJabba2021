package com.cometproject.server.network.skeletor_protocol.outgoing.common;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class OnlineCountComposer extends OutgoingSkeletorMessage {
    public OnlineCountComposer(int count) {
        super("online_count");
        this.data.add("count", new JsonPrimitive(count));
    }
}
