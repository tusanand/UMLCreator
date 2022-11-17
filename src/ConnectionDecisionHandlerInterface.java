import java.util.List;

import javax.swing.JPanel;

public interface ConnectionDecisionHandlerInterface {

	public void setSuccessor(ConnectionDecisionHandlerInterface successor);
	
	public void handleRequest(int x1, int y1, int x2, int y2, String connectionType, ClassInfo parentClass, ClassInfo childClass, JPanel panel);

	public List<String> handleRequest(String className,String connectionType, List<String> displayMessage);
}
