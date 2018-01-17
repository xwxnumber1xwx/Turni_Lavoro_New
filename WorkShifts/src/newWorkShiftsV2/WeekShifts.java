package newWorkShiftsV2;

import java.util.ArrayList;

public class WeekShifts {
	private ArrayList<OneDayShift> weekShifts = new ArrayList<>(6);
	private String Sunday = "", Monday = "", Tuesday = "", Wednesday = "", Thursday = "", Friday = "", Saturday = "";

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
	
	public String getSunday () {
		String day = "";
		day = setdayOff(0, weekShifts);
		this.Sunday = day;
		return day;
	}
	
	public String getMonday () {
		String day = "";
		day = setdayOff(1, weekShifts);
		this.Monday = day;
		return day;
	}
	
	public String getTuesday () {
		String day = "";
		day = setdayOff(2, weekShifts);
		this.Tuesday = day;
		return day;
	}
	
	public String getWednesday () {
		String day = "";
		day = setdayOff(3, weekShifts);
		this.Wednesday = day;
		return day;
	}
	
	public String getThursday () {
		String day = "";
		day = setdayOff(4, weekShifts);
		this.Thursday = day;
		return day;
	}
	
	public String getFriday () {
		String day = "";
		day = setdayOff(5, weekShifts);
		this.Friday = day;
		return day;
	}
	
	public String getSaturday () {
		String day = "";
		day = setdayOff(5, weekShifts);
		this.Saturday = day;
		return day;
	}
	
	
	public static String setdayOff(int day, ArrayList<OneDayShift> weekShifts) {
		String result = "";
		if (weekShifts.get(day).isDayOff()) {
			result = "Day-off";
			} else {
				result = weekShifts.get(day).getStartWorkTime() + "-" + weekShifts.get(day).getEndWorkTime();
				}
		return result;
	}

}