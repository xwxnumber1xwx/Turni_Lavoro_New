import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class DayOff {
	static String directoryDayOff = "requestedDaysOff";
	static int numberOfEmployeersLine = 1;
	public static ArrayList<Employee> addDayOff(ArrayList<Employee> employeeArrayList, IOFile save) {
		Scanner scan = new Scanner (System.in);
		boolean found = false;
		do {
			System.out.println("enter the employee's surname");
			String surnameScan = scan.nextLine();
			Employee employee;
				for (int x=0; x < employeeArrayList.size(); x++) {
					employee = employeeArrayList.get(x);
					String surnameFromDatabase = employee.getSurname().toLowerCase();
					if (surnameFromDatabase.compareTo(surnameScan.toLowerCase()) == 0) {
							found = true;
						System.out.println(employee.getSurname() + " Which day off? DDMMYY, 1 for EXIT");
							long dayOff = scan.nextLong();
							if (dayOff != 1) {
									int DD = (int) (dayOff/10000);
									int MM = (int) (dayOff - (DD*10000));
									MM = MM /100;
									int YY = (int) (dayOff - (DD*10000) - (MM*100) + (2000));
									LocalDate freeDay = LocalDate.of(YY, MM, DD);
									save.freeday(employee, freeDay, directoryDayOff);
									
							}
					}
				}
				if (found == false) {
					System.out.println("employee not found");
			}
		} while (found == false);
		return employeeArrayList;
	}
	
	public static int[] setDayOff(Employee employee, IOFile save, LocalDate date, int[] dayOffsundayFriday) {
		int dayOff = 0;
		employee.setDayOffThisWeek(false);
		employee.setDayOff(0);
		//dipendente.setDayOffLD(date);
		if (employee.getMorningNight() == 2) {
			dayOff = 4; // Inizio giovedi così chi ha poche ore notturne accumultate non ha la domenica libera perché il metodo a seguire attribuirà al primo dipendente la domenica libera
		}
		ArrayList <LocalDate> dayOffArrayList = new ArrayList<LocalDate>();
		LocalDate dayOffLD = date;
				dayOffArrayList = save.checkFreeDay(employee, directoryDayOff);
				if (dayOffArrayList.size() != 0) { 
					for (int y = 0; y < dayOffArrayList.size(); y++) {
						if (dayOffArrayList.get(y).isAfter(date) && dayOffArrayList.get(y).isBefore(date.plusDays(7)) || dayOffArrayList.get(y).isEqual(date)) {
						//	for (int y = 0; y < giornoLiberoArray.size(); y++) {
							employee.setDayOffThisWeek(true);
							dayOffLD = dayOffArrayList.get(y);
							DayOfWeek DoW = DayOfWeek.from(dayOffArrayList.get(y));
							if (DoW == DayOfWeek.SUNDAY) {
								dayOff = 0;
								dayOffsundayFriday[0] ++;
							} else if (DoW == DayOfWeek.MONDAY) {
								dayOff = 1;
								dayOffsundayFriday[1] ++;
							} else if (DoW == DayOfWeek.TUESDAY) {
								dayOff = 2;
								dayOffsundayFriday[2] ++;
							} else if (DoW == DayOfWeek.WEDNESDAY) {
								dayOff = 3;
								dayOffsundayFriday[3] ++;
							} else if (DoW == DayOfWeek.THURSDAY) {
								dayOff = 4;
								dayOffsundayFriday[4] ++;
							} else if (DoW == DayOfWeek.WEDNESDAY) {
								dayOff = 5;
							}
							employee.setDayOff(dayOff);
							dayOffLD = date.plusDays(dayOff);
							//dipendente.setDayOffLD(giornoLiberoLD);
							
							//log
							String logText = ("added dayOffsundayFriday[" + dayOff + "] " + employee.getName() + " " + employee.getSurname());
							IOFile.writeLog("log", "log", logText);
							return dayOffsundayFriday;
							
						} else {
							dayOffsundayFriday = DayOff.setRandomDayOff(employee, dayOffsundayFriday, dayOff);
						}
					}
				} else {
					dayOffsundayFriday = DayOff.setRandomDayOff(employee, dayOffsundayFriday, dayOff);
			}
				return dayOffsundayFriday;
	}
	
	public static int [] setRandomDayOff (Employee employee, int [] dayOffsundayFriday, int dayOff) {
		if (employee.getMorningNight() == 2) { 
			for (int u = dayOffsundayFriday.length-2; u > -1; u--) {
					if (dayOffsundayFriday[u+1] > dayOffsundayFriday[u]) {
						dayOff = u;
						for (int i=u-2; i > 0 ; i--) {
							if (dayOffsundayFriday[u]>dayOffsundayFriday[i]) {
								u = i;
								dayOff = u;
							}
						}
					} 											
			}		
		employee.setDayOff(dayOff);
		dayOffsundayFriday[dayOff]++;
		String logText = ("added dayOffsundayFriday[" + dayOff + "] " + employee.getName() + " " + employee.getSurname());
		IOFile.writeLog("log", "log", logText);
		}
		return dayOffsundayFriday;
	}
	
	public static int setMorningOrEveningShift (Employee employee, int night) {
		
		int minEmployeeNight = Integer.parseInt(Proprieties.getOnePropriety("NIGHT_MINIMUM_EMPLOYEE"));
		int numberOfEmployeeNightLine1 = Integer.parseInt(Proprieties.getOnePropriety("NIGHT_LINE1_NUMBER_EMPLOYEE"));
		if (employee.getOnlyMorning() == true) {
			if (employee.getWorkLine() == 1 || employee.getWorkLine() == 3) {
				employee.setShiftLine(1);
			} else {
				employee.setShiftLine(2);
			}
			employee.setMorningNight(1); //TAG
		} else {
			if (employee.getWorkLine() == 1 || employee.getWorkLine() == 3) {
				if (night <= minEmployeeNight) {
					if (numberOfEmployeersLine <= numberOfEmployeeNightLine1) {
						night++;
						numberOfEmployeersLine++;
						employee.setShiftLine(1);
						employee.setMorningNight(2); //Nacht
					} else if (employee.getWorkLine() == 2) {
							night++;
							employee.setShiftLine(2);
							employee.setMorningNight(2); //Nacht									
					}
				} else {
					employee.setShiftLine(1);
					employee.setMorningNight(1); //TAG
				}
			} else if (employee.getWorkLine() == 2 || employee.getWorkLine() == 3) {
				if (night <= minEmployeeNight) {
					night++;
					employee.setShiftLine(2);
					employee.setMorningNight(2); //Nacht
				} else {
					employee.setShiftLine(2);
					employee.setMorningNight(1); //TAG
				}
			}
		}
		return night;
	}
}
