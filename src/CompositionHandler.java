import java.util.List;

import javax.swing.JPanel;

public class CompositionHandler implements ConnectionDecisionHandlerInterface {

	private ConnectionDecisionHandlerInterface successor;
	
	@Override
	public void setSuccessor(ConnectionDecisionHandlerInterface successor) {
		this.successor = successor;
	}
		
	@Override
	public void handleRequest(int x1, int y1, int x2, int y2, String connectionType, ClassInfo parentClass, ClassInfo childClass, JPanel panel) {
		if(connectionType == "COMPOSITION") {
			ConnectClassInterface line = new DrawLine(panel);
			LineDecorator associate = new DrawAssociation();
			LineDecorator compose = new DrawComposition();
			associate.decorate(line);
			compose.decorate(associate);
			compose.draw(x1, y1, x2, y2);
			ClassData.getInstance().addConnectionType(parentClass, childClass, connectionType);
			StatusLogger.getInstance().showMessage("Connected classes using composition");
		} else if(successor != null) {
			successor.handleRequest(x1, y1, x2, y2, connectionType, parentClass, childClass, panel);
		}
		
	}

	@Override
	public List<String> handleRequest(String className, String connectionType, List<String> message) {
		String msg = message.get(2);
		if(connectionType == "COMPOSITION") {
			msg += className + ", ";
			message.set(2, msg);
		} else if(successor != null) {
			return successor.handleRequest(className, connectionType, message);
		}
		return message;
	}
}
