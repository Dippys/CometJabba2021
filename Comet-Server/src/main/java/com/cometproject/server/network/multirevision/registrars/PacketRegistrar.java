package com.cometproject.server.network.multirevision.registrars;

import com.cometproject.server.network.multirevision.HeaderTranslator;

public interface PacketRegistrar {
    void registerIncoming(HeaderTranslator translator);
    void registerOutgoing(HeaderTranslator translator);
}

