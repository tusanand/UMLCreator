import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings({ "serial", "deprecation" })
public class UmlDescriptor extends JPanel implements Observer {
	ConnectionDecisionHandlerInterface associationHandler;
	ConnectionDecisionHandlerInterface inheritanceHandler;
	ConnectionDecisionHandlerInterface compositionHandler;
	List<ClassInfo> classList;
	
	UmlDescriptor() {
		associationHandler = new AssociationHandler();
		inheritanceHandler = new InheritanceHandler();
		compositionHandler = new CompositionHandler();
		associationHandler.setSuccessor(inheritanceHandler);
		inheritanceHandler.setSuccessor(compositionHandler);
	}

	@Override
	public void update(Observable o, Object arg) {
		classList = ((ClassData)o).getClassList();
		String displayMessage = "";
		for (ClassInfo classInfo : classList) {
			displayMessage += "class " + classInfo.getName() + " ";
			displayMessage += this.addDependencies(classInfo);
			displayMessage += "<br/>}<br/>";
		}
		this.showClassDescription(displayMessage);
	}
	
	public String addDependencies(ClassInfo classInfo) {
		List<String> connectedClasses = new ArrayList<String>();
		connectedClasses.add(""); //association classes
		connectedClasses.add(""); //inheritance classes
		connectedClasses.add(""); //composition classes
		Map<Integer, String> connectionsList = classInfo.getConnections();
		for(Integer key: connectionsList.keySet()) {
			for (ClassInfo info : classList) {
				if(info.getId() == key) {
					connectedClasses = associationHandler.handleRequest(info.getName(), connectionsList.get(key), connectedClasses);
				}
			}
		}
		return formDisplayMessage(connectedClasses);
	}
	
	private String formDisplayMessage(List<String> connectedClasses) {
		String message = "";
		if(connectedClasses.get(1).length() > 0) {
			message += "extends " + connectedClasses.get(1).substring(0, connectedClasses.get(1).length()-2);
		}
		message += "{ <br/>";
		if(connectedClasses.get(2).length() > 0) {
			message += connectedClasses.get(2).substring(0, connectedClasses.get(2).length()-2) + " <br/>";
		}
		if(connectedClasses.get(0).length() > 0) {
			message += "method() { <br/>" + connectedClasses.get(0).substring(0, connectedClasses.get(0).length()-2) + "<br/> } <br/>";
		}
		return message;
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
