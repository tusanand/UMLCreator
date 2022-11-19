import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Timer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler extends Observable {
	UmlDesigner umlDesigner;
	
	FileHandler(UmlDesigner umlDesigner) {
		this.umlDesigner = umlDesigner;
	}
	
	private void saveToFile(String filePath) {
		try {
			FileWriter writer = new FileWriter(filePath); 
			for(ClassInfo classInfo: ClassData.getInstance().getClassList()) {
				String classData = classInfo.getId() + "," + classInfo.getX() + "," + classInfo.getY() + "," + classInfo.getName();
				Map<Integer, String> classConnection = classInfo.getConnections();
				for(Integer key: classConnection.keySet()) {
					classData += "," + key + "|" + classConnection.get(key);
				}
				writer.write(classData + System.lineSeparator());
				System.out.println(classData);
			}
			writer.close();
			StatusLogger.getInstance().showMessage("Data successfully saved.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method loads the configuration from the selected file
	 * 
	 * @param filePath
	 */
	private void loadFromFile(String filePath) {
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				StatusLogger.getInstance().showMessage("No saved data found.");
				return;
			}
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			if (lines.isEmpty()) {
				StatusLogger.getInstance().showMessage("Saved file is empty.");
				return;
			}
			this.parseFileData(lines);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void parseFileData(List<String> lines) {
		List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
		for(String line: lines) {
			String[] classData = line.split(",");
			ClassInfo classInfo = new ClassInfo();
			classInfo.setId(Integer.parseInt(classData[0]));
			classInfo.setX(Integer.parseInt(classData[1]));
			classInfo.setY(Integer.parseInt(classData[2]));
			classInfo.setName(classData[3]);
			for(int i = 4; i < classData.length; i++) {
				String[] connectionInfo = classData[i].split("\\|");
				classInfo.setConnections(Integer.parseInt(connectionInfo[0]), connectionInfo[1]);
			}
			classInfoList.add(classInfo);
		}
		StatusLogger.getInstance().showMessage("Data successfully loaded.");
		setChanged();
		notifyObservers(classInfoList);
	}
	
	/**
	 * This method opens a file chooser to save/load a file
	 * 
	 * @param type
	 */
	public void selectSaveFile(String action) {
		if (action == "Save" && ClassData.getInstance().getClassList().isEmpty()) {
			StatusLogger.getInstance().showMessage("No data added. Please add/load data before saving.");
			return;
		}
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle(action + " File");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
		fileChooser.setFileFilter(filter);
		if (action == "Load") {
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				ClassData.getInstance().clearData();
				Timer timer = new java.util.Timer();
				timer.schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						loadFromFile(fileChooser.getSelectedFile().getAbsolutePath());
						timer.cancel();
					}
				}, 100);

			}
		}
		if (action == "Save") {
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				if (!filePath.contains(".txt")) {
					filePath += ".txt";
				}
				this.saveToFile(filePath);
			}
		}
	}

}
