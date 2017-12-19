import java.io.BufferedOutputStream;
import static java.nio.file.StandardOpenOption.APPEND;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IOFile {
	
	public boolean InitTurni(String directoryEXT, String nomeFile, LocalDate date) {
		boolean yn = false;
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nomeFile);
		try {
			Files.createDirectories(directory);
			Files.createFile(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			DayOfWeek giorno = DayOfWeek.SUNDAY;
			TextStyle stileNorm = TextStyle.FULL;
			Locale deutsch = Locale.GERMAN;
			writer.write(date.toString());
			date.plusDays(7);
			writer.write("<--->");
			writer.write(date.toString());
			writer.newLine();
			/*
			writer.write("\t");
			writer.write(giorno.getDisplayName(stileNorm, deutsch));
			writer.write("\t");
			giorno = DayOfWeek.MONDAY;
			writer.write(giorno.getDisplayName(stileNorm, deutsch));
			giorno = DayOfWeek.TUESDAY;
			writer.write(giorno.getDisplayName(stileNorm, deutsch));
			writer.write("\t");
			giorno = DayOfWeek.WEDNESDAY;
			writer.write(giorno.getDisplayName(stileNorm, deutsch));
			writer.write("\t");
			giorno = DayOfWeek.THURSDAY;
			writer.write(giorno.getDisplayName(stileNorm, deutsch));
			writer.write("\t");
			giorno = DayOfWeek.WEDNESDAY;
			writer.write(giorno.getDisplayName(stileNorm, deutsch));
			writer.write("\t");
			giorno = DayOfWeek.SATURDAY;
			writer.write(giorno.getDisplayName(stileNorm, deutsch));
			writer.newLine();
			*/
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return yn;
	}
		
	public boolean ExportTurniDiTutti (String directoryEXT, String nomeFile, String turniWeek) {
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
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset, APPEND)) {
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
	boolean sn = Files.exists(path);
	if (sn == false) {
		try {
			Files.createFile(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
	public List<String> ImportShiftFile (String directoryEXT, String nomeFile) {
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

	public boolean checkWeek(int inputWeekEXT, String directoryEXT) {
		// TODO Auto-generated method stub
		directoryEXT = (directoryEXT + "//" + inputWeekEXT + ".txt");
		Path directory = Paths.get(directoryEXT);
		boolean yesNo = Files.exists(directory);
		return yesNo;
	}
	
	public ArrayList<LocalDate> checkFreeDay (Dipendente dipendente, String directoryEXT) {
		
		directoryEXT = (directoryEXT + "//" + dipendente.cognome + ".txt");
		Path directory = Paths.get(directoryEXT);
		boolean yesNo = Files.exists(directory);
		ArrayList<LocalDate> days = new ArrayList<LocalDate>();
		int YY, MM, DD;
		long date;
		if (yesNo == true) {
			List<String> day = new ArrayList<String>();
			try {
				day = Files.readAllLines(directory);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int x=0; x<day.size(); x++) {
				date = Long.parseLong(day.get(x).replaceAll("-", ""));
				 YY = (int) (date/10000);
				 MM = (int) ((date - YY*10000) / 100);
				 DD = (int) ((date - YY*10000) - (MM*100));					
				days.add(LocalDate.of(YY, MM, DD));
			}
		}
		
		return days;
	}
	
	public void freeday (Dipendente dipendente, LocalDate day, String directoryEXT) {
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		String nomeFile = dipendente.getCognome();
		Path path = Paths.get(directory + "//" + nomeFile + ".txt");
		try {
			Files.createDirectories(directory);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean sn = Files.exists(path);
		if (sn == false) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (sn == false) {
			try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
				String fileToSave = day.toString();
						writer.write(fileToSave, 0, fileToSave.length());
						writer.newLine();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		} else {
			try (BufferedWriter writer = Files.newBufferedWriter(path, charset, APPEND)) {
				String fileToSave = day.toString();
						writer.write(fileToSave, 0, fileToSave.length());
						writer.newLine();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		}
	
	}
}