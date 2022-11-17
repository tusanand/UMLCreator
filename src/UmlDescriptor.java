import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings({ "serial", "deprecation" })
public class UmlDescriptor extends JPanel implements Observer {
	ConnectionDecisionHandlerInterface associationHandler;
	ConnectionDecisionHandlerInterface inheritanceHandler;
	ConnectionDecisionHandlerInterface compositionHandler;
	
	public UmlDescriptor() {
		associationHandler = new AssociationHandler();
		inheritanceHandler = new InheritanceHandler();
		compositionHandler = new CompositionHandler();
		inheritanceHandler.setSuccessor(compositionHandler);
		compositionHandler.setSuccessor(associationHandler);
	}

	@Override
	public void update(Observable o, Object arg) {
		List<ClassInfo> classList = ((ClassData)o).getClassList();
		String displayMessage = "";
		for (ClassInfo classInfo : classList) {
			displayMessage += "class " + classInfo.getName();
			if(classInfo.getConnections().size() > 0) {
				displayMessage += this.addDependencies(classInfo);
			}
			displayMessage += "<br/>}<br/>";
		}
		this.showClassDescription(displayMessage);
	}
	
	public String addDependencies(ClassInfo classInfo) {
		return inheritanceHandler.handleRequest(classInfo, "");
	}
	
	private void showClassDescription(String message) {
		removeAll();
		JLabel label = new JLabel("<html>" + message + "</html>");
		this.add(label);
		this.revalidate();
		this.repaint();
		StatusLogger.getInstance().showMessage("Class Added");
	}

}
