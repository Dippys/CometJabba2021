package com.cometproject.server.network.messages.outgoing.room.engine;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.rooms.types.mapping.RoomTile;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;

import java.util.List;

public class UpdateStackMapMessageComposer extends MessageComposer {
    private final List<RoomTile> tilesToUpdate;
    private final RoomTile singleTile;
    private final double hauteur;
    private final int x;
    private final int y;

    public UpdateStackMapMessageComposer(final List<RoomTile> tilesToUpdate) {
        this.tilesToUpdate = tilesToUpdate;
        this.singleTile = null;
        this.hauteur = 0;
        this.x = 0;
        this.y = 0;
    }

    public UpdateStackMapMessageComposer(double hauteur, int x, int y) {
        this.tilesToUpdate = null;
        this.singleTile = null;

        this.hauteur = hauteur;
        this.x = x;
        this.y = y;
    }

    public UpdateStackMapMessageComposer(RoomTile tile) {
        this.tilesToUpdate = null;
        this.singleTile = tile;
        this.hauteur = 0;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public short getId() {
        return Composers.UpdateStackMapMessageComposer;
    }

    @Override
    public void compose(IComposer msg)
    {
        if(singleTile == null && tilesToUpdate == null)
        {
            msg.writeByte(1);
            msg.writeByte(this.x);
            msg.writeByte(this.y);
            msg.writeShort((int) (this.hauteur) * 256);
            return;
        }

        msg.writeByte(singleTile != null ? 1 : tilesToUpdate.size());

        if (singleTile != null) {
            this.composeUpdate(this.singleTile, msg);
            return;
        }

        for (RoomTile tile : tilesToUpdate) {
            this.composeUpdate(tile, msg);
        }
    }

    private void composeUpdate(RoomTile tile, IComposer msg) {
        msg.writeByte(tile.getPosition().getX());
        msg.writeByte(tile.getPosition().getY());

        msg.writeShort((int) ((tile.getStackHeight()) * 256));
    }
}
