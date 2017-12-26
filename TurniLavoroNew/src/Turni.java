import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class Turni {
		//String TAG = ("2:30-10:36");
		String TAG = (Preferenze.getOnePreference("TAG_INIZIO_H") + ":" + Preferenze.getOnePreference("TAG_INIZIO_M") + "-" + Preferenze.getOnePreference("TAG_FINE_H") + ":" + Preferenze.getOnePreference("TAG_FINE_M"));
		String NACHT = (Preferenze.getOnePreference("NACHT_INIZIO_H") + ":" + Preferenze.getOnePreference("NACHT_INIZIO_M") + "-" + Preferenze.getOnePreference("NACHT_FINE_H") + ":" + Preferenze.getOnePreference("NACHT_FINE_M"));
		String VEN_TAG = (Preferenze.getOnePreference("TAG_VEN_INIZIO_H") + ":" + Preferenze.getOnePreference("TAG_VEN_INIZIO_M") + "-" + Preferenze.getOnePreference("TAG_VEN_FINE_H") + ":" + Preferenze.getOnePreference("TAG_VEN_FINE_M"));
		String VEN_NACHT = (Preferenze.getOnePreference("NACHT_VEN_INIZIO_H") + ":" + Preferenze.getOnePreference("NACHT_VEN_INIZIO_M") + "-" + Preferenze.getOnePreference("NACHT_VEN_FINE_H") + ":" + Preferenze.getOnePreference("NACHT_VEN_FINE_M"));
		String VEN_NACHT_DOM = (Preferenze.getOnePreference("NACHT_VEN_DOM_INIZIO_H") + ":" + Preferenze.getOnePreference("NACHT_VEN_DOM_INIZIO_M") + "-" + Preferenze.getOnePreference("NACHT_VEN_DOM_FINE_H") + ":" + Preferenze.getOnePreference("NACHT_VEN_DOM_FINE_M"));
		DayOfWeek domenica = DayOfWeek.SUNDAY;
		DayOfWeek lunedi = DayOfWeek.MONDAY;
		DayOfWeek martedi = DayOfWeek.TUESDAY;
		DayOfWeek mercoledi = DayOfWeek.WEDNESDAY;
		DayOfWeek giovedi = DayOfWeek.THURSDAY;
		DayOfWeek venerdi = DayOfWeek.FRIDAY;
		DayOfWeek sabato = DayOfWeek.SATURDAY;
		TextStyle stileNorm = TextStyle.FULL;
		Locale deutsch = Locale.GERMAN;
		String settimana [][] = {
			{domenica.getDisplayName(stileNorm, deutsch), "Libero", NACHT},	
			{lunedi.getDisplayName(stileNorm, deutsch), TAG, NACHT},
			{martedi.getDisplayName(stileNorm, deutsch), TAG, NACHT},
			{mercoledi.getDisplayName(stileNorm, deutsch), TAG, NACHT},
			{giovedi.getDisplayName(stileNorm, deutsch), TAG, NACHT},
			{venerdi.getDisplayName(stileNorm, deutsch),VEN_TAG, VEN_NACHT, VEN_NACHT_DOM},
			{sabato.getDisplayName(stileNorm, deutsch), "Libero", "Libero"}
			};

		LocalTime tagInizio = LocalTime.of( Integer.parseInt(Preferenze.getOnePreference("TAG_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("TAG_INIZIO_M")));
		LocalTime tagFine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("TAG_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("TAG_FINE_M")));
		LocalTime nachtInizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_INIZIO_M")));
		LocalTime nachtFine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_FINE_M")));
		LocalTime FineNachtZuSchlag = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_FINE_M")));
		LocalTime InizioNachtZuSchlag = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_INIZIO_M")));
		LocalTime venNacht_DomInizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_DOM_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_DOM_INIZIO_M")));
		LocalTime venNacht_DomFine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_DOM_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_DOM_FINE_M")));
		LocalTime venNacht_Inizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_INIZIO_M")));
		LocalTime venNacht_Fine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_FINE_M")));
		LocalTime domNacht_inizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_DOM_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_DOM_INIZIO_M")));
		LocalTime domNacht_Fine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_DOM_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_DOM_FINE_M")));
		
		public String[][] getSettimana() {
			return this.settimana;
		}
				
		public LocalTime getTagInizio () {
			return tagInizio;
		}
		
		public LocalTime getTagFine() {
			return tagFine;
		}
		
		public LocalTime getNachtInizio() {
			return nachtInizio;
		}
		
		public LocalTime getNachtFine() {
			return nachtFine;
		}
		
		public LocalTime getFineNachtZuSchlag() {
			return FineNachtZuSchlag;
		}
		
		public LocalTime getInizioNachtZuSchlag() {
			return InizioNachtZuSchlag;
		}
		
		public LocalTime getInizioNacht_VEN_Dom () {
			return venNacht_DomInizio;
		}
		
		public LocalTime getFineNacht_VEN_Dom () {
			return venNacht_DomFine;
		}
		
		public LocalTime getInizioNacht_VEN() {
			return venNacht_Inizio;
		}
		
		public LocalTime getFineNacht_VEN () {
			return venNacht_Fine;
		}
		
		public LocalTime getdomNacht_inizio () {
			return domNacht_inizio;
		}
		public LocalTime getdomNach_fine () {
			return domNacht_Fine;
		}
		public static ArrayList<LocalTime> calcoloZuSchlag (Dipendente dipendente, LocalTime inizio, LocalTime fine, LocalTime inzioZuSchlag, LocalTime fineZuSchlag, LocalDate date, DayOfWeek day) {
																							// 15			//23 				//21:00					//4:00			//2017.11.26		Sonntag
			ArrayList<LocalTime> zuschlager = new ArrayList<LocalTime>(3);
			LocalTime grenzeLT = LocalTime.of(23, 59);
			LocalDateTime grenzeLDT = LocalDateTime.of(date, grenzeLT);
			LocalTime zuschlagNacht = LocalTime.of(00, 00);
			LocalTime zuschlagFeiertag = LocalTime.of(00, 00);
			LocalTime zuschlagSonntag = LocalTime.of(00, 00);
			LocalDateTime inizioZuSchlagLDT = LocalDateTime.of(date, inzioZuSchlag);
			date = date.plusDays(1);
			LocalDateTime fineZuSchlagLDT = LocalDateTime.of(date, fineZuSchlag);
			date = date.minusDays(1);
			LocalDateTime inizioLDT = LocalDateTime.of(date, inizio);
			LocalDateTime fineLDT;
			if (fine.isBefore(inizio)) {
				date = date.plusDays(1);
				fineLDT = LocalDateTime.of(date, fine);
				date = date.minusDays(1);
			}else {
				fineLDT = LocalDateTime.of(date, fine);
			}
			while (inizioLDT.isBefore(fineLDT) || inizioLDT.compareTo(fineLDT) == 0) {
			if ((inizioLDT.isAfter(inizioZuSchlagLDT) & inizioLDT.isBefore(fineZuSchlagLDT)) || (inizioLDT.isAfter(inizioZuSchlagLDT.minusDays(1)) & inizioLDT.isBefore(fineZuSchlagLDT.minusDays(1)))) { //COMPRERSO TRA 21 e 4
				if (day == DayOfWeek.SUNDAY) {
					if (inizioLDT.isAfter(grenzeLDT) == true) {
						zuschlagNacht = zuschlagNacht.plusMinutes(1);
					} else {
						zuschlagSonntag = zuschlagSonntag.plusMinutes(1);
					} 
				}	
				if (day == DayOfWeek.SATURDAY) {
						if (inizioLDT.isAfter(grenzeLDT) == true) {
							zuschlagSonntag = zuschlagSonntag.plusMinutes(1);
						} else {
						zuschlagNacht = zuschlagNacht.plusMinutes(1);
					}
				}
				if ((day != DayOfWeek.SATURDAY) && (day != DayOfWeek.SUNDAY) == true) {
					zuschlagNacht = zuschlagNacht.plusMinutes(1);
				}
			}
			inizioLDT = inizioLDT.plusMinutes(1);
			}
			zuschlager.add(zuschlagNacht);
			zuschlager.add(zuschlagSonntag);
			zuschlager.add(zuschlagFeiertag);
			return zuschlager;
		}
}
