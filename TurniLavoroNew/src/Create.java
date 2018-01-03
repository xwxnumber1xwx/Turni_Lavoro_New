import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Create {
	
	public static void createEmployeeFromForm(long employeeCode, String Surname, String name, int lineAbility, int lineLeader, Boolean onlyMorning) {
		IOFile save = new IOFile();
		Employee employee  = new Employee();
		employee.setEmployeeCode(employeeCode);
		employee.setName(name);
		employee.setSurname(Surname);
		employee.setWorkLine(lineAbility);
		employee.setLineLeader(lineLeader);
		employee.setOnlyMorning(onlyMorning);
		String nameFile = (employee.getSurname() + "_" + employee.getName() + ".dbs");
		nameFile = nameFile.toLowerCase();
		save.ExportObjectToFile("database", nameFile, employee);
		AlertBox.Display("Done", "Employee has been saved!");
		
	}
	
	public static int setWeekToElaborate(int inputWeek, IOFile save, LocalDate date, String directory) {
		do {
			Scanner scan = new Scanner (System.in);
			System.out.print("Welche kalenderWoche wollen Sie?" + "\n");
			inputWeek = scan.nextInt();
			// Look if the Week already exist
			boolean weekAlredyExist = save.checkWeek(inputWeek, directory);
			if (weekAlredyExist == true) {
				System.out.println("This week Alredy Exist");
				inputWeek = 100;
			} else {
				if (inputWeek < 1 || inputWeek > 53) {
					System.out.print("week's number is not correct" + "\n");
				}
			}
		} while (inputWeek < 1 || inputWeek > 53);
		
		return inputWeek;
	}
}
