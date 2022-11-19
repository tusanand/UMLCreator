import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class ClassData extends Observable {
	private static ClassData classData;
	private List<ClassInfo> classInfoList;
	int idGenerator;
	
	private ClassData() {
		classInfoList = new ArrayList<ClassInfo>();
		idGenerator = 1;
	}
	
	public static ClassData getInstance() {
		if(classData == null) {
			classData = new ClassData();
		}
		return classData;
	}
	
	public void addClass(ClassInfo classInfo) {
		if(classInfo.getId() == null) {
			classInfo.setId(idGenerator);
		}
		idGenerator++;
		this.classInfoList.add(classInfo);
		setChanged();
		notifyObservers();
	}
	
	public List<ClassInfo> getClassList() {
		return classInfoList;
	}
	
	public void addConnectionType(ClassInfo parentClass, ClassInfo childClass, String connectionType) {
		classInfoList.get(classInfoList.indexOf(parentClass)).setConnections(childClass.getId(), connectionType);
		setChanged();
		notifyObservers();
	}
	
	public void clearData() {
		idGenerator = 1;
		classInfoList.clear();
	}

}
