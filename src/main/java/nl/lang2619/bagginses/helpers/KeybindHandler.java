package nl.lang2619.bagginses.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nl.lang2619.bagginses.Bagginses;
import nl.lang2619.bagginses.helpers.Messages.OpenBagMessage;
import org.lwjgl.input.Keyboard;

/**
 * Created by alex_ on 17/08/2016.
 */
@SideOnly(Side.CLIENT)
public class KeybindHandler {

    public static KeyBinding openBag;

    public static void registerKeybinds() {
        openBag = new KeyBinding("key.OpenBag", Keyboard.KEY_C, "key.categories.bagginses");
        ClientRegistry.registerKeyBinding(openBag);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Minecraft.getMinecraft().inGameHasFocus) {
            if (openBag.isPressed()) {
                Bagginses.INSTANCE.sendToServer(new OpenBagMessage());
            }
        }
    }
}
