package net.gabriel.internal.login.com;

import net.gabriel.internal.login.com.commands.ChangepassCommand;
import net.gabriel.internal.login.com.commands.LoginCommand;
import net.gabriel.internal.login.com.commands.RegisterCommand;
import net.gabriel.internal.login.com.commands.UnregisterCommand;
import net.gabriel.internal.login.com.managers.UserManager;
import net.gabriel.internal.login.com.storage.Cache;
import net.gabriel.internal.login.com.storage.ConfigManager;
import net.gabriel.internal.login.com.storage.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class InternalLogin extends JavaPlugin {
    private static UserManager userManager;

    private static MySQL sql;

    public void onEnable() {
        userManager = new UserManager();
        sql = ConfigManager.getSQL();
        sql.connect();
        sql.createTables();
        Cache.loadData();
        getCommand("unregister").setExecutor(new UnregisterCommand());
        getCommand("register").setExecutor(new RegisterCommand());
        getCommand("login").setExecutor(new LoginCommand());
        getCommand("changepassword").setExecutor(new ChangepassCommand());
        print("§9[InternalLogin] §aPlugin habilitado com sucesso!");
    }

    public void onDisable() {
        sql.disconnect();
        Cache.saveData();
        print("§9[InternalLogin] §cPlugin desabilitado com sucesso!");
    }

    private void print(String s) {
        Bukkit.getConsoleSender().sendMessage(s);
    }

    public static InternalLogin getInstance() {
        return InternalLogin.getPlugin(InternalLogin.class);
    }

    public MySQL getSQL() {
        return sql;
    }

    public static UserManager getUserManager() {
        return userManager;
    }
}
