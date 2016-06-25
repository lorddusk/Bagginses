/**
 * (C) 2015 NPException
 */
package nl.lang2619.bagginses.gameanalytics.events;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import nl.lang2619.bagginses.gameanalytics.Analytics;


/**
 * Base event class. Contains values that are mandatory for all event types.
 *
 * @author NPException
 *
 */
public abstract class GAEvent {
	private static Gson GSON; // only initialized if necessary

	public final transient Analytics.KeyPair keyPair;

	@SerializedName("user_id")
	private final String userID;

	@SerializedName("session_id")
	private final String sessionID;

	@SerializedName("build")
	private final String build;

	GAEvent(Analytics an) {
		keyPair = an.keyPair();
		userID = an.getUserID();
		sessionID = an.getSessionID();
		build = an.build();
	}

	public abstract String category();

	private transient String toString;

	@Override
	public String toString() {
		if (toString == null) {
			String tmp;
			try {
				if (GSON == null) {
					GSON = new Gson();
				}
				tmp = GSON.toJson(this);
			} catch (Exception ex) {
				tmp = super.toString();
			}
			tmp = tmp + " + " + String.valueOf(keyPair);
			toString = tmp;
		}
		return toString;
	}
}
