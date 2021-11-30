package com.cometproject.server.network.skeletor_protocol.outgoing.jukebox;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class PlayStopComposer extends OutgoingSkeletorMessage {
    public PlayStopComposer(boolean isPlaying) {
        super("play_stop");
        this.data.add("playing", new JsonPrimitive(isPlaying));
    }
}
