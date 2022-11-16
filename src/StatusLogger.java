import javax.swing.JLabel;
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
	
	public void showMessage(String message) {
		removeAll();
		JLabel label = new JLabel("<html>" + message + "</html>");
		this.add(label);
		this.revalidate();
		this.repaint();
	}

}
