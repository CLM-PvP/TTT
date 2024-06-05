package de.clmpvp.ttt.Listeners;

import de.clmpvp.ttt.Gamestates.LobbyState;
import de.clmpvp.ttt.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private Main plugin;

    public PlayerConnectionListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
        Player p = e.getPlayer();
        plugin.getPlayers().add(p);
        e.setJoinMessage(Main.prefix + "§6" + p.getDisplayName() + " §7hat das Spiel betreten. [" +
                        plugin.getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");
        if (plugin.getPlayers().size() >= LobbyState.MIN_PLAYERS)
            Bukkit.broadcastMessage("Das Spiel würde jetzt Starten!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
        Player p = e.getPlayer();
        plugin.getPlayers().remove(p);
        e.setQuitMessage(Main.prefix + "§6" + p.getDisplayName() + " §7hat das Spiel verlassen. [" +
                plugin.getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");
    }

}
