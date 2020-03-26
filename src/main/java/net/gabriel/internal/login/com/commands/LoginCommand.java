package net.gabriel.internal.login.com.commands;

import net.gabriel.internal.login.com.InternalLogin;
import net.gabriel.internal.login.com.events.PlayerLoginEvent;
import net.gabriel.internal.login.com.objects.User;
import net.gabriel.internal.login.com.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage("§c§lLOGIN §fVocê precisa ser um jogador de verdade para executar este comando!");
        } else {
            Player p = (Player)s;
            User user = InternalLogin.getInstance().getUserManager().getUser(p.getUniqueId());
            if (user.isLogged()) {
                p.sendMessage("§c§lLOGIN §fVocê já está logado!");
                return false;
            }
            if (!(user.isRegistered())) {
                p.sendMessage("§c§lLOGIN §fVocê precisa se registrar primeiro.");
                return false;
            }
            if (args.length == 0) {
                p.sendMessage("§c§lLOGIN §fInsira uma senha válida.");
                return false;
            }
            if (args.length != 1) {
                p.sendMessage("§c§lLOGIN §fVocê inseriu argumentos inválidos.");
                return false;
            }
            if (!(args[0].equals(user.getPassword()))) {
                p.sendMessage("§c§lLOGIN §fSenha incorreta!");
                return false;
            } else {
                PlayerLoginEvent event = new PlayerLoginEvent(p, user.getPassword());
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    user.setLogged(true);
                    p.setWalkSpeed(0.2f);
                    p.setFlySpeed(0.1f);
                    p.sendMessage("§a§lLOGIN §fVocê se logou com sucesso!");
                    Utils.sendTitle(p, "§a§lSUCESSO", "§fVocê se logou com sucesso!");
                    return false;
                }
            }
        }
        return false;
    }
}
