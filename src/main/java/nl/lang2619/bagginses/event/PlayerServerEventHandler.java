package nl.lang2619.bagginses.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.helpers.Bags;
import nl.lang2619.bagginses.helpers.Messages.BagDescMessage;
import nl.lang2619.bagginses.items.ModItems;

/**
 * Created by alex on 22/06/16.
 */
public class PlayerServerEventHandler {

    public void playerJoinServer(FMLNetworkEvent.ServerConnectionFromClientEvent e) {
        System.out.println("Sending message");
        Bagginses.INSTANCE.sendToAll(new BagDescMessage());
    }

    @SubscribeEvent
    public void playerLeave(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        for (Bags bag : ModItems.bags.values()) {
            bag.setServerDesc("");
        }
    }

    @SubscribeEvent
    public void playerJoin(PlayerEvent.PlayerLoggedInEvent e) {
        Bagginses.analytics.eventDesign("playerJoin:server");
        Bagginses.INSTANCE.sendTo(new BagDescMessage(), (EntityPlayerMP) e.player);
    }
}
