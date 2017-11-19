import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Stampa {
	public void StampaSuVideo (Dipendente dipendente, ArrayList<String> Turni, int LineaLavoro) {
		System.out.print(dipendente.nome + " Stufe: " + dipendente.livello + " Linie: " + LineaLavoro + "\n");
		Turni.forEach(System.out::println);
		System.out.println("Ore Notturne: " + dipendente.getOreNotturneLT());
		System.out.println("Ore Domenica: " + dipendente.getOreDomenicaLT());
		System.out.println("Giorni Malattia: " + dipendente.getGiorniMalattia());
		System.out.println("Somma calcolata con % Zuschlag: " + dipendente.getTotZuSchlag());

	}
}
