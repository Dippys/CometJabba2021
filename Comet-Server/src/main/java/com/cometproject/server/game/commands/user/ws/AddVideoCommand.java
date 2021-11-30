package com.cometproject.server.game.commands.user.ws;

import com.cometproject.api.game.players.data.PlayerAvatar;
import com.cometproject.api.utilities.JsonUtil;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.SqlHelper;
import com.google.common.collect.Maps;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;


import java.sql.*;
import java.util.Map;

public class AddVideoCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (!client.getPlayer().getEntity().getRoom().getRights().hasRights(client.getPlayer().getId())
                && !client.getPlayer().getPermissions().getRank().roomFullControl()) {
            return;
        }
        if (params.length < 1) {
            return;
        }

        final String videoId = params[0];
        final String message = this.merge(params, 1);
        final PlayerAvatar avatar = client.getPlayer().getData();

        saveVideo(client.getPlayer().getEntity().getRoom().getId(), avatar.getUsername(), videoId);
        isExecuted(client);
    }
    public static void saveVideo(int roomId, String adder, String v) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = SqlHelper.getConnection();

            preparedStatement = SqlHelper.prepare("INSERT into yt_tv (room_id, adder, v, tempo) VALUES(?, ?, ?, ?);", sqlConnection);

            preparedStatement.setInt(1, roomId);
            preparedStatement.setString(2, adder);
            preparedStatement.setString(3, v);
            preparedStatement.setInt(4, (int) Comet.getTime());

            preparedStatement.execute();
        } catch (SQLException e) {
            SqlHelper.handleSqlException(e);
        } finally {
            SqlHelper.closeSilently(preparedStatement);
            SqlHelper.closeSilently(sqlConnection);
        }
    }


    @Override
    public String getPermission() {
        return "roomvideo_command";
    }

    @Override
    public String getParameter() {
        return "%video% %msg%";
    }

    @Override
    public String getDescription() {
        return Locale.getOrDefault("command.addvideo.description", "añade un video a la jukebox");
    }
}
