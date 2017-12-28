import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FreeDay {
	public static ArrayList<Dipendente> addFreeDay(ArrayList<Dipendente> dipendenteArrayList, IOFile save) {
		Scanner scan = new Scanner (System.in);
		boolean finded = false;
		do {
			System.out.println("inserire cognome dipendente");
			String surname = scan.nextLine();
			surname = scan.nextLine();
				Dipendente dipendente;
				for (int x=0; x < dipendenteArrayList.size(); x++) {
					dipendente = dipendenteArrayList.get(x);
					String cognome = dipendente.getCognome().toLowerCase();
					if (cognome.compareTo(surname.toLowerCase()) == 0) {
							finded = true;
						System.out.println(dipendente.getCognome() + " quale Giorno Libero? DDMMYY per giorno libero, 1 per uscire");
							long free = scan.nextLong();
							if (free != 1) {
									int DD = (int) (free/10000);
									int MM = (int) (free - (DD*10000));
									MM = MM /100;
									int YY = (int) (free - (DD*10000) - (MM*100) + (2000));
									LocalDate freeDay = LocalDate.of(YY, MM, DD);
									save.freeday(dipendente, freeDay, "frei_als_wunch");
									
							}
					}
				}
				if (finded == false) {
					System.out.println("dipendente non trovato");
			}
		} while (finded == false);
		return dipendenteArrayList;
	}
	
	public static int[] setFreeDay(Dipendente dipendente, IOFile save, LocalDate date, int[] DoVe) {
		int giornoLibero = 0;
		dipendente.setFreeThisWeek(false);
		dipendente.setGiornoLibero(0);
		dipendente.setGiornoLiberoLD(date);
		if (dipendente.getTagNacht() == 2) {
			giornoLibero = 4; // Inizio giovedi così chi ha poche ore notturne accumultate non ha la domenica libera perché il metodo a seguire attribuirà al primo dipendente la domenica libera
		}
		ArrayList <LocalDate> giornoLiberoArray = new ArrayList<LocalDate>();
		LocalDate giornoLiberoLD = date;
				giornoLiberoArray = save.checkFreeDay(dipendente, "frei_als_wunch");
				if (giornoLiberoArray.size() != 0) { 
					for (int y = 0; y < giornoLiberoArray.size(); y++)
						if (giornoLiberoArray.get(y).isAfter(date) && giornoLiberoArray.get(y).isBefore(date.plusDays(7)) || giornoLiberoArray.get(y).isEqual(date)) {
						//	for (int y = 0; y < giornoLiberoArray.size(); y++) {
							dipendente.setFreeThisWeek(true);
							giornoLiberoLD = giornoLiberoArray.get(y);
							DayOfWeek DoW = DayOfWeek.from(giornoLiberoArray.get(y));
							if (DoW == DayOfWeek.SUNDAY) {
								giornoLibero = 0;
								DoVe[0] ++;
							} else if (DoW == DayOfWeek.MONDAY) {
								giornoLibero = 1;
								DoVe[1] ++;
							} else if (DoW == DayOfWeek.TUESDAY) {
								giornoLibero = 2;
								DoVe[2] ++;
							} else if (DoW == DayOfWeek.WEDNESDAY) {
								giornoLibero = 3;
								DoVe[3] ++;
							} else if (DoW == DayOfWeek.THURSDAY) {
								giornoLibero = 4;
								DoVe[4] ++;
							} else if (DoW == DayOfWeek.WEDNESDAY) {
								giornoLibero = 5;
							}
							dipendente.setGiornoLibero(giornoLibero);
							giornoLiberoLD = date.plusDays(giornoLibero);
							dipendente.setGiornoLiberoLD(giornoLiberoLD);
						} else {
							DoVe = FreeDay.setRandomFreeDay(dipendente, DoVe, giornoLibero);
						}
					} else {
						DoVe = FreeDay.setRandomFreeDay(dipendente, DoVe, giornoLibero);
					}
				return DoVe;
	}
	
	public static int [] setRandomFreeDay (Dipendente dipendente, int [] DoVe, int giornoLibero) {
		if (dipendente.getTagNacht() == 2) { 
			for (int u = DoVe.length-2; u > -1; u--) {
					if (DoVe[u+1] > DoVe[u]) {
						giornoLibero = u;
						for (int i=u-2; i > 0 ; i--) {
							if (DoVe[u]>DoVe[i]) {
								u = i;
								giornoLibero = u;
							}
						}
					} 											
			}		
			dipendente.setGiornoLibero(giornoLibero);
			DoVe[giornoLibero]++;
			}
		return DoVe;
	}
}
