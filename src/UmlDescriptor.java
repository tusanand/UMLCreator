import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UmlDescriptor extends JPanel implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		List<ClassInfo> classList = ((ClassData)o).getClassList();
		String displayMessage = "";
		//TODO: change this logic to chain of responsibility
		for (ClassInfo classInfo : classList) {
			displayMessage += "class " + classInfo.getName() + "{<br/>";
			if(classInfo.getConnections().size() > 0) {
				displayMessage = this.addDependencies(classInfo, displayMessage);
			}
			displayMessage += "}<br/>";
		}
		this.showClassDescription(displayMessage);
	}
	
	public String addDependencies(ClassInfo classInfo, String displayMessage) {
		//TODO: Add logic to concatenate string for the different types of dependencies
		return displayMessage;
	}
	
	private void showClassDescription(String message) {
		removeAll();
		JLabel label = new JLabel("<html>" + message + "</html>");
		this.add(label);
		this.revalidate();
		this.repaint();
		StatusLogger.getInstance().showMessage("Class Added");
	}

}
