import java.time.LocalTime;

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
		
		LocalTime tagInizio = LocalTime.of(2,30);
		LocalTime tagFine = LocalTime.of(10,36);
		LocalTime nachtInizio = LocalTime.of(16, 30);
		LocalTime nachFine = LocalTime.of(00, 36);
		LocalTime InizioNachtZuSchlagTAG = LocalTime.of(00, 00);
		LocalTime FineNachtZuSchlagTAG = LocalTime.of(4, 00);
		LocalTime InizioNachtZuSchlagNACHT = LocalTime.of(21, 00);
		LocalTime FineNachtZuSchlagNACHT = LocalTime.of(00, 00);
	
		public static int calcoloZuSchlagTAG (LocalTime inizio, LocalTime fine, LocalTime inzioZuSchlag, LocalTime fineZuSchlag) {
			int zuSchlag = 0;
			int inizioIntH = inizio.getHour();
			int inizioIntM = inizio.getMinute();
			int fineIntH = fine.getHour();
			int fineIntM = fine.getMinute();
			int inizioZuSchlagIntH = inzioZuSchlag.getHour();
			int inizioZuSchlagIntM = inzioZuSchlag.getMinute();
			int fineZuSchlagIntH = fineZuSchlag.getHour();
			int fineZuSchlagIntM = fineZuSchlag.getMinute();
			int oreLavorate = fineIntH - inizioIntH;
			for (int x = 0; x < oreLavorate; x++) {
				if (fineIntH != 0) {
					if (fineIntH <= fineZuSchlagIntH)
					zuSchlag++;
				}
				fineIntH--;
			}
			
			return zuSchlag;
		}
}
