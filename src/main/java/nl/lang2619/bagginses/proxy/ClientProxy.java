package nl.lang2619.bagginses.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Tim on 8/24/2014.
 */
public class ClientProxy extends CommonProxy{
    @Override
    protected void tryDeliverNotifications(){
        if (Minecraft.getMinecraft().thePlayer != null){
            deliverNotificationsToPlayer(Minecraft.getMinecraft().thePlayer);
            clearNotifications();
        }
    }

    @Override
    public void onPlayerLogin(EntityPlayer player){
        deliverNotificationsToPlayer(player);
        clearNotifications();
    }
}
