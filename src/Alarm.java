import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.*;

public class Alarm extends Thread {
	private Paziente idPaziente;
	private int state;
	public Alarm(Paziente idPaziente, int state) {
		this.idPaziente=idPaziente;
		this.state=state;
	}
	
	/**
	 * capire che allarme è 
	 * genera finestra pop-up per gestire allarme 
	 * se non ripsonde entro tempo, notifica il primario
	 * 
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
		
		JFrame frm= new JFrame("Gestore allarme");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(300, 250);
		JPanel intestazione=new JPanel();
		intestazione.add(new JLabel("Tipo allarme: "+nomeAllarme));
		JPanel input= new JPanel();
		input.add(new JLabel("input : "));
		input.add(new JTextArea(10,8));
		input.setMaximumSize(new Dimension(300, 100));
		frm.add(input);
		JButton button=new JButton("Spegni Allarme");
		button.setMargin(new Insets(10, 25, 10, 25));
		button.addActionListener(new AlarmListener(input));
	}

	
}
