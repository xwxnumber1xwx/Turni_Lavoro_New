package newWorkShiftsV2;
import java.time.LocalDateTime;

public class OneDayShift {

		private boolean dayOff = false;
		private LocalDateTime startWorkTime = LocalDateTime.MIN;
		private LocalDateTime endWorkTime = LocalDateTime.MIN;


		/**
		 * @return the dayOff
		 */
		public boolean isDayOff() {
			return dayOff;
		}
		/**
		 * @param dayOff the dayOff to set
		 */
		public void setDayOff(boolean dayOff) {
			this.dayOff = dayOff;
		}
		/**
		 * @return the startWorkTime
		 */
		public LocalDateTime getStartWorkTime() {
			return startWorkTime;
		}
		/**
		 * @param startWorkTime the startWorkTime to set
		 */
		public void setStartWorkTime(LocalDateTime startWorkTime) {
			this.startWorkTime = startWorkTime;
		}
		/**
		 * @return the endWorkTime
		 */
		public LocalDateTime getEndWorkTime() {
			return endWorkTime;
		}
		/**
		 * @param endWorkTime the endWorkTime to set
		 */
		public void setEndWorkTime(LocalDateTime endWorkTime) {
			this.endWorkTime = endWorkTime;
		}	
		
		
	
}
