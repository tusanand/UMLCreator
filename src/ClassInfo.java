import java.util.HashMap;
import java.util.Map;

/**
 * This class creates a data structure to save class and connection information.
 */
public class ClassInfo {
	private Integer id;
	private int x;
	private int y;
	private String name;
	private Map<Integer, String> connectionsList = new HashMap<Integer, String>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Map<Integer, String> getConnections() {
		return connectionsList;
	}
	public void setConnections(int id, String type) {
		this.connectionsList.put(id, type);
	}
}
