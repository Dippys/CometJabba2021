package com.cometproject.server.network.skeletor_protocol.outgoing.common;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class YoutubeTVComposer extends OutgoingSkeletorMessage {
    public YoutubeTVComposer(String videoId, int itemId) {
        super("youtube_tv");
        this.data.add("videoId", new JsonPrimitive(videoId));
        this.data.add("itemId", new JsonPrimitive(itemId));
    }
}
