package com.cometproject.server.network.skeletor_protocol.outgoing.jukebox;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class ChangeVolumeComposer extends OutgoingSkeletorMessage {
    public ChangeVolumeComposer(int volume) {
        super("change_volume");
        this.data.add("volume", new JsonPrimitive(volume));
    }
}
