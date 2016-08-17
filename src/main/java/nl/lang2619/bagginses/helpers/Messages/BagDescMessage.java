package nl.lang2619.bagginses.helpers.Messages;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nl.lang2619.bagginses.helpers.Bags;
import nl.lang2619.bagginses.items.ModItems;

/**
 * Created by alex on 22/06/16.
 */
public class BagDescMessage implements IMessage {

    String bag;

    public BagDescMessage() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        for (Bags bag : ModItems.bags.values()) {
            bag.setServerDesc(ByteBufUtils.readUTF8String(buf));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (Bags bag : ModItems.bags.values()) {
            ByteBufUtils.writeUTF8String(buf, bag.getDesc());
        }
    }

    public static class MyMessageHandler implements IMessageHandler<BagDescMessage, IMessage> {
        // Do note that the default constructor is required, but implicitly defined in this case

        @Override
        public IMessage onMessage(BagDescMessage message, MessageContext ctx) {
            return null;
        }
    }
}
