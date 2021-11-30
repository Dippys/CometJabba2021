package com.cometproject.server.game.commands.development;

import com.cometproject.server.config.Locale;
import com.cometproject.server.game.blibV75.BlibV75;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.network.messages.outgoing.room.avatar.WhisperMessageComposer;
import com.cometproject.server.network.sessions.Session;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.LinkedList;

public class V75Command extends ChatCommand {
    @Override
    public void execute(Session client, String[] params) {
        if (params.length == 1) {
            sendNotif(":v75 %nombre de caballo% %cantidad de apuesta%", client);
            return;
        }

        if (client.getPlayer().getEntity().getRoom().getGame().getV75Usernames().contains(client.getPlayer().getData().getUsername())) {
            sendNotif("¡Ya has apostado!", client);
            return;
        }

        if(client.getPlayer().getEntity().getRoom().getGame().getV75Instance() != null){
            if(client.getPlayer().getEntity().getRoom().getGame().getV75Instance().getHasStarted()){
                sendNotif("¡Por favor, espere hasta que esta ronda esté completa!", client);
                return;
            }
        }

        try {
            Collection<String> petNames = new LinkedList<>();
            petNames.add("lisa");
            petNames.add("sten");
            petNames.add("zlatan");
            petNames.add("kalle");

            boolean isNumeric = StringUtils.isNumeric(params[1]);
            if (!isNumeric) {
                sendNotif("Por favor introduce un valor númerico ejemplo: :v75 Zlatan 100", client);
                return;
            }
            String horseName = params[0].toLowerCase();
            int betAmound = Integer.parseInt(params[1]);

            if(client.getPlayer().getData().getCredits() < betAmound) {
                sendNotif("Has apostado una cantidad que no tienes.", client);
                return;
            }
            if (!petNames.contains(horseName)) {
                sendNotif("No existe ningún caballo con este nombre", client);
                return;
            }
            client.getPlayer().getEntity().getRoom().getGame().getV75StringMap().put(new BlibV75(client.getPlayer().getData().getUsername(), betAmound), horseName);
            client.getPlayer().getEntity().getRoom().getGame().getV75Usernames().add(client.getPlayer().getData().getUsername());
            petNames.clear();
            client.getPlayer().getData().decreaseCredits(betAmound);
            client.getPlayer().sendBalance();
            client.getPlayer().getData().save();
            client.getPlayer().getEntity().getRoom().getEntities().broadcastMessage(new WhisperMessageComposer(client.getPlayer().getId(), "" + client.getPlayer().getData().getUsername() + " ha apostado " + betAmound + " para " + horseName + "!", 30));
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }


    @Override
    public String getPermission() {
        return "v75_command";
    }

    @Override
    public String getParameter() {
        return Locale.getOrDefault("command.parameter.amount", "%amount%");
    }

    @Override
    public String getDescription() {
        return Locale.get("command.v75.description");
    }
}

