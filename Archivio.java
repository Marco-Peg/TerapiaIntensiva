import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.tools.DiagnosticListener;

/**
 * Gestore dei pazienti nella sessione attuale 
 * pattern: singleton
 */
public class Archivio implements Visualizzazione{
	private List<Paziente> listaPazienti;
	private int dim;
	private static Archivio instance;
	static final String archivioPath="files/archivio/";
	private JMenu jmParametriVitali=new JMenu("ParametriVitali");
	private JMenu jmDiagnosi=new JMenu("Diagnosi");
	
	/**
	 * Costruttore
	 * @param dim
	 */
	private Archivio(int dim){
		this.dim=dim;
		listaPazienti = new ArrayList<Paziente>(dim);
		try(BufferedReader read = new BufferedReader(new FileReader(new File(Start.defaultPath,"archivio")));) {
			String v=read.readLine();
			while(v!=null) {
				if(v.length()>2) {
					String[] s=v.split(";");
					Paziente p=new Paziente(new File(s[0]),s[1]);
					this.addArc(p);
				}
				v=read.readLine();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e, "Login", JOptionPane.WARNING_MESSAGE); return;
		}
	}
	
	/**
	 * Restriuisce l'unica istanza di archivio
	 * @param i numero di posti massimo, se inizializzato la prima volta
	 */
	public static Archivio getArchivio(int i) {
		if(instance== null)
			instance=new Archivio(i);
		return instance;
	}
	
	/**
	 * Restriuisce l'unica istanza di archivio, default massimo 10
	 */
	public static Archivio getArchivio() {
		if(instance== null)
			instance=new Archivio(10);
		return instance;
	}
	
	/**
	 * JMenu per l'accesso a tutti i parametri vitali
	 * @return
	 */
	JMenu getParamVitali() {
		return jmParametriVitali;
	}
	
	/**
	 * aggiorna il jmenu dei parametri vitali
	 */
	private void updateParamVitali() {
		jmParametriVitali.removeAll();
		for(Paziente p: listaPazienti) {
			JMenuItem paz=new JMenuItem(p.getID());
			jmParametriVitali.add(paz);
			paz.addActionListener(p.getMonitor());
		}
	}
	
	
	/**
	 * Determina se è al completo
	 */
	public boolean isFull() {
		return (dim==listaPazienti.size());
	}
	
	/**
	 * Aggiunge un paziente e aggiorna jmenu parametri vitali
	 * @param id nuovo paziente
	 */
	public void addArc(Paziente id){	
		listaPazienti.add(id);
		updateParamVitali();
		updateDiagnosi();
	}
	
	public JMenu getDiagnosi() {
		return jmDiagnosi;
	}
	
	private void updateDiagnosi() {
		jmDiagnosi.removeAll();
		for(Paziente p: listaPazienti) {
			JMenuItem paz=new JMenuItem(p.getID());
			jmDiagnosi.add(paz);
			paz.addActionListener(new DiagnosiListener());
		}
		
	}

	/**
	 * Rimuove un paziente e aggiorna jmenu parametri vitali
	 * @param id paziente da rimuovere
	 */
	public void delArc(Paziente id){	
		listaPazienti.remove(id);
		updateParamVitali();
	}
	
	public int getArc(Paziente id){		//trova elemento in lista
		return listaPazienti.indexOf(id);
	}
	
	public Paziente getPazFromIndex(int i){		//trova elemento in lista
		return listaPazienti.get(i);
	}
	
	public String[] getArray(){			//ritorna lista completa
		String[] v= new String[listaPazienti.size()];
		int i=0;
		for(Paziente p: listaPazienti)
			v[i++]=p.getID();
		return v;
	}

	public void visualSomm(Paziente id) {
		// TODO Auto-generated method stub
		JFrame frm = new JFrame("Somministrazioni ricevute");
		frm.setSize(400,100);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setVisible(true);
		
		Container frmCP = frm.getContentPane();
		
		JTextArea area = new JTextArea();
		
		
		
		//area.setEditable(false);
		
		
	}

	public void visualPresc(Paziente id) {
		// TODO Auto-generated method stub
		
	}

	public void visualRicPreg(Paziente id) {
		// TODO Auto-generated method stub
		
	}

	public void visualParam(Paziente id) {
		// TODO Auto-generated method stub
		
	}

	public void visualMonitor(Paziente id) {
		// TODO Auto-generated method stub
		
	}
	
	public void report(Paziente id){
		// TODO Auto-generated method stub
		
	}
	
}
