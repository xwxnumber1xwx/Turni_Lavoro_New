
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Employee implements Serializable {
	private static final long serialVersionUID = 00000002L;
	long employeeCode = 0;
	String name;
	String surname;
	int rank = 0;
	int workLine = 0;
	int shiftLine = 0;
	int shiftLineLeader = 0;
	int lineLeader = 0;
	boolean onlyMorning = false;
	boolean dayOffThisWeek = false;
	private boolean holidayThisWeek = false;
	private double nightRate = 0;
	private double holidayRate = 0;
	private double sundayRate = 0;
	private int sicknessDays = 0;
	boolean disease = false;
	double allNightRates = 0D;
	int dayOff = 0;
	//LocalDate dayOffLD;
	int morningNight = 1; //1 = TAG default
	ArrayList<String> weekShift = new ArrayList<String>();
	LocalDateTime Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday = LocalDateTime.MIN;
	
	public void setEmployeeCode (long employeeCode) {
		this.employeeCode = employeeCode;
	}
	
	public long getEmployeeCode () {
		return this.employeeCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname (String surname) {
		this.surname = surname;
	}
	
	public int getRank () {
		return rank;
	}
	public void setRank (int rank) {
		this.rank = rank;
	}
	public int getWorkLine () {
		return workLine;
	}
	public void setWorkLine (int workLine) {
		this.workLine = workLine;
	}
	
	public int getShiftLine () {
		return shiftLine;
	}
	public void setShiftLineLeader (int shiftLineLeader) {
		this.shiftLineLeader = shiftLineLeader;
	}
	public int getShiftLineLeader () {
		return shiftLineLeader;
	}
	public void setShiftLine (int i) {
		this.shiftLine = i;
	}
	public void setSicknessDays (int sicknessDays) {
		this.sicknessDays += sicknessDays;
	}
	public int getSicknessDays () {
		return sicknessDays;
	}
	public Double getAllNightRates () {
		return allNightRates;
	}
	public void setAllNightRates (Double allNightRates) {
		this.allNightRates += allNightRates;
	}
	public void initNightRates () {
		this.allNightRates = 0;
		this.sundayRate = 0;
		this.holidayRate = 0;
		this.nightRate = 0;
	}
	
	public void setNightRate(LocalTime nightRate) {
	int OreNotturneInt = ((nightRate.getHour() * 60) + nightRate.getMinute());
	this.nightRate = OreNotturneInt;
	double percentuale = (double)(Integer.parseInt(Proprieties.getOnePropriety("NIGHT_RATE")));
	double setTotZuSchlag = 0D;
	setTotZuSchlag = (percentuale/100)*OreNotturneInt;
	setAllNightRates(setTotZuSchlag);
	}
	public Double getNightRate() {
		return nightRate;
	}
	public void setHolidayRate(LocalTime holidayRate) {
		int OreFestivitaInt = ((holidayRate.getHour() * 60) + holidayRate.getMinute());
		this.holidayRate = OreFestivitaInt;
		double percentuale = (double)(Integer.parseInt(Proprieties.getOnePropriety("HOLIDAY_RATE")));
		double setTotZuSchlag = 0D;
			setTotZuSchlag = (percentuale/100)*OreFestivitaInt;
		setAllNightRates(setTotZuSchlag);
	}
	public double getHolidayRate() {
		return holidayRate;
	}
	public void setSundayRate(LocalTime sundayRate) {
		int OreDomenicaInt = ((sundayRate.getHour() * 60) + sundayRate.getMinute());
		this.sundayRate = OreDomenicaInt;
		double percentuale = (double)(Integer.parseInt(Proprieties.getOnePropriety("SUNDAY_RATE")));
		double setTotZuSchlag = 0D;
		setTotZuSchlag = (percentuale/100)*OreDomenicaInt;
		setAllNightRates(setTotZuSchlag);
	}
	public double getSundayRate() {
		return sundayRate;
	}
	/*
	public void setDayOffLD (LocalDate dayOffLD) {
		this.dayOffLD = dayOffLD;
	}
	public LocalDate getDayOffLD() {
		return dayOffLD;
	}
	*/
	public void setDayOff (int dayOff) {
		this.dayOff = dayOff;
	}
	public int getDayOff() {
		return dayOff;
	}
	public void setMorningNight (int morningNight) {
		this.morningNight = morningNight;
	}
	public int getMorningNight() {
		return morningNight;
	}
	public void setWeekShift(ArrayList<String> weekShift) {
		this.weekShift = weekShift;
	}
	public ArrayList<String> getWeekShift() {
		return weekShift;
	}	
	
	public void setOnlyMorning (boolean onlyMorning) {
		this.onlyMorning = onlyMorning;
	}
	public boolean getOnlyMorning () {
		return this.onlyMorning;
	}
	public void setLineLeader (int lineLeader) {
		this.lineLeader = lineLeader;
	}
	public int getlineLeader () {
		return this.lineLeader;
	}
	
	public void setDayOffThisWeek (boolean dayOffThisWeek) {
		this.dayOffThisWeek = dayOffThisWeek;
	}
	
	public boolean getDayOffThisWeek () {
		return dayOffThisWeek;
	}
	
	public void setDisease (boolean disease) {
		this.disease = disease;
	}
	
	public boolean getDisease () {
		return disease;
	}
	public void setTime (LocalDate date, LocalTime dayLT, int dayINT) {
		switch (dayINT) {
			case 0: this.Sunday = LocalDateTime.of(date, dayLT);
			break;
			
			case 1: this.Monday = LocalDateTime.of(date, dayLT);
			break;
			
			case 2: this.Tuesday = LocalDateTime.of(date, dayLT);
			break;
			
			case 3: this.Wednesday = LocalDateTime.of(date, dayLT);
			break;
			
			case 4: this.Thursday = LocalDateTime.of(date, dayLT);
			break;
			
			case 5: this.Friday = LocalDateTime.of(date, dayLT);
			break;
			
			case 6: this.Saturday = LocalDateTime.of(date, dayLT);
			break;
		}
	}
	
	public LocalDateTime getTime (int day) {
		LocalDateTime dayLDT = LocalDateTime.MIN;
		switch (day) {
			case 0: dayLDT = Sunday;
			break;
			
			case 1: dayLDT = Monday;
			break;
			
			case 2: dayLDT = Tuesday;
			break;
			
			case 3: dayLDT = Wednesday;
			break;
			
			case 4: dayLDT = Thursday;
			break;
			
			case 5: dayLDT = Friday;
			break;
			
			case 6: dayLDT = Saturday;
			break;
			
		}
		return dayLDT;
	}

	public boolean getHolidayThisWeek() {
		return holidayThisWeek;
	}

	public void setHolidayThisWeek(boolean holidayThisWeek) {
		this.holidayThisWeek = holidayThisWeek;
	}
}