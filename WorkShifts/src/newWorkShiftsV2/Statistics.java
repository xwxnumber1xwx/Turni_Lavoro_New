package newWorkShiftsV2;

import java.time.LocalDate;
import java.util.ArrayList;

public class Statistics {
	private ArrayList<Double> nightRates = new ArrayList<Double>();
	private ArrayList<Double> sundayRates = new ArrayList<Double>();
	private ArrayList<Double> holidayRates = new ArrayList<Double>();
	private ArrayList<LocalDate> disease = new ArrayList<LocalDate>();
	/**
	 * @return the nightRates
	 */
	public ArrayList<Double> getNightRates() {
		return nightRates;
	}
	/**
	 * @param nightRates the nightRates to set
	 */
	public void setNightRates(Double nightRates) {
		this.nightRates.add(nightRates);
	}
	/**
	 * @return the sundayRates
	 */
	public ArrayList<Double> getSundayRates() {
		return sundayRates;
	}
	/**
	 * @param sundayRates the sundayRates to set
	 */
	public void setSundayRates(Double sundayRates) {
		this.sundayRates.add(sundayRates);
	}
	/**
	 * @return the holidayRates
	 */
	public ArrayList<Double> getHolidayRates() {
		return holidayRates;
	}
	/**
	 * @param holidayRates the holidayRates to set
	 */
	public void setHolidayRates(Double holidayRates) {
		this.holidayRates.add(holidayRates);
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
	public void setDisease(LocalDate disease) {
		this.disease.add(disease);
	}
	

}
