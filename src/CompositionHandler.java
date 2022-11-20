import java.util.List;

import javax.swing.JPanel;

public class CompositionHandler implements ConnectionDecisionHandlerInterface {

	private ConnectionDecisionHandlerInterface successor;

	@Override
	public void setSuccessor(ConnectionDecisionHandlerInterface successor) {
		this.successor = successor;
	}

	@Override
	public void handleRequest(String connectionType, ClassInfo parentClass, ClassInfo childClass, JPanel panel) {
		if (connectionType.equals("COMPOSITION")) {
			ConnectClassInterface line = new DrawLine(panel);
			LineDecorator compose = new DrawComposition(panel);
			compose.decorate(line);
			compose.draw(parentClass.getX(), parentClass.getY(), childClass.getX(), childClass.getY());
			ClassData.getInstance().addConnectionType(parentClass, childClass, connectionType);
			StatusLogger.getInstance().showMessage("Connected classes using composition");
		} else if (successor != null) {
			successor.handleRequest(connectionType, parentClass, childClass, panel);
		}

	}

	@Override
	public List<String> handleRequest(String className, String connectionType, List<String> message) {
		String msg = message.get(2);
		if (connectionType.equals("COMPOSITION")) {
			msg += className + ", ";
			message.set(2, msg);
		} else if (successor != null) {
			return successor.handleRequest(className, connectionType, message);
		}
		return message;
	}
}
