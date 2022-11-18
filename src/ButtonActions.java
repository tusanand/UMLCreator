import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ButtonActions extends JFrame implements ActionListener {
	protected JButton connectionTypeSetter;
	
	public ButtonActions() {

	}

	/**
	 * This method implements the button trigger.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.connectionTypeSetter) {
			
		}
	}

}
