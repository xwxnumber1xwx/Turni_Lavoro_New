import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.*;

public class IOFile {
	
	public boolean ExportTurni (String directoryEXT, String nomeFile, ArrayList<String> turniWeek) {
		boolean yn = false;
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nomeFile);
		try {
			Files.createDirectories(directory);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fileToSave = (turniWeek.toString());
		fileToSave = fileToSave.replace("[", "");
		fileToSave = fileToSave.replace("]", "");
		fileToSave = fileToSave.replace(",", "");
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			try {
				writer.write(fileToSave, 0, fileToSave.length());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.newLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return yn;
	}
	
	
	@SuppressWarnings("static-access")
	public boolean ExportShift(String directoryEXT, String nomeFile, Dipendente dipendente, LocalDate date) {
	boolean yn = false;
	Charset charset = Charset.forName("UTF-8");
	Path directory = Paths.get(directoryEXT);
	Path path = Paths.get(directory + "//" + nomeFile);
	try {
		Files.createDirectories(directory);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	try (BufferedWriter writer = Files.newBufferedWriter(path, charset, APPEND)) {
		ArrayList<String> fileToSaveArray = (dipendente.getWeekShift());
		String fileToSave = "";
		for (int x = 0; x < fileToSaveArray.size(); x++) {
			fileToSave = fileToSaveArray.get(x);
			writer.write(fileToSave, 0, fileToSave.length());
			writer.newLine();
		}
		yn = true;
		System.out.println("File " + nomeFile + " creato correttamente" + " \n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return yn;
	}
	
	public boolean ExportObjectToFile(String directoryEXT, String nomeFile, Dipendente dipendente) {
		
		boolean yn = false;
		FileOutputStream fos = null;
		
		Path directory = Paths.get(directoryEXT);
		try {
			Files.createDirectories(directory);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			fos = new FileOutputStream(directoryEXT + "//" + nomeFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos.writeObject(dipendente);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		yn = true;
		if (yn == false) {
			System.out.println("File " + directoryEXT + " non creato o non Aggiornato");
		}
		return yn;
	}
	
	public ArrayList<Dipendente> ImportObjectFromFile (String directoryEXT) {
		String nomeFile = "";
		File file = new File(directoryEXT);
		File[] listaFile = file.listFiles();
		ArrayList <Dipendente> dipendente = new ArrayList<Dipendente>();
		FileInputStream fis = null;
		for (int x = 0; x < listaFile.length; x++) {
			if(listaFile[x].isFile()) {
				nomeFile = listaFile[x].toString();
			try {
				fis = new FileInputStream(nomeFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				dipendente.add((Dipendente)ois.readObject());
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return dipendente;
	}
	
	
	public List<String> ImportFile (String directoryEXT, String nomeFile) {
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nomeFile);
		List<String> database = null;
		try (BufferedReader ins =  Files.newBufferedReader(path)) {
			database = Files.readAllLines(path);
			database.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return database;
	}
}