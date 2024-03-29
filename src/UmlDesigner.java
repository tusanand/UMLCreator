import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

/**
 * This class creates the diagram.
 */
@SuppressWarnings("serial")
public class UmlDesigner extends JPanel implements MouseListener, Observer, MouseMotionListener {
	private Connection connection;
	private boolean isDragged = false;

	public UmlDesigner() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		connection = new Connection(this);
	}

	/**
	 * This method stores the information of the class.
	 */
	private void storeandDrawClassInfo(int x, int y, String name, Integer... id) {
		DrawRectangleInterface rect = new Rectangle(this);
		rect.draw(x, y, name);
		ClassInfo classInfo = new ClassInfo();
		classInfo.setX(x);
		classInfo.setY(y);
		classInfo.setName(name);
		if (id.length != 0) {
			classInfo.setId(id[0]);
		}
		ClassData.getInstance().addClass(classInfo);
	}


	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		this.clearScreen();
		List<ClassInfo> classInfoList = (List<ClassInfo>) arg;
		this.drawUml(classInfoList);
		this.connection.connectClasses(classInfoList);
	}

	/**
	 * This method draws the class on the view
	 */
	private void drawUml(List<ClassInfo> classInfoList) {
		for (ClassInfo classInfo : classInfoList) {
			this.storeandDrawClassInfo(classInfo.getX(), classInfo.getY(), classInfo.getName(), classInfo.getId());
		}
		StatusLogger.getInstance().showMessage("Classes updated on screen");
	}
	
	/**
	 * clears the stored data
	 */
	public void clearData() {
		ClassData.getInstance().clearData();
		this.clearScreen();
	}

	/**
	 * clears the screen to redraw
	 */
	private void clearScreen() {
		Graphics graphics = getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, Config.UML_WIDTH, Config.UML_DESCRIPTOR_HEIGHT);

		graphics.setColor(Color.BLACK);
		graphics.drawRect(0, 0, Config.UML_WIDTH-2, Config.UML_DESCRIPTOR_HEIGHT-2);
	}

	/**
	 *  updates class x and y coordinate that is being dragged
	 */
	private void updateClassList(ClassInfo updatedClass) {
		this.clearScreen();
		for (ClassInfo classInfo : ClassData.getInstance().getClassList()) {
			if (classInfo.getId() == updatedClass.getId()) {
				classInfo.setX(updatedClass.getX());
				classInfo.setY(updatedClass.getY());
				break;
			}
		}
		List<ClassInfo> classInfoList = new ArrayList<ClassInfo>(ClassData.getInstance().getClassList());
		ClassData.getInstance().clearData();
		this.drawUml(classInfoList);
		this.connection.connectClasses(classInfoList);
	}

	public Connection getConnectionInstance() {
		return connection;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (connection.checkIfExist(x, y, false)) {
			return;
		}
		connection.clearSelection();
		String name = JOptionPane.showInputDialog(this, "Enter Name");
		if (name == null || name.equals("")) {
			return;
		}
		this.storeandDrawClassInfo(x, y, name);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!isDragged) {
			return;
		}
		isDragged = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method implements the drag and drop functionality
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (!isDragged && !connection.checkIfExist(e.getX(), e.getY(), true)) {
			return;
		}
		isDragged = true;
		ClassInfo classInfo = connection.getDraggingClassInfo();
		classInfo.setX(e.getX());
		classInfo.setY(e.getY());
		this.updateClassList(classInfo);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
