import java.util.List;

import javax.swing.JPanel;

public interface ConnectionDecisionHandlerInterface {

	public void setSuccessor(ConnectionDecisionHandlerInterface successor);
	
	public void handleRequest(String connectionType, ClassInfo parentClass, ClassInfo childClass, JPanel panel);

	public List<String> handleRequest(String className,String connectionType, List<String> displayMessage);
}
