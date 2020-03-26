package net.gabriel.internal.login.com.commands;

import net.gabriel.internal.login.com.InternalLogin;
import net.gabriel.internal.login.com.objects.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangepassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage("§c§lTROCAR §fVocê precisa ser um jogador de verdade para executar este comando!");
        } else {
            Player p = (Player) s;
            User user = InternalLogin.getUserManager().getUser(p.getUniqueId());
            if (args.length != 2) {
                p.sendMessage("§c§lTROCAR §fVocê inseriu argumentos inválidos ou insuficientes.");
                return false;
            }
            if (!args[0].equals(user.getPassword())) {
                p.sendMessage("§c§lTROCAR §fSenha incorreta.");
                return false;
            }
            if (args[1].length() < 5) {
                p.sendMessage("§c§lTROCAR1 §fSua senha deve ter pelo menos 6 caractéres!");
                return false;
            }
            if (args[1].length() > 16) {
                p.sendMessage("§c§lTROCAR §fSua senha pode conter no máximo 16 caractéres!");
                return false;
            } else {
                user.setPassword(args[0]);
                p.kickPlayer("§a§lTROCAR\n§a\n§fSua senha foi alterada com sucesso!");
            }
        }
        return false;
    }
}
