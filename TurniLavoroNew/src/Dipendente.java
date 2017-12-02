
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Dipendente implements Serializable{
	//  azubi, nuovoArrivato, lifeFirma devo metterli una una classe estesa?
	// Oppure devo mettere Dipendente come superclasse e poi Mitarbeirer, azubi, nuovoArrivato, lifeFirma?
	// cambiare i valori OreNotturne, ecc... da double a LocalTime e le relative funzioni
	private static final long serialVersionUID = 00000001L;
	long personalnummer = 0;
	String nome;
	String cognome;
	int livello = 0;
	int lineaLavoro = 0;
	int linieLeiter = 0;
	boolean soloMattina = false;
	private double OreNotturne = 0;
	private double OreFestivita = 0;
	private double OreDomenica = 0;
	private int giorniMalattia = 0;
	boolean malattia = false;
	double TotZuSchlag = 0D;
	int giornoLibero = 0;
	int TagNacht = 1; //1 = TAG default
	ArrayList<String> weekShift = new ArrayList<String>();
	
	public void setPersonalnummer (long personalnummer) {
		this.personalnummer = personalnummer;
	}
	
	public long getPersonalnummer () {
		return this.personalnummer;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome (String cognome) {
		this.cognome = cognome;
	}
	
	public int getLivello () {
		return livello;
	}
	public void setLivello (int livello) {
		this.livello = livello;
	}
	public int getLineaLavoro () {
		return lineaLavoro;
	}
	public void setLineaLavoro (int i) {
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
	public void setGiornoLibero (int giornoLibero) {
		this.giornoLibero = giornoLibero;
	}
	public int getGiornoLibero() {
		return giornoLibero;
	}
	public void setTagNacht (int TagNacht) {
		this.TagNacht = TagNacht;
	}
	public int getTagNacht() {
		return TagNacht;
	}
	public void setWeekShift(ArrayList<String> weekShift) {
		this.weekShift = weekShift;
	}
	public ArrayList<String> getWeekShift() {
		return weekShift;
	}
	public void setSoloMattina (boolean soloMattina) {
		this.soloMattina = soloMattina;
	}
	public boolean getSoloMattina () {
		return this.soloMattina;
	}
	public void setLinieLeiter (int linieLeiter) {
		this.linieLeiter = linieLeiter;
	}
	public int getlinieLeiter () {
		return this.linieLeiter;
	}
}