package com.cometproject.stresstest;

import com.cometproject.stresstest.commands.CommandHandler;
import com.cometproject.stresstest.connections.CometClientConfig;
import com.cometproject.stresstest.connections.CometClientConnection;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CometStressTest {

    public static boolean isRunning = true;
    private int botCount;
    private String botNamePrefix;

    private Map<Integer, AtomicInteger> rooms;

    private final EventLoopGroup clientLoopGroup = new NioEventLoopGroup(16);
    private final List<CometClientConnection> connections = new ArrayList<>();
    private final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(8);

    public CometStressTest(String[] args) {
        this.botCount = 50;
        this.botNamePrefix = "comet-testing-";

        this.rooms = new ConcurrentHashMap<>();

        int roomId = 589;
        this.rooms.put(roomId, new AtomicInteger(0));

        this.executorService.scheduleAtFixedRate(() -> {
            for(CometClientConnection cometClientConnection : this.connections) {
                if(CometStressTest.getRandom(1, 50) > 40) {
                    cometClientConnection.tick();
                }
            }
        }, 1000L, 1000L, TimeUnit.MILLISECONDS);
    }

    public void initialize() {
        for (int i = 0; i < botCount; i++) {
            final CometClientConnection clientConnection = new CometClientConnection(new CometClientConfig("35.238.199.74", 30000, "comet-testing-" + i), this.clientLoopGroup);

            connections.add(clientConnection);
        }
    }

    public Map<Integer, AtomicInteger> getRooms() {
        return rooms;
    }

    public List<CometClientConnection> getConnections() {
        return connections;
    }

    public static void main(String[] args) {
        final CometStressTest stressTest = new CometStressTest(args);

        CommandHandler.init(stressTest);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stressTest.connections.forEach(CometClientConnection::disconnect);
            isRunning = false;
        }));

        stressTest.initialize();
    }

    public static int getRandom(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
