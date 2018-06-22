
public interface Visualizzazione {
	void visualSomm(Paziente id);
	void visualPresc(Paziente id);
	void visualRicPreg(Paziente id);
	void visualParam(Paziente id);		//Ultime 2 ore, visibile su autenticazione
	void visualMonitor(Paziente id);	//Ultimi 15 min, tutti possono vederlo
	void report(Paziente id);
}
