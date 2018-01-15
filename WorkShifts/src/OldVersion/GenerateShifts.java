
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class GenerateShifts {
	public static ArrayList<String> generateShift (Employee employee, LocalDate date, ArrayList<LocalDate> holiday) {
		/*Inserire:
			- Calcolare la malattia per pochi giorni
		*/
		Shift sett = new Shift();
		ArrayList <String> shiftEmployeeArrayList = new ArrayList<String>(6);
		int x = 0;
		LocalTime startMorning = sett.getStartMorning();
		LocalTime startFridayMorning = sett.getStartFridayMorning();
		LocalTime endFridayMorning = sett.getEndFridayMorning();
		LocalTime endMorning = sett.getEndMorning();
		LocalTime startNight = sett.getStartNight();
		LocalTime endNight = sett.getEndNight();
		LocalTime startFridayNightSundayOff = sett.getStartFridayNightSudayOff();
		LocalTime endFridayNightSundayOff = sett.getEndFridayNightSudayOff();
		LocalTime startFridayNight = sett.getStartFridayNight();
		LocalTime endFridayNight = sett.getEndFridayNight();
		LocalTime startSundayNight = sett.getStartSundayNight();
		LocalTime endSundayNight = sett.getEndSundayNight();
		LocalTime startSundayRate = LocalTime.of(00, 00);
		LocalTime endSundayRate = LocalTime.of(23, 59);
		LocalTime startNightRate = sett.getStartNightRate();
		LocalTime endNightRate = sett.getEndNightRate();
		ArrayList<LocalTime> nightRates = new ArrayList<LocalTime>(3);
		LocalTime zero = LocalTime.of(0, 0);
		DayOfWeek dayWeek = DayOfWeek.SUNDAY;
		boolean holidayAdded = false;
		
		String logText = (employee.getName() + " " + employee.getSurname());
		IOFile.writeLog("log", "log", logText);
		for (x = 0; x < 7; x++) {
			holidayAdded = false;
			if (x == employee.getDayOff()) {
				shiftEmployeeArrayList.add(employee.getDayOff(), "\n" + date + " " + sett.getWeek()[employee.getDayOff()][0] + "\t" + "dayOff");
				employee.setTime(date, zero, x);
			} else {
				if (employee.getDisease() == true) {
					shiftEmployeeArrayList.add(x, "\n" + date + " " + sett.getWeek()[x][0] + "\t" + " disease"); //scrive la malattia per quel giorno
					employee.setSicknessDays(1); //aggiunge 1 giorno malattia al conto delle malattie del dipendente. metodo ancora da sviluppare
					date = date.plusDays(1);
					dayWeek = dayWeek.plus(1);
					continue;
					}
				if (employee.getMorningNight() == 2) { // DA SISTEMARE LA TARIFFA NOTTURNA CON 24 ore di nachtzuschlag
					if (x == 0) { // working Sunday
						shiftEmployeeArrayList.add("\n" + date + " " + sett.getWeek()[x][x] + "\t" + sett.getWeek()[x][employee.getMorningNight()]);
						employee.setTime(date, startSundayNight, x);
						nightRates = Shift.calculateNightRates(startSundayNight, endSundayNight, startSundayRate, endSundayRate, date, dayWeek); //mi aggiunge il nachtzuschlag al conto delle ore di domenica e del totale del nachtzuschlag
						setNightTariffEmployee(employee, nightRates);
						
						//log
						logText = ("Night Rate added (Night)" + sett.getWeek()[x][0]+ " : " + Shift.calculateNightRates(startSundayNight, endSundayNight, startSundayRate, endSundayRate, date, dayWeek));
						IOFile.writeLog("log", "log", logText);
						
						date = date.plusDays(1);
						dayWeek = dayWeek.plus(1);
						continue;
					}
					if (x == 5 & employee.getDayOff() == 0) { // ven 18 perche domenica libera
						shiftEmployeeArrayList.add (x, "\n" + date + " " + sett.getWeek()[x][0] + "\t" + sett.getWeek()[x][employee.getMorningNight()+1]); //impostata Venerdi alle 18 se domenica NACHT è libero
						employee.setTime(date, startFridayNightSundayOff, x);
						nightRates = Shift.calculateNightRates(startFridayNightSundayOff, endFridayNightSundayOff, startNightRate, endNightRate, date, dayWeek);  //mi aggiunge il nachtzuschlag al conto delle ore di domenica e del totale del nachtzuschlag
						setNightTariffEmployee(employee, nightRates);
						
						//log
						logText = ("Night Rate added (Night)" + sett.getWeek()[x][0] + " : " + (Shift.calculateNightRates(startFridayNightSundayOff, endFridayNightSundayOff, startNightRate, endNightRate, date, dayWeek)));
						IOFile.writeLog("log", "log", logText);
																	
						date = date.plusDays(1);
						dayWeek = dayWeek.plus(1);
						continue;
					}
					if (x == 5 & employee.getDayOff() != 0) { //ven 15 perche ha lavorato di domenica
						nightRates = Shift.calculateNightRates(startFridayNight, endFridayNight, startNightRate, endNightRate, date, dayWeek);
						setNightTariffEmployee(employee, nightRates);
						shiftEmployeeArrayList.add(x, "\n" + date + " " + sett.getWeek()[x][0] + "\t" + sett.getWeek()[x][employee.getMorningNight()]); //Scrive Il giono "domenica, lunedi, ecc.." oppure "libero"
						employee.setTime(date, startFridayNight, x);
						
						//log
						logText = ("Night Rate added (Night)" + sett.getWeek()[x][0] + " : " + Shift.calculateNightRates(startFridayNight, endFridayNight, startNightRate, endNightRate, date, dayWeek));
						IOFile.writeLog("log", "log", logText);
						
						date = date.plusDays(1);
						dayWeek = dayWeek.plus(1);
						continue;
					}
				}
				if (employee.getHolidayThisWeek() == true) {
					for (int z = 0; z < holiday.size(); z++) {
						if (date.isEqual(holiday.get(z)) == true) {
						shiftEmployeeArrayList.add(x, "\n" + date + " " + sett.getWeek()[x][0] + "\t" + "holiday");
						holidayAdded = true;
						logText = ("holiday Added to " + employee.getSurname() + " " + employee.getName() + " on " + date);
						IOFile.writeLog("log", "log", logText);
						}
					}
				}
				if (holidayAdded == false) {
					// x = 1, 2, 3, 4, 5, 6; M, T, W, T, S
					shiftEmployeeArrayList.add(x, "\n" + date + " " + sett.getWeek()[x][0] + "\t" + sett.getWeek()[x][employee.getMorningNight()]); //Scrive il resto dei giorni non scritti "domenica, lunedi, ecc.." oppure "libero"
					
					if (employee.getMorningNight() == 1 & x != 6 & x != 0) { //mattina
						if (x != 5) {
						nightRates = Shift.calculateNightRates(startMorning, endMorning, startNightRate, endNightRate, date, dayWeek);
						employee.setTime(date, startMorning, x);
						setNightTariffEmployee(employee, nightRates);
						
						//log
						logText = ("Night Rate added (Morning)" + sett.getWeek()[x][0] + " : " + Shift.calculateNightRates(startMorning, endMorning, startNightRate, endNightRate, date, dayWeek));
						IOFile.writeLog("log", "log", logText);
						} else { // Friday 2:00
							nightRates = Shift.calculateNightRates(startFridayMorning, endFridayMorning, startNightRate, endNightRate, date, dayWeek);
							employee.setTime(date, startMorning, x);
							setNightTariffEmployee(employee, nightRates);
							//log
							logText = ("Night Rate added (Morning)" + sett.getWeek()[x][0] + " : " + Shift.calculateNightRates(startFridayMorning, endFridayMorning, startNightRate, endNightRate, date, dayWeek));
							IOFile.writeLog("log", "log", logText);
						}
					}
					if (employee.getMorningNight() == 2 & x != 6 & x != employee.getDayOff()) {
						nightRates = Shift.calculateNightRates(startNight, endNight, startNightRate, endNightRate, date, dayWeek);
						employee.setTime(date, startNight, x);
						setNightTariffEmployee(employee, nightRates);
						
						//log
						logText = ("Night Rate added (Night)"  + sett.getWeek()[x][0] + " : " + Shift.calculateNightRates(startNight, endNight, startNightRate, endNightRate, date, dayWeek));
						IOFile.writeLog("log", "log", logText);
						
					}
					}
					if (x == 6) { //Sabato
						employee.setTime(date, zero, x);
					}
			}
			date = date.plusDays(1);
			dayWeek = dayWeek.plus(1);
		}

		return shiftEmployeeArrayList;
	}
	
	public static void setNightTariffEmployee (Employee employee, ArrayList<LocalTime> nightlyRates) {
		
		employee.setNightRate(nightlyRates.get(0));
		employee.setSundayRate(nightlyRates.get(1));
		employee.setHolidayRate(nightlyRates.get(2));
	}
}

