import java.util.List;

import javax.swing.JPanel;

public class InheritanceHandler implements ConnectionDecisionHandlerInterface {

	private ConnectionDecisionHandlerInterface successor;

	@Override
	public void setSuccessor(ConnectionDecisionHandlerInterface successor) {
		this.successor = successor;
	}

	@Override
	public void handleRequest(String connectionType, ClassInfo parentClass, ClassInfo childClass, JPanel panel) {
		if (connectionType.equals("INHERITANCE")) {
			ConnectClassInterface line = new DrawLine(panel);
			LineDecorator inherit = new DrawInheritance(panel);
			inherit.decorate(line);
			inherit.draw(parentClass.getX(), parentClass.getY(), childClass.getX(), childClass.getY());
			ClassData.getInstance().addConnectionType(parentClass, childClass, connectionType);
			StatusLogger.getInstance().showMessage("Connected classes using inheritance");
		} else if (successor != null) {
			successor.handleRequest(connectionType, parentClass, childClass, panel);
		}

	}

	@Override
	public List<String> handleRequest(String className, String connectionType, List<String> message) {
		String msg = message.get(1);
		if (connectionType.equals("INHERITANCE")) {
			msg += className + ", ";
			message.set(1, msg);
		} else if (successor != null) {
			return successor.handleRequest(className, connectionType, message);
		}
		return message;
	}
}
