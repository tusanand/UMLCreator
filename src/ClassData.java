import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ClassData extends Observable {
	private static ClassData classData;
	private List<ClassInfo> classInfo;
	
	private ClassData() {
		classInfo = new ArrayList<ClassInfo>();
	}
	
	public static ClassData getInstance() {
		if(classData == null) {
			classData = new ClassData();
		}
		return classData;
	}
	
	public void addClass(ClassInfo classInfo) {
		this.classInfo.add(classInfo);
		setChanged();
		notifyObservers();
	}
	
	public List<ClassInfo> getClassList() {
		return classInfo;
	}

}
