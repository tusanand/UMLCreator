import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UmlDesigner extends JPanel implements MouseListener {
	Connection connection;
	
	public UmlDesigner() {
		this.addMouseListener(this);
		connection = new Connection(this);
	}
	
	private void storeClassInfo(int x, int y, String name) {
		ClassInfo classInfo = new ClassInfo();
		classInfo.setX(x);
		classInfo.setY(y);
		classInfo.setName(name);
		ClassData.getInstance().addClass(classInfo);
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
		Rectangle rect = new Rectangle(this);
		rect.draw(x, y, name);
		this.storeClassInfo(x, y, name);
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
