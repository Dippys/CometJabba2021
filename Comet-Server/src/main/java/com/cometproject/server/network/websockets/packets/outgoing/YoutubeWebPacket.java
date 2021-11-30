package com.cometproject.server.network.websockets.packets.outgoing;

public class YoutubeWebPacket {
    private final String handle;
    private final String url;

    public YoutubeWebPacket(String handle, String url) {
        this.handle = handle;
        this.url = url;
    }
}
