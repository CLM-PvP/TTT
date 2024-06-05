package de.clmpvp.ttt.main;

import de.clmpvp.ttt.Gamestates.GameStateManager;
import de.clmpvp.ttt.Gamestates.Gamestate;
import de.clmpvp.ttt.Listeners.PlayerConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    public static final String prefix = "§7[§cTTT§7] ";
    public static final String noperms = "§7[§cTTT§7] §cDazu hast du keine Rechte ";
    public static final String use = "§7[§cTTT§7] §eBitte benutze /";

    private GameStateManager gameStateManager;
    private ArrayList<Player> players;

    @Override
    public void onEnable() {
        gameStateManager = new GameStateManager(this);
        players = new ArrayList<>();

        gameStateManager.setGameState(Gamestate.LOBBY_STATE);

        init(Bukkit.getPluginManager());
        Bukkit.getConsoleSender().sendMessage("§cPlugin gestartet");
    }
    private void init(PluginManager pm) {
        pm.registerEvents(new PlayerConnectionListener(this), this);

    }

    @Override
    public void onDisable() {
        System.out.println("[TTT] Das Plugin wurde gestoppt");
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }
}

