package com.cometproject.server.game.players.login;

import com.cometproject.api.config.CometSettings;
import com.cometproject.api.events.players.OnPlayerLoginEvent;
import com.cometproject.api.events.players.args.OnPlayerLoginEventArgs;
import com.cometproject.api.game.achievements.types.AchievementType;
import com.cometproject.api.game.players.data.components.messenger.IMessengerFriend;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.config.Locale;
import com.cometproject.server.game.moderation.BanManager;
import com.cometproject.server.game.moderation.ModerationManager;
import com.cometproject.server.game.moderation.types.BanType;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.game.players.data.PlayerData;
import com.cometproject.server.game.players.types.Player;
import com.cometproject.server.game.rooms.RoomManager;
import com.cometproject.server.game.rooms.objects.entities.types.PlayerEntity;
import com.cometproject.server.game.snowwar.SnowWar;
import com.cometproject.server.game.snowwar.data.SnowWarPlayerData;
import com.cometproject.server.modules.ModuleManager;
import com.cometproject.server.network.NetworkManager;
import com.cometproject.server.network.messages.incoming.handshake.SSOTicketMessageEvent;
import com.cometproject.server.network.messages.outgoing.gamecenter.GameListMessageComposer;
import com.cometproject.server.network.messages.outgoing.handshake.AuthenticationOKMessageComposer;
import com.cometproject.server.network.messages.outgoing.handshake.HomeRoomMessageComposer;
import com.cometproject.server.network.messages.outgoing.handshake.UniqueIDMessageComposer;
import com.cometproject.server.network.messages.outgoing.landing.calendar.CampaignCalendarDataMessageComposer;
import com.cometproject.server.network.messages.outgoing.messenger.FriendToolbarNotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.messenger.InviteFriendMessageComposer;
import com.cometproject.server.network.messages.outgoing.misc.OpenLinkMessageComposer;
import com.cometproject.server.network.messages.outgoing.moderation.CfhTopicsInitMessageComposer;
import com.cometproject.server.network.messages.outgoing.moderation.ModToolMessageComposer;
import com.cometproject.server.network.messages.outgoing.navigator.FavouriteRoomsMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.MotdNotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.notification.NotificationMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.details.AvailabilityStatusMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.details.PlayerSettingsMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.inventory.EffectsInventoryMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.mistery.MisteryBoxDataMessageComposer;
import com.cometproject.server.network.messages.outgoing.user.permissions.FuserightsMessageComposer;
import com.cometproject.server.network.sessions.Session;
import com.cometproject.server.network.sessions.SessionManager;
import com.cometproject.server.storage.queries.player.PlayerAccessDao;
import com.cometproject.server.storage.queries.player.PlayerDao;
import com.cometproject.server.storage.queries.player.SubscriptionDao;
import com.cometproject.server.tasks.CometTask;
import com.cometproject.server.tasks.CometThreadManager;
import com.cometproject.server.utilities.RandomUtil;
import com.mrpowergamerbr.temmiewebhook.DiscordEmbed;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import com.mrpowergamerbr.temmiewebhook.embed.ThumbnailEmbed;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class PlayerLoginRequest implements CometTask {

    private final Session client;
    private final String ticket;

    public PlayerLoginRequest(Session client, String ticket) {
        this.client = client;
        this.ticket = ticket;
    }

    @Override
    public void run() {
        if (this.client == null) {// || this.client.getChannel().pipeline().get("encryptionDecoder") == null) {
            return;
        }

        try {

            Player player = PlayerDao.getPlayer(ticket);

            if (player == null) {
                player = PlayerDao.getPlayerFallback(ticket);

                if (player == null) {
                    client.disconnect();
                    return;
                }
            }

            Session cloneSession = NetworkManager.getInstance().getSessions().getByPlayerId(player.getId());

            if (cloneSession != null && cloneSession.getPlayer() != null && cloneSession.getPlayer().getData() != null) {
                player.setData(cloneSession.getPlayer().getData().clone()); // thanks for the nullpointers LEON
                player.getData().setPlayer(player);
                cloneSession.disconnect();
            }

            if (BanManager.getInstance().hasBan(Integer.toString(player.getId()), BanType.USER)) {
                client.getLogger().warn("Banned player: " + player.getId() + " tried logging in");
                client.disconnect("banned");
                return;
            }

            player.setSession(client);
            client.setPlayer(player);

            String ipAddress = client.getIpAddress();

            if (ipAddress != null && !ipAddress.isEmpty()) {
                if (BanManager.getInstance().hasBan(ipAddress, BanType.IP)) {
                    client.getLogger().warn("Banned player: " + player.getId() + " tried logging in");
                    client.disconnect("banned");
                    return;
                }

                client.getPlayer().getData().setIpAddress(ipAddress);

                if (PlayerManager.getInstance().getPlayerCountByIpAddress(ipAddress) > CometSettings.maxConnectionsPerIpAddress) {
                    client.disconnect();
                    return;
                }
            }

            if (CometSettings.saveLogins)
                PlayerAccessDao.saveAccess(player.getId(), client.getUniqueId(), ipAddress);

            RoomManager.getInstance().loadRoomsForUser(player);

            client.getLogger().debug(client.getPlayer().getData().getUsername() + " logged in");

            player.setOnline(true);

            PlayerDao.updatePlayerStatus(player, player.isOnline(), true);

            client.sendQueue(new UniqueIDMessageComposer(client.getUniqueId())).
                    sendQueue(new AuthenticationOKMessageComposer()).
                    sendQueue(new FuserightsMessageComposer(client.getPlayer().getSubscription().exists(), client.getPlayer().getData().getRank())).
                    sendQueue(new FavouriteRoomsMessageComposer(client.getPlayer().getNavigator().getFavouriteRooms())).
                    sendQueue(new AvailabilityStatusMessageComposer(true, false, true)).
                    sendQueue(new PlayerSettingsMessageComposer(player.getSettings())).
                    sendQueue(new HomeRoomMessageComposer(player.getSettings().getHomeRoom(), player.getSettings().getHomeRoom())).
                    sendQueue(new MisteryBoxDataMessageComposer(player.getMistery())).
                    sendQueue(new CampaignCalendarDataMessageComposer(player.getGifts())).
                    sendQueue(new EffectsInventoryMessageComposer(player.getInventory().getEffects(), player.getInventory().getEquippedEffect())).
                    sendQueue(new CfhTopicsInitMessageComposer());

            if (client.getPlayer().getPermissions().getRank().modTool()) {
                client.sendQueue(new ModToolMessageComposer());
            }

            if (CometSettings.motdEnabled) {
                client.sendQueue(new OpenLinkMessageComposer("habbopages/bienvenida.txt?" + Comet.getTime()));
            }

            TemmieWebhook temmie = new TemmieWebhook("https://discord.com/api/webhooks/913621867196198992/DUyF31vWwt-CBOLYfXKB9N8dDst4Qxplgy46PQkRm57yJMEbHEhyavUSZ6gI1IUFk7gH");
            DiscordEmbed de = new DiscordEmbed("[INFORMACIÓN EN JABBA]", player.getData().getUsername() + " ha entrado al hotel => IP: " + "(" + player.getData().getIpAddress() + ")");
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://imgur.com/StqoUo3.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            DiscordMessage dm = new DiscordMessage("Jabba", "", "https://imgur.com/StqoUo3.png");
            dm.getEmbeds().add(de);
            temmie.sendMessage(dm);

            if (client.getPlayer().getData().getEndVipTimestamp() <= (int) Comet.getTime()) {
                SubscriptionDao.deleteSubscription(client.getPlayer().getId());
                client.getPlayer().getData().save();
            }

                // SISTEMA DE BONUS POR LOGIN
                /*if (client.getPlayer().getData().getRank() == 2) {
                    player = client.getPlayer();
                    int amount = RandomUtil.getRandomInt(1, 50);

                    player.sendNotif("Bienvenid@", "Bonus login: \n" + player.getData().getUsername() + ", haz recibido tu premio diario, que son exactamente " + amount + " créditos");

                    player.getData().increaseCredits(amount);
                    player.getData().save();

                    player.sendBalance();
                } else {
                    return;
                }*/

            if (CometSettings.onlineRewardDoubleDays.size() != 0) {
                LocalDate date = LocalDate.now();

                if (CometSettings.onlineRewardDoubleDays.contains(date.getDayOfWeek())) {
                    client.sendQueue(new MotdNotificationMessageComposer(Locale.getOrDefault("reward.double.points", "Hey %username%, \n\nToday we're giving out double points!").replace("%username%", player.getData().getUsername())));
                }
            }

            client.flush();

                for (IMessengerFriend friend : client.getPlayer().getMessenger().getFriends().values()) {
                    if (!friend.isOnline() || friend.getUserId() == client.getPlayer().getId()) {
                        continue;
                    }
                    friend.getSession().send(new NotificationMessageComposer("/usr/look/" + player.getData().getUsername(),
                            Locale.getOrDefault("player.online", "%username% está conectado!")
                                    .replace("%username%", player.getData().getUsername())));
                }

            if(client.getPlayer().getSettings().getNuxStatus() == 0){
                for (Session staff : ModerationManager.getInstance().getModerators()) {
                    staff.send(new InviteFriendMessageComposer(Locale.getOrDefault("onboarding.alert", "%p acaba de registrarse en " + CometSettings.hotelName).replace("%p", client.getPlayer().getData().getUsername()), Integer.MIN_VALUE + 5000));
                }
            }

            // Process the achievementsG
            client.getPlayer().getAchievements().progressAchievement(AchievementType.LOGIN, 1);

            int regDate = StringUtils.isNumeric(client.getPlayer().getData().getRegDate()) ? Integer.parseInt(client.getPlayer().getData().getRegDate()) : client.getPlayer().getData().getRegTimestamp();

            if (regDate != 0) {
                int daysSinceRegistration = (int) Math.floor((((int) Comet.getTime()) - regDate) / 86400);

                if (!client.getPlayer().getAchievements().hasStartedAchievement(AchievementType.REGISTRATION_DURATION)) {
                    client.getPlayer().getAchievements().progressAchievement(AchievementType.REGISTRATION_DURATION, daysSinceRegistration);
                } else {
                    // Progress their achievement from the last progress to now.
                    int progress = client.getPlayer().getAchievements().getProgress(AchievementType.REGISTRATION_DURATION).getProgress();
                    if (daysSinceRegistration > client.getPlayer().getAchievements().getProgress(AchievementType.REGISTRATION_DURATION).getProgress()) {
                        int amountToProgress = daysSinceRegistration - progress;
                        client.getPlayer().getAchievements().progressAchievement(AchievementType.REGISTRATION_DURATION, amountToProgress);
                    }
                }
            }

            if (player.getData().getAchievementPoints() < 0) {
                player.getData().setAchievementPoints(0);
                player.getData().save();
            }

            if (!Comet.isDebugging) {
                PlayerDao.nullifyAuthTicket(player.getData().getId());
            }

            if (ModuleManager.getInstance().getEventHandler().handleEvent(OnPlayerLoginEvent.class, new OnPlayerLoginEventArgs(client.getPlayer()))) {
                client.disconnect();
            }

            if (SessionManager.isLocked) {
                client.send(new AlertMessageComposer("Hotel cerrado, vuelve pronto!"));
                CometThreadManager.getInstance().executeSchedule(client::disconnect, 5, TimeUnit.SECONDS);
            }

            if (client.getPlayer().getData().getTimeMuted() != 0) {
                if (client.getPlayer().getData().getTimeMuted() < (int) Comet.getTime()) {
                    PlayerDao.addTimeMute(player.getData().getId(), 0);
                }
            }

            player.setSsoTicket(this.ticket);
            PlayerManager.getInstance().getSsoTicketToPlayerId().put(this.ticket, player.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
