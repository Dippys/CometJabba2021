package com.cometproject.server.game.commands.user.room;

import com.cometproject.api.game.rooms.models.RoomTileState;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.room.engine.UpdateStackMapMessageComposer;
import com.cometproject.server.network.sessions.Session;
public class SetZCommand extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length == 0)
        {
            sendNotif(Locale.getOrDefault("command.setz.invalide", "Debes indicar una altura para poder continuar ... :" + Locale.get("command.setz.name") + " [altura] !"), client);
            return;
        }

        if(params[0].equals("stop"))
        {
            sendNotif("Para detener el setz, ejecute el comando: setzstop !", client);
            return;
        }

        try
        {
            double value = Double.parseDouble(params[0].replace(",","."));
            if(value < -100 || value > 100)
            {
                sendNotif(Locale.getOrDefault("command.setz.error", "La altura debe establecerse entre -30 y 100 !"), client);
                return;
            }
            client.getPlayer().getEntity().setzok = true;
            client.getPlayer().getEntity().setz = value;

            for (int y = 0; y < client.getPlayer().getEntity().getRoom().getModel().getSizeY(); y++)
            {
                for (int x = 0; x < client.getPlayer().getEntity().getRoom().getModel().getSizeX(); x++)
                {
                    if (client.getPlayer().getEntity().getRoom().getModel().getSquareState()[x][y] != RoomTileState.INVALID)
                        client.send(new UpdateStackMapMessageComposer(value, x, y));
                }
            }

            sendNotif(Locale.getOrDefault("command.setz.enable", "La altura ha sido bien definida !"), client);
        }
        catch (Exception e)
        {
            sendNotif(Locale.getOrDefault("command.setz.invalid", "Il faut indiquer une hauteur pour pouvoir continuer... :" + Locale.get("command.setz.name") + " [altura] !"), client);
        }
    }

    @Override
    public String getPermission() {
        return "height_command";
    }

    @Override
    public String getParameter() {
        return "%nombre%";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.setz.description");
    }
}