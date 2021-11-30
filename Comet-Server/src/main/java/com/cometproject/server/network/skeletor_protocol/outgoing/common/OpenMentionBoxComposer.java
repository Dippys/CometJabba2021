package com.cometproject.server.network.skeletor_protocol.outgoing.common;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;

public class OpenMentionBoxComposer extends OutgoingSkeletorMessage {
    public OpenMentionBoxComposer() {
        super("open_mentions");
    }
}
