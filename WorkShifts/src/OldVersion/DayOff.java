package OldVersion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class DayOff {
	static String directoryDayOff = "requestedDaysOff";
	static int numberOfEmployeersLine = 1;
	
		public static void AddDayOffromForm(Employee employee, LocalDate dayOff) {
		IOFile save = new IOFile();
		save.dayOff(employee, dayOff, directoryDayOff);
	}
	
	public static ArrayList <LocalDate> checkHoliday(Employee employee, IOFile save, LocalDate date) {
			employee.setHolidayThisWeek(false);
			ArrayList <LocalDate> allHolidayArrayList = new ArrayList<LocalDate>();
			ArrayList <LocalDate> holidayArrayList = new ArrayList<LocalDate>();
					allHolidayArrayList = save.checkDayOffOrHoliday(employee, "holiday");
					if (allHolidayArrayList.size() != 0) { 
						for (int y = 0; y < allHolidayArrayList.size(); y++) {
							if (allHolidayArrayList.get(y).isAfter(date) && allHolidayArrayList.get(y).isBefore(date.plusDays(7)) || allHolidayArrayList.get(y).isEqual(date)) {
								employee.setHolidayThisWeek(true);
								holidayArrayList.add(allHolidayArrayList.get(y));
								
							}
						}
					} 
			return holidayArrayList;
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
		//LocalDate dayOffLD = date;
				dayOffArrayList = save.checkDayOffOrHoliday(employee, directoryDayOff);
				if (dayOffArrayList.size() != 0) { 
					for (int y = 0; y < dayOffArrayList.size(); y++) {
						if (dayOffArrayList.get(y).isAfter(date) && dayOffArrayList.get(y).isBefore(date.plusDays(7)) || dayOffArrayList.get(y).isEqual(date)) {
						//	for (int y = 0; y < giornoLiberoArray.size(); y++) {
							employee.setDayOffThisWeek(true);
							//dayOffLD = dayOffArrayList.get(y);
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
							//dayOffLD = date.plusDays(dayOff);
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
	
	public static int setMorningOrEveningShift (Employee employee, int night, LocalDate date) {
		IOFile save = new IOFile();
		DayOff.checkHoliday(employee, save, date);
		if ( employee.getHolidayThisWeek() == false) {	
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
						employee.setShiftLineLeader(0);
					}
				}
			} 
			} else {
				employee.setShiftLine(2);
				employee.setMorningNight(1);
				employee.setShiftLineLeader(0);
				//Log
				String logText = (employee.getName() + " " + employee.getSurname() + " have holiday this week");
				IOFile.writeLog("log", "log", logText);
			}
		return night;
	}
}
