import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Dipendente {
	//  azubi, nuovoArrivato, lifeFirma devo metterli una una classe estesa?
	// Oppure devo mettere Dipendente come superclasse e poi Mitarbeirer, azubi, nuovoArrivato, lifeFirma?
	// cambiare i valori OreNotturne, ecc... da double a LocalTime e le relative funzioni
	long codiceLavoratore = 0;
	String nome;
	int livello = 0;
	int lineaLavoro[] = {1,2};
	boolean linieLeiter = false;
	boolean soloMattina = false;
	private Duration OreNotturneLT = Duration.of(0, ChronoUnit.MINUTES);
	private double OreNotturne = 0;
	private Duration OreFestivitaLT = Duration.of(0, ChronoUnit.MINUTES);
	private double OreFestivita = 0;
	private Duration OreDomenicaLT = Duration.of(0, ChronoUnit.MINUTES);
	private double OreDomenica = 0;
	private int giorniMalattia = 0;
	boolean azubi = false;
	boolean nuovoArrivato = false;
	boolean lifeFirma = false;
	boolean licenziato = false;
	boolean malattia = false;
	double TotZuSchlag = 0D;
	
	public String getNome() {
		return nome;
	}
	public void setNome (String nome) {
		this.nome = nome;
	}
	public int getLivello () {
		return livello;
	}
	public void setLivello (int livello) {
		this.livello = livello;
	}
	public int[] getLineaLavoro () {
		return lineaLavoro;
	}
	public void setLineaLavoro (int[] i) {
		this.lineaLavoro = i;
	}

	public void setGiorniMalattia (int giorniMalattia) {
		this.giorniMalattia += giorniMalattia;
	}
	public int getGiorniMalattia () {
		return giorniMalattia;
	}
	public Double getTotZuSchlag () {
		return TotZuSchlag;
	}
	public void setTotZuSchlag (Double TotZuSchlag) {
		this.TotZuSchlag += TotZuSchlag;
	}
	public void setOreNotturne(LocalTime OreNotturne) {
	int OreNotturneInt = ((OreNotturne.getHour() * 60) + OreNotturne.getMinute());
	this.OreNotturne += OreNotturneInt;
	double percentuale = (double)(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT")));
	double setTotZuSchlag = 0D;
	setTotZuSchlag += (percentuale/100)*OreNotturneInt;
	setTotZuSchlag(setTotZuSchlag);
	}
	public Double getOreNotturne() {
		return OreNotturne;
	}
	public void setOreFestivita(LocalTime OreFestivita) {
		int OreFestivitaInt = ((OreFestivita.getHour() * 60) + OreFestivita.getMinute());
		this.OreFestivita += OreFestivitaInt;
		double percentuale = (double)(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_FEIERTAG")));
		double setTotZuSchlag = 0D;
			setTotZuSchlag += (percentuale/100)*OreFestivitaInt;
		setTotZuSchlag(setTotZuSchlag);
	}
	public double getOreFestivita() {
		return OreFestivita;
	}
	public void setOreDomenica(LocalTime OreDomenica) {
		int OreDomenicaInt = ((OreDomenica.getHour() * 60) + OreDomenica.getMinute());
		this.OreDomenica += OreDomenicaInt;
		double percentuale = (double)(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_SONNTAG")));
		double setTotZuSchlag = 0D;
		setTotZuSchlag = (percentuale/100)*OreDomenicaInt;
		setTotZuSchlag(setTotZuSchlag);
	}
	public double getOreDomenica() {
		return OreDomenica;
	}
	public void setOreNotturneLT (LocalTime OreNotturneLT) {
		long vari = OreNotturneLT.getHour() + OreNotturneLT.getMinute();
		this.OreNotturneLT.plus(vari, ChronoUnit.MINUTES);
	//	this.OreNotturneLT = this.OreNotturneLT.ofMinutes(OreNotturneLT.getMinute());
	//	this.OreNotturneLT = this.OreNotturneLT.ofHours(OreNotturneLT.getHour());
		
		double percentuale = (double)(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_NACHT")));
		double setTotZuSchlag = (percentuale/100)*(OreNotturneLT.getMinute() + (OreNotturneLT.getHour()*60));
		setTotZuSchlag(setTotZuSchlag);
	}
	public Duration getOreNotturneLT () {
		return OreNotturneLT;
	}
	public void setOreDomenicaLT(LocalTime OreDomenicaLT) {
		this.OreDomenicaLT.addTo(OreDomenicaLT);
		double percentuale = (double)(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_SONNTAG")));
		double setTotZuSchlag = (percentuale/100)*(OreDomenicaLT.getMinute() + (OreDomenicaLT.getHour() * 60));
		setTotZuSchlag(setTotZuSchlag);
	}
	public Duration getOreDomenicaLT () {
		return OreDomenicaLT;
	}
	public void setOreFestivitaLT(LocalTime OreFestivitaLT) {
		this.OreFestivitaLT.addTo(OreFestivitaLT);
		double percentuale = (double)(Integer.parseInt(Preferenze.getOnePreference("ZUSCHLAG_FEIERTAG")));
		double setTotZuSchlag = (percentuale/100)* (OreFestivitaLT.getMinute() + (OreFestivitaLT.getHour() * 60));
		setTotZuSchlag(setTotZuSchlag);
		}
	public Duration getOreFestivitaLT () {
		return OreFestivitaLT;
	}
}


