package com.cometproject.server.network.multirevision;

import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.network.multirevision.handlers.RevisionBridgeHandler;
import com.cometproject.server.network.multirevision.modifiers.ModifierManager;
import com.cometproject.server.network.multirevision.registrars.PacketRegistrarManager;
import com.cometproject.server.network.multirevision.revisions.AIR63_201911271159_623255659;
import com.cometproject.server.network.multirevision.revisions.PRODUCTION_201709192204_203982672;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import org.apache.log4j.Logger;

public class RevisionTest implements Initialisable {
    private static final Logger LOGGER = Logger.getLogger(RevisionTest.class.getName());
    private static RevisionTest INSTANCE;
    private final HeaderTranslator headerTranslator;
    private final ModifierManager modifierManager;
    private final PacketRegistrarManager registrarManager;
    private final ServerBootstrap serverBootstrap;

    public RevisionTest() {
        RevisionTest.INSTANCE = this;
        headerTranslator = new HeaderTranslator();
        modifierManager = new ModifierManager();
        registrarManager = new PacketRegistrarManager();
        serverBootstrap = new ServerBootstrap();
    }

    public static RevisionTest getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new RevisionTest();
        }

        return RevisionTest.INSTANCE;
    }

    @Override
    public void initialize() {
        this.registrarManager.addRegistrar(new AIR63_201911271159_623255659());
        this.registrarManager.addRegistrar(new PRODUCTION_201709192204_203982672());
        this.registrarManager.registerAllPackets(this.headerTranslator);
        LOGGER.info("MultiRevision Initialize");
    }

    public HeaderTranslator getHeaderTranslator() {
        return headerTranslator;
    }

    public ModifierManager getModifierManager() {
        return modifierManager;
    }

    public static Logger getLogger() {
        return RevisionTest.LOGGER;
    }

}
