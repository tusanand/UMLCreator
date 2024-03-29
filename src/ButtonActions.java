import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * This class invokes all the button actions
 *
 */
@SuppressWarnings("serial")
public class ButtonActions extends JFrame implements ActionListener {
	protected JPanel chooseConnectionType;
	protected JRadioButton associationBtn;
	protected JRadioButton inheritanceBtn;
	protected JRadioButton compositionBtn;
	protected JPanel teamInfoPanel;

	protected JMenuBar menuBar;
	protected JMenu fileMenu;
	protected JMenuItem saveMenuItem;
	protected JMenuItem newMenuItem;
	protected JMenuItem loadMenuItem;
	protected JMenuItem connectionSetterMenuItem;
	protected JMenu helpMenu;
	protected JMenuItem about;
	protected UmlDesigner umlDesigner;
	protected UmlDescriptor umlDescriptor;
	protected FileHandler fileHandler;

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
			umlDesigner.getConnectionInstance().setGlobalConnectionType("ASSOCIATION");
		} else if (e.getSource() == this.inheritanceBtn) {
			umlDesigner.getConnectionInstance().setGlobalConnectionType("INHERITANCE");
		} else if (e.getSource() == this.compositionBtn) {
			umlDesigner.getConnectionInstance().setGlobalConnectionType("COMPOSITION");
		} else if (e.getSource() == this.newMenuItem) {
			umlDesigner.clearData();
		} else if (e.getSource() == this.saveMenuItem) {
			fileHandler.selectSaveFile("Save");
		} else if (e.getSource() == this.loadMenuItem) {
			fileHandler.selectSaveFile("Load");
		} else if (e.getSource() == this.about) {
			JOptionPane.showMessageDialog(this, teamInfoPanel, "Team Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
