package newWorkShiftsV2;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConditionEmployee {
	private boolean onlyMorning = false;
	private ArrayList<LocalDate> holiday = new ArrayList<LocalDate>();
	private ArrayList<LocalDate> disease = new ArrayList<LocalDate>();
	/**
	 * @return the onlyMorning
	 */
	public boolean isOnlyMorning() {
		return onlyMorning;
	}
	/**
	 * @param onlyMorning the onlyMorning to set
	 */
	public void setOnlyMorning(boolean onlyMorning) {
		this.onlyMorning = onlyMorning;
	}
	/**
	 * @return the holiday
	 */
	public ArrayList<LocalDate> getHoliday() {
		return holiday;
	}
	/**
	 * @param holiday the holiday to set
	 */
	public void setHoliday(ArrayList<LocalDate> holiday) {
		this.holiday = holiday;
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
	

}
