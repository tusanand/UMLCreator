import java.util.Map;

import javax.swing.JPanel;

public class InheritanceHandler implements ConnectionDecisionHandlerInterface {

	private ConnectionDecisionHandlerInterface successor;
	
	@Override
	public void setSuccessor(ConnectionDecisionHandlerInterface successor) {
		this.successor = successor;
	}
		
	@Override
	public void handleRequest(int x1, int y1, int x2, int y2, String connectionType, ClassInfo parentClass, ClassInfo childClass, JPanel panel) {
		if(connectionType == "INHERITANCE") {
			ConnectClassInterface line = new DrawLine(panel);
			LineDecorator inherit = new DrawInheritance();
			inherit.decorate(line);
			inherit.draw(x1, y1, x2, y2);
			ClassData.getInstance().addConnectionType(parentClass, childClass, connectionType);
			StatusLogger.getInstance().showMessage("Connected classes using inheritance");
		} else if(successor != null) {
			successor.handleRequest(x1, y1, x2, y2, connectionType, parentClass, childClass, panel);
		}
		
	}

	@Override
	public String handleRequest(ClassInfo classInfo, String message) {
		//message += " extends ";
		Map<ClassInfo, String> connectionsList = classInfo.getConnections();
		for(ClassInfo key: connectionsList.keySet()) {
			if(connectionsList.get(key) == "INHERITANCE") {
				message += key.getName() + ", ";
			}
		}
		//message = message.substring(0, message.length()-2);
		message += "{ <br/>";
		if(successor == null) {
			return message;
		}
		return successor.handleRequest(classInfo, message);
	}
}
