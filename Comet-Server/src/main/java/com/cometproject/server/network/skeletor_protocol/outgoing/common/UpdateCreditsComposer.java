package com.cometproject.server.network.skeletor_protocol.outgoing.common;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class UpdateCreditsComposer extends OutgoingSkeletorMessage {
    public UpdateCreditsComposer(int credits) {
        super("update_credits");
        this.data.add("credits", new JsonPrimitive(credits));
    }
}
