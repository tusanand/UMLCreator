public class StatusLogger {
	private static StatusLogger statusLogger;
	
	private StatusLogger() {
	}
	
	public static StatusLogger getInstance() {
		if(statusLogger == null) {
			statusLogger = new StatusLogger();
		}
		return statusLogger;
	}

}
