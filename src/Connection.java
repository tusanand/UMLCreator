import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Connection {
	List<ClassInfo> selectedClasses;
	JPanel panel;
	ConnectionDecisionHandlerInterface associationHandler;
	ConnectionDecisionHandlerInterface inheritanceHandler;
	ConnectionDecisionHandlerInterface compositionHandler;
	
	Connection(JPanel panel) {
		this.panel = panel;
		selectedClasses = new ArrayList<ClassInfo>();
		associationHandler = new AssociationHandler();
		inheritanceHandler = new InheritanceHandler();
		compositionHandler = new CompositionHandler();
		associationHandler.setSuccessor(inheritanceHandler);
		inheritanceHandler.setSuccessor(compositionHandler);
	}
	
	public boolean checkIfExist(int x, int y) {
		List<ClassInfo> classInfoList = ClassData.getInstance().getClassList();
		for (ClassInfo classInfo : classInfoList) {
			if (x >= classInfo.getX()-35 && x <= classInfo.getX()+35 && y >= classInfo.getY()-25 && y <= classInfo.getY()+25) {
				if(selectedClasses.contains(classInfo)) {
					return true;
				}
				if(selectedClasses.size() == 1) {
					selectedClasses.add(classInfo);
					associationHandler.handleRequest(
							selectedClasses.get(0).getX(), 
							selectedClasses.get(0).getY(), 
							selectedClasses.get(1).getX(), 
							selectedClasses.get(1).getY(), 
							"ASSOCIATION", 
							selectedClasses.get(0), 
							selectedClasses.get(1),
							panel);
					this.clearSelection();
					return true;
				}
				selectedClasses.add(classInfo);
				return true;
			}
		}
		return false;
	}
	
	public void clearSelection() {
		selectedClasses.clear();
	}

}
