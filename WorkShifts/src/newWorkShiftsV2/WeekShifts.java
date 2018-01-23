package newWorkShiftsV2;

import java.util.ArrayList;

public class WeekShifts {
	private ArrayList<OneDayShift> weekShifts = new ArrayList<>(6);
	private int idWeek = 0;

	/**
	 * @return the weekShifts
	 */
	public ArrayList<OneDayShift> getWeekShifts() {
		return weekShifts;
	}

	/**
	 * @param weekShifts
	 *            the weekShifts to set
	 */
	public void setWeekShifts(ArrayList<OneDayShift> weekShifts) {
		this.weekShifts = weekShifts;
	}

	public OneDayShift getOneDayWeekShifts(int index) {
		return weekShifts.get(index);
	}
	
	public void setOneDayShift (OneDayShift oneDayShift) {
		this.weekShifts.add(oneDayShift);
	}

	/**
	 * @return the idWeek
	 */
	public int getIdWeek() {
		return idWeek;
	}

	/**
	 * @param idWeek the idWeek to set
	 */
	public void setIdWeek(int idWeek) {
		this.idWeek = idWeek;
	}

}