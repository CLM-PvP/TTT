package de.clmpvp.ttt.Gamestates;

public abstract class Gamestate {

    public static final int LOBBY_STATE = 0,
                            INGAME_STATE = 1,
                            ENDING_STATE = 2;

    public abstract void start();
    public abstract void stop();

}
