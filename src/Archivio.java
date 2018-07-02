import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * 
 * pattern: singleton
 *
 */
public class Archivio implements Visualizzazione{
	private List<Paziente> listaPazienti;
	private int dim;
	private static Archivio instance;
	static final String archivioPath="files/archivio/";
	
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
	 * 
	 * @param i
	 * @return
	 */
	public static Archivio getArchivio(int i) {
		if(instance== null)
			instance=new Archivio(i);
		return instance;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Archivio getArchivio() {
		if(instance== null)
			instance=new Archivio(10);
		return instance;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isFull() {
		return (dim==listaPazienti.size());
	}
	
	public void addArc(Paziente id){	//aggiungi elemento a lista
		listaPazienti.add(id);
	}
	
	public void delArc(Paziente id){	//rimuovi elemento da lista
		listaPazienti.remove(id);
	}
	
	public int getArc(Paziente id){		//trova elemento in lista
		return listaPazienti.indexOf(id);
	}
	
	public List<Paziente> getLista(){			//ritorna lista completa
		return listaPazienti;
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
