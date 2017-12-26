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
}
