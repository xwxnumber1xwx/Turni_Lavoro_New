package newWorkShiftsV2;

import java.util.ArrayList;

public class WorkingLine {
	private String nameLine;
	private ArrayList <WeekShifts> shift = new ArrayList<WeekShifts>();
	/**
	 * @return the nameLine
	 */
	public String getNameLine() {
		return nameLine;
	}
	/**
	 * @param nameLine the nameLine to set
	 */
	public void setNameLine(String nameLine) {
		this.nameLine = nameLine;
	}
	/**
	 * @return the shift
	 */
	public ArrayList <WeekShifts> getShift() {
		return shift;
	}
	/**
	 * @param shift the shift to set
	 */
	public void setShift(ArrayList <WeekShifts> shift) {
		this.shift = shift;
	}
	
	
	public void addOneShift (WeekShifts shift) {
		this.shift.add(shift);
		
	}
	
	public boolean deleteWeekShift(String idWeekShift) {
		boolean yesNo = false;
		int size = shift.size();
		for (int x = 0; x < size;  x++) {
			if (shift.get(x).getIdWeek() == idWeekShift) {
				shift.remove(x);
				size = shift.size();
				yesNo = true;
			}
		}
		return yesNo;
	}

}
