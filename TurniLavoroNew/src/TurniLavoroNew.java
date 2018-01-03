import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class TurniLavoroNew {
	
	//public static void main(String[] args)
	public static void Elaborate()	
		throws IOException {
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
				LocalDate date = LocalDate.of(2017, 1, 1);
				int inputWeek = 0;
				String nameFile = "";
				int yn;
				IOFile save = new IOFile();
				Scanner scan = new Scanner (System.in);
				
				// load employees from database
				employeeArrayList = save.ImportObjectFromFile("database");
				System.out.println("you want to add a day off to an employee? 1 = Y, 2 = N");
				int z = scan.nextInt();
				
				if (z == 1) {
					DayOff.addDayOff(employeeArrayList, save);
					
				}
		
				//
				// chooses which week the program should process
				String directory = "shift_" + date.getYear();
				inputWeek = Create.setWeekToElaborate(inputWeek, save, date, directory);
				inputWeek -= 1;
				date = date.plusWeeks(inputWeek);
				ArrayList <String> shiftWeek = new ArrayList<String>();
				ArrayList <String> shiftEmployee = new ArrayList<String>();
				
				//sort the workers based on the accumulated night hours
				employeeArrayList = Sorting.SortingNightTariffLessToHigh(employeeArrayList);

				//CHOICE WHO WORKS IN THE MORNING OR IN THE EVENING
				for (int x = 0 ; x < employeeArrayList.size(); x++) {
					Employee employee = employeeArrayList.get(x);
					if (inputWeek == 0) {
						employee.initNightRates();
					}
					// Morning or Night
					night = DayOff.setMorningOrEveningShift(employee, night);
					}
				//generates Shift and Save it 
				directory = "shift_" + date.getYear();
				save.initShifts(directory,  String.valueOf(inputWeek+1) + ".txt", date);
				employeeArrayList = Sorting.sortingByWorkDepartment(employeeArrayList, 2, numberOfEmployeeMorningLine1, numberOfEmployeeNightLine1, (minEmployeeNight-numberOfEmployeeNightLine1), date, save);
				for (int x = 0; x < employeeArrayList.size(); x++) {
					Employee employee = employeeArrayList.get(x);
					shiftEmployee = GenerateShifts.generateShift(employee, date.with(DayOfWeek.SUNDAY));
					shiftEmployee.add("\n");
					shiftWeek.add(employee.getSurname() +" Line Leader: " + employee.getShiftLineLeader() + " Line: " + employee.getShiftLine() +  " " + shiftEmployee);
					employee.setWeekShift(shiftEmployee);
					nameFile = (employee.getSurname() + ".txt");
					directory = "shift_employee";
					save.ExportShiftLT(directory, nameFile, employee, date);
					directory = "shift_" + date.getYear();
					save.saveAllShifts(directory, String.valueOf(inputWeek+1) + ".txt", shiftWeek.get(x));
					nameFile = (employee.getSurname() + ".dbs");
					save.ExportObjectToFile("database", nameFile, employee);
				}
				employeeArrayList = Sorting.SortingNightTariffLessToHigh(employeeArrayList);
				for(int h = 0; h < employeeArrayList.size(); h++) {
					Employee employee = employeeArrayList.get(h);
					String logText = (employee.getSurname() + " TOT: " + employee.getAllNightRates());
					IOFile.writeLog("log", "log", logText);
				}
				scan.close();
				AlertBox.Display("Done", "You can find the shift´s file on shift" + date.getYear());
				}
	
}