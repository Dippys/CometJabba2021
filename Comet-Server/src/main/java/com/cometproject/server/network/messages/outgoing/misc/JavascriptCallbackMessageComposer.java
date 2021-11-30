package com.cometproject.server.network.messages.outgoing.misc;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.network.skeletor_protocol.outgoing.OutgoingSkeletorMessage;
import com.cometproject.server.network.skeletor_protocol.utils.JsonFactory;

public class JavascriptCallbackMessageComposer extends OpenLinkMessageComposer{
    private OutgoingSkeletorMessage message;

    public JavascriptCallbackMessageComposer(OutgoingSkeletorMessage message) {
        super("");
        this.message = message;
    }

    @Override
    public void compose(IComposer msg) {
        //replace the / char so the string doesnt get cutoff by the swf
        String jsonMessage = JsonFactory.getInstance().toJson(message).replace("/", "&#47;");
        msg.writeString("habblet/open/" + jsonMessage);
    }
}
