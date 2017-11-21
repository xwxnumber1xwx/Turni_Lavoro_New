import java.util.ArrayList;
import java.util.Random;
import java.io.*;


class TurniLavoroNew {
	
	public static void main(String[] args)
		throws IOException { //Sicurezza per eventuali errori di Input e Output
				// Creo 9 dipendenti come prova su un array di dipendenti
				String filenome = "options.proprieties";
				if (Preferenze.FileExist(filenome) == false) {
				Preferenze.InitFile();
				}
				ArrayList <Dipendente> dipendenteArrayList = new ArrayList<Dipendente>(9);
				Stampa stampa = new Stampa();
				int LineaLavoro = 1; // da AGGIUNGERE funzione per determinare quale line lavoro
				int Nacht = 0;
				int TagNacht = 1;
				int NumMitarbeiterLinee = 1;
				int minNacht = Integer.parseInt(Preferenze.getOnePreference("NACHT_MIN_MITARBEITER"));
				int NumMitarbeiterNacht = Integer.parseInt(Preferenze.getOnePreference("NUM_MITARBEITER_LINEE1_NACHT"));
				IOFile saveFile = new IOFile();
				for (int i = 0; i < 9; i++) {
					Dipendente dipendente  = new Dipendente();
					dipendente.setNome("DipendenteArray" + i);
					dipendente.setLivello(4);
					int Capacitalinea = new  Random() .nextInt(3) + 1; //1 o 2 o 3(per entrambe)
					dipendente.setLineaLavoro(Capacitalinea);
					//dipendente.setTotZuSchlag((new  Random().nextDouble())*100); //Giusto una prova per far ordinare il metodo getMenoore
					
					//dipendente.malattia = new Random () .nextBoolean(); // solo una prova per la gente malata, da cancellare
					dipendenteArrayList.add (dipendente); // DA COMPLETARE
					if (Nacht <= minNacht) {
						Nacht++;
						TagNacht = 2; //NACHT
						if (NumMitarbeiterLinee <= NumMitarbeiterNacht) {
							LineaLavoro = 1;
							NumMitarbeiterLinee++;
						} else {
								LineaLavoro = 2;
							}
					} else {
						TagNacht = 1; //TAG
					}
					
					int giornoLibero = 0;
						if (TagNacht == 2) { 
								giornoLibero = new  Random() .nextInt(5) ; // 0 per DOM, 1 per LUN, 2 per MAR, 3 ... VEN non si è mai liberi.
						}			
						stampa.StampaSuVideo(dipendente, SwitchTurni.generaTurni(giornoLibero, TagNacht, dipendente, dipendente.malattia), LineaLavoro);
						System.out.println("\n");
						String pathExtern = ("D:\\Dropbox\\eclipse_desktop\\Turni_Lavoro_New\\TurniLavoroNew\\Dipendenti\\" + dipendente.getNome() + ".txt");
						saveFile.ExportToFile(dipendente.getNome(), pathExtern, dipendente);
				}
				//ORDINAMENTO ARRAY SOLO UNA PROVA
			//	dipendenteArrayList.forEach(System.out::println);
				dipendenteArrayList = getMenoOre.OrdinePerNottiArray(dipendenteArrayList);
			//	System.out.println ("");
			//	dipendenteArrayList.forEach(System.out::println);
				
				
				//getMenoOre menoOre = new getMenoOre ();
				//dipendenteArrayList = menoOre.OrdinePerNotti(dipendenteArrayList);
				//dipendenteArrayList.forEach(System.out::println);
				
				//Inserire inserimento da Tastiera su Database
				//Chiedere se qualcuno è malato, le OreNotturne, OreFestivita, OreDomeniche le riceverà comunque 
				
				
					
				//Inserire lettura da Database
				//Implementare metodo per quanto riguarda: Licenziamenti, azubi o lifeFirma che se ne vanno.
				//Inserire Matrice con turni di tutti i Dipendenti, poi salvarla su database
				
				}

}