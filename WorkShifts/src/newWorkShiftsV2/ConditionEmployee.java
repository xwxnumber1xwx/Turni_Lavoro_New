package newWorkShiftsV2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ConditionEmployee {
	private long idEmployee;
	private ArrayList<LocalDate> holiday = new ArrayList<LocalDate>();
	private ArrayList<LocalDate> disease = new ArrayList<LocalDate>();

	/**
	 * @return the holiday
	 */
	public ArrayList<LocalDate> getHoliday() {
		return holiday;
	}
	/**
	 * @param holiday the holiday to set
	 */
	public void setHoliday(LocalDate holidayStart, LocalDate holidayEnd) {
		long until =  holidayStart.until(holidayEnd, ChronoUnit.DAYS) + 1;
		for (int x = 0; x < until; x++) {
			this.holiday.add(holidayStart);
			holidayStart = holidayStart.plusDays(1);
		}
		
	}
	/**
	 * @return the disease
	 */
	public ArrayList<LocalDate> getDisease() {
		return disease;
	}
	/**
	 * @param disease the disease to set
	 */
	public void setDisease(ArrayList<LocalDate> disease) {
		this.disease = disease;
	}
	/**
	 * @return the idEmployee
	 */
	public long getIdEmployee() {
		return idEmployee;
	}
	/**
	 * @param idEmployee the idEmployee to set
	 */
	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}
	

}
