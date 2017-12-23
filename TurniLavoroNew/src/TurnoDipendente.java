import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TurnoDipendente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 00000002L;
	LocalDate date;
	LocalTime Sunday;
	LocalTime Monday;
	LocalTime Tuesday;
	LocalTime Wednesday;
	LocalTime Thursday;
	LocalTime Friday;
	LocalTime Saturday;
	
	
	
	
	public void setTime (LocalDate date, LocalTime dayLT, int FreeDay, int day) {
		switch (day) {
			case 0: this.Sunday = dayLT;
			break;
			
			case 1: this.Monday = dayLT;
			break;
			
			case 2: this.Tuesday = dayLT;
			break;
			
			case 3: this.Wednesday = dayLT;
			break;
			
			case 4: this.Thursday = dayLT;
			break;
			
			case 5: this.Friday = dayLT;
			break;
			
			case 6: this.Saturday = dayLT;
			break;
		}
	}
	
	public LocalDateTime getTime (int day) {
		LocalTime dayLT;
		switch (day) {
			case 0: dayLT = Sunday;
			break;
			
			case 1: dayLT = Monday;
			break;
			
			case 2: dayLT = Tuesday;
			break;
			
			case 3: dayLT = Wednesday;
			break;
			
			case 4: dayLT = Thursday;
			break;
			
			case 5: dayLT = Friday;
			break;
			
			case 6: dayLT = Saturday;
			break;
			
			default: dayLT =  LocalTime.of(0, 0);
			break;
		}
		LocalDateTime dayLDT = LocalDateTime.of(date, dayLT);
		return dayLDT;
	}
}
