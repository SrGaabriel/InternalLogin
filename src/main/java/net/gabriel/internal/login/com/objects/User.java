package net.gabriel.internal.login.com.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private UUID player;

    private boolean logged;

    private String password;

    public User(UUID player) {
        this.player = player;
        this.logged = false;
        this.password = null;
    }

    public User(UUID player, String password) {
        this.player = player;
        this.logged = false;
        this.password = password;
    }


    public UUID getPlayer() {
        return player;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean arg0) {
        this.logged = arg0;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isRegistered() {
        return password != null;
    }

}
