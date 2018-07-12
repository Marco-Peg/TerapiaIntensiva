import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
	private JFrame frm=new JFrame();
	private int last;
	private ConcreteSubject subject;

	/**
	 * Costruttore: inizializza i gestori di segnali e allarmi
	 * @param idPaziente paziente a cui è associato il monitor
	 * @param subject sorgente di allarmi
	 */
	public Monitor(Paziente idPaziente, ConcreteSubject subject){
		this.idPaziente=idPaziente;
		pressione= new Signal( Signal.tipoSegnale.PRESSIONE, 1, idPaziente.getPath(), new File("files/Monitoraggio/",  Signal.tipoSegnale.PRESSIONE.toString()));
		fCardiaca= new Signal( Signal.tipoSegnale.FREQUENZACARDIACA, 5,idPaziente.getPath(), new File("files/Monitoraggio/",  Signal.tipoSegnale.FREQUENZACARDIACA.toString()));
		temperatura= new Signal( Signal.tipoSegnale.TEMPERATURA, 3, idPaziente.getPath(), new File("files/Monitoraggio/",Signal.tipoSegnale.TEMPERATURA.toString()));
		pressione.start();
		fCardiaca.start();
		temperatura.start();
		this.subject=subject;
		subject.addObserver(this); //allarme 
		subject.start();
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
		subject.deleteObserver(this);
	}
	/**
	 * Finestra di visualizzazione dei parametri ultimi last minuti(sempre aggiornata)
	 * @param last ultimi minuti in cui registrare
	 */
	public void visualizza(int last){
		this.last=last;
		frm=new JFrame("Parametri "+idPaziente.getID());
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setSize(750, 250);
		JLabel jlabel = new JLabel("MONITORAGGIO PARAMETRI VITALI "+idPaziente.getID());
	    jlabel.setFont(new Font("Verdana",1,12));
	    JPanel jpv=new JPanel();
		jpv.setLayout(new FlowLayout(FlowLayout.CENTER));	
		jpv.add(jlabel);
		frm.add(jpv, BorderLayout.NORTH);
		JPanel values=new JPanel();
		values.setLayout(new GridLayout(1,3,1,1));
		//Creo i componenti nel monitor
		
		 jpv=new JPanel();
		jpv.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpv.add(new JLabel(pressione.getSignal().toString()+":"));
		jpv.add(pressione.getPanel(last));
		values.add(jpv);
		
		jpv=new JPanel();
		jpv.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpv.add(new JLabel(fCardiaca.getSignal().toString()+":"));
		jpv.add(fCardiaca.getPanel(last));
		values.add(jpv);
		
		jpv=new JPanel();
		jpv.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpv.add(new JLabel(temperatura.getSignal().toString()+":"));
		jpv.add(temperatura.getPanel(last));
		values.add(jpv);
		values.setBorder(BorderFactory.createEtchedBorder());
		frm.add(values, BorderLayout.CENTER);
		frm.setVisible(true);
	}
	

	/**
	 * Reazione al notify del subject
	 */
	public void update(Subject sub) {
		 Alarm allarme;
		 allarme=new Alarm(idPaziente, sub.getSubjectState());
		 allarme.start();
	}

	/**
	 * azione effettuata alla pressione del tasto visualizzazione
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(frm.isShowing()) return;
		if(!Start.logged) visualizza(15);
		else visualizza(2*60);
	}
	
}
