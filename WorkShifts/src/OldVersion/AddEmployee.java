package OldVersion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AddEmployee {
	
	public static void addEmployeeFromForm(long employeeCode, String Surname, String name, int lineAbility, int lineLeader, Boolean onlyMorning) {
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
}
