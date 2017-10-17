import java.util.Random;
import java.io.*;

class TurniLavoroNew {

	public static void main(String[] args)
		throws IOException { //Sicurezza per eventuali errori di Input e Output
		//Dipendente dipendente = new Dipendente();
		// Creo 5 dipendenti come prova su un array di dipendenti
		Dipendente dipendente[];
		dipendente = new Dipendente[4];
		Stampa stampa = new Stampa();
		for (int i = 0; i < 4; i++) {
			dipendente[i] = new Dipendente();
			dipendente[i].setNome("Dipendente n: " + i);
			dipendente[i].setLivello(4);
			dipendente[i].setLineaLavoro(1);
			int OrarioLavorativo = 1; // 1 per TAG, 2 per NACHT
			int giornoLibero = 0;
				OrarioLavorativo = new Random () .nextInt(2) + 1;
					if (OrarioLavorativo == 2) { 
							giornoLibero = new  Random() .nextInt(5) ; // 0 per DOM, 1 per LUN, 2 per MAR, 3 ... VEN non si è mai liberi.
					}
			stampa.StampaSuVideo(dipendente[i], SwitchTurni.generaTurni(giornoLibero, OrarioLavorativo, dipendente[i]));
			System.out.println("\n");
			
		}
		
		
		
	
		//Inserire inserimento da Tastiera su Database
		//Chiedere se qualcuno è malato, le OreNotturne, OreFestivita, OreDomeniche le riceverà comunque 
		
		
			
		//Inserire lettura da Database
		//Implementare metodo per quanto riguarda: Licenziamenti, azubi o lifeFirma che se ne vanno.
		//Inserire Matrice con turni di tutti i Dipendenti, poi salvarla su database
		
		
		}

}