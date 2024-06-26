package de.clmpvp.ttt.Gamestates;

import de.clmpvp.ttt.Countdowns.LobbyCountdown;
import de.clmpvp.ttt.main.Main;
import org.bukkit.Bukkit;

public class LobbyState extends Gamestate{

    public static final int MIN_PLAYERS = 1,
                            MAX_PLAYERS = 12;

    private LobbyCountdown countdown;

    public LobbyState(GameStateManager gameStateManager) {
        countdown = new LobbyCountdown(gameStateManager);
    }

    @Override
    public void start() {
        countdown.startIdle();
    }

    @Override
    public void stop() {
        Bukkit.broadcastMessage(Main.prefix + "Wir wären jetzt im Ingame State");
    }

    public LobbyCountdown getCountdown() {
        return countdown;
    }
}
