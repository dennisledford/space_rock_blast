/**
 * 
 */
package util;

import org.lwjgl.Sys;

/**
 * @author dennisledford
 * 
 */
public class TimeHelper {

	private long lastFrame;

	public TimeHelper() {
		this.lastFrame = getTime();
	}

	public long getLastFrame() {
		return lastFrame;
	}

	public void setLastFrame(long lastFrame) {
		this.lastFrame = lastFrame;
	}

	public long getTime() {
		return ((Sys.getTime() * 1000) / Sys.getTimerResolution());
	}

	public int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = currentTime;
		return delta;
	}
}
