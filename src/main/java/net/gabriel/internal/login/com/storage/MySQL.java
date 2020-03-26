package net.gabriel.internal.login.com.storage;

import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {
    private String ip;
    private String port;
    private String database;
    private String user;
    private String password;

    private Connection con;

    public MySQL(String ip, String porta, String database, String user, String password) {
        this.ip = ip;
        this.port = porta;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        if (!(isConnected())) {
            try {
                Class.forName("java.sql.DriverManager");
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + ip + ":" + port + "/" + database, user, password);
                print("Conexão efetuada com sucesso!");
            } catch (Exception e) {
                print("§cA conexão foi interrompida!");

            }
        } else {
            print("§cA conexão já foi efetuada!");
        }
    }

    public void createTables() {
        if (isConnected()) {
            try {
                PreparedStatement ps = con.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS loginTable " +
                                "( `UUID` varchar(36) NOT NULL, `Password` varchar(16) NOT NULL, PRIMARY KEY(UUID)" +
                                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
                ps.executeUpdate();
                print("§aTabelas criadas com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPassword(String uuid, String password) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO loginTable (UUID, Password) VALUES(?, ?) " +
                            "ON DUPLICATE KEY UPDATE " +
                            "UUID= ?, Password= ?");
            ps.setString(1, uuid);
            ps.setString(2, password);
            ps.setString(3, uuid);
            ps.setString(4, password);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unregister(String uuid) {
        if (isConnected()) {
            try {
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM loginTable " +
                                "WHERE UUID = ?");
                ps.setString(1, uuid);
                ps.executeUpdate();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getPassword(String uuid){
        try{
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM " + "loginTable" + " WHERE UUID= ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            String password = rs.next() ? rs.getString("Password") : null;
            rs.close();
            ps.close();
            return password;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                con = null;
                print("§cConexão MySQL interrompida com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            print("§cA conexão é inexistente!");
        }
    }

    public boolean isConnected() {
        return con != null;
    }
    private void print(String s){ Bukkit.getConsoleSender().sendMessage(s);}

}
