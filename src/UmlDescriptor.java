import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings({ "serial", "deprecation" })
public class UmlDescriptor extends JPanel implements Observer {
	private ConnectionDecisionHandlerInterface associationHandler;
	private ConnectionDecisionHandlerInterface inheritanceHandler;
	private ConnectionDecisionHandlerInterface compositionHandler;
	private List<ClassInfo> classList;
	private JTextArea textArea;
	
	UmlDescriptor() {
		associationHandler = new AssociationHandler();
		inheritanceHandler = new InheritanceHandler();
		compositionHandler = new CompositionHandler();
		associationHandler.setSuccessor(inheritanceHandler);
		inheritanceHandler.setSuccessor(compositionHandler);
		textArea = new JTextArea(Config.TEXTAREA_ROWS, Config.TEXT_AREA_COLS);
		textArea.setMargin(new Insets(
				Config.PADDING,
				Config.PADDING,
				Config.PADDING,
				Config.PADDING));
		textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(textArea);
		this.add(scroll);
	}

	@Override
	public void update(Observable o, Object arg) {
		classList = ((ClassData)o).getClassList();
		String displayMessage = "";
		for (ClassInfo classInfo : classList) {
			displayMessage += "class " + classInfo.getName() + " ";
			displayMessage += this.addDependencies(classInfo);
			displayMessage += "}\n";
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
		message += " { \n";
		if(connectedClasses.get(2).length() > 0) {
			message += "    " + connectedClasses.get(2).substring(0, connectedClasses.get(2).length()-2) + " \n";
		}
		if(connectedClasses.get(0).length() > 0) {
			message += "    method() { \n" + "        " + connectedClasses.get(0).substring(0, connectedClasses.get(0).length()-2) + "\n" + "    }" + "\n";
		}
		return message;
	}
	
	private void showClassDescription(String message) {
		textArea.setText(message);
		StatusLogger.getInstance().showMessage("Class Added");
	}

}
