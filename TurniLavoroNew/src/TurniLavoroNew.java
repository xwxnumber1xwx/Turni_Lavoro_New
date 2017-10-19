import java.util.ArrayList;
import java.util.Random;
import java.io.*;

class TurniLavoroNew {

	public static void main(String[] args)
		throws IOException { //Sicurezza per eventuali errori di Input e Output
		// Creo 5 dipendenti come prova su un array di dipendenti
		ArrayList <Dipendente> dipendenteArrayList = new ArrayList<Dipendente>(6);
		Stampa stampa = new Stampa();
		for (int i = 0; i < 4; i++) {
			Dipendente dipendente  = new Dipendente();
			dipendente.setNome("DipendenteArray n: " + i);
			dipendente.setLivello(4);
			dipendente.setLineaLavoro(1);
			dipendente.malattia = false;
			dipendenteArrayList.add (dipendente);
			int OrarioLavorativo = 1; // 1 per TAG, 2 per NACHT
			int giornoLibero = 0; //serve per non far venire l'errore su Switchturni DA ELIMINARE
				OrarioLavorativo = new Random () .nextInt(2) + 1;
					if (OrarioLavorativo == 2) { 
							giornoLibero = new  Random() .nextInt(5) ; // 0 per DOM, 1 per LUN, 2 per MAR, 3 ... VEN non si è mai liberi.
					}
					giornoLibero = 0;
			//stampa.StampaSuVideo(dipendente, SwitchTurni.generaTurni(giornoLibero, OrarioLavorativo, dipendente, dipendente.malattia));
			stampa.StampaSuVideo(dipendente, SwitchTurni.generaTurni(giornoLibero, OrarioLavorativo, dipendente, dipendente.malattia));
			System.out.println("\n");
			
		}
		
		
		
	
		//Inserire inserimento da Tastiera su Database
		//Chiedere se qualcuno è malato, le OreNotturne, OreFestivita, OreDomeniche le riceverà comunque 
		
		
			
		//Inserire lettura da Database
		//Implementare metodo per quanto riguarda: Licenziamenti, azubi o lifeFirma che se ne vanno.
		//Inserire Matrice con turni di tutti i Dipendenti, poi salvarla su database
		
		
		}

}