package com.cometproject.server.game.rooms.objects.items.types.floor.wired.highscore;

import com.cometproject.api.game.rooms.objects.data.RoomItemData;
import com.cometproject.server.game.rooms.objects.items.types.floor.wired.data.ScoreboardItemData;
import com.cometproject.server.game.rooms.types.Room;

import java.util.List;

public class HighscoreClassicFloorItem extends HighscoreFloorItem {
    public HighscoreClassicFloorItem(RoomItemData roomItemData, Room room) {
        super(roomItemData, room);
    }

    @Override
    public void onTeamWins(List<String> users, int score) {
        //test adds points
        final ScoreboardItemData.HighscoreEntry entry = this.getScoreData().getEntryByTeam(users);

        if (entry != null) {
            entry.incrementScore();
            this.updateEntry(entry);
        } else {
            this.addEntry(users, score);
        }
    }

    @Override
    public int getScoreType() {
        return 2;
    }
}