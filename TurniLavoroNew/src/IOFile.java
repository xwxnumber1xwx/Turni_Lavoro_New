import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.*;

public class IOFile {
	
	public boolean ExportTurni (String directoryEXT, String pathExtern, ArrayList<String> turniWeek) {
		boolean yn = false;
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + pathExtern);
		try {
			Files.createDirectories(directory);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fileToSave = (turniWeek.toString());
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
	public boolean ExportToFile(String directoryEXT, String pathExtern, Dipendente dipendente) {
	boolean yn = false;
	Charset charset = Charset.forName("UTF-8");
	Path directory = Paths.get(directoryEXT);
	Path path = Paths.get(directory + "//" + pathExtern);
	try {
		Files.createDirectories(directory);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String fileToSave = (dipendente.getNome() + " " + dipendente.getCognome());
	try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
		writer.write(fileToSave, 0, fileToSave.length());
		writer.newLine();
		writer.write("NightTarif:");
		writer.newLine();
		fileToSave = fileToSave.valueOf(dipendente.getTotZuSchlag());
		writer.write(fileToSave, 0, fileToSave.length());
		writer.newLine();
		writer.write("krank:");
		writer.newLine();
		fileToSave = fileToSave.valueOf(dipendente.getGiorniMalattia());
		writer.write(fileToSave, 0, fileToSave.length());
		writer.newLine();
		writer.write("Linee:");
		writer.newLine();
		fileToSave = fileToSave.valueOf(dipendente.getLivello());
		writer.write(fileToSave, 0, fileToSave.length());
		writer.newLine();
		writer.write("Linee:");
		writer.newLine();
		fileToSave = fileToSave.valueOf(dipendente.getLineaLavoro());
		writer.write(fileToSave, 0, fileToSave.length());
		writer.newLine();
		writer.write("NurTag:");
		writer.newLine();
		fileToSave = fileToSave.valueOf(dipendente.soloMattina);
		writer.write(fileToSave, 0, fileToSave.length());
		writer.newLine();
		writer.write("LineeLeiter:");
		writer.newLine();
		fileToSave = fileToSave.valueOf(dipendente.linieLeiter);
		writer.write(fileToSave, 0, fileToSave.length());
		writer.newLine();
		writer.write("Frei:");
		writer.newLine();
		fileToSave = fileToSave.valueOf(dipendente.getGiornoLibero());
		writer.write(fileToSave, 0, fileToSave.length());
		writer.newLine();
		yn = true;
		System.out.println("File " + pathExtern + " creato correttamente" + " \n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*
	//Secondo metodo tando per imparare, ma e meglio il primo e piu efficiente credo
	 * 
	String addsToSave = ("Krank: " + dipendente.getGiorniMalattia() + "  Stufe: " + dipendente.getLivello() + "  Linee: " + dipendente.getLineaLavoro());
	byte data[] = addsToSave.getBytes();
	try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE, APPEND))) { //APPEND aggiunge al file giä esistente altre informazioni
		out.write(data, 0, data.length);
		out.
		yn = true;
		System.out.println("File " + nameFile + " AGGIORNATO correttamente" + " \n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (yn == false) {
		System.out.println("File " + nameFile + " non creato o non Aggiornato");
	}
	*/
	return yn;
	}
	
	public List<String> ImportFile (String nameFile, String pathExtern, Dipendente dipendente) {
		Path path = Paths.get("mitarbeiter//" + pathExtern);
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