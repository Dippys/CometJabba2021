package com.cometproject.server.network.multirevision.registrars;

import com.cometproject.server.network.multirevision.HeaderTranslator;

import java.util.ArrayList;

public class PacketRegistrarManager {
    private ArrayList<PacketRegistrar> packetRegistrars;

    public PacketRegistrarManager() {
        this.packetRegistrars = new ArrayList<>();
    }

    public void addRegistrar(PacketRegistrar registrar) {
        this.packetRegistrars.add(registrar);
    }

    public void registerAllPackets(HeaderTranslator translator) {
        for(PacketRegistrar registrar : this.packetRegistrars) {
            registrar.registerIncoming(translator);
            registrar.registerOutgoing(translator);
        }
    }

    public ArrayList<PacketRegistrar> getPacketRegistrars() {
        return packetRegistrars;
    }
}

