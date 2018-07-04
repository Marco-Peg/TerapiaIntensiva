import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.time.LocalTime;

import javax.swing.*;

/**
 * Gestore dell'allarme
 * @author Marco
 *
 */
public class Alarm extends Thread {
	private Paziente idPaziente;
	private int state;
	private JFrame frm;
	private LocalTime end;
	
	public Alarm(Paziente idPaziente, int state) {
		this.idPaziente=idPaziente;
		this.state=state;
	}
	
	/*
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
		
		 end= (LocalTime.now()).plusMinutes(tempo);
		frm= new JFrame("Gestore allarme");
		frm.setLayout(new FlowLayout());
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(700, 300);
		 JPanel intestazione=new JPanel();
		intestazione.setLayout(new FlowLayout());
		intestazione.add(new JLabel("Paziente: "+idPaziente.getCognome()+' '+idPaziente.getNome()));
		intestazione.add(new JLabel("Tipo allarme: "+nomeAllarme));
		intestazione.add(new JLabel("Termine allarme: "+end.toString()));
		frm.add(intestazione);
		 JPanel id= new JPanel();
		JTextField idField= new JTextField(10);
		id.add(new JLabel("ID utente :"));
		id.add(idField);
		id.setMaximumSize(new Dimension(300, 100));
		frm.add(id);
		 JPanel password= new JPanel();
		JPasswordField passField=new JPasswordField(10);
		password.add(new JLabel("Password : "));
		password.add(passField);
		password.setMaximumSize(new Dimension(300, 100));
		frm.add(password);
		 JPanel input= new JPanel();
		JTextArea inputField= new JTextArea(10,15);
		input.add(new JLabel("Attività effettuate: "));
		input.add(inputField);
		input.setMaximumSize(new Dimension(300, 100));
		frm.add(input);
		 JButton button=new JButton("Spegni Allarme");
		button.setMargin(new Insets(10, 25, 10, 25));
		button.addActionListener(new AlarmListener(null, idField,passField, inputField));
		frm.add(button);
		
		frm.setVisible(true);
		try {
			Thread.sleep(tempo*60*1000);
		} catch (InterruptedException e) {
			return;
		}
		
		JOptionPane.showMessageDialog(null,
			    "L'allarme del paziente "+idPaziente.getCognome()+' '+idPaziente.getNome()+"del tipo "+nomeAllarme +" non è statto gestito.",
			    "Allarme non gestito", JOptionPane.ERROR_MESSAGE);
		//?salvataggio dell'allarme non gestito
		frm.dispose();
	}

	
}
