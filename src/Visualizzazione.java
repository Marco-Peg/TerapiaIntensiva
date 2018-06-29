public interface Visualizzazione {
	void visualSomm(Paziente id);		//Visualizza Somministrazioni
	void visualPresc(Paziente id);		//Visualizza Prescrizioni
	void visualRicPreg(Paziente id);	//Visualizza Ricoveri Pregressi
	void visualParam(Paziente id);		//Ultime 2 ore, visibile su autenticazione
	void visualMonitor(Paziente id);	//Ultimi 15 min, tutti possono vederlo
	void report(Paziente id);			//Report
}
