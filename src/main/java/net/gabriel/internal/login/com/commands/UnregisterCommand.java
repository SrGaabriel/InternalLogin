package net.gabriel.internal.login.com.commands;

import net.gabriel.internal.login.com.InternalLogin;
import net.gabriel.internal.login.com.objects.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnregisterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage("§c§lUNREGISTER §fVocê precisa ser um jogador de verdade para executar este comando!");
        } else {
            Player p = (Player)s;
            User user = InternalLogin.getInstance().getUserManager().getUser(p.getUniqueId());
            if (!(user.isLogged())) {
                s.sendMessage("§c§lUNREGISTER §fVocê precisa estar logado para executar este comando!");
                return false;
            }
            if (args.length != 1) {
                s.sendMessage("§c§lUNREGISTER §fVocê inseriu argumentos inválidos ou insuficientes.");
                return false;
            }
            if (!(args[0].equals(user.getPassword()))) {
                p.sendMessage("§c§lUNREGISTER §fSenha incorreta.");
                return false;
            } else {
                InternalLogin.getInstance().getUserManager().deleteUser(user);
                p.kickPlayer("§c§lDESREGISTRADO\n§a\n§7Sua conta foi retirada de nossos sistemas com sucesso.");
                return false;
            }
        }
        return false;
    }
}
