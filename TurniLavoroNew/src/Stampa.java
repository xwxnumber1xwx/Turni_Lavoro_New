import java.util.ArrayList;

public class Stampa {
	public void StampaSuVideo (Dipendente dipendente, ArrayList<String> Turni, int LineaLavoro) {
		System.out.print(dipendente.nome + " Stufe: " + dipendente.livello + " Linie: " + LineaLavoro + "\n");
		Turni.forEach(System.out::println);
		System.out.println("Minuti lavorati di Notte: " + dipendente.getOreNotturne());
		System.out.println("Minuti lavorati di Domenica: " + dipendente.getOreDomenica());
		System.out.println("Giorni Malattia: " + dipendente.getGiorniMalattia());
		System.out.println("Somma minuti guadagno rapportato all 100% dello stipendio " + dipendente.getTotZuSchlag());

	}
}
