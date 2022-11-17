import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Connection {
	List<ClassInfo> selectedClasses;
	JPanel panel;
	
	Connection(JPanel panel) {
		this.panel = panel;
		selectedClasses = new ArrayList<ClassInfo>();
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
					
					ConnectClassInterface line = new DrawLine(panel);
					//TODO: implement chain of responsibility to add decoration on line
					//current implementation is for association, move this to different classes as per chain of responsibility
					LineDecorator associate = new DrawAssociation();
					associate.decorate(line);
					associate.draw(selectedClasses.get(0).getX(), selectedClasses.get(0).getY(), selectedClasses.get(1).getX(), selectedClasses.get(1).getY());
					ClassData.getInstance().addConnectionType(classInfo, "ASSOCIATION");
					StatusLogger.getInstance().showMessage("Connected classes using association");
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
