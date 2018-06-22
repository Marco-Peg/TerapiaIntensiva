

public class Alarm extends Thread {
	private Paziente idPaziente;
	private int state;
	public Alarm(Paziente idPaziente, int state) {
		this.idPaziente=idPaziente;
		this.state=state;
	}
	
	/*
	 * capire che allarme è 
	 * genera finestra pop-up per gestire allarme 
	 * se non ripsonde entro tempo, notifica il primario
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		String nomeAllarme;
		int tempo;
		switch(state){ //livelloTipo
		case 10: //aritmia
			nomeAllarme="aritmia";
			tempo=3;
			break;
		case 11:  //tachicardia
			nomeAllarme="tachicardia";
			tempo=3;
			break;
		case 30:  //flutter_fibrillazione
			nomeAllarme="flutter/fibrillazione venticolare";
			tempo=1;
			break;
		case 20:  //ipertensione
			nomeAllarme="ipertensione";
			tempo=2;
			break;
		case 21:  //ipotensione
			nomeAllarme="ipotensione";
			tempo=2;
			break;
		case 22:  //ipertermia
			nomeAllarme="ipertermia";
			tempo=2;
			break;
		case 23:  //ipotermia
			nomeAllarme="ipotermia";
			tempo=2;
			break;
		default: 
			nomeAllarme="allarme generico";
			tempo=1; break;
		}
		
	}

	
}
