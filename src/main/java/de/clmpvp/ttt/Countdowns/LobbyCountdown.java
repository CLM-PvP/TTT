package de.clmpvp.ttt.Countdowns;

import de.clmpvp.ttt.Gamestates.GameStateManager;
import de.clmpvp.ttt.Gamestates.Gamestate;
import de.clmpvp.ttt.Gamestates.LobbyState;
import de.clmpvp.ttt.main.Main;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown{

    private static final int COUNTDOWN_TIME = 20, IDLE_TIME = 15;

    private GameStateManager gameStateManager;

    private int seconds;
    private int idleID;
    private boolean isRunning;
    private boolean isIdling;

    public LobbyCountdown(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {
            @Override
            public void run() {
                switch (seconds) {
                    case 20: case 10: case 5: case 3: case 2:
                        Bukkit.broadcastMessage(Main.prefix + "§7 Das Spiel startet in §a" + seconds + " Sekunden§7.");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Main.prefix + "§7 Das Spiel startet in §aeiner Sekunde§7.");
                        break;
                    case 0:
                        gameStateManager.setGameState(Gamestate.INGAME_STATE);
                        break;

                    default:
                        break;
                }
                seconds--;
            }
        }, 0, 20);
    }

    @Override
    public void stop() {
        if (isRunning) {
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = COUNTDOWN_TIME;
        }
    }

    public void startIdle() {
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(Main.prefix + "§7Bis zum Spielstart fehlen noch §6" + (LobbyState.MIN_PLAYERS - gameStateManager.getPlugin().getPlayers().size() + " Spieler§7."));
            }
        }, 0, 20 * IDLE_TIME);
    }
    public void stopIdle() {
        if (isIdling) {
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
