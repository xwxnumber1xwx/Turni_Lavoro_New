import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
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
	/*	double oreNotturne [][] = { //DEPRECATED
				{0, 8}, // DOM
				{1.5, 3}, // LUN
				{1.5, 3}, // MAR
				{1.5, 3}, // MER
				{1.5, 3}, // GIO
				{2, 2, 5}, // VEN
				{0, 0}, //SAB
		};
		*/
		//LocalDate data; // solo una prova
		LocalTime tagInizio = LocalTime.of( Integer.parseInt(Preferenze.getOnePreference("TAG_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("TAG_INIZIO_M")));
		//LocalDateTime tagInizioData = LocalDateTime.of(data, tagInizio); //solo una prova
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
		public static LocalTime calcoloZuSchlag (LocalTime inizio, LocalTime fine, LocalTime inzioZuSchlag, LocalTime fineZuSchlag, LocalDate date) {
															// 15			//23 				//21:00					//4:00			//2017.11.26
			long oreLavorate = 0;
			LocalTime zuschlag = LocalTime.of(00, 00);
			if (fine.isBefore(inizio) == true){
				LocalDateTime inizioEGirono = LocalDateTime.of(date, inizio);
				date = date.plusDays(1);
				LocalDateTime fineEGiorni = LocalDateTime.of(date, fine);
				Duration durata = Duration.between(inizioEGirono, fineEGiorni);
				oreLavorate = durata.toMinutes();
				for (int x = 0; x < oreLavorate; x++) {
					if(!(fine.equals(inizio))) {
						if (fine.compareTo(fineZuSchlag) <= 0 || fine.compareTo(inzioZuSchlag) > 0) {
							zuschlag = zuschlag.plusMinutes(1);
						}
						fine = fine.minusMinutes(1);
					}
				}
			}else {
			oreLavorate = inizio.until(fine, ChronoUnit.MINUTES);
			}
			for (int x = 0; x < oreLavorate; x++) {
				if(!(fine.equals(inizio))) {
					if (fine.compareTo(fineZuSchlag) > 0 & inizio.compareTo(fineZuSchlag) > 0) {
						fineZuSchlag = LocalTime.of(23, 59);
					}
					if (inizio.compareTo(fineZuSchlag) < 0 & (fine.compareTo(fineZuSchlag) > 0)) {
						inzioZuSchlag = LocalTime.of(00, 00);
					}
					if (fine.compareTo(fineZuSchlag) <= 0 & fine.compareTo(inzioZuSchlag) > 0) {
						zuschlag = zuschlag.plusMinutes(1);
					}
					fine = fine.minusMinutes(1);
				}
			}
			return zuschlag;
		}
}
