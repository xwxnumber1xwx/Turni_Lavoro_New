
public class Stampa {
	public void StampaSuVideo (Dipendente dipendente, String [] turni) {
		System.out.print(dipendente.nome + " Stufe: " + dipendente.livello + " Linie: " + dipendente.lineaLavoro + "\n");
		int arrayLength = turni.length; //mi dice quanto è grande o lungo l'array ricevuto
		for (int x = 0; x < arrayLength; x++) {
			System.out.print(turni[x] + " \n");
		}
		System.out.println("Ore Notturne: " + dipendente.getOreNotturne());
		System.out.println("Ore Domenica: " + dipendente.getOreDomenica());
	}
	public void StampaSuCarta (Dipendente dipendente, String [] turni) {
		//Implementare metodo per stampare su carta
	}
}
