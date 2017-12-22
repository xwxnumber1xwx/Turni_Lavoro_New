
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class SwitchTurni {
	public static ArrayList<String> generaTurni (LocalDate giornoLiberoLD, int giornoLibero, int MattSera, Dipendente dipendente, boolean malattia, LocalDate date) {
		/*Inserire:
			- Calcolare la malattia per pochi giorni
		*/
		Turni sett = new Turni();
		ArrayList <String> turniLavoratoreArrayList = new ArrayList<String>(6);
		ArrayList <LocalTime> turniLavoratoreArrayListLD = new ArrayList<LocalTime>(6);
		int x = 0;
		LocalTime inizioTAG = sett.getTagInizio();
		LocalTime fineTAG = sett.getTagFine();
		LocalTime inizioNACHT = sett.getNachtInizio();
		LocalTime fineNACHT = sett.getNachtFine();
		LocalTime InizioNacht_VEN_Dom = sett.getInizioNacht_VEN_Dom();
		LocalTime FineNacht_VEN_Dom = sett.getFineNacht_VEN_Dom();
		LocalTime InizioNacht_VEN = sett.getInizioNacht_VEN();
		LocalTime FineNacht_VEN = sett.getFineNacht_VEN();
		LocalTime domNach_inizio = sett.getdomNacht_inizio();
		LocalTime domNach_Fine = sett.getdomNach_fine();
		LocalTime inizio_NachtZuSchlag_dom = LocalTime.of(00, 00);
		LocalTime fine_NachtZuSchlag_dom = LocalTime.of(23, 59);
		LocalTime ZUSCHLAG_INIZIO = sett.getInizioNachtZuSchlag();
		LocalTime ZUSCHLAG_FINE = sett.getFineNachtZuSchlag();
		ArrayList<LocalTime> OreNotturneTot = new ArrayList<LocalTime>(3);
		DayOfWeek day = DayOfWeek.SUNDAY;
		
		//implementare qualcosa se il Dipendente non ha giorni libero per quella settimana
		for (x = 0; x < 7; x++) {
			if (x == giornoLibero) {
				turniLavoratoreArrayList.add(giornoLibero, "\n" + date + " " + sett.settimana[giornoLibero][0] + "\t" + "frei");
				//una prova
				//turniLavoratoreArrayListLD.add(LocalTime.of(sett., minute))
			} else {
				if (malattia == true) {
					turniLavoratoreArrayList.add(x, "\n" + date + " " + sett.settimana[giornoLibero][0] + "\t" + " krank"); //scrive krank per quel giorno
					dipendente.setGiorniMalattia(1); //aggiunge 1 giorno malattia al conto delle malattie del dipendente.
					date = date.plusDays(1);
					day = day.plus(1);
					continue;
					}
				if (MattSera == 2) { // DA SISTEMARE LA TARIFFA NOTTURNA CON 24 ore di nachtzuschlag
					if (x == 0) { // Domenica Lavorariva
						turniLavoratoreArrayList.add("\n" + date + " " + sett.settimana[x][x] + "\t" + sett.settimana[x][MattSera]);
						OreNotturneTot = Turni.calcoloZuSchlag(dipendente, domNach_inizio, domNach_Fine, inizio_NachtZuSchlag_dom, fine_NachtZuSchlag_dom, date, day); //mi aggiunge il nachtzuschlag al conto delle ore di domenica e del totale del nachtzuschlag
						dipendente.setOreNotturne(OreNotturneTot.get(0));
						dipendente.setOreDomenica(OreNotturneTot.get(1));
						dipendente.setOreFestivita(OreNotturneTot.get(2));
						System.out.println("ore NACHT aggiunte " + sett.settimana[x][0]+ " : " + Turni.calcoloZuSchlag(dipendente, domNach_inizio, domNach_Fine, inizio_NachtZuSchlag_dom, fine_NachtZuSchlag_dom, date, day));
						date = date.plusDays(1);
						day = day.plus(1);
						continue;
					}
					if (x == 5 & giornoLibero == 0) { // ven 18 perche domenica libera
						turniLavoratoreArrayList.add (x, "\n" + date + " " + sett.settimana[x][0] + "\t" + sett.settimana[x][MattSera+1]); //impostata Venerdi alle 18 se domenica NACHT è libero
						OreNotturneTot = Turni.calcoloZuSchlag(dipendente, InizioNacht_VEN_Dom, FineNacht_VEN_Dom, ZUSCHLAG_INIZIO, ZUSCHLAG_FINE, date, day);  //mi aggiunge il nachtzuschlag al conto delle ore di domenica e del totale del nachtzuschlag
						dipendente.setOreNotturne(OreNotturneTot.get(0));
						dipendente.setOreDomenica(OreNotturneTot.get(1));
						dipendente.setOreFestivita(OreNotturneTot.get(2));
						System.out.println("ore NACHT aggiunte " + sett.settimana[x][0] + " : " + (Turni.calcoloZuSchlag(dipendente, InizioNacht_VEN_Dom, FineNacht_VEN_Dom, ZUSCHLAG_INIZIO, ZUSCHLAG_FINE, date, day)));
						date = date.plusDays(1);
						day = day.plus(1);
						continue;
					}
					if (x == 5 & giornoLibero != 0) { //ven 15 perche ha lavorato di domenica
						OreNotturneTot = Turni.calcoloZuSchlag(dipendente, InizioNacht_VEN, FineNacht_VEN, ZUSCHLAG_INIZIO, ZUSCHLAG_FINE, date, day);
						dipendente.setOreNotturne(OreNotturneTot.get(0));
						dipendente.setOreDomenica(OreNotturneTot.get(1));
						dipendente.setOreFestivita(OreNotturneTot.get(2));
						turniLavoratoreArrayList.add(x, "\n" + date + " " + sett.settimana[x][0] + "\t" + sett.settimana[x][MattSera]); //Scrive Il giono "domenica, lunedi, ecc.." oppure "libero"
						System.out.println("ore NACHT aggiunte " + sett.settimana[x][0] + " : " + Turni.calcoloZuSchlag(dipendente, InizioNacht_VEN, FineNacht_VEN, ZUSCHLAG_INIZIO, ZUSCHLAG_FINE, date, day));
						date = date.plusDays(1);
						day = day.plus(1);
						continue;
					}
				}


				turniLavoratoreArrayList.add(x, "\n" + date + " " + sett.settimana[x][0] + "\t" + sett.settimana[x][MattSera]); //Scrive il resto dei giorni non scritti "domenica, lunedi, ecc.." oppure "libero"
				
				if (MattSera == 1 & x != 6 & x != 0) { //mattina
					OreNotturneTot = Turni.calcoloZuSchlag(dipendente, inizioTAG, fineTAG, ZUSCHLAG_INIZIO, ZUSCHLAG_FINE, date, day);
					dipendente.setOreNotturne(OreNotturneTot.get(0));
					dipendente.setOreDomenica(OreNotturneTot.get(1));
					dipendente.setOreFestivita(OreNotturneTot.get(2));
					System.out.println("ore tag aggiunte " + sett.settimana[x][0] + " : " + Turni.calcoloZuSchlag(dipendente, inizioTAG, fineTAG, ZUSCHLAG_INIZIO, ZUSCHLAG_FINE, date, day));
				}
				if (MattSera == 2 & x != 6 & x != giornoLibero) {
					OreNotturneTot = Turni.calcoloZuSchlag(dipendente, inizioNACHT, fineNACHT, ZUSCHLAG_INIZIO, ZUSCHLAG_FINE, date, day);
					dipendente.setOreNotturne(OreNotturneTot.get(0));
					dipendente.setOreDomenica(OreNotturneTot.get(1));
					dipendente.setOreFestivita(OreNotturneTot.get(2));
					System.out.println("ore NACHT aggiunte " + sett.settimana[x][0] + " : " + Turni.calcoloZuSchlag(dipendente, inizioNACHT, fineNACHT, ZUSCHLAG_INIZIO, ZUSCHLAG_FINE, date, day));
				}
			}
			date = date.plusDays(1);
			day = day.plus(1);
		}

		return turniLavoratoreArrayList;
	}
}

