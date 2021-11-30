package com.cometproject.server.network.skeletor_protocol.outgoing.common;

import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.google.gson.JsonPrimitive;

public class SlotMachineComposer extends OutgoingSkeletorMessage {
    public SlotMachineComposer(int itemId, int credits) {
        super("slot_machine");
        this.data.add("itemId", new JsonPrimitive(itemId));
        this.data.add("credits", new JsonPrimitive(credits));
    }
}
