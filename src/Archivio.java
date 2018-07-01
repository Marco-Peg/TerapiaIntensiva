import java.awt.Container;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * 
 * pattern: singleton
 * @author Marco
 *
 */
public class Archivio implements Visualizzazione{
	private List<Paziente> listaPazienti;
	private int dim;
	private static Archivio instance;
	
	//?
	private Archivio(int dim){
		this.dim=dim;
		listaPazienti = new ArrayList<Paziente>(dim);
	}
	//?
	public static Archivio getArchivio(int i) {
		if(instance== null)
			instance=new Archivio(i);
		return instance;
	}
	//?
	public static Archivio getArchivio() {
		if(instance== null)
			instance=new Archivio(10);
		return instance;
	}
	//?
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
	
	/*public String creaDir(Paziente id){
		Path path = Path.getPath("files/database/"+id.getID()+"/"+id.getDataRicovero());
		(new File(path.toString())).mkdirs();
		return path.toString();
	}*/

	
}
