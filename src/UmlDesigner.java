import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UmlDesigner extends JPanel implements MouseListener, Observer, MouseMotionListener {
	private Connection connection;
	
	public UmlDesigner() {
		this.addMouseListener(this);
		connection = new Connection(this);
	}
	
	private void storeandDrawClassInfo(int x, int y, String name, Integer... id) {
		Rectangle rect = new Rectangle(this);
		rect.draw(x, y, name);
		ClassInfo classInfo = new ClassInfo();
		classInfo.setX(x);
		classInfo.setY(y);
		classInfo.setName(name);
		if(id.length != 0) {
			classInfo.setId(id[0]);
		}
		ClassData.getInstance().addClass(classInfo);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		this.removeAll();
		this.updateUI();
		List<ClassInfo> classInfoList = (List<ClassInfo>) arg;
		this.drawUml(classInfoList);
		this.connection.connectClasses(classInfoList);
	}
	
	private void drawUml(List<ClassInfo> classInfoList) {
		for(ClassInfo classInfo: classInfoList) {
			this.storeandDrawClassInfo(classInfo.getX(), classInfo.getY(), classInfo.getName(), classInfo.getId());
		}
	}
	
//	private void updateClassList(ClassInfo updatedClass) {
//		for(ClassInfo classInfo: ClassData.getInstance().getClassList()) {
//			if(classInfo.getId() == updatedClass.getId()) {
//				classInfo.setX(updatedClass.getX());
//				classInfo.setY(updatedClass.getY());
//			}
//		}
//		ClassData.getInstance().clearData();
//		this.drawUml(ClassData.getInstance().getClassList());
//		this.connection.connectClasses(ClassData.getInstance().getClassList());
//	}
 	
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(connection.checkIfExist(x, y, false)) {
			return;
		}
		connection.clearSelection();
		String name=JOptionPane.showInputDialog(this, "Enter Name");
		if(name == null || name.equals("")) {
			return;
		}
		this.storeandDrawClassInfo(x, y, name);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
//		ClassInfo classInfo = connection.getDraggingClassInfo();
//		classInfo.setX(e.getX());
//		classInfo.setY(e.getY());
//		this.updateClassList(classInfo);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//connection.checkIfExist(e.getX(), e.getY(), true);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
