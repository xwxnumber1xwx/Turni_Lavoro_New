//sostituire l'obsoleto Array con un ArrayList che è molto meglio

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
				{0, 8},
				{1.5, 3},
				{1.5, 3},
				{1.5, 3},
				{1.5, 3},
				{2, 2, 5},
				{0, 0},
		};
}
