import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class InitializeFrame extends ButtonActions {
	public InitializeFrame() {
		this.initialize();
		this.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	private void initialize() {
		this.setTitle("UML DESIGNER");
	    this.setBounds(10, 10, 500,500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.getContentPane().setLayout(new GridLayout(2, 2));
	    
	    UmlDescriptor umlDescriptor = new UmlDescriptor();
	    umlDescriptor.setBackground(Color.white);
	    umlDescriptor.setBorder(BorderFactory.createLineBorder(Color.black));
	    this.getContentPane().add(umlDescriptor);
	    
	    UmlDesigner umlDesigner = new UmlDesigner();
	    umlDesigner.setBackground(Color.white);
	    umlDesigner.setBorder(BorderFactory.createLineBorder(Color.black));
	    this.getContentPane().add(umlDesigner);
	    
	    this.getContentPane().add(StatusLogger.getInstance());    
	    ClassData.getInstance().addObserver(umlDescriptor);
	}
}
