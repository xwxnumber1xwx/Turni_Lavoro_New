import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Create {
	public static void createEmployee() {
		IOFile save = new IOFile();
		Scanner scan = new Scanner (System.in);
		String var;
		Employee employee  = new Employee();
		System.out.println("Employee Code: " + "\n");
		long employeeCode = (long)scan.nextInt();
		employee.setEmployeeCode(employeeCode);
		scan.nextLine();
		System.out.println("Name: " + "\n");
		var = scan.nextLine();
		employee.setName(var);
		System.out.println("Surname: " + "\n");
		var = scan.nextLine();
		employee.setSurname(var);
		System.out.println("Line ability: ex. 1, 2 or 3 for both" + "\n");
		int lineAbility = scan.nextInt();
		employee.setWorkLine(lineAbility);
		System.out.println("Line Leader? ex. 0 = N, 1, 2 or 3 for both" + "\n");
		int lineLeader = scan.nextInt();
		employee.setLinieLeiter(lineLeader);
		System.out.println(employee.getSurname() + " whant to work only in the morning? 1 = Y, 2 = N" + "\n");
		int onlyMorning = scan.nextInt();
		if (onlyMorning == 1) {
			employee.setOnlyMorning(true);
		}
		System.out.println("Employee saved!" + "\n");
		scan.nextLine();
		String nameFile = (employee.getSurname() + ".dbs");
		save.ExportObjectToFile("database", nameFile, employee);
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
