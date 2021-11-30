package com.cometproject.server.game.commands.user.room;

import com.cometproject.api.game.rooms.models.RoomTileState;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.network.messages.outgoing.room.engine.UpdateStackMapMessageComposer;
import com.cometproject.server.network.sessions.Session;

public class SetZStopCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params)
    {
        client.getPlayer().getEntity().setzok = false;
        sendNotif(Locale.getOrDefault("command.setz.disable", "La altura ha sido bien definida !"), client);

        for (int y = 0; y < client.getPlayer().getEntity().getRoom().getModel().getSizeY(); y++)
        {
            for (int x = 0; x < client.getPlayer().getEntity().getRoom().getModel().getSizeX(); x++)
            {
                if (client.getPlayer().getEntity().getRoom().getModel().getSquareState()[x][y] != RoomTileState.INVALID)
                {
                    RoomTile tile = client.getPlayer().getEntity().getRoom().getMapping().getTile(x, y);
                    if (tile != null)
                    {
                        try
                        {
                            client.send(new UpdateStackMapMessageComposer(tile.getStackHeight(), x, y));
                        }
                        catch (Exception e)
                        {
                            client.send(new UpdateStackMapMessageComposer(client.getPlayer().getEntity().getRoom().getModel().getSquareHeight()[x][y], x, y));
                        }
                    }
                    else
                    {
                        client.send(new UpdateStackMapMessageComposer(client.getPlayer().getEntity().getRoom().getModel().getSquareHeight()[x][y], x, y));
                    }

                }
            }
        }
    }

    @Override
    public String getPermission() {
        return "height_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.setzstop.description");
    }
}
