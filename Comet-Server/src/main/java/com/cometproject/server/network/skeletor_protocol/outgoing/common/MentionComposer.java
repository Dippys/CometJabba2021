package com.cometproject.server.network.skeletor_protocol.outgoing.common;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class MentionComposer extends OutgoingSkeletorMessage {
    public MentionComposer(String sender, String message, String senderLook) {
        super("mention");
        this.data.add("sender", new JsonPrimitive(sender));
        this.data.add("message", new JsonPrimitive(message));
        this.data.add("senderLook", new JsonPrimitive(senderLook));
    }
}
