package com.cometproject.server.network.skeletor_protocol.outgoing.common;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import java.util.List;

public class CommandsComposer extends OutgoingSkeletorMessage {
    public CommandsComposer(List<String> commands) {
        super("commands");
        JsonArray json_cmd = new JsonArray();
        for (String c : commands) {
            json_cmd.add(c);
        }
        this.data.add("commands", json_cmd);
    }
}
