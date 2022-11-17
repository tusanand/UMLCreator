import javax.swing.JPanel;

public interface ConnectionDecisionHandlerInterface {

	public void setSuccessor(ConnectionDecisionHandlerInterface successor);
	
	void handleRequest(int x1, int y1, int x2, int y2, String connectionType, ClassInfo parentClass, ClassInfo childClass, JPanel panel);

	String handleRequest(ClassInfo classInfo, String message);
}
