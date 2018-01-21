package newWorkShiftsV2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOFileV2 {

	public static boolean initFile(String directoryEXT, String nameFile) {
		boolean yn = false;
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nameFile + ".json");
		try {
			Files.createDirectories(directory);
			if (!Files.exists(path)) {
				Files.createFile(path);
				yn = true;
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return yn;
	}

	public static void exportJson(String directoryEXT, String nameFile, String jsonString) {
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nameFile + ".json");
		try {
			Files.createDirectories(directory);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			try {
				writer.write(jsonString, 0, jsonString.length());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public String ImportJson(String directoryEXT, String nameFile) {
		IOFileV2.initFile(directoryEXT, nameFile);
		directoryEXT = (directoryEXT + "//" + nameFile + ".json");
		Path path = Paths.get(directoryEXT);
		List<String> fileJson = new ArrayList<String>();
		try (BufferedReader ins = Files.newBufferedReader(path)) {
			fileJson = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		for(String s : fileJson) {
		    builder.append(s);
		}
		String str = builder.toString();
		
		return str;
	}
}