package com.cometproject.server.network.multirevision.handlers;

import com.cometproject.server.boot.CometServer;
import com.cometproject.server.network.messages.MessageHandler;
import com.cometproject.server.network.multirevision.Attributes;
import com.cometproject.server.network.multirevision.RevisionTest;
import com.cometproject.server.network.multirevision.modifiers.Modifier;
import com.cometproject.server.protocol.headers.Events;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

import java.net.SocketAddress;
import java.util.List;
import java.util.logging.Logger;

public class RevisionBridgeHandler extends MessageToMessageDecoder<ByteBuf> implements ChannelOutboundHandler {

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int header = in.getShort(0);
        if(header == Events.GetClientVersionMessageEvent) {
            int stringLength = in.getShort(2);
            byte[] data = new byte[stringLength];
            in.getBytes(4, data);
            String revision = new String(data);
            ctx.channel().attr(Attributes.REVISION).set(revision);
            RevisionTest.getLogger().debug("Client with revision: " + revision);
        }

        String revision = ctx.channel().attr(Attributes.REVISION).get();
        if(revision != null) {
            int newHeader = RevisionTest.getInstance().getHeaderTranslator().getOriginalIncomingPacketFromRevision(revision, header);
            if(newHeader == -1) {
                RevisionTest.getLogger().debug("Skipping incoming message, no matching header found for " + header);
                in.skipBytes(in.readableBytes()); // discard this message
                return;
            }
            in.setShort(0, newHeader);
            RevisionTest.getLogger().debug("Replaced Incoming header: " + header + " to " + newHeader);
            Modifier modifier = RevisionTest.getInstance().getModifierManager().getIncomingModifiers().get(newHeader);
            if(modifier != null) {
                out.add(modifier.modify(in, revision, ctx).retain());
                return;
            }
        }
        out.add(in.retain());
    }


    /*protected void encode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        String revision = ctx.channel().attr(RevisionTest.REVISION).get();
        if(revision != null) {
            int header = in.getShort(4);
            int newHeader = RevisionTest.getInstance().getHeaderTranslator().getOutgoingPacketForRevision(revision, header);
            if(newHeader == -1) {
                RevisionTest.getLogger().debug("Skipping outgoing message, no matching header found for " + header);
                in.skipBytes(in.getInt(0)); // discard this message
                return;
            }
            in.setShort(4, newHeader);
            RevisionTest.getLogger().debug("Replaced outgoing header " + header + " to " + newHeader);
            Modifier modifier = RevisionTest.getInstance().getModifierManager().getOutgoingModifiers().get(header);
            if(modifier != null) {
                out.add(modifier.modify(in, revision, header).retain());
                return;
            }
        }
        out.add(in.retain());
    }*/


    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress,
                     ChannelPromise promise) throws Exception {
        ctx.bind(localAddress, promise);
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        ctx.connect(remoteAddress, localAddress, promise);
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.disconnect(promise);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.close(promise);
    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.deregister(promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        ctx.read();
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object message, ChannelPromise promise) throws Exception {
        String revision = ctx.channel().attr(Attributes.REVISION).get();
        ByteBuf msg = (ByteBuf) message;

        if (revision != null) {
            int header = msg.getShort(4);
            int newHeader = RevisionTest.getInstance().getHeaderTranslator().getOutgoingPacketForRevision(revision, header);
            if (newHeader == -1) {
                RevisionTest.getLogger().debug("Skipping outgoing message, no matching header found for " + header);
                ReferenceCountUtil.release(msg);//discard message
                promise.setSuccess();// what to do with promise???
                return;
            }
            msg.setShort(4, newHeader);
            RevisionTest.getLogger().debug("Replaced outgoing header " + header + " to " + newHeader);
            Modifier modifier = RevisionTest.getInstance().getModifierManager().getOutgoingModifiers().get(header);
            if (modifier != null) {
                ctx.write(modifier.modify(msg, revision, ctx), promise);
                return;
            }
        }
        ctx.write(msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
