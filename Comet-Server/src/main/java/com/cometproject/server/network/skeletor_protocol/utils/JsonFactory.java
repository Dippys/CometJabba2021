package com.cometproject.server.network.skeletor_protocol.utils;

import com.google.gson.Gson;

public class JsonFactory {
    private static final Gson gson = new Gson();

    public static Gson getInstance() {
        return gson;
    }
}