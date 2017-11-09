import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class Turni {
		//String TAG = ("2:30-10:36");
		String TAG = (Preferenze.getOnePreference("TAG_INIZIO_H") + ":" + Preferenze.getOnePreference("TAG_INIZIO_M") + "-" + Preferenze.getOnePreference("TAG_FINE_H") + ":" + Preferenze.getOnePreference("TAG_FINE_M"));
		String NACHT = (Preferenze.getOnePreference("NACHT_INIZIO_H") + ":" + Preferenze.getOnePreference("NACHT_INIZIO_M") + "-" + Preferenze.getOnePreference("NACHT_FINE_H") + ":" + Preferenze.getOnePreference("NACHT_FINE_M"));
		String VEN_TAG = (Preferenze.getOnePreference("TAG_VEN_INIZIO_H") + ":" + Preferenze.getOnePreference("TAG_VEN_INIZIO_M") + "-" + Preferenze.getOnePreference("TAG_VEN_FINE_H") + ":" + Preferenze.getOnePreference("TAG_VEN_FINE_M"));
		String VEN_NACHT = (Preferenze.getOnePreference("NACHT_VEN_INIZIO_H") + ":" + Preferenze.getOnePreference("NACHT_VEN_INIZIO_M") + "-" + Preferenze.getOnePreference("NACHT_VEN_FINE_H") + ":" + Preferenze.getOnePreference("NACHT_VEN_FINE_M"));
		String VEN_NACHT_DOM = (Preferenze.getOnePreference("NACHT_VEN_DOM_INIZIO_H") + ":" + Preferenze.getOnePreference("NACHT_VEN_DOM_INIZIO_M") + "-" + Preferenze.getOnePreference("NACHT_VEN_DOM_FINE_H") + ":" + Preferenze.getOnePreference("NACHT_VEN_DOM_FINE_M"));
		
		String settimana [][] = {
			{"Domenica", "Libero", NACHT},	
			{"Lunedi", TAG, NACHT},
			{"Martedi", TAG, NACHT},
			{"Mercoledi", TAG, NACHT},
			{"Giovedi", TAG, NACHT},
			{"Venerdi",VEN_TAG, VEN_NACHT, VEN_NACHT_DOM},
			{"Sabato", "Libero", "Libero"}
			};
		double oreNotturne [][] = {
				{0, 8}, // DOM
				{1.5, 3}, // LUN
				{1.5, 3}, // MAR
				{1.5, 3}, // MER
				{1.5, 3}, // GIO
				{2, 2, 5}, // VEN
				{0, 0}, //SAB
		};
		//LocalDate data; // solo una prova
		LocalTime tagInizio = LocalTime.of( Integer.parseInt(Preferenze.getOnePreference("TAG_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("TAG_INIZIO_M")));
		//LocalDateTime tagInizioData = LocalDateTime.of(data, tagInizio); //solo una prova
		LocalTime tagFine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("TAG_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("TAG_FINE_M")));
		LocalTime nachtInizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_INIZIO_M")));
		LocalTime nachtFine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_FINE_M")));
		LocalTime InizioNachtZuSchlagTAG = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_INIZIO_M")));
		LocalTime FineNachtZuSchlagTAG = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_FINE_M")));
		LocalTime InizioNachtZuSchlagNACHT = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_INIZIO_M")));
		LocalTime FineNachtZuSchlagNACHT = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_FINE_M")));
		LocalTime venNacht_DomInizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_DOM_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_DOM_INIZIO_M")));
		LocalTime venNacht_DomFine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_DOM_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_DOM_FINE_M")));
		LocalTime venNacht_Inizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_INIZIO_M")));
		LocalTime venNacht_Fine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_VEN_FINE_M")));
		
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
		
		public LocalTime getInizioNachtZuSchlagTAG() {
			return InizioNachtZuSchlagTAG;
		}
		
		public LocalTime getFineNachtZuSchlagTAG() {
			return FineNachtZuSchlagTAG;
		}
		
		public LocalTime getInizioNachtZuSchlagNACHT() {
			return InizioNachtZuSchlagNACHT;
		}
		
		public LocalTime getFineNachtZuSchlagNACHT() {
			return FineNachtZuSchlagNACHT;
		}
		
		public LocalTime getInizioNachtZuSchlagVEN_Dom () {
			return venNacht_DomInizio;
		}
		
		public LocalTime getFineNachtZuSchlagVEN_Dom () {
			return venNacht_DomFine;
		}
		
		public LocalTime getInizioNachtZuSchlagVEN() {
			return venNacht_Inizio;
		}
		
		public LocalTime getFineNachtZuSchlagVEN () {
			return venNacht_Fine;
		}
		
		public static LocalTime calcoloZuSchlag (LocalTime inizio, LocalTime fine, LocalTime inzioZuSchlag, LocalTime fineZuSchlag) {
			long oreLavorate = 0;
			LocalTime zuschlag = LocalTime.of(00, 00);
			if (fine.isBefore(inizio) == true){
				LocalDate data = LocalDate.now();
				LocalDateTime inizioEGirono = LocalDateTime.of(data, inizio);
				data = data.plusDays(1);
				LocalDateTime fineEGiorni = LocalDateTime.of(data, fine);
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
					if (fine.compareTo(fineZuSchlag) <= 0) {
						zuschlag = zuschlag.plusMinutes(1);
					}
					fine = fine.minusMinutes(1);
				}
			}
			return zuschlag;
		}
}
