package net.gabriel.internal.login.com.storage;

import net.gabriel.internal.login.com.InternalLogin;

public class ConfigManager {

    public static MySQL getSQL() {
        Config config = new Config(InternalLogin.getInstance().getDataFolder().getPath(), "config.yml", InternalLogin.getInstance());

        ConfigValue cv1 = new ConfigValue(config, "MySQL.Host", "localhost");
        String host = cv1.getString();

        ConfigValue cv2 = new ConfigValue(config, "MySQL.Port", "3306");
        String port = cv1.getString();

        ConfigValue cv3 = new ConfigValue(config, "MySQL.User", "root");
        String user = cv1.getString();

        ConfigValue cv4 = new ConfigValue(config, "MySQL.Database", "core-db");
        String database = cv1.getString();

        ConfigValue cv5 = new ConfigValue(config, "MySQL.Password", "");
        String password = cv1.getString();

        return new MySQL(host, port, database, user, password);
    }

}
