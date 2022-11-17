import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class ClassData extends Observable {
	private static ClassData classData;
	private List<ClassInfo> classInfoList;
	
	private ClassData() {
		classInfoList = new ArrayList<ClassInfo>();
	}
	
	public static ClassData getInstance() {
		if(classData == null) {
			classData = new ClassData();
		}
		return classData;
	}
	
	public void addClass(ClassInfo classInfo) {
		this.classInfoList.add(classInfo);
		setChanged();
		notifyObservers();
	}
	
	public List<ClassInfo> getClassList() {
		return classInfoList;
	}
	
	public void addConnectionType(ClassInfo classInfo, String connectionType) {
		classInfoList.get(classInfoList.indexOf(classInfo)).setConnections(classInfo, connectionType);
	}

}
