import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class ButtonActions extends JFrame implements ActionListener {
	protected JPanel chooseConnectionType;
	protected JRadioButton associationBtn;
	protected JRadioButton inheritanceBtn;
	protected JRadioButton compositionBtn;

	protected JMenuBar menuBar;

	protected JMenu fileMenu;
	protected JMenuItem newMenuItem;
	protected JMenuItem saveMenuItem;
	protected JMenuItem loadMenuItem;
	protected JMenuItem connectionSetterMenuItem;
	protected JMenuItem exitMenuItem;

	protected JMenu helpMenu;

	protected UmlDesigner umlDesigner;

	public ButtonActions() {

	}

	/**
	 * This method implements the button trigger.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.connectionSetterMenuItem) {
			JOptionPane.showMessageDialog(this, chooseConnectionType, "Connection Type",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == this.associationBtn) {
			umlDesigner.connection.setGlobalConnectionType("ASSOCIATION");
		} else if (e.getSource() == this.inheritanceBtn) {
			umlDesigner.connection.setGlobalConnectionType("INHERITANCE");
		} else if (e.getSource() == this.compositionBtn) {
			umlDesigner.connection.setGlobalConnectionType("COMPOSITION");
		} else if (e.getSource() == this.saveMenuItem) {
			FileHandler fileHandler = new FileHandler();
			fileHandler.selectSaveFile("Save");
		}
	}

}
