package nl.lang2619.bagginses.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tim on 8/24/2014.
 */
public class CommonProxy {
    protected static final String prefix = "[Bagginses] ";
    protected List<String> notifications = Collections.synchronizedList(new ArrayList<String>());

    public final void register(){
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public final void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e){
        onPlayerLogin(e.player);
    }

    public final void report(String message){
        notifications.add(prefix+message);
        tryDeliverNotifications();
    }

    public final void report(String message, boolean noPrefix){
        notifications.add((noPrefix ? "" : prefix)+message);
        tryDeliverNotifications();
    }

    protected final void deliverNotificationsToPlayer(EntityPlayer player){
        for(String notification:notifications)player.addChatMessage(new ChatComponentText(notification));
    }

    protected final void clearNotifications(){
        notifications.clear();
    }

    protected void tryDeliverNotifications(){
        boolean delivered = false;
        ServerConfigurationManager manager = MinecraftServer.getServer().getConfigurationManager();
        List<EntityPlayer> players = manager.playerEntityList;

        for(EntityPlayer player:players){
            if (manager.func_152596_g(player.getGameProfile())){
                deliverNotificationsToPlayer(player);
                delivered = true;
            }
        }

        if (delivered)clearNotifications();
    }

    protected void onPlayerLogin(EntityPlayer player){
        if (MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile())){
            deliverNotificationsToPlayer(player);
            clearNotifications();
        }
    }
}
