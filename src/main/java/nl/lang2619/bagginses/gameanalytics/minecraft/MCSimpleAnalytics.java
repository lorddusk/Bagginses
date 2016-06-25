/**
 * (C) 2015 NPException
 */
package nl.lang2619.bagginses.gameanalytics.minecraft;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import nl.lang2619.bagginses.config.ModConfig;
import nl.lang2619.bagginses.gameanalytics.SimpleAnalytics;

/**
 * @author NPException
 *
 */
public class MCSimpleAnalytics extends SimpleAnalytics {

	public final boolean isClient;

	/**
	 * Creates a new MCSimpleAnalytics instance which automatically transmits a
	 * session keep-alive event to GA every 10 seconds.
	 *
	 * @param build The build version of your minecraft mod
	 * @param gameKey The game key for your GA project
	 * @param secretKey The secret key for your GA project
	 */
	public MCSimpleAnalytics(String build, String gameKey, String secretKey) {
		super(build, gameKey, secretKey);
		isClient = FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
		if (ModConfig.analytics) {
			ActivityReportTickEventHandler.addToReportList(this);
		}
	}

	@Override
	public boolean isActive() {
		return ((isClient ? Minecraft.getMinecraft().isSnooperEnabled() : isServerSnooper()) && ModConfig.analytics);
	}

	/**
	 * We try to grab the snooper settings from the server. If they are not yet
	 * initialized, we assume true.
	 *
	 * @return
	 */
	private static boolean isServerSnooper() {
		try {
			return FMLCommonHandler.instance().getMinecraftServerInstance().isSnooperEnabled();
		} catch (NullPointerException npe) {
			return true;
		}
	}

	@Override
	public String userPrefix() {
		return isClient ? "client" : "server";
	}
}
