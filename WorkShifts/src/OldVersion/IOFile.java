package OldVersion;

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
	
	public boolean initShifts(String directoryEXT, String nameFile, LocalDate date) {
		boolean yn = false;
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nameFile);
		try {
			Files.createDirectories(directory);
			Files.createFile(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			writer.write(date.toString());
			date = date.plusDays(7);
			writer.write("<--->");
			writer.write(date.toString());
			writer.newLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return yn;
	}
		
	public boolean exportAllShifts (String directoryEXT, String nameFile, String shiftWeek) {
		boolean yn = false;
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nameFile);
		try {
			Files.createDirectories(directory);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fileToSave = (shiftWeek.toString());
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
		
	public boolean exportOneEmployeeShift(String directoryEXT, String nameFile, Employee employee, LocalDate date) {
		DayOfWeek dayWeek = DayOfWeek.from(date);
		
		TextStyle stileNorm = TextStyle.FULL;
		Locale language = Locale.ENGLISH;
		boolean yn = false;
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nameFile);
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
			String fileToSave = "";
			int plusMinutes = Integer.parseInt(Proprieties.getOnePropriety("HOW_MANY_MINUTES_TO_WORK"));
			for (int x = 0; x < 7; x++) {
				if (employee.getDayOff() != x & x != 6) {
					fileToSave = employee.getTime(x).toLocalDate().toString() + " " +  dayWeek.getDisplayName(stileNorm, language).toString().toLowerCase() + " " + employee.getTime(x).toLocalTime().toString() + "-"+ employee.getTime(x).plusMinutes(plusMinutes).toLocalTime();
				} else {
					fileToSave = employee.getTime(x).toLocalDate().toString() + " " + dayWeek.getDisplayName(stileNorm, language).toString().toLowerCase() + " " + "DayOff";
				}
				fileToSave = fileToSave.toLowerCase();
				writer.write(fileToSave, 0, fileToSave.length());
				writer.newLine();
				dayWeek = dayWeek.plus(1);
			}
			yn = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yn;
		}
	
	public boolean ExportObjectToFile(String directoryEXT, String nameFile, Employee employee) {
		
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
			fos = new FileOutputStream(directoryEXT + "//" + nameFile);
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
			oos.writeObject(employee);
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
	
	public ArrayList<Employee> ImportObjectFromFile (String directoryEXT) {
		String nameFile = "";
		File file = new File(directoryEXT);
		File[] listOfFile = file.listFiles();
		ArrayList <Employee> employee = new ArrayList<Employee>();
		FileInputStream fis = null;
		for (int x = 0; x < listOfFile.length; x++) {
			if(listOfFile[x].isFile()) {
				nameFile = listOfFile[x].toString();
			try {
				fis = new FileInputStream(nameFile);
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
				employee.add((Employee)ois.readObject());
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
	return employee;
	}
	
	public List<String> ImportShiftFile (String directoryEXT, String nameFile) {
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nameFile);
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
	
	public ArrayList<LocalDate> checkDayOffOrHoliday (Employee employee, String directoryEXT) {
		
		directoryEXT = (directoryEXT + "//" + employee.getSurname()  + "_" + employee.getName() + ".txt");
		directoryEXT = directoryEXT.toLowerCase();
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
	
	public void dayOff (Employee employee, LocalDate day, String directoryEXT) {
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		String nameFile = (employee.getSurname()  + "_" + employee.getName());;
		nameFile = nameFile.toLowerCase();
		Path path = Paths.get(directory + "//" + nameFile + ".txt");
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

	public static boolean writeLog(String directoryEXT, String nameFile, String LogText) {
		LocalDate dateTime = LocalDate.now();
		boolean yn = false;
		Charset charset = Charset.forName("UTF-8");
		Path directory = Paths.get(directoryEXT);
		Path path = Paths.get(directory + "//" + nameFile + dateTime.toString().replaceAll("-", "") + ".txt");
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
		String fileToSave = LogText;
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
}