package net.gabriel.internal.login.com.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {

    public static void sendSound(Player p, Sound sound) {
        p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
    }

    @SuppressWarnings("rawtypes")
    public static void sendTitle(Player p, String titulo, String subtitulo) {
        PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + titulo +"\"}"));
        PacketPlayOutTitle packetPlayOutTitle2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subtitulo +"\"}"));
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutTitle1);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutTitle2);
    }

    public static boolean checkChars(String arg0) {
        return !StringUtils.isEmpty(arg0);
    }


}
