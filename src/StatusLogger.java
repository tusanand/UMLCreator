import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusLogger {
	private static StatusLogger statusLogger;
	private JPanel panel;
	
	private StatusLogger() {
	}
	
	public static StatusLogger getInstance() {
		if(statusLogger == null) {
			statusLogger = new StatusLogger();
		}
		return statusLogger;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	public void showMessage(String message) {
		panel.removeAll();
		JLabel label = new JLabel("<html>" + message + "</html>");
		panel.add(label);
		panel.revalidate();
		panel.repaint();
	}

}
