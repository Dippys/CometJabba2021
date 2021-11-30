package com.cometproject.server.network.skeletor_protocol.outgoing.jukebox;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class PlaySongComposer extends OutgoingSkeletorMessage {
    public PlaySongComposer(int index) {
        super("play_song");
        this.data.add("index", new JsonPrimitive(index));
    }
}
