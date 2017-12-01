import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Label;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;


class TurniLavoroNew {
	
	public static void main(String[] args)
		throws IOException { //Sicurezza per eventuali errori di Input e Output
				// Creo 9 dipendenti come prova su un array di dipendenti
				String filenome = "options.proprieties";
				if (Preferenze.FileExist(filenome) == false) {
					Preferenze.InitFile();
				}
				boolean creazioneDipendente = false;
				Stampa stampa = new Stampa();
				ArrayList <Dipendente> dipendenteArrayList = new ArrayList<Dipendente>();
				int LineaLavoro = 1; // da AGGIUNGERE funzione per determinare quale line lavoro
				int Nacht = 0;
				int NumMitarbeiterLinee = 1;
				int minNacht = Integer.parseInt(Preferenze.getOnePreference("NACHT_MIN_MITARBEITER"));
				int NumMitarbeiterNacht = Integer.parseInt(Preferenze.getOnePreference("NUM_MITARBEITER_LINEE1_NACHT"));
				//IMPUT KalenderWoche
				LocalDate date = LocalDate.of(2017, 1, 1);
				int inputWeek = 0;
				String nomeFile = "";
				int yn;
				IOFile save = new IOFile();
				do { //CREAZIONE DIPENDENTE
					Scanner scan = new Scanner (System.in);
					System.out.print("Volete Aggiungere un nuovo Dipendente? 1 = Y , 2 = N" + "\n");
					yn = scan.nextInt();
					if (yn == 1) {
						String var;
						Dipendente dipendente  = new Dipendente();
						scan.nextLine();
						System.out.println("Nome: " + "\n");
						var = scan.nextLine();
						dipendente.setNome(var);
						System.out.println("Cognome: " + "\n");
						var = scan.nextLine();
						dipendente.setCognome(var);
						System.out.println("capacita linee: es. 1, 2 oppure digitare 3 per entrambe" + "\n");
						int Capacitalinea = scan.nextInt();
						dipendente.setLineaLavoro(Capacitalinea);
						System.out.println("Linee Leiter? es. 0 = N, 1, 2 oppure digitare 3 per entrambe" + "\n");
						int linieLeiter = scan.nextInt();
						dipendente.setLinieLeiter(linieLeiter);
						System.out.println("Dipendente Salvato!" + "\n");
						scan.nextLine();
						nomeFile = (dipendente.getCognome() + ".dbs");
						save.ExportObjectToFile("database", nomeFile, dipendente);
					}
				} while (yn == 1);
				
				//Carico i Dipendenti dal Database
				dipendenteArrayList = save.ImportObjectFromFile("database");
				
				do {
					Scanner scan = new Scanner (System.in);
					System.out.print("Welche kalenderWoche wollen Sie?" + "\n");
					inputWeek = scan.nextInt();
					if (inputWeek < 0 || inputWeek > 53) {
						System.out.print("kalenderWoche falsche gescrieben" + "\n");
					}
				} while (inputWeek < 0 || inputWeek > 53);
				inputWeek -= 1;
				date = date.plusWeeks(inputWeek);
				ArrayList <String> turniWeek = new ArrayList<String>();
				ArrayList <String> turnoDipendente = new ArrayList<String>();
				
				//SCELTA DI CHI LAVORA LA MATTINA O LA SERA
					for (int x = 0 ; x < dipendenteArrayList.size(); x++) {
					Dipendente dipendente = dipendenteArrayList.get(x);
					if (dipendente.getSoloMattina() == true) {
						dipendente.setTagNacht(1); //TAG
					} else {
						if (Nacht <= minNacht) {
							Nacht++;
							dipendente.setTagNacht(2); //Nacht
							if (NumMitarbeiterLinee <= NumMitarbeiterNacht) {
								LineaLavoro = 1;
								NumMitarbeiterLinee++;
							} else {
								LineaLavoro = 2;
							}
						} else {
							dipendente.setTagNacht(1); //TAG
						}
					}
					int giornoLibero = 0;
						if (dipendente.getTagNacht() == 2) { 
								giornoLibero = new  Random() .nextInt(5) ; // 0 per DOM, 1 per LUN, 2 per MAR, 3 ... VEN non si è mai liberi.
						}
					dipendente.setGiornoLibero(giornoLibero);
				}
				
				//GENERAZIONE TURNI E SALVATAGGIO	
				String directory = "";
				for (int x = 0; x < dipendenteArrayList.size(); x++) {
					Dipendente dipendente = dipendenteArrayList.get(x);
					turnoDipendente = SwitchTurni.generaTurni(dipendente.getGiornoLibero(), dipendente.getTagNacht(), dipendente, dipendente.malattia, date.with(DayOfWeek.SUNDAY));
					turnoDipendente.add("\n");
					turniWeek.add(dipendente.getCognome() +  " " + turnoDipendente);
					dipendente.setWeekShift(turnoDipendente);
					System.out.println("\n");
					nomeFile = (dipendente.getCognome() + ".txt");
					directory = "mitarbeiter"; //MODIFICARE DA QUI IN POI E SALVARE I TURNI DEL DIPENDENTE SU UN FILE TEXT E AGGIUNGERLI MANO A MANO. con DATA
					save.ExportToFile(directory, nomeFile, dipendente);
					List<String> saved = save.ImportFile(directory, nomeFile);
					directory = "turni_" + date.getYear();
					save.ExportTurni(directory, String.valueOf(inputWeek+1) + ".txt", turniWeek);
					directory = "database";
					nomeFile = (dipendente.getCognome() + ".dbs");
					save.ExportObjectToFile(directory, nomeFile, dipendente);
				}
				turniWeek.forEach(System.out::println);
				dipendenteArrayList = getMenoOre.OrdinePerNottiArrayList(dipendenteArrayList);
				for(int h = 0; h < dipendenteArrayList.size(); h++) {
					Dipendente dipendenteVar = dipendenteArrayList.get(h);
					System.out.println(dipendenteVar.getCognome() + " TOT: " + dipendenteVar.getTotZuSchlag());
				}
				directory = "database";
				dipendenteArrayList = save.ImportObjectFromFile(directory); //.forEach(System.out::println);
				//Dipendente dipendente = dipendenteArrayList.get(1);
				//System.out.println(dipendente.getNome() + dipendente.getCognome());
				//Inserire inserimento da Tastiera su Database
				//Chiedere se qualcuno è malato, le OreNotturne, OreFestivita, OreDomeniche le riceverà comunque 
				//Inserire lettura da Database
				//Implementare metodo per quanto riguarda: Licenziamenti, azubi o lifeFirma che se ne vanno.
				//Inserire Matrice con turni di tutti i Dipendenti, poi salvarla su database
				
				}

}