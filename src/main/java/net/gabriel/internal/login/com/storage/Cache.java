package net.gabriel.internal.login.com.storage;

import net.gabriel.internal.login.com.InternalLogin;
import net.gabriel.internal.login.com.managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class Cache {

    public static void loadData() {
        UserManager manager = InternalLogin.getInstance().getUserManager();
        MySQL sql = InternalLogin.getInstance().getSQL();
        for (OfflinePlayer x : Bukkit.getOfflinePlayers()) {
            if (sql.getPassword(x.getUniqueId().toString()) != null) {
                manager.createUser(x.getUniqueId(), sql.getPassword(x.getUniqueId().toString()));
            }
        }
    }

    public static void saveData() {
        UserManager manager = InternalLogin.getInstance().getUserManager();
        MySQL sql = InternalLogin.getInstance().getSQL();
        for (OfflinePlayer x : Bukkit.getOfflinePlayers()) {
            if (manager.getUser(x.getUniqueId()).isRegistered()) {
                sql.setPassword(x.getUniqueId().toString(), manager.getUser(x.getUniqueId()).getPassword());
            }
        }
    }

}
