package com.cometproject.server.network.multirevision;

import com.cometproject.server.protocol.headers.Events;

import java.util.HashMap;
import java.util.Map;

public class HeaderTranslator {
    private HashMap<Integer, HashMap<String, Integer>> incomingHeaders;
    private HashMap<Integer, HashMap<String, Integer>> outgoingHeaders;

    public HeaderTranslator() {
        this.incomingHeaders = new HashMap<>();
        this.outgoingHeaders = new HashMap<>();
        this.registerOriginalIncomingHeaders();
        this.registerOriginalOutgoingHeaders();
    }



    private void registerOriginalIncomingHeaders() {
        this.incomingHeaders.put(Events.GetClientVersionMessageEvent, new HashMap<>());
        this.incomingHeaders.put(Events.UniqueIDMessageEvent, new HashMap<>());
        this.incomingHeaders.put(Events.SSOTicketMessageEvent, new HashMap<>());
        this.incomingHeaders.put(Events.OpenFlatConnectionMessageEvent, new HashMap<>());
    }

    private void registerOriginalOutgoingHeaders() {

    }

    public HashMap<Integer, HashMap<String, Integer>> getIncomingHeaders() {
        return incomingHeaders;
    }

    public HashMap<Integer, HashMap<String, Integer>> getOutgoingHeaders() {
        return outgoingHeaders;
    }


    /**
     *
     * @param revision string of revision being registered
     * @param originalId id for revision 20161129
     * @param newId id for revision being registered
     */
    public void registerIncomingPacketForRevision(String revision, int originalId, int newId) {
        if (!this.incomingHeaders.containsKey(originalId)) {
            this.incomingHeaders.put(originalId, new HashMap<>());
        }
        this.incomingHeaders.get(originalId).put(revision, newId);
    }

    /**
     *
     * @param revision string of revision being registered
     * @param originalId id for revision 20161129
     * @param newId id for revision being registered
     */
    public void registerOutgoingPacketForRevision(String revision, int originalId, int newId) {
        if (!this.outgoingHeaders.containsKey(originalId)) {
            this.outgoingHeaders.put(originalId, new HashMap<>());
        }
        this.outgoingHeaders.get(originalId).put(revision, newId);
    }

    public int getIncomingPacketForRevision(String revision, int originalId) {
        if(this.incomingHeaders.containsKey(originalId)) {
            if(this.incomingHeaders.get(originalId).containsKey(revision))
                return this.incomingHeaders.get(originalId).get(revision);
        }
        return -1;
    }

    public int getOutgoingPacketForRevision(String revision, int originalId) {
        if(this.outgoingHeaders.containsKey(originalId)) {
            if(this.outgoingHeaders.get(originalId).containsKey(revision))
                return this.outgoingHeaders.get(originalId).get(revision);
        }
        return -1;
    }

    public int getOriginalIncomingPacketFromRevision(String revision, int revisionPacketId) {
        for (Map.Entry<Integer, HashMap<String, Integer>> entry : this.incomingHeaders.entrySet()) {
            if(entry.getValue().get(revision) != null && entry.getValue().get(revision) == revisionPacketId)
                return entry.getKey();
        }
        return -1;
    }

    public int getOriginalOutgoingPacketFromRevision(String revision, int revisionPacketId) {
        for (Map.Entry<Integer, HashMap<String, Integer>> entry : this.outgoingHeaders.entrySet()) {
            if(entry.getValue().get(revision) != null && entry.getValue().get(revision) == revisionPacketId)
                return entry.getKey();
        }
        return -1;
    }

}
