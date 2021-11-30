package com.cometproject.server.network.websockets.packets.incoming;

import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.sessions.Session;
import io.netty.channel.ChannelHandlerContext;

public class AuthenticationHandler extends AbstractWebSocketHandler<AuthenticationHandler.AuthenticationData> {

    public AuthenticationHandler() {
        super(AuthenticationData.class);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, AuthenticationData authenticationData) {
        if(authenticationData.ssoTicket.isEmpty() || !isNumeric(authenticationData.ssoTicket))
            return;

        Session s = NetworkManager.getInstance().getSessions().getByPlayerId(Integer.parseInt(authenticationData.ssoTicket));

        if(s == null)
            return;

        System.out.println("[WS] - WebSocket autenticado para: " + s.getPlayer().getData().getUsername() + ".\n");

        s.setWsChannel(ctx);

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    class AuthenticationData {
        private String ssoTicket;

        AuthenticationData() {
        }
    }
}
