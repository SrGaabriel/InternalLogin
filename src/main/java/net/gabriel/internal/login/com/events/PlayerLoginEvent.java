package net.gabriel.internal.login.com.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLoginEvent extends Event implements Cancellable {
    private HandlerList handlerList;

    private Player player;

    private boolean cancelled;

    private String password;

    public PlayerLoginEvent(Player player, String password) {
        this.handlerList = new HandlerList();
        this.player = player;
        this.password = password;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public Player getPlayer() {
        return player;
    }

    public String getPassword() {
        return password;
    }

}
