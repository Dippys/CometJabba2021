package com.cometproject.gamecenter.fastfood;

import com.cometproject.gamecenter.fastfood.objects.FoodPlate;
import com.cometproject.gamecenter.fastfood.objects.MissileType;
import com.cometproject.gamecenter.fastfood.net.FastFoodGameSession;
import com.cometproject.gamecenter.fastfood.net.FastFoodNetSession;
import com.cometproject.gamecenter.fastfood.net.composers.DropFoodMessageComposer;
import com.cometproject.gamecenter.fastfood.net.composers.PlayerJoinGameMessageComposer;
import com.cometproject.gamecenter.fastfood.players.MockPlayerBuilder;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FastFoodGame {
    private Set<FastFoodNetSession> players;

    private boolean started = false;
    private Future scheduledFuture;

    private final AtomicInteger counter = new AtomicInteger(0);

    public FastFoodGame() {
        this.players = Sets.newConcurrentHashSet();
    }

    private void tick() {
        for (FastFoodNetSession netSession : this.players) {
            final FoodPlate foodPlate = netSession.getGameSession().getCurrentPlate();

            if (foodPlate != null && !foodPlate.isFinalized()) {
                foodPlate.tick(netSession.getGameSession(), this);
            }
        }
    }

    public void startGame(ScheduledExecutorService executorService) {
        for (FastFoodNetSession netSession : this.getPlayers()) {
            if (netSession.getChannel() != null) {
                netSession.getChannel().writeAndFlush(new PlayerJoinGameMessageComposer(this,
                        netSession.getGameSession()));
            }
        }

        this.scheduledFuture = executorService.scheduleAtFixedRate(this::tick, 1000, 100,
                TimeUnit.MILLISECONDS);

        this.started = true;
    }

    public void stopGame() {
        this.scheduledFuture.cancel(true);
    }

    public void launch(int type, FastFoodGameSession gameSession) {
        if (type != 0 && gameSession.getCurrentPlate() != null && !gameSession.getCurrentPlate().isFinalized()) {
            gameSession.getCurrentPlate().openParachute(this);
            return;
        }

        final int objectId = this.counter.getAndIncrement();
        final FoodPlate foodPlate = new FoodPlate(objectId, gameSession.getPlayerId());

        gameSession.setCurrentPlate(foodPlate);
        this.broadcast(new DropFoodMessageComposer(objectId, foodPlate));
    }

    public void broadcast(MessageComposer messageComposer) {
        for (FastFoodNetSession netSession : this.players) {
            if (netSession.getChannel() != null) {
                netSession.getChannel().writeAndFlush(messageComposer);
            }
        }
    }

    public boolean hasStarted() {
        return this.started;
    }

    public boolean canStart() {
        return this.players.size() == 3;
    }

    public Set<FastFoodNetSession> getPlayers() {
        return this.players;
    }

    public AtomicInteger getCounter() {
        return counter;
    }
}
