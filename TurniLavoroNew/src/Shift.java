import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class Shift {
	LocalTime startFridayMorning = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("MORNING_FRIDAY_START_H")), Integer.parseInt(Proprieties.getOnePropriety("MORNING_FRIDAY_START_M")));
	LocalTime endFridayMorning = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("MORNING_FRIDAY_END_H")), Integer.parseInt(Proprieties.getOnePropriety("MORNING_FRIDAY_END_M")));
	LocalTime startMorning = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("MORNING_START_H")), Integer.parseInt(Proprieties.getOnePropriety("MORNING_START_M")));
	LocalTime endMorning = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("MORNING_FRIDAY_END_H")), Integer.parseInt(Proprieties.getOnePropriety("MORNING_FRIDAY_END_M")));
	LocalTime startNight = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_START_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHT_START_M")));
	LocalTime endNight = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_END_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHT_END_M")));
	LocalTime endNightRate = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHTRATE_END_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHTRATE_END_M")));
	LocalTime startNightRate = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHTRATE_START_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHTRATE_START_M")));
	LocalTime startFridayNightSudayOff = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_FRIDAY_SUNDAYOFF_START_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHT_FRIDAY_SUNDAYOFF_START_M")));
	LocalTime endFridayNightSudayOff = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_FRIDAY_SUNDAYOFF_END_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHT_FRIDAY_SUNDAYOFF_END_M")));
	LocalTime startFridayNight = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_FRIDAY_START_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHT_FRIDAY_START_M")));
	LocalTime endFridayNight = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_FRIDAY_END_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHT_FRIDAY_END_M")));
	LocalTime startSundayNight = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_SUNDAY_START_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHT_SUNDAY_START_M")));
	LocalTime endSundayNight = LocalTime.of(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_SUNDAY_END_H")), Integer.parseInt(Proprieties.getOnePropriety("NIGHT_SUNDAY_END_M")));

	String night = startNight.toString() + "-" + endNight.toString();
	String sundayNight = startSundayNight.toString() + "-" + endSundayNight.toString();
	String morning =  startMorning.toString() + "-" + endMorning.toString();
	String fridayMorning = startFridayMorning.toString() + "-" + endFridayMorning.toString();
	String fridayNight = startFridayNight.toString() + "-" + endFridayNight.toString();
	String fridayNightSundayOff = startFridayNightSudayOff.toString() + "-" + endFridayNightSudayOff.toString();
	
	
	DayOfWeek sunday = DayOfWeek.SUNDAY;
	DayOfWeek monday = DayOfWeek.MONDAY;
	DayOfWeek tuesday = DayOfWeek.TUESDAY;
	DayOfWeek wednesday = DayOfWeek.WEDNESDAY;
	DayOfWeek thursday = DayOfWeek.THURSDAY;
	DayOfWeek friday = DayOfWeek.FRIDAY;
	DayOfWeek saturday = DayOfWeek.SATURDAY;
	TextStyle stileNorm = TextStyle.FULL;
	Locale deutsch = Locale.GERMAN;
	Locale english = Locale.ENGLISH;
	String week [][] = {
		{sunday.getDisplayName(stileNorm, english), "dayOff", sundayNight},	
		{monday.getDisplayName(stileNorm, english), morning, night},
		{tuesday.getDisplayName(stileNorm, english), morning, night},
		{wednesday.getDisplayName(stileNorm, english), morning, night},
		{thursday.getDisplayName(stileNorm, english), morning, night},
		{friday.getDisplayName(stileNorm, english),fridayMorning, fridayNight, fridayNightSundayOff},
		{saturday.getDisplayName(stileNorm, english), "dayOff", "dayOff"}
		};
	
	public String[][] getWeek() {
		return this.week;
	}
			
	public LocalTime getStartMorning () {
		return startMorning;
	}
	
	public LocalTime getEndMorning() {
		return endMorning;
	}
	
	public LocalTime getStartNight() {
		return startNight;
	}
	
	public LocalTime getEndNight() {
		return endNight;
	}
	
	public LocalTime getEndNightRate() {
		return endNightRate;
	}
	
	public LocalTime getStartNightRate() {
		return startNightRate;
	}
	
	public LocalTime getStartFridayMorning() {
		return startFridayMorning;
	}
	public LocalTime getEndFridayMorning() {
		return endFridayMorning;
	}
	
	public LocalTime getStartFridayNightSudayOff () {
		return startFridayNightSudayOff;
	}
	
	public LocalTime getEndFridayNightSudayOff () {
		return endFridayNightSudayOff;
	}
	
	public LocalTime getStartFridayNight() {
		return startFridayNight;
	}
	
	public LocalTime getEndFridayNight () {
		return endFridayNight;
	}
	
	public LocalTime getStartSundayNight () {
		return startSundayNight;
	}
	public LocalTime getEndSundayNight () {
		return endSundayNight;
	}
	public static ArrayList<LocalTime> calculateNightRates (LocalTime start, LocalTime end, LocalTime startNightRate, LocalTime endNightRate, LocalDate date, DayOfWeek day) {
												//example		// 15			//23 				//21:00					//4:00			//2017.11.26		Sunday
		ArrayList<LocalTime> nightlyRates = new ArrayList<LocalTime>(3);
		LocalDateTime borderLDT = LocalDateTime.of(date, LocalTime.of(23, 59));
		LocalTime nightRate = LocalTime.of(00, 00);
		LocalTime holidayRate = LocalTime.of(00, 00);
		LocalTime sundayRate = LocalTime.of(00, 00);
		LocalDateTime startNightRateLDT = LocalDateTime.of(date, startNightRate);
		date = date.plusDays(1);
		LocalDateTime endNightRateLDT = LocalDateTime.of(date, endNightRate);
		date = date.minusDays(1);
		LocalDateTime startLDT = LocalDateTime.of(date, start);
		LocalDateTime endLDT;
		if (end.isBefore(start)) {
			date = date.plusDays(1);
			endLDT = LocalDateTime.of(date, end);
			date = date.minusDays(1);
		}else {
			endLDT = LocalDateTime.of(date, end);
		}
		while (startLDT.isBefore(endLDT) || startLDT.compareTo(endLDT) == 0) {
		if ((startLDT.isAfter(startNightRateLDT) & startLDT.isBefore(endNightRateLDT)) || (startLDT.isAfter(startNightRateLDT.minusDays(1)) & startLDT.isBefore(endNightRateLDT.minusDays(1)))) { //COMPRERSO TRA 21 e 4
			if (day == DayOfWeek.SUNDAY) {
				if (startLDT.isAfter(borderLDT) == true) {
					nightRate = nightRate.plusMinutes(1);
				} else {
					sundayRate = sundayRate.plusMinutes(1);
				} 
			}	
			if (day == DayOfWeek.SATURDAY) {
					if (startLDT.isAfter(borderLDT) == true) {
						sundayRate = sundayRate.plusMinutes(1);
					} else {
						nightRate = nightRate.plusMinutes(1);
				}
			}
			if ((day != DayOfWeek.SATURDAY) && (day != DayOfWeek.SUNDAY) == true) {
				nightRate = nightRate.plusMinutes(1);
			}
		}
		startLDT = startLDT.plusMinutes(1);
		}
		nightlyRates.add(nightRate);
		nightlyRates.add(sundayRate);
		nightlyRates.add(holidayRate);
		return nightlyRates;
	}
}
