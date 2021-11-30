package com.cometproject.server.network.skeletor_protocol.outgoing.jukebox;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;

public class DisposePlaylistComposer extends OutgoingSkeletorMessage {
    public DisposePlaylistComposer() {
        super("dispose_playlist");
    }
}
