import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class InitializeFrame extends ButtonActions {
	public InitializeFrame() {
		this.initialize();
		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	private void initialize() {
		this.setTitle("UML DESIGNER");
		this.setBounds(Config.WINDOW_START_X, Config.WINDOW_START_Y, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		// TODO: Add vertical scroll
		UmlDescriptor umlDescriptor = new UmlDescriptor();
		umlDescriptor.setBackground(Color.white);
		umlDescriptor.setBounds(Config.PADDING, Config.PADDING, Config.DESCRIPTOR_WIDTH,
				Config.UML_DESCRIPTOR_HEIGHT);
		umlDescriptor.setBorder(BorderFactory.createLineBorder(Color.black));
		this.getContentPane().add(umlDescriptor);

		umlDesigner = new UmlDesigner();
		umlDesigner.setBackground(Color.white);
		umlDesigner.setBounds(Config.PADDING + Config.DESCRIPTOR_WIDTH + Config.OFFSET, Config.PADDING,
				Config.UML_WIDTH,
				Config.UML_DESCRIPTOR_HEIGHT);
		umlDesigner.setBorder(BorderFactory.createLineBorder(Color.black));
		this.getContentPane().add(umlDesigner);

		fileHandler = new FileHandler(umlDesigner);
		fileHandler.addObserver(umlDesigner);

		menuBar = new JMenuBar();

		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
		newMenuItem.addActionListener(this);
		fileMenu.add(newMenuItem);

		saveMenuItem = new JMenuItem("Save As", KeyEvent.VK_S);
		saveMenuItem.addActionListener(this);
		fileMenu.add(saveMenuItem);

		loadMenuItem = new JMenuItem("Load Data", KeyEvent.VK_L);
		loadMenuItem.addActionListener(this);
		fileMenu.add(loadMenuItem);

		connectionSetterMenuItem = new JMenuItem("Connection Type", KeyEvent.VK_C);
		connectionSetterMenuItem.addActionListener(this);
		fileMenu.add(connectionSetterMenuItem);

		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);

		about = new JMenuItem("About", KeyEvent.VK_A);
		about.addActionListener(this);
		helpMenu.add(about);

		this.setJMenuBar(menuBar);

		associationBtn = new JRadioButton("ASSOCIATION");
		associationBtn.setMnemonic(KeyEvent.VK_A);
		associationBtn.setActionCommand("ASSOCIATION");
		associationBtn.setSelected(true);

		inheritanceBtn = new JRadioButton("INHERITANCE");
		inheritanceBtn.setMnemonic(KeyEvent.VK_I);
		inheritanceBtn.setActionCommand("INHERITANCE");
		inheritanceBtn.setSelected(false);

		compositionBtn = new JRadioButton("COMPOSITION");
		compositionBtn.setMnemonic(KeyEvent.VK_D);
		compositionBtn.setActionCommand("COMPOSITION");
		compositionBtn.setSelected(false);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(associationBtn);
		group.add(inheritanceBtn);
		group.add(compositionBtn);

		chooseConnectionType = new JPanel();
		chooseConnectionType.add(associationBtn);
		chooseConnectionType.add(inheritanceBtn);
		chooseConnectionType.add(compositionBtn);

		// Register a listener for the radio buttons.
		associationBtn.addActionListener(this);
		inheritanceBtn.addActionListener(this);
		compositionBtn.addActionListener(this);

		teamInfoPanel = new JPanel();
		String info = "Tushar Anand - 1219436270 <br/>"
				+ "Vaibhav Somani - ASU ID <br/>"
				+ "Madhavan Raja - ASU ID <br/>"
				+ "Kyle - ASU ID <br/>"
				+ "Amber - ASU ID <br/>";
		JLabel teamInfo = new JLabel("<html>" + info + "</html>");
		teamInfoPanel.add(teamInfo);

		JPanel loggerPanel = new JPanel();
		loggerPanel.setBackground(Color.white);
		loggerPanel.setBounds(
				Config.PADDING,
				2 * Config.PADDING + Config.UML_DESCRIPTOR_HEIGHT,
				Config.WINDOW_WIDTH - 3 * Config.PADDING,
				Config.LOGGER_HEIGHT);
		loggerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		StatusLogger.getInstance().setPanel(loggerPanel);
		this.getContentPane().add(loggerPanel);

		ClassData.getInstance().addObserver(umlDescriptor);
	}
}
