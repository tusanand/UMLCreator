import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatusLogger extends JPanel {
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
