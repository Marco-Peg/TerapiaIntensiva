import java.io.File;

import javax.swing.*;

public class Monitor implements Observer{
	private Paziente idPaziente;
	private Signal  pressione, fCardiaca, temperatura;
	
	public Monitor(Paziente idPaziente, Subject subject){
		this.idPaziente=idPaziente;
		pressione= new Signal( Signal.tipoSegnale.PRESSIONE, 2, new File(idPaziente.getPath()+"/Pressione"));
		fCardiaca= new Signal( Signal.tipoSegnale.FREQUENZACARDIACA, 5, new File(idPaziente.getPath()+"/FrequenzaCardiaca"));
		temperatura= new Signal( Signal.tipoSegnale.TEMPERATURA, 3, new File(idPaziente.getPath()+"/Temperatura"));
		pressione.run();
		fCardiaca.run();
		temperatura.run();
		subject.addObserver(this);
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
	
	public void visualizza(){
		JFrame frm=new JFrame("Parametri "+idPaziente);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(300, 250);
		
		JPanel values=new JPanel();
		values.setLayout(new BoxLayout(values, BoxLayout.Y_AXIS));
		JLabel pres_value=new JLabel(pressione.getValue());
		JLabel fCard_value=new JLabel(fCardiaca.getValue());
		JLabel temp_value=new JLabel(temperatura.getValue());
		JPanel pressionePanel=new JPanel();
		pressionePanel.add(new JLabel("Pressione: "));
		pressionePanel.add(pres_value);
		JPanel fCardiacaPanel=new JPanel();
		fCardiacaPanel.add(new JLabel("FrequenzaCardiaca: "));
		fCardiacaPanel.add(fCard_value);
		JPanel temperaturaPanel=new JPanel();
		temperaturaPanel.add(new JLabel("Temperatura: "));
		temperaturaPanel.add(temp_value);
		
		values.add(pressionePanel);
		values.add(fCardiacaPanel);
		values.add(temperaturaPanel);
		frm.setVisible(true);
		
		try {
		do{
			pres_value.setText(pressione.getValue());
			fCard_value.setText(fCardiaca.getValue());
			temp_value.setText(temperatura.getValue());
				Thread.sleep(30*1000);
		}while(true);
		} catch (InterruptedException e) {
		}
	}
	
	/*
	 * dichiarare interfaccia subject
	 * definire metodo update che genera thread alarm per gestire l'allarme
	 */

	@Override
	public void update(Subject sub) {
		// TODO Auto-generated method stub
		 Alarm allarme;
		 allarme=new Alarm(idPaziente, sub.getState());
		 allarme.run();
	}
	
}
