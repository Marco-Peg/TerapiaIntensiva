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

		JFrame frm = new JFrame("Somministrazioni");
		frm.setSize(400,400);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setVisible(true);
		
		JLabel label = new JLabel("SOMMINISTRAZIONI RICEVUTE:");

		JTextArea area = new JTextArea();
		area.setColumns(20);
		area.setLineWrap(true);
		area.setRows(5);
		area.setWrapStyleWord(true);
		area.setEditable(false);

		
		JScrollPane scroll = new JScrollPane(area);

		GroupLayout layout = new GroupLayout(frm.getContentPane());
		frm.getContentPane().setLayout(layout);
        
        //Create a parallel group for the horizontal axis
		ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        //Create a sequential and a parallel groups
		SequentialGroup h1 = layout.createSequentialGroup();
		ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        //Add a scroll panel and a label to the parallel group h2
		h2.addComponent(scroll, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
		h2.addComponent(label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
        
        //Add a container gap to the sequential group h1
		h1.addContainerGap();
        // Add the group h2 to the group h1
		h1.addGroup(h2);
		h1.addContainerGap();
        //Add the group h1 to hGroup
		hGroup.addGroup(Alignment.TRAILING,h1);
        //Create the horizontal group
		layout.setHorizontalGroup(hGroup);
        
        //Create a parallel group for the vertical axis
		ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        //Create a sequential group
		SequentialGroup v1 = layout.createSequentialGroup();
        //Add a container gap to the sequential group v1
		v1.addContainerGap();
        //Add a label to the sequential group v1
		v1.addComponent(label);
		v1.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
        //Add scroll panel to the sequential group v1
		v1.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
		v1.addContainerGap();
        //Add the group v1 to vGroup
		vGroup.addGroup(v1);
        //Create the vertical group
		layout.setVerticalGroup(vGroup);
		frm.pack();

		try(FileReader file = new FileReader(id.getPath(), "Somministrazioni")){
	        BufferedReader reader =  new BufferedReader(file);
	        area.read(reader, null);
		}catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
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
