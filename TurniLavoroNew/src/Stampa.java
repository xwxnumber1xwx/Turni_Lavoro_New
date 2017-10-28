import java.util.ArrayList;

public class Stampa {
	public void StampaSuVideo (Dipendente dipendente, ArrayList<String> Turni, int LineaLavoro) {
		System.out.print(dipendente.nome + " Stufe: " + dipendente.livello + " Linie: " + LineaLavoro + "\n");
		// a seguire causa ERRORE su arraylistr che è grande piu dei dati inseriti e quindi arrayListLenght è uguale a -1
		//int arrayListLenght = Turni.size();// mi da la lunghezza dell'arrayList
		//for (int x = 0; x < arrayListLenght; x++) {
		//	System.out.print(Turni.get(x) + " \n");
		//}
		Turni.forEach(System.out::println);
		System.out.println("Ore Notturne: " + dipendente.getOreNotturne());
		System.out.println("Ore Domenica: " + dipendente.getOreDomenica());
		System.out.println("Giorni Malattia: " + dipendente.getGiorniMalattia());
		System.out.println("Somma calcolata con % Zuschlag: " + dipendente.getTotZuSchlag());

	}
	public void StampaSuCarta (Dipendente dipendente, String [] turni) {
		//Implementare metodo per stampare su carta
	}
}
