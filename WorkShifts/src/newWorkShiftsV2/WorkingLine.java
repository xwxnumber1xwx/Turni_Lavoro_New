package newWorkShiftsV2;

import java.util.ArrayList;

public class WorkingLine implements Cloneable{
	private String nameLine;
	private ArrayList <WeekShifts> weekShifts = new ArrayList<WeekShifts>();
	/**
	 * @return the nameLine
	 */
	public String getNameLine() {
		return this.nameLine;
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
	public ArrayList <WeekShifts> getWeekShifts() {
		return this.weekShifts;
	}
	
	public ArrayList <WeekShifts> getShiftWhitOutID() {
		
		for (int x = 0; x < weekShifts.size(); x++) {
			
		}
		return this.weekShifts;
	}
	/**
	 * @param shift the shift to set
	 */
	public void setWeekShifts(ArrayList <WeekShifts> shift) {
		this.weekShifts = shift;
	}
	
	
	public void addOneWeekShift (WeekShifts weekShift) {
		this.weekShifts.add(weekShift);
		
	}
	
	public boolean deleteWeekShift(String idWeekShift) {
		boolean yesNo = false;
		int size = weekShifts.size();
		for (int x = 0; x < size;  x++) {
			if (weekShifts.get(x).getIdWeek() == idWeekShift) {
				weekShifts.remove(x);
				size = weekShifts.size();
				yesNo = true;
			}
		}
		return yesNo;
	}
	
	@Override
    public WorkingLine clone() {
        try {
            return (WorkingLine) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new Error("Something impossible just happened");
        }
    }

}
