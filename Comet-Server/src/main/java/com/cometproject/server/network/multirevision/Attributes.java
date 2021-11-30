package com.cometproject.server.network.multirevision;

import io.netty.util.AttributeKey;

public class Attributes {
    public static final AttributeKey<String> REVISION = AttributeKey.valueOf("REVISION");
    public static final AttributeKey<Integer> LAST_FLAT_ID = AttributeKey.valueOf("LAST_FLAT_ID");
}

