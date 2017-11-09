import java.time.LocalTime;
import java.util.ArrayList;

//C´e un errore che se domenica si ü liberi, venerdi viene stampato 2 volte prima alle 18 poi dopo il sabato alle 15

public class SwitchTurni {
	public static ArrayList<String> generaTurni (int giornoLibero, int MattSera, Dipendente dipendente, boolean malattia) {
		/*Inserire:
			- Calcolare la malattia per pochi giorni
		*/
		Turni sett = new Turni();
		ArrayList <String> turniLavoratoreArrayList = new ArrayList<String>(6);
		int x = 0;
		LocalTime inizioTAG = sett.getTagInizio();
		LocalTime fineTAG = sett.getTagFine();
		LocalTime InizioNachtZuSchlagTAG = sett.getInizioNachtZuSchlagTAG();
		LocalTime FineNachtZuSchlagTAG = sett.getFineNachtZuSchlagTAG();
		LocalTime inizioNACHT = sett.getNachtInizio();
		LocalTime fineNACHT = sett.getNachtFine();
		LocalTime InizioNachtZuSchlagNACHT = sett.getInizioNachtZuSchlagNACHT();
		LocalTime FineNachtZuSchlagNACHT = sett.getFineNachtZuSchlagNACHT();
		LocalTime InizioNachtZuSchlagVEN_Dom = sett.getInizioNachtZuSchlagVEN_Dom();
		LocalTime FineNachtZuSchlagVEN_Dom = sett.getFineNachtZuSchlagVEN_Dom();
		LocalTime InizioNachtZuSchlagVEN = sett.getInizioNachtZuSchlagVEN();
		LocalTime FineNachtZuSchlagVEN = sett.getFineNachtZuSchlagVEN();
		
		//implementare qualcosa se il Dipendente non ha giorni libero per quella settimana
		do {
			
			if (x != giornoLibero) {
				if (malattia == false) {
				turniLavoratoreArrayList.add(x, sett.settimana[x][0] + " " + sett.settimana[x][MattSera]);
				}
				else {
					turniLavoratoreArrayList.add(x, sett.settimana[giornoLibero][0] + " krank");
					dipendente.setGiorniMalattia(1);
				}
				if (x == 0 & MattSera == 2) { //Domenica NACHT
					dipendente.setOreDomenica(sett.oreNotturne[x][MattSera-1]); //Calcolo oreDomenica
				//	turniLavoratore[x] = sett.settimana[5][0] + " " + sett.settimana[5][MattSera+1]; //Venerdì però mi riscrive sopra con i prossimi cicli quando x = 5
				} 
				else {
					if (x == 5 & MattSera == 2 & giornoLibero == 0) {
						if (malattia == false) {
						turniLavoratoreArrayList.add (5, sett.settimana[5][0] + " " + sett.settimana[5][MattSera+1]); //impostata Venerdi alle 18 se domenica NACHT è libero
						dipendente.setOreNotturne(sett.oreNotturne[x][MattSera]); //Calcolo oreNotturne VENERDI alle 18  /DEPRECATED
						dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(InizioNachtZuSchlagVEN_Dom, FineNachtZuSchlagVEN_Dom, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
						}
						else {
							turniLavoratoreArrayList.add(x, sett.settimana[giornoLibero][0] + " krank");
							dipendente.setGiorniMalattia(1);
						}
					}
					else {
						if (MattSera == 2) { //Calcolo ore notturne della settimana e venerdi alle 15
					dipendente.setOreNotturne(sett.oreNotturne[x][MattSera-1]); //Calcolo oreNotturne venerdi alle 15  //DEPRECATED
					dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(InizioNachtZuSchlagVEN, FineNachtZuSchlagVEN, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
					System.out.println("IL NACHTZUSCHLAG NACHT GUADAGNATO OGGI: " + Turni.calcoloZuSchlag(inizioNACHT, fineNACHT, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
						} else {
							dipendente.setOreNotturne(sett.oreNotturne[x][MattSera-1]); //Calcolo oreNotturne del tag
							dipendente.setOreNotturneLT(Turni.calcoloZuSchlag(inizioTAG, fineTAG, InizioNachtZuSchlagNACHT, FineNachtZuSchlagNACHT));
							System.out.println("IL NACHTZUSCHLAG TAG GUADAGNATO OGGI: " + Turni.calcoloZuSchlag(inizioTAG, fineTAG, InizioNachtZuSchlagTAG, FineNachtZuSchlagTAG));
						}
					
					}
				}
			}
			else {
				if (malattia == false) {
					turniLavoratoreArrayList.add(giornoLibero, sett.settimana[giornoLibero][0] + " libero"); // ERRORE perche l'arrayList non ha ancora la posizione del giornoLibero
					}
					else {
						turniLavoratoreArrayList.add(giornoLibero, sett.settimana[giornoLibero][0] + " krank");
						dipendente.setGiorniMalattia(1);
			}
			}
			x++;
		}
		while (x < 7);

		return turniLavoratoreArrayList;
	}
}

