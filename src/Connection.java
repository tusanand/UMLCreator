import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class Connection {
	private List<ClassInfo> selectedClasses;
	private JPanel panel;
	private ConnectionDecisionHandlerInterface associationHandler;
	private ConnectionDecisionHandlerInterface inheritanceHandler;
	private ConnectionDecisionHandlerInterface compositionHandler;
	private String globalConnectionType = "ASSOCIATION"; //default value
	
	Connection(JPanel panel) {
		this.panel = panel;
		selectedClasses = new ArrayList<ClassInfo>();
		associationHandler = new AssociationHandler();
		inheritanceHandler = new InheritanceHandler();
		compositionHandler = new CompositionHandler();
		associationHandler.setSuccessor(inheritanceHandler);
		inheritanceHandler.setSuccessor(compositionHandler);
	}
	
	private void connectClasses(ClassInfo parentClass, ClassInfo childClass, String connectionType) {
		associationHandler.handleRequest(
				connectionType, 
				parentClass, 
				childClass,
				panel);
	}
	
	public void connectClasses(List<ClassInfo> classInfoList) {
		for(ClassInfo parentClass: classInfoList) {
			Map<Integer, String> parentClassConnections = parentClass.getConnections();
			for(Integer key: parentClassConnections.keySet()) {
				for(ClassInfo childClass: ClassData.getInstance().getClassList()) {
					if(childClass.getId() == key) {
						this.connectClasses(ClassData.getInstance().getClassList().get(classInfoList.indexOf(parentClass)), childClass, parentClassConnections.get(key));
						break;
					}
				}
			}
		}
	}
	
	public void setGlobalConnectionType(String connectionType) {
		this.globalConnectionType = connectionType;
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
					this.connectClasses(
							selectedClasses.get(0), 
							selectedClasses.get(1),
							globalConnectionType);
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
