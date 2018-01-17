package OldVersion;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

class ElaborateShift {
	
	//public static void main(String[] args)
	public static void Elaborate(int weekToElaborate)	
		throws IOException {
		
			String logText = ("+++++++++++++++++++++++++++++++++++++++++++++++++" + LocalDateTime.now()+ "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			IOFile.writeLog("log", "log", logText);
			String path = "options.proprieties";
			if (Proprieties.FileExist(path) == false) {
				Proprieties.InitFile();
			}
			ArrayList <Employee> employeeArrayList = new ArrayList<Employee>();
			int night = 0;
			int minEmployeeNight = Integer.parseInt(Proprieties.getOnePropriety("NIGHT_MINIMUM_EMPLOYEE"));
			int numberOfEmployeeNightLine1 = Integer.parseInt(Proprieties.getOnePropriety("NIGHT_LINE1_NUMBER_EMPLOYEE"));
			int numberOfEmployeeMorningLine1 = Integer.parseInt(Proprieties.getOnePropriety("MORNING_LINE1_NUMBER_EMPLOYEE"));
			
			//IMPUT KalenderWoche
			LocalDate date = LocalDate.of(LocalDate.now().getYear(), 1, 1);
			String nameFile = "";
			int yn;
			IOFile save = new IOFile();
			Scanner scan = new Scanner (System.in);
			
			// load employees from database
			employeeArrayList = save.ImportObjectFromFile("database");
			
			// chooses which week the program should process
			String directory = "shift_" + date.getYear();
			weekToElaborate--;
			date = date.plusWeeks(weekToElaborate);
			ArrayList <String> shiftWeek = new ArrayList<String>();
			ArrayList <String> shiftEmployee = new ArrayList<String>();
			
			//sort the workers based on the accumulated night hours
			employeeArrayList = Sorting.SortingNightTariffLessToHigh(employeeArrayList);

			//CHOICE WHO WORKS IN THE MORNING OR IN THE EVENING
			for (int x = 0 ; x < employeeArrayList.size(); x++) {
				Employee employee = employeeArrayList.get(x);
				if (weekToElaborate == 0) {
					employee.initNightRates();
				}
				// Morning or Night
				night = DayOff.setMorningOrEveningShift(employee, night, date);
				}
			//generates Shift and Save it 
			directory = "shift_" + date.getYear();
			save.initShifts(directory,  String.valueOf(weekToElaborate+1) + ".txt", date);
			employeeArrayList = Sorting.sortingByWorkDepartment(employeeArrayList, 2, numberOfEmployeeMorningLine1, numberOfEmployeeNightLine1, (minEmployeeNight-numberOfEmployeeNightLine1), date, save);
			for (int x = 0; x < employeeArrayList.size(); x++) {
				Employee employee = employeeArrayList.get(x);
				ArrayList<LocalDate> holiday = DayOff.checkHoliday(employee, save, date.with(DayOfWeek.SUNDAY));
				shiftEmployee = GenerateShifts.generateShift(employee, date.with(DayOfWeek.SUNDAY), holiday);
				shiftEmployee.add("\n");
				shiftWeek.add(employee.getSurname() + " " + employee.getName() +" Line Leader: " + employee.getShiftLineLeader() + " Line: " + employee.getShiftLine() +  " " + shiftEmployee);
				employee.setWeekShift(shiftEmployee);
				nameFile = (employee.getSurname()  + "_" + employee.getName() + ".txt");
				nameFile = nameFile.toLowerCase();
				directory = "shift_employee";
				save.exportOneEmployeeShift(directory, nameFile, employee, date);
				directory = "shift_" + date.getYear();
				save.exportAllShifts(directory, String.valueOf(weekToElaborate+1) + ".txt", shiftWeek.get(x));
				nameFile = (employee.getSurname() + "_" + employee.getName() + ".dbs");
				nameFile = nameFile.toLowerCase();
				save.ExportObjectToFile("database", nameFile, employee);
			}
			employeeArrayList = Sorting.SortingNightTariffLessToHigh(employeeArrayList);
			for(int h = 0; h < employeeArrayList.size(); h++) {
				Employee employee = employeeArrayList.get(h);
				logText = (employee.getSurname() + " TOT: " + employee.getAllNightRates());
				IOFile.writeLog("log", "log", logText);
			}
			scan.close();
			AlertBox.Display("Done", "DONE! You can find the shift´s file into the folder: shift_" + date.getYear());
			}
	
}