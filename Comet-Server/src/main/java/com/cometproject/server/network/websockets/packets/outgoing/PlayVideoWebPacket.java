package com.cometproject.server.network.websockets.packets.outgoing;

public class PlayVideoWebPacket {
    private final String video;
    private final String username;

    public PlayVideoWebPacket(String videoId, String username) {
        this.video = videoId;
        this.username = username;
    }

    public String getVideoId() {return video;}

    public String getUsername() { return username; }
}
