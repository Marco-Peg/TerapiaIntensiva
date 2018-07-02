import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;

/**
 * pattern: facade
 * @author Marco
 *
 */
public class Monitor implements Observer,ActionListener{
	private Paziente idPaziente;
	private Signal  pressione, fCardiaca, temperatura;
	private JFrame frm;
	private JTextArea pres_value, fCard_value,temp_value;
	private int last;
	
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
	
	public File getPressione(){
		return pressione.getPath();
	}
	public File getFrequenza(){
		return fCardiaca.getPath(); 
	}
	public File getTemperatura(){
		return temperatura.getPath();
	}
	
	public void stop(){
		pressione.interrupt();
		fCardiaca.interrupt();
		temperatura.interrupt();
	}
	/**
	 * Finestra di visualizzazione dei parametri ultimi 15 minuti(sempre aggiornata)
	 */
	public void visualizza(int last){
		this.last=last;//?come gestisco last?
		frm=new JFrame("Parametri "+idPaziente);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setSize(300, 250);
		JPanel values=new JPanel();
		
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
		//Compongo i componenti del monitor
		values.add(pressione.getPanel(last));
		values.add(fCardiaca.getPanel(last));
		values.add(temperatura.getPanel(last));
		 
		
		frm.setVisible(true);
		/*
		pressione.addObserver(this);
		fCardiaca.addObserver(this);
		temperatura.addObserver(this);*/
		
	}
	

	@Override
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Start.logged) visualizza(15);
		else visualizza(2*60);
	}
	
	
	
}
