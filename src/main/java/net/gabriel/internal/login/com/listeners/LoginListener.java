package net.gabriel.internal.login.com.listeners;

import net.gabriel.internal.login.com.InternalLogin;
import net.gabriel.internal.login.com.managers.UserManager;
import net.gabriel.internal.login.com.objects.User;
import net.gabriel.internal.login.com.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;

public class LoginListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void aoEntrar(PlayerJoinEvent e) {
        for (int i = 0; i < 256; i++) {
            e.getPlayer().sendMessage("");
        }
        UserManager manager = InternalLogin.getInstance().getUserManager();
        User u = manager.createUser(e.getPlayer().getUniqueId());
        u.setLogged(false);
        e.getPlayer().setWalkSpeed(0f);
        e.getPlayer().setFlySpeed(0f);
        startLoginProcess(e.getPlayer());
    }

    private void startLoginProcess(Player arg0) {
        User u = InternalLogin.getInstance().getUserManager().getUser(arg0.getUniqueId());
        if (u.isRegistered()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!(u.isLogged())) {
                        if (arg0.isOnline()) {
                            Utils.sendTitle(arg0, "§9§lLOGIN", "§fLogue-se para proceder!");
                        }
                    }
                }
            }.runTaskTimer(InternalLogin.getInstance(), 3L, 60L);
        } else {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!(u.isLogged())) {
                        if (arg0.isOnline()) {
                            Utils.sendTitle(arg0, "§9§lREGISTRO", "§fRegistre-se para proceder!");
                        }
                    }
                }
            }.runTaskTimer(InternalLogin.getInstance(), 3L, 60L);
        }
    }

    @EventHandler
    public void aoSair(PlayerQuitEvent e) {
        InternalLogin.getInstance().getUserManager().createUser(e.getPlayer().getUniqueId()).setLogged(false);
    }

    @EventHandler
    public void aoMover(PlayerMoveEvent e) {
        if (!(InternalLogin.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).isLogged())) {
            if (!(e.getFrom().getX() == e.getTo().getX() && e.getFrom().getY() == e.getTo().getY() && e.getFrom().getZ() == e.getTo().getZ()))
                e.getPlayer().teleport(e.getFrom(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    @EventHandler
    public void aoFalar(AsyncPlayerChatEvent e) {
        if (!(InternalLogin.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).isLogged())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§c>> §7Você precisa se logar para mandar mensagens!");
        }
    }

    @EventHandler
    public void aoExecutarComando(PlayerCommandPreprocessEvent e) {
        if (!(InternalLogin.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).isLogged())) {
            if (!(e.getMessage().startsWith("/login") || e.getMessage().startsWith("/register"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§c>> §7Você precisa se logar para executar este comando!");
            }
        }
    }

    @EventHandler
    public void aoLevarDano(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (!(InternalLogin.getInstance().getUserManager().getUser(p.getUniqueId()).isLogged())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void aoQuebrar(BlockBreakEvent e) {
        if (!(InternalLogin.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).isLogged())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void aoColocar(BlockPlaceEvent e) {
        if (!(InternalLogin.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId()).isLogged())) {
            e.setCancelled(true);
        }
    }

}
