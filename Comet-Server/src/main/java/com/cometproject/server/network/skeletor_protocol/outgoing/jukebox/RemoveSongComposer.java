package com.cometproject.server.network.skeletor_protocol.outgoing.jukebox;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class RemoveSongComposer extends OutgoingSkeletorMessage {
    public RemoveSongComposer(int index) {
        super("remove_song");
        this.data.add("index", new JsonPrimitive(index));
    }
}
