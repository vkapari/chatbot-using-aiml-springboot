package demo.project.chatbot.model;

/**
 * Specialized timer function for program instrumentation
 */
public class Timer {
	private long startTimeMillis;

	public Timer() {
		start();
	}

	public void start() {
		startTimeMillis = System.currentTimeMillis();
	}

	public long elapsedTimeMillis() {
		return System.currentTimeMillis() - startTimeMillis + 1;
	}

	public long elapsedRestartMs() {
		long ms = System.currentTimeMillis() - startTimeMillis + 1;
		start();
		return ms;
	}

	public float elapsedTimeSecs() {
		return elapsedTimeMillis() / 1000F;
	}

	public float elapsedTimeMins() {
		return elapsedTimeSecs() / 60F;
	}
}