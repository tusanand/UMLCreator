import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * This class is a singleton storage for saving data.
 *
 */
@SuppressWarnings("deprecation")
public class ClassData extends Observable {
	private static ClassData classData;
	private List<ClassInfo> classInfoList;
	private int idGenerator;
	
	private ClassData() {
		classInfoList = new ArrayList<ClassInfo>();
		idGenerator = 1;
	}
	
	private void notifyListeners() {
		setChanged();
		notifyObservers();
	}
	
	public static ClassData getInstance() {
		if(classData == null) {
			classData = new ClassData();
		}
		return classData;
	}
	
	/**
	 * This method saves classes created into our data structure. 
	 */
	public void addClass(ClassInfo classInfo) {
		if(classInfo.getId() == null) {
			classInfo.setId(idGenerator);
		}
		idGenerator++;
		this.classInfoList.add(classInfo);
		this.notifyListeners();
		StatusLogger.getInstance().showMessage("New class created");
	}
	
	public List<ClassInfo> getClassList() {
		return classInfoList;
	}
	
	/**
	 * This method saves connections between classes into our data structure. 
	 */
	public void addConnectionType(ClassInfo parentClass, ClassInfo childClass, String connectionType) {
		classInfoList.get(classInfoList.indexOf(parentClass)).setConnections(childClass.getId(), connectionType);
		this.notifyListeners();
	}
	
	public void clearData() {
		idGenerator = 1;
		classInfoList.clear();
		this.notifyListeners();
		StatusLogger.getInstance().showMessage("Data cleared");
	}

}
