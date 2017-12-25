import java.util.Scanner;

public class Create {
	public static void createDipendente () {
		IOFile save = new IOFile();
		Scanner scan = new Scanner (System.in);
		String var;
		Dipendente dipendente  = new Dipendente();
		System.out.println("Personalnummer: " + "\n");
		long personalnummer = (long)scan.nextInt();
		dipendente.setPersonalnummer(personalnummer);
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
		System.out.println(dipendente.getCognome() + " Vuole lavorare solo la mattina? 1 = Y, 2 = N" + "\n");
		int soloMattina = scan.nextInt();
		if (soloMattina == 1) {
		dipendente.setSoloMattina(true);
		}
		System.out.println("Dipendente Salvato!" + "\n");
		scan.nextLine();
		String nomeFile = (dipendente.getCognome() + ".dbs");
		save.ExportObjectToFile("database", nomeFile, dipendente);
	}
}
