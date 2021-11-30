package com.cometproject.server.game.commands.development;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.game.quests.QuestType;
import com.cometproject.server.composers.catalog.CatalogPublishMessageComposer;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.commands.ChatCommand;
import com.cometproject.server.game.rooms.bundles.RoomBundleManager;
import com.cometproject.server.game.rooms.bundles.types.RoomBundle;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.rooms.types.Room;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.room.engine.RoomActionMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.mistery.MisteryBoxDataMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.storage.queries.rooms.BundleDao;
import com.cometproject.server.utilities.RandomUtil;

public class FinalEventCommand extends ChatCommand {

    @Override
    public void execute(Session client, String[] params) {
        String commandName = params[0];

        switch (commandName) {
            case "ween":
                final Room room = client.getPlayer().getEntity().getRoom();

                for (PlayerEntity p : room.getEntities().getPlayerEntities()) {
                    int k = RandomUtil.getRandomInt(1,2);
                    String key = k == 1 ? "lilac" : "green", box = k == 1 ? "green" : "lilac";
                    p.getPlayer().getMistery().setMisteryKey(key);
                    p.getPlayer().getMistery().setMisteryBox(box);

                    serializeInfo(p, "ween");
                }

                room.getEntities().broadcastMessage(new RoomActionMessageComposer(1));
                room.getEntities().broadcastMessage(new NotificationMessageComposer("event", "Custom ha sido derrotado y liberado del espíritu maligno y agradece a todos los usuarios con un obsequio."));

                break;

            case "destroy":

                break;

        }
    }

    @Override
    public String getPermission() {
        return "furnifix_command";
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getDescription() {
        return Locale.get("command.finalevent.description");
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    private void serializeInfo(PlayerEntity p, String type){
        switch (type) {
            case "ween":
                p.getPlayer().getSession().send(new MisteryBoxDataMessageComposer(p.getPlayer().getMistery()));
                p.getPlayer().getData().increaseSeasonalPoints(5);
                p.getPlayer().getData().increaseActivityPoints(500);
                p.getPlayer().getData().increaseVipPoints(2);
                p.getPlayer().getInventory().addBadge("LAB07", true);
                p.getPlayer().sendBalance();
                break;
            default:
                break;
        }
    }
}
