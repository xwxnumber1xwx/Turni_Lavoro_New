import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class Turni {
		String TAG = ("2:30-10:36");
		String NACHT = ("16:30-00:36");
		String VEN_TAG = ("2:00-10:06");
		String VEN_NACHT = ("15:00-23:06");
		String VEN_NACHT_DOM = ("18:00-2:00");
		
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
		
		LocalTime tagInizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("TAG_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("TAG_INIZIO_M")));
		LocalTime tagFine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("TAG_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("TAG_FINE_M")));
		LocalTime nachtInizio = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_INIZIO_M")));
		LocalTime nachFine = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("NACHT_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("NACHT_FINE_M")));
		LocalTime InizioNachtZuSchlagTAG = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_INIZIO_M")));
		LocalTime FineNachtZuSchlagTAG = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_FINE_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_TAG_FINE_M")));
		LocalTime InizioNachtZuSchlagNACHT = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_INIZIO_M")));
		LocalTime FineNachtZuSchlagNACHT = LocalTime.of(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_INIZIO_H")), Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT_INIZIO_M")));
	
		public LocalTime getTagInizio () {
			return tagInizio;
		}
		
		
		public static LocalTime calcoloZuSchlagTAG (LocalTime inizio, LocalTime fine, LocalTime inzioZuSchlag, LocalTime fineZuSchlag) {
			
			LocalTime zuschlag = LocalTime.of(00, 00);
			long oreLavorate = inizio.until(fine, ChronoUnit.MINUTES);
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
