package net.gabriel.internal.login.com.commands;

import net.gabriel.internal.login.com.InternalLogin;
import net.gabriel.internal.login.com.events.PlayerRegisterEvent;
import net.gabriel.internal.login.com.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterCommand implements CommandExecutor  {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage("§c§lREGISTER §fVocê precisa ser um jogador de verdade para executar este comando!");
        } else {
            Player p = (Player)s;
            User user = InternalLogin.getInstance().getUserManager().getUser(p.getUniqueId());
            if (user.isLogged()) {
                p.sendMessage("§c§lREGISTER §fVocê já está logado!");
                return false;
            }
            if (user.isRegistered()) {
                p.sendMessage("§c§lREGISTER §fVocê já está registrado!");
                return false;
            }
            if (args.length == 0) {
                p.sendMessage("§c§lREGISTER §fInsira uma senha válida!");
                return false;
            }
            if (args.length != 2) {
                p.sendMessage("§c§lREGISTER §fVocê inseriu argumentos inválidos ou insuficientes.");
                return false;
            }
            if (!(args[0].equals(args[1]))) {
                p.sendMessage("§c§lREGISTER §fAs senhas não se coincidem!");
                return false;
            }
            if (args[0].length() < 5) {
                p.sendMessage("§c§lREGISTER §fSua senha deve ter pelo menos 6 caractéres!");
                return false;
            }
            if (args[0].length() > 16) {
                p.sendMessage("§c§lREGISTER §fSua senha pode conter no máximo 16 caractéres!");
                return false;
            } else {
                PlayerRegisterEvent event = new PlayerRegisterEvent(p, args[0]);
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    user.setPassword(args[0]);
                    p.kickPlayer("§a§lREGISTRADO!\n§a\n§fVocê se registrou com sucesso!");
                }
                return false;
            }
        }
        return false;
    }

}
