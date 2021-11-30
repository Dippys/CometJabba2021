package com.cometproject.server.game.players.components;

import com.cometproject.api.game.players.IPlayer;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.game.players.types.PlayerComponent;
import com.cometproject.server.storage.queries.player.SubscriptionDao;


public class SubscriptionComponent extends PlayerComponent {
    private boolean hasSub;
    private int expire;
    private int presents;

    public SubscriptionComponent(IPlayer player) {
        super(player);

        this.load();
    }

    public void load() {
        this.hasSub = true;
        this.expire = (int) Comet.getTime() + 315569260;
        this.presents = getPresentsFromDao();

        // TODO: Subscriptions
    }

    public void add(int days) {
        // TODO: Add or extend the subscription
    }

    public void delete() {
        this.hasSub = false;
        this.expire = 0;
    }

    public boolean isValid() {
        return this.getExpire() > Comet.getTime();
    }

    public boolean exists() {
        return this.hasSub;
    }

    public int getExpire() {
        return this.expire;
    }

    public int getPresents() {
        return this.presents;
    }

    public void decrementPresents(int playerId){
        this.presents--;
        SubscriptionDao.decrementPresents(playerId, this.presents);
    }

    public int getPresentsFromDao() {
        return SubscriptionDao.getPresents(this.player.getId());
    }

    public IPlayer getPlayer() {
        return this.player;
    }
}
