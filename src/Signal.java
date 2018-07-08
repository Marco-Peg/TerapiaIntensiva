import java.awt.Color;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

/**
 *Gestore dei segnali
 * @author Marco
 */
public class Signal extends Thread /*implements Subject*/{
	public static enum tipoSegnale{PRESSIONE, FREQUENZACARDIACA,TEMPERATURA};
	private BufferedReader input;
	private int time, last;
	private File path;
	private String value=null;
	private tipoSegnale sig;
	private JTextArea panelValues= null;
	static private DateFormat form=  new  SimpleDateFormat();
	
	/**
	 * Costruttore: crea il path in cui salvare, e setta la registrazione dei segnali
	 * @param sig  specifica il tipo di segnale da registrare
	 * @param time ogni quanto leggere segnale da registrare
	 * @param path file in cui salvare i dati
	 * @param inputFile sorgente dei segnali
	 */
	public Signal(tipoSegnale sig, int time, File path, File inputFile){
		this.path=new File(path, sig.toString());
		if(!this.path.exists()) {
			try {
				this.path.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		set(sig, time, inputFile);
	}
	
	/**
	 * Settaggio del gestore de segnale
	 * @param sig segnale da gestire
	 * @param time intervallo di tempo della registrazione
	 * @param inputFile sorgente dei segnali
	 */
	public void set(tipoSegnale sig, int time,  File inputFile){
		this.sig=sig;
		this.time=time;
		try {
			input=new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Thread: registra il segnale dalla sorgente ogni intervallo di tempo fino a quando non viene interotta dal monitor
	 */
	public void run(){
		try (FileWriter out=new FileWriter(path,true);){
			do{
				value=input.readLine();
				out.write(form.format(new Date())+';'+value+"\n"); out.flush();
				 //if(panelValues != null)
				try {
				 	panelValues.setText(getValues(last));
				}catch (Exception e) {
				}
				Thread.sleep(time*30*1000);
			}while(! this.isInterrupted());
		} catch (IOException e) { JOptionPane.showMessageDialog(null,e, sig.toString(), JOptionPane.WARNING_MESSAGE);
		} catch (InterruptedException e) { JOptionPane.showMessageDialog(null,e, sig.toString(), JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Costruisce il pannello contente i segnali registrati
	 * @param last ultimi minuti in cui prendere valori
	 * @return	JPanel con i valori
	 */
	public JComponent getPanel(int last){
		this.last=last;
		panelValues=new JTextArea( getValues(last), 9, 20);
		panelValues.setEditable(false);
		panelValues.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel panel=new JPanel();
		//panel.add(new JLabel(sig.toString()+": "));
		panel.add(new JScrollPane(panelValues));
		return panel;
	}
	
	
	
	/**
	 * Fornisce una stringa contente i valori generati negli ultimi last minuti
	 * @param last ultimi minuti in cui cercare valori
	 * @return stringa contente i valori generati negli ultimi last minuti
	 */
	public String getValues(int last){
		String values="";
		Date t;
		try(BufferedReader read=new BufferedReader(new FileReader(path))) {
			String r=read.readLine();
			Calendar c=Calendar.getInstance();
			c.add(Calendar.MINUTE, -last);
			Date min= c.getTime();
			//Instant min=(Instant.now()).minusSeconds(last*60);
			while(r!= null){
			if(r.length()>1) {
				String v[]=r.split(";");// instant;valore
				t=form.parse(v[0]);
				if(t.after(min) ){
					values=v[1]+" ; "+v[0]+"\n"+values;
				}
				r=read.readLine();
			}
			}
		} catch (IOException e) {
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return values;
		
	}
	
	/**
	 * @return percorso in cui salva i segnali registrati
	 */
	public File getPath(){
		return path;
	}
	/**
	 * @return tipo del segnale che registra
	 */
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
