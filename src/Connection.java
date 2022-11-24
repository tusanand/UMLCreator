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
	private ClassInfo draggingClass;
	
	public Connection(JPanel panel) {
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
					if(childClass.getId() == key && parentClass.getId() != childClass.getId()) {
						this.connectClasses(ClassData.getInstance().getClassList().get(classInfoList.indexOf(parentClass)), childClass, parentClassConnections.get(key));
						break;
					}
				}
			}
		}
	}
	
	public void setGlobalConnectionType(String connectionType) {
		this.globalConnectionType = connectionType;
		StatusLogger.getInstance().showMessage("Changed connection type to " + connectionType);
	}
	
	private boolean populateSelectedClasses(ClassInfo classInfo) {
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
		StatusLogger.getInstance().showMessage("Parent class selected");
		selectedClasses.add(classInfo);
		return true;
	}
	
	public boolean checkIfExist(int x, int y, boolean dragAndDrop) {
		List<ClassInfo> classInfoList = ClassData.getInstance().getClassList();
		for (ClassInfo classInfo : classInfoList) {
			if (x >= classInfo.getX()-Config.BOX_WIDTH/2 && x <= classInfo.getX()+Config.BOX_WIDTH/2 && y >= classInfo.getY()-Config.BOX_HEIGHT/2 && y <= classInfo.getY()+Config.BOX_HEIGHT/2) {
				if(!dragAndDrop) {
					return this.populateSelectedClasses(classInfo);
				} else {
					this.clearSelection();
					this.setDraggingClassInfo(classInfo);
					return true;
				}
			}
		}
		return false;
	}
	
	private void setDraggingClassInfo(ClassInfo classInfo) {
		draggingClass = classInfo;
	}
	
	public ClassInfo getDraggingClassInfo() {
		return draggingClass;
	}
	
	public void clearSelection() {
		selectedClasses.clear();
	}

}
