
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorting {
	
	
	public static  ArrayList<Employee> SortingNightTariffLessToHigh(ArrayList<Employee> dipendenti){
		
		Collections.sort(dipendenti, new Comparator<Employee>() {
			@Override
			public int compare(Employee dipendenti2, Employee dipendenti1) //mi ritorna -1,0 o 1 se dipendenti1.gerOreNotturne Ã¨ minore uguale o maggiore di dipendenti2.getOreNotturne
			{
				return dipendenti2.getAllNightRates().compareTo(dipendenti1.getAllNightRates());
				
			}
		});
		return dipendenti;
	}
	
	public static ArrayList<Employee> sortingByWorkDepartment(ArrayList<Employee> employee, int numberOfLeaders, int numberOfEmployeeforLine1Morning, int numberOfEmployeeforLine1Night, int numberOfEmployeeforLine2Night, LocalDate date, IOFile save) {
		
		ArrayList <Employee> leadersAndEmployee = new ArrayList<Employee>();
		int[] dayOffsundayFriday = new int[5];
		int o = 0;
			for (int h = 1; h < 3; h++) { //NACHT
				int numberOfLeadersL1 = numberOfLeaders;
				o=0;
				int size = employee.size();
				for (int x = 0; x < size; x++) {
					//LinieLeiter NACHT
					
					// ERRORE: a volte trova solo un leader nella linea 1, probabilemnte perché tutti gli altri sono messi di mattina
					Employee employeeTemp = employee.get(o);
					if (numberOfLeadersL1 != 0 && employeeTemp.getMorningNight() == 2) {
						if (employeeTemp.getlinieLeiter() == h || employeeTemp.getlinieLeiter() == 3) {
							dayOffsundayFriday = DayOff.setDayOff(employeeTemp, save, date, dayOffsundayFriday);
							setEmployeeOnShift(employeeTemp, h, h);
							leadersAndEmployee.add(employeeTemp);
							employee.remove(o);
							numberOfLeadersL1--;
						} else {
							o++;
						}
					} else {
						o++;
					}
				}
				
				numberOfLeadersL1 = numberOfLeaders;
				o=0;
				size = employee.size();
				for (int x = 0; x < size; x++) {
					//LinieLeiter TAG
					Employee employeeTemp = employee.get(o);
					if (numberOfLeadersL1 != 0 && employeeTemp.getMorningNight() == 1) {
					if (employeeTemp.getlinieLeiter() == h || employeeTemp.getlinieLeiter() == 3) {
						dayOffsundayFriday = DayOff.setDayOff(employeeTemp, save, date, dayOffsundayFriday);
						setEmployeeOnShift(employeeTemp, h, h);
						leadersAndEmployee.add(employeeTemp);
						employee.remove(o);
						numberOfLeadersL1--;
					} else {
						o++;
					}
				} else {
					o++;
				}
			}
			}
			for (int h = 1; h < 3; h++) {
				o=0;
				int size = employee.size();
				for (int x = 0; x < size; x++) {
					// Linie NACHT
					Employee dip1 = employee.get(o);
					if (dip1.getMorningNight() == 2) {
					if (dip1.getWorkLine() == h || dip1.getWorkLine() == 3) {
						if (numberOfEmployeeforLine1Night != 0) {
							dayOffsundayFriday = DayOff.setDayOff(dip1, save, date, dayOffsundayFriday);
							setEmployeeOnShift(dip1, 0, 1);
							leadersAndEmployee.add(dip1);
							employee.remove(o);
							numberOfEmployeeforLine1Night--;
						} else if (numberOfEmployeeforLine2Night != 0) {
							dayOffsundayFriday = DayOff.setDayOff(dip1, save, date, dayOffsundayFriday);
							setEmployeeOnShift(dip1, 0, 2);
							leadersAndEmployee.add(dip1);
							employee.remove(o);
							numberOfEmployeeforLine2Night--;
						} else {
							o++;
						}
					} else {
						o++;
					}
				} else {
					o++;
				}
				}
				o=0;
				size = employee.size();
				for (int x = 0; x < size; x++) {
					//Line TAG
					Employee employeeTemp = employee.get(o);
					if (employeeTemp.getMorningNight() == 1) {
					if (employeeTemp.getWorkLine() == h || employeeTemp.getWorkLine() == 3) {
						if (h == 1 && numberOfEmployeeforLine1Morning != 0) {
							setEmployeeOnShift(employeeTemp, 0, 1);
							dayOffsundayFriday = DayOff.setDayOff(employeeTemp, save, date, dayOffsundayFriday);
							leadersAndEmployee.add(employeeTemp);
							employee.remove(o);
							numberOfEmployeeforLine1Morning--;
						} else {
							
							setEmployeeOnShift(employeeTemp, 0, 2);
							dayOffsundayFriday = DayOff.setDayOff(employeeTemp, save, date, dayOffsundayFriday);
							leadersAndEmployee.add(employeeTemp);
							employee.remove(o);
						}
					} else {
						o++;
					}
					
					} else {
						o++;
					}
				}
			}
			int size = employee.size();
			for (int x = 0; x < size; x++) {
				Employee emplozeeTemp = employee.get(0);
				setEmployeeOnShift(emplozeeTemp, 0, 2, 1); // Dato extra La mattina perche ha superato il numero massimo di persone di sera
				dayOffsundayFriday = DayOff.setDayOff(emplozeeTemp, save, date, dayOffsundayFriday);
				leadersAndEmployee.add(emplozeeTemp);
				employee.remove(0);
				

			}
			//log
			String logText = ("leadersAndEmployee is empty? " + employee.isEmpty());
			IOFile.writeLog("log", "log", logText);
			employee.clear();
			
			// Sort and move ArrayLeiter to employee array
			employee = sortLeadersAndEmployeeToEmployee(leadersAndEmployee, employee);
			
			//log
			for (int b = 0; b < dayOffsundayFriday.length ; b++) {
				logText = ("Giorni Liberi aggiunti X: " + b + ": "+ dayOffsundayFriday[b]);
				IOFile.writeLog("log", "log", logText);
			}
			
		return employee;
	}
	
	public static void setEmployeeOnShift (Employee employee, int shiftLineLeader, int lineShift, int moringNignt) {
		
		employee.setShiftLineLeader(shiftLineLeader);
		employee.setShiftLine(lineShift);
		employee.setMorningNight(moringNignt);
	}
	
	public static void setEmployeeOnShift (Employee employee, int shiftLineLeader, int lineShift) {
		
		employee.setShiftLineLeader(shiftLineLeader);
		employee.setShiftLine(lineShift);
	}
	
	public static ArrayList<Employee> sortLeadersAndEmployeeToEmployee (ArrayList<Employee> ArrayLeiter, ArrayList<Employee> employee) {

		ArrayList <Employee> line1Morning = new ArrayList<Employee>();
		ArrayList <Employee> line1Night = new ArrayList<Employee>();
		ArrayList <Employee> line2Morning = new ArrayList<Employee>();
		ArrayList <Employee> line2Night = new ArrayList<Employee>();

		for (int x = 0; x < ArrayLeiter.size(); x++) {
			Employee dip = ArrayLeiter.get(x);
			if (dip.getShiftLineLeader() == 1) {
				if(dip.getMorningNight() == 1) {
					line1Morning.add(0, dip);
				} else {
					line1Night.add(0, dip);
				}
			} else if (dip.getShiftLine() == 1) {
				if(dip.getMorningNight() == 1) {
					line1Morning.add(dip);
				} else {
					line1Night.add(dip);
				}
			} else if (dip.getShiftLineLeader() == 2) {
				if(dip.getMorningNight() == 1) {
					line2Morning.add(0, dip);
				} else {
					line2Night.add(0, dip);
				}
			} else if (dip.getShiftLine() == 2) {
				if(dip.getMorningNight() == 1) {
					line2Morning.add(dip);
				} else {
					line2Night.add(dip);
				}
			}
		}
		employee.addAll(line1Morning);
		employee.addAll(line2Morning);
		employee.addAll(line1Night);
		employee.addAll(line2Night);
		
		for (int x = 0; x < employee.size(); x++) {
			Employee employeeTemp = employee.get(x);
			String logText = (employeeTemp.getSurname() + " Line Leader: " + employeeTemp.getShiftLineLeader() +  " Line: "  + employeeTemp.getShiftLine());
			if (employeeTemp.getMorningNight() == 1) {
				logText = logText + " Morning";
			} else  {
				logText = logText + " Night";
			}
			
			IOFile.writeLog("log", "log", logText);
		}
		
		return employee;
	}
	
}