package com.cometproject.server.storage.queries.rooms;

import com.cometproject.server.game.rooms.objects.items.RoomItem;
import com.cometproject.server.storage.SqlHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RoomItemDao {

    private static Logger log = Logger.getLogger(RoomItemDao.class.getName());

public static void deleteItem(long itemId) {
    Connection sqlConnection = null;
    PreparedStatement preparedStatement = null;

    try {
        sqlConnection = SqlHelper.getConnection();

        preparedStatement = SqlHelper.prepare("DELETE FROM items WHERE id = ?", sqlConnection);
        preparedStatement.setLong(1, itemId);

        SqlHelper.executeStatementSilently(preparedStatement, false);
    } catch (SQLException e) {
        SqlHelper.handleSqlException(e);
    } finally {
        SqlHelper.closeSilently(preparedStatement);
        SqlHelper.closeSilently(sqlConnection);
    }
}
}
