package com.cometproject.server.network.multirevision.modifiers;

import com.cometproject.server.network.multirevision.modifiers.incoming.room.session.OpenFlatConnectionModifier;
import com.cometproject.server.network.multirevision.modifiers.outgoing.inventory.badges.BadgesModifier;
import com.cometproject.server.network.multirevision.modifiers.outgoing.room.permissions.YouAreControllerModifier;
import com.cometproject.server.network.multirevision.modifiers.outgoing.room.permissions.YouAreNotControllerModifier;
import com.cometproject.server.network.multirevision.modifiers.outgoing.room.permissions.YouAreOwnerModifier;
import com.cometproject.server.network.multirevision.modifiers.outgoing.room.session.FlatAccessibleModifier;
import com.cometproject.server.network.multirevision.modifiers.outgoing.room.session.OpenConnectionModifier;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.headers.Events;

import java.util.HashMap;

public class ModifierManager {
    private HashMap<Integer, Modifier> incomingModifiers;
    private  HashMap<Integer, Modifier> outgoingModifiers;

    public ModifierManager() {
        this.incomingModifiers = new HashMap<>();
        this.outgoingModifiers = new HashMap<>();
        this.registerIncomingModifiers();
        this.registerOutgoingModifiers();
    }

    private void registerIncomingModifiers() {
        this.incomingModifiers.put(Events.OpenFlatConnectionMessageEvent, new OpenFlatConnectionModifier());
    }

    private void registerOutgoingModifiers() {
        this.outgoingModifiers.put(Composers.YouAreControllerMessageComposer, new YouAreControllerModifier());
        this.outgoingModifiers.put(Composers.RoomNoRightsComposer, new YouAreNotControllerModifier());
        this.outgoingModifiers.put(Composers.YouAreOwnerMessageComposer, new YouAreOwnerModifier());
        this.outgoingModifiers.put(Composers.FlatAccessibleMessageComposer, new FlatAccessibleModifier());
        this.outgoingModifiers.put(Composers.OpenConnectionMessageComposer, new OpenConnectionModifier());
        this.outgoingModifiers.put(Composers.BadgesMessageComposer, new BadgesModifier());
    }

    public HashMap<Integer, Modifier> getIncomingModifiers() {
        return incomingModifiers;
    }

    public HashMap<Integer, Modifier> getOutgoingModifiers() {
        return outgoingModifiers;
    }
}

