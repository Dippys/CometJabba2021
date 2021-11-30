package com.cometproject.server.network.skeletor_protocol.outgoing.jukebox;

import com.cometproject.server.game.rooms.types.components.YoutubeJukeboxComponent;
import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class AddSongComposer extends OutgoingSkeletorMessage {
    public AddSongComposer(YoutubeJukeboxComponent.YoutubeVideo video) {
        super("add_song");
        JsonObject song = new JsonObject();
        song.add("name", new JsonPrimitive(video.name));
        song.add("videoId", new JsonPrimitive(video.videoId));
        song.add("channel", new JsonPrimitive(video.channel));
        this.data.add("song", song);
    }
}
