import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	    
	    UmlDesigner umlDesigner = new UmlDesigner();
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
	    
	    ClassData.getInstance().addObserver(umlDescriptor);
	}
}
