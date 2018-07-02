import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

/**
 * Monitor che gestisce la ricezione dei segnali e eventuali allarmi
 * pattern: facade
 *
 */
public class Monitor implements Observer,ActionListener{
	private Paziente idPaziente;
	private Signal  pressione, fCardiaca, temperatura;
	private JFrame frm;
	private int last;

	/**
	 * Costruttore: inizializza i gestori di segnali e allarmi
	 * @param idPaziente paziente a cui è associato il monitor
	 * @param subject sorgente di allarmi
	 */
	public Monitor(Paziente idPaziente, Subject subject){
		this.idPaziente=idPaziente;
		pressione= new Signal( Signal.tipoSegnale.PRESSIONE, 2, idPaziente.getPath(), new File("files/Monitoraggio/",  Signal.tipoSegnale.PRESSIONE.toString()));
		fCardiaca= new Signal( Signal.tipoSegnale.FREQUENZACARDIACA, 5,idPaziente.getPath(), new File("files/Monitoraggio/",  Signal.tipoSegnale.FREQUENZACARDIACA.toString()));
		temperatura= new Signal( Signal.tipoSegnale.TEMPERATURA, 3, idPaziente.getPath(), new File("files/Monitoraggio/",Signal.tipoSegnale.TEMPERATURA.toString()));
		pressione.start();
		fCardiaca.start();
		temperatura.start();
		subject.addObserver(this); //allarme 
	}
	
	/**
	 * @return path in cui salva pressione
	 */
	public File getPressione(){
		return pressione.getPath();
	}
	/**
	 * @return path in cui salva frequenza cardiaca
	 */
	public File getFrequenza(){
		return fCardiaca.getPath(); 
	}
	/**
	 * @return path in cui salva temperatura
	 */
	public File getTemperatura(){
		return temperatura.getPath();
	}
	/**
	 * termina i gestori dei segnali
	 */
	public void stop(){
		pressione.interrupt();
		fCardiaca.interrupt();
		temperatura.interrupt();
	}
	/**
	 * Finestra di visualizzazione dei parametri ultimi last minuti(sempre aggiornata)
	 * @param last ultimi minuti in cui registrare
	 */
	public void visualizza(int last){
		this.last=last;//?come gestisco last?
		frm=new JFrame("Parametri "+idPaziente);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setSize(300, 500);
		JLabel jlabel = new JLabel("MONITORAGGIO PARAMETRI VITALI "+idPaziente.getID());
	    jlabel.setFont(new Font("Verdana",1,20));
	    frm.add(jlabel, BorderLayout.NORTH);
		JPanel values=new JPanel();
		values.setLayout(new FlowLayout());
		//Creo i componenti nel monitor
		/*
		values.setLayout(new BoxLayout(values, BoxLayout.Y_AXIS));
		pres_value=new JTextArea( pressione.getValues(last), 5, 10);
		fCard_value=new JTextArea( fCardiaca.getValues(last), 5, 10);
		temp_value=new JTextArea( temperatura.getValues(last), 5, 10);
		JPanel pressionePanel=new JPanel();
		pressionePanel.add(new JLabel("Pressione: "));
		pressionePanel.add(pres_value);
		JPanel fCardiacaPanel=new JPanel();
		fCardiacaPanel.add(new JLabel("FrequenzaCardiaca: "));
		fCardiacaPanel.add(fCard_value);
		JPanel temperaturaPanel=new JPanel();
		temperaturaPanel.add(new JLabel("Temperatura: "));
		temperaturaPanel.add(temp_value);
		pres_value.setEditable(false);
		fCard_value.setEditable(false);
		temp_value.setEditable(false);
		
		values.add(pressionePanel);
		values.add(fCardiacaPanel);
		values.add(temperaturaPanel);
		*/
		//Compongo i panel dei gestori del monitor
		values.add(pressione.getPanel(last), BorderLayout.CENTER);
		values.add(fCardiaca.getPanel(last), BorderLayout.CENTER);
		values.add(temperatura.getPanel(last), BorderLayout.CENTER);
		 
		frm.setVisible(true);
		/*
		pressione.addObserver(this);
		fCardiaca.addObserver(this);
		temperatura.addObserver(this);*/
		
	}
	

	/**
	 * Reazione al notify del subject
	 */
	public void update(Subject sub) {
		/*if(sub instanceof Signal){//aggiorno la finestra di visualizzazione
			switch(((Signal) sub).getSignal()){
			case PRESSIONE: pres_value.setText(pressione.getValues(last));
				break;
			case FREQUENZACARDIACA: fCard_value.setText(pressione.getValues(last));
				break;
			case TEMPERATURA: temp_value.setText(pressione.getValues(last));
				break;
			default: break;
			}
		}*/
		if( sub instanceof Alarm){//creo gestione allarme
		 Alarm allarme;
		 allarme=new Alarm(idPaziente, sub.getSubjectState());
		 allarme.run();
		}
	}

	/**
	 * azione effettuata alla pressione del tasto visualizzazione
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Start.logged) visualizza(15);
		else visualizza(2*60);
	}
	
	
	
}
