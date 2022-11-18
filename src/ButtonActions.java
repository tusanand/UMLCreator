import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class ButtonActions extends JFrame implements ActionListener {
	protected JButton connectionTypeSetter;
	protected JPanel chooseConnectionType;
	protected JRadioButton associationBtn;
	protected JRadioButton inheritanceBtn;
	protected JRadioButton compositionBtn;
	protected UmlDesigner umlDesigner;
	
	public ButtonActions() {

	}

	/**
	 * This method implements the button trigger.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.connectionTypeSetter) {
			JOptionPane.showMessageDialog(this, chooseConnectionType, "Connection type", JOptionPane.INFORMATION_MESSAGE);
		} else if(e.getSource() == this.associationBtn) {
			umlDesigner.connection.setGlobalConnectionType("ASSOCIATION");
		} else if(e.getSource() == this.inheritanceBtn) {
			umlDesigner.connection.setGlobalConnectionType("INHERITANCE");
		} else if(e.getSource() == this.compositionBtn) {
			umlDesigner.connection.setGlobalConnectionType("COMPOSITION");
		}
	}

}
