package nl.lang2619.bagginses.helpers.Messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.helpers.BagFinder;
import nl.lang2619.bagginses.items.bags.Bag;
import nl.lang2619.bagginses.proxy.GuiInfo;
import nl.lang2619.bagginses.references.BagTypes;

/**
 * Created by alex_ on 17/08/2016.
 */
public class OpenBagMessage implements IMessage {

    public OpenBagMessage() {}

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class MyMessageHandler implements IMessageHandler<OpenBagMessage, IMessage> {
        // Do note that the default constructor is required, but implicitly defined in this case

        @Override
        public IMessage onMessage(OpenBagMessage message, MessageContext ctx) {
            // Always use a construct like this to actually handle your message. This ensures that
            // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
            // is called on the networking thread so it is not safe to do a lot of things
            // here.
            System.out.println("Listening");
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(OpenBagMessage message, MessageContext ctx) {
            // This code is run on the server side. So you can do server-side calculations here
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            BagTypes type = ((Bag) BagFinder.getBag(player).getItem()).getType();
            if (type == BagTypes.TIER1)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK, player.worldObj, 0, 0, 0);
            if (type == BagTypes.TIER2)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK_T2, player.worldObj, 0, 0, 0);
            if (type == BagTypes.TIER3)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK_T3, player.worldObj, 0, 0, 0);
            if (type == BagTypes.VOID)
                player.openGui(Bagginses.instance, GuiInfo.GUI_BACKPACK_VOID, player.worldObj, 0, 0, 0);
            if(type == BagTypes.ENDER) {
                InventoryEnderChest inventoryEnderChest = player.getInventoryEnderChest();
                player.displayGUIChest(inventoryEnderChest);
            }
        }
    }
}
