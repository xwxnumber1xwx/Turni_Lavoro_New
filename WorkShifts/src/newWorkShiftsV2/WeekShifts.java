package newWorkShiftsV2;

import java.util.ArrayList;

public class WeekShifts {
	private ArrayList <OneDayShift> weekShifts = new ArrayList<>();

	/**
	 * @return the weekShifts
	 */
	public ArrayList <OneDayShift> getWeekShifts() {
		return weekShifts;
	}

	/**
	 * @param weekShifts the weekShifts to set
	 */
	public void setWeekShifts(ArrayList <OneDayShift> weekShifts) {
		this.weekShifts = weekShifts;
	}
}
