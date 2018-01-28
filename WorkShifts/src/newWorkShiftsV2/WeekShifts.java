package newWorkShiftsV2;

import java.util.ArrayList;

public class WeekShifts implements Cloneable{
	private ArrayList<OneDayShift> oneDayShifts = new ArrayList<>(6);
	private String idWeek = "";

	/**
	 * @return the weekShifts
	 */
	public ArrayList<OneDayShift> getOneDayShifts() {
		return oneDayShifts;
	}

	/**
	 * @param oneDayShifts
	 *            the weekShifts to set
	 */
	public void setOneDayShifts(ArrayList<OneDayShift> oneDayShifts) {
		this.oneDayShifts = oneDayShifts;
	}

	public OneDayShift getOneDayWeekShifts(int index) {
		return oneDayShifts.get(index);
	}
	
	public void addOneDayShift (OneDayShift oneDayShift) {
		this.oneDayShifts.add(oneDayShift);
	}

	/**
	 * @return the idWeek
	 */
	public String getIdWeek() {
		return idWeek;
	}

	/**
	 * @param idWeek the idWeek to set
	 */
	public void setIdWeek(String idWeek) {
		this.idWeek = idWeek;
	}
	@Override
    public WeekShifts clone() {
        try {
            return (WeekShifts) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new Error("Something impossible just happened");
        }
    }
}