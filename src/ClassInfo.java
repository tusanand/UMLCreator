import java.util.HashMap;
import java.util.Map;

public class ClassInfo {
	private int x;
	private int y;
	private String name;
	private Map<ClassInfo, String> connections = new HashMap<ClassInfo, String>();
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<ClassInfo, String> getConnections() {
		return connections;
	}
	public void setConnections(ClassInfo classInfo, String type) {
		this.connections.put(classInfo, type);
	}
}
