package com.cometproject.server.network.skeletor_protocol;

import com.cometproject.server.boot.Comet;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.skeletor_protocol.incoming.IncomingSkeletorMessage;
import com.cometproject.server.network.skeletor_protocol.incoming.common.*;
import com.cometproject.server.network.skeletor_protocol.incoming.jukebox.*;
import com.cometproject.server.network.skeletor_protocol.incoming.tools.RoomBackgroundEditEvent;
import com.cometproject.server.network.skeletor_protocol.utils.JsonFactory;

import java.util.HashMap;

public class SkeletorProtocolManager {
    private static SkeletorProtocolManager _instance;
    static {
        try {
            _instance = new SkeletorProtocolManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final HashMap<String, Class<? extends IncomingSkeletorMessage>> _incomingMessages;

    public SkeletorProtocolManager() {
        this._incomingMessages = new HashMap<>();
        initializeMessages();
    }

    public void initializeMessages() {
        this.registerMessage("move_avatar", MoveAvatarEvent.class);
        this.registerMessage("request_credits", RequestCreditsEvent.class);
        this.registerMessage("spin_slot_machine", RequestSpinSlotMachineEvent.class);
        this.registerMessage("skeletor", OperationFUEvent.class);
        this.registerMessage("add_song", AddSongEvent.class);
        this.registerMessage("next_song", NextSongEvent.class);
        this.registerMessage("prev_song", PreviousSongEvent.class);
        this.registerMessage("play_stop", PlayStopEvent.class);
        this.registerMessage("remove_song", RemoveSongEvent.class);
        this.registerMessage("song_ended", SongEndedEvent.class);
        this.registerMessage("edit_tv", EditTVEvent.class);
        this.registerMessage("edit_bg", RoomBackgroundEditEvent.class);
    }

    public void registerMessage(String key, Class<? extends IncomingSkeletorMessage> message) {
        this._incomingMessages.put(key, message);
    }

    public HashMap<String, Class<? extends IncomingSkeletorMessage>> getIncomingMessages() {
        return this._incomingMessages;
    }

    public static SkeletorProtocolManager getInstance(){
        if (_instance == null) {
            _instance = new SkeletorProtocolManager();
        }
        return _instance;
    }

    public void OnMessage(String jsonPayload, Session sender) {
        try {
            IncomingSkeletorMessage.JSONIncomingEvent heading = JsonFactory.getInstance().fromJson(jsonPayload, IncomingSkeletorMessage.JSONIncomingEvent.class);
            Class<? extends IncomingSkeletorMessage> message = SkeletorProtocolManager.getInstance().getIncomingMessages().get(heading.header);
            IncomingSkeletorMessage webEvent = message.getDeclaredConstructor().newInstance();
            webEvent.handle(sender, JsonFactory.getInstance().fromJson(heading.data.toString(), webEvent.type));
        } catch(Exception e) {
            Comet.getServer().getLogger().debug("unknown message: " + jsonPayload);
        }
    }

    public void Dispose() {
        _incomingMessages.clear();
        _instance = null;
    }
}
