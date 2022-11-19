import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UmlDesigner extends JPanel implements MouseListener, Observer {
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
		List<ClassInfo> classInfoList = (List<ClassInfo>) arg;
		this.drawUml(classInfoList);
		this.connection.connectClasses(classInfoList);
	}
	
	private void drawUml(List<ClassInfo> classInfoList) {
		for(ClassInfo classInfo: classInfoList) {
			this.storeandDrawClassInfo(classInfo.getX(), classInfo.getY(), classInfo.getName(), classInfo.getId());
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(connection.checkIfExist(x, y)) {
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
