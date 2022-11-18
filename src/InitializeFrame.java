import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
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
	    this.setBounds(10, 10, 550,700);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.getContentPane().setLayout(null);
	    
	    UmlDescriptor umlDescriptor = new UmlDescriptor();
	    umlDescriptor.setBackground(Color.white);
	    umlDescriptor.setBounds(5, 50, 150, 440);
	    umlDescriptor.setBorder(BorderFactory.createLineBorder(Color.black));
	    this.getContentPane().add(umlDescriptor);
	    
	    umlDesigner = new UmlDesigner();
	    umlDesigner.setBackground(Color.white);
	    umlDesigner.setBounds(160, 50, 350, 440);
	    umlDesigner.setBorder(BorderFactory.createLineBorder(Color.black));
	    this.getContentPane().add(umlDesigner);
	    
	    JPanel loggerPanel = new JPanel();
	    loggerPanel.setBackground(Color.white);
	    loggerPanel.setBounds(5, 500, 505, 100);
	    loggerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	    StatusLogger.getInstance().setPanel(loggerPanel);
	    this.getContentPane().add(loggerPanel);
	    
	    connectionTypeSetter = new JButton("Set Connection Type");
	    connectionTypeSetter.setFont(new Font("Iosevka", Font.BOLD, 12));
	    connectionTypeSetter.setBounds(5, 20, 170, 20);
	    connectionTypeSetter.addActionListener(this);
	    this.getContentPane().add(connectionTypeSetter);
	    
	    associationBtn = new JRadioButton("ASSOCIATION");
	    associationBtn.setMnemonic(KeyEvent.VK_B);
	    associationBtn.setActionCommand("ASSOCIATION");
	    associationBtn.setSelected(true);

	    inheritanceBtn = new JRadioButton("INHERITANCE");
	    inheritanceBtn.setMnemonic(KeyEvent.VK_B);
	    inheritanceBtn.setActionCommand("INHERITANCE");
	    inheritanceBtn.setSelected(false);
	    
	    compositionBtn = new JRadioButton("COMPOSITION");
	    compositionBtn.setMnemonic(KeyEvent.VK_B);
	    compositionBtn.setActionCommand("COMPOSITION");
	    compositionBtn.setSelected(false);

	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(associationBtn);
	    group.add(inheritanceBtn);
	    group.add(compositionBtn);
	    
	    chooseConnectionType = new JPanel();
	    chooseConnectionType.add(associationBtn);
	    chooseConnectionType.add(inheritanceBtn);
	    chooseConnectionType.add(compositionBtn);

	    //Register a listener for the radio buttons.
	    associationBtn.addActionListener(this);
	    inheritanceBtn.addActionListener(this);
	    compositionBtn.addActionListener(this);
	    
	    ClassData.getInstance().addObserver(umlDescriptor);
	}
}
