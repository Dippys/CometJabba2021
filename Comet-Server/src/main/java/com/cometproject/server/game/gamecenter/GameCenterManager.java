package com.cometproject.server.game.gamecenter;


import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.game.players.data.GamePlayer;
import com.cometproject.server.storage.queries.crafting.CraftingDao;
import com.cometproject.server.tasks.CometThreadManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class GameCenterManager implements Initialisable {
    private static GameCenterManager gameCenterManagerInstance;
    private static int gameId;
    private List<GamePlayer> currentWeek;
    private List<GamePlayer> lastWeek;
    private Logger log = Logger.getLogger(GameCenterManager.class.getName());

    private List<GameCenterInfo> gamesList;

    public GameCenterManager() {
    }

    @Override
    public void initialize() {
        this.gamesList = new ArrayList<>();

        this.loadLeaderboards();
        this.loadGameCenterList();

        System.out.println("GameCenter inicializado -> LISTO!");
    }

    private void loadLeaderboards() {
        if (this.currentWeek != null) {
            this.currentWeek.clear();
        }

        if (this.lastWeek != null) {
            this.lastWeek.clear();
        }

        //this.currentWeek = BetDao.getLeaderBoard(3, 0, false, false);
        //this.lastWeek = BetDao.getLeaderBoard(3, 0, true, false);

        // Queue it to be refreshed again in 5 minutes.
        CometThreadManager.getInstance().executeSchedule(this::loadLeaderboards,1, TimeUnit.MINUTES);
    }

    public List<GamePlayer> getLeaderboardByWeek(boolean isCurrent){
        if(isCurrent)
            return this.currentWeek;
        else return lastWeek;
    }

    public static GameCenterManager getInstance() {
        if (gameCenterManagerInstance == null)
            gameCenterManagerInstance = new GameCenterManager();

        return gameCenterManagerInstance;
    }

    public void loadGameCenterList() {
        if(!this.gamesList.isEmpty()) {
            this.gamesList.clear();
        }

        this.gamesList = CraftingDao.getGames();
    }

    public GameCenterInfo getGameById(int gameId){

        GameCenterInfo gameInfo = null;
        for(GameCenterInfo infoGame : this.gamesList){
            if(infoGame.getGameId() == gameId){
                gameInfo = infoGame;
                break;
            }
        }
        return gameInfo;
    }

    public List<GameCenterInfo> getGamesList() {
        return this.gamesList;
    }
}
