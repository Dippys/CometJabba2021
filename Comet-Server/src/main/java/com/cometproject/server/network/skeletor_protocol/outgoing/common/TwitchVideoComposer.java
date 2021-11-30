package com.cometproject.server.network.skeletor_protocol.outgoing.common;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class TwitchVideoComposer extends OutgoingSkeletorMessage {
    public TwitchVideoComposer(String videoId) {
        super("twitch");
        this.data.add("videoId", new JsonPrimitive(videoId));
    }
}
