import java.io.*;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.*;
/**
 *
 * @author Marco
 *
 */
public class Signal extends Thread /*implements Subject*/{
	public static enum tipoSegnale{PRESSIONE, FREQUENZACARDIACA,TEMPERATURA};
	private BufferedReader input;
	private int time, last;
	private File path;
	private String value=null;
	private tipoSegnale sig;
	private ArrayList<Observer> observers= new ArrayList<>();
	private JTextArea panelValues= null;
	
	public Signal(tipoSegnale sig, int time, File path){
		this.path=new File(path, sig.toString());
		set(sig, time);
	}
	
	public void set(tipoSegnale sig, int time){
		this.sig=sig;
		this.time=time;
		try {
			input=new BufferedReader(new FileReader("files/Monitoraggio/"+sig.toString()));
		} catch (FileNotFoundException e) {
		}
	}
	
	public void run(){
		try {
			FileWriter out=new FileWriter(path);
			do{
				value=input.readLine();
				out.append((Instant.now()).toString()+';'+value+"\n");
				notifyAll();
				 if(panelValues!= null)
				 	panelValues.setText(getValues(last));
				Thread.sleep(time*60*1000);
			}while(! this.isInterrupted());
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
	}
	
	
	public JComponent getPanel(int last){
		this.last=last;
		panelValues=new JTextArea( getValues(15), 5, 10);
		JPanel panel=new JPanel();
		panel.add(new JLabel(sig.toString()+": "));
		panel.add(panelValues);
		return panel;
	}
	
	
	
	/**
	 * Fornisce una stringa contente i valori generati negli ultimi last minuti
	 * @param last ultimi minuti in cui cercare valori
	 * @return stringa contente i valori generati negli ultimi last minuti
	 */
	public String getValues(int last){
		String values="";
		int t;
		try {
			BufferedReader read=new BufferedReader(new FileReader(path));
			String r=read.readLine();
			Instant min=(Instant.now()).minusSeconds(last*60);
			while(r!= null){
				String v[]=r.split(";");// instant;valore
				t=Integer.parseInt(v[0]);
				if( Instant.ofEpochMilli(t).isAfter(min) ){
					values+=v[1];
				}
			}
		} catch (IOException e) {
		}
		return values;
		
	}
	
	public File getPath(){
		return path;
	}
	public String getValue(){
		return value;
	}
	public tipoSegnale getSignal(){
		return sig;
	}

	/*
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void NotifyAll() {
		for(Observer o:observers){
			o.update(this);
		}
	}

	@Override
	public void setState(int state) {
		
	}

	@Override
	public int getSubjectState() {
		return 0;
	}*/
}
