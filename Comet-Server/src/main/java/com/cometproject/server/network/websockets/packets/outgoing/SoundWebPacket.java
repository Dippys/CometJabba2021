package com.cometproject.server.network.websockets.packets.outgoing;

public class SoundWebPacket {
    private String handle;
    private String sound;

    public SoundWebPacket(String handle, String sound) {
        this.handle = handle;
        this.sound = sound;
    }
}
