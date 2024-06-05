package de.clmpvp.ttt.Gamestates;

import de.clmpvp.ttt.main.Main;

public class GameStateManager {

    private Main plugin;
    private Gamestate[] gameStates;
    private Gamestate currentGameState;

    public GameStateManager(Main plugin) {
        this.plugin = plugin;
        gameStates = new Gamestate[3];

        gameStates[Gamestate.LOBBY_STATE] = new LobbyState();
        gameStates[Gamestate.INGAME_STATE] = new IngameState();
        gameStates[Gamestate.ENDING_STATE] = new EndingState();
    }

    public void setGameState(int gameStateID) {
        if (currentGameState != null)
            currentGameState.stop();
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }

    public void stopCurrentGameState() {
        if (currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;
        }
    }

    public Gamestate getCurrentGameState() {
        return currentGameState;
    }
}
