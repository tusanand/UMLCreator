import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class InitializeFrame extends ButtonActions {
	public InitializeFrame() {
		this.initialize();
		this.setVisible(true);
	}
	
	private void initialize() {
		this.setTitle("UML DESIGNER");
	    this.setBounds(10, 10, 500,500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.getContentPane().setLayout(new GridLayout(1, 2));
	    
	    UmlDescriptor umlDescriptor = new UmlDescriptor();
	    umlDescriptor.setBackground(Color.white);
	    umlDescriptor.setBorder(BorderFactory.createLineBorder(Color.black));
	    this.getContentPane().add(umlDescriptor);
	    
	    UmlDesigner umlDesigner = new UmlDesigner();
	    umlDesigner.setBackground(Color.white);
	    umlDesigner.setBorder(BorderFactory.createLineBorder(Color.black));
	    this.getContentPane().add(umlDesigner);
	    
	    ClassData.getInstance().addObserver(umlDescriptor);
	}
}
