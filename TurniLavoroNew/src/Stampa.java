import java.util.ArrayList;

public class Stampa {
	public void StampaSuVideo (Dipendente dipendente, ArrayList<String> Turni) {
		System.out.print(dipendente.nome + " Stufe: " + dipendente.livello + " Linie: " + dipendente.lineaLavoro + "\n");
		//int arrayLength = Turni.length; //mi dice quanto è grande o lungo l'array ricevuto
		// a seguire causa ERRORE su arraylistr che è grande piu dei dati inseriti e quindi arrayListLenght è uguale a -1
		int arrayListLenght = 6; //Turni.lastIndexOf(dipendente);// mi da l´indice dell ultimo elemento dipendente presente nell´arrayList
		for (int x = 0; x < arrayListLenght; x++) {
			System.out.print(Turni.get(x) + " \n");
		}
		System.out.println("Ore Notturne: " + dipendente.getOreNotturne());
		System.out.println("Ore Domenica: " + dipendente.getOreDomenica());
	}
	public void StampaSuCarta (Dipendente dipendente, String [] turni) {
		//Implementare metodo per stampare su carta
	}
}
