package newWorkShiftsV2;

import java.time.LocalTime;

public class OneDayShift {

	private boolean dayOff = false;
	private LocalTime startWorkTime = LocalTime.MIN;
	private LocalTime endWorkTime = LocalTime.MIN;

	/**
	 * @return the dayOff
	 */
	public boolean isDayOff() {
		return dayOff;
	}

	/**
	 * @param dayOff
	 *            the dayOff to set
	 */
	public void setDayOff(boolean dayOff) {
		this.dayOff = dayOff;
	}

	/**
	 * @return the startWorkTime
	 */
	public LocalTime getStartWorkTime() {
		return startWorkTime;
	}

	/**
	 * @param startWorkTime
	 *            the startWorkTime to set
	 */
	public void setStartWorkTime(LocalTime startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	/**
	 * @return the endWorkTime
	 */
	public LocalTime getEndWorkTime() {
		return endWorkTime;
	}

	/**
	 * @param endWorkTime
	 *            the endWorkTime to set
	 */
	public void setEndWorkTime(LocalTime endWorkTime) {
		this.endWorkTime = endWorkTime;
	}

	public String getStartEnd() {
		if (this.dayOff == true) {
			return "Day off";
		} else {
			return this.startWorkTime + "-" + this.endWorkTime;
		}
	}

}
