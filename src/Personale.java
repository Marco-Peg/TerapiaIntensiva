import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.*;
/**
 * pattern: factory, template
 *
 */
public abstract class Personale {
	private JFrame frm;
	protected String nome, cognome, id, ruolostr,ruolo;
	
	public String ID(){
		return id;
	}
	
	public String Nome(){
		return cognome + " " + nome;
	}
	
	public String strRuolo(){
		return ruolostr;
	}

	public String Ruolo(){
		return ruolo;
	}
	
	public final void window() {
		frm= new JFrame("Terapia intesiva");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(400, 400);
		//intestazione(ruolo id)
		frm.add(setHeaderPanel(), BorderLayout.NORTH);
		//Corpo centrale(bottoni con varie opzioni)
		frm.add(setPanel(), BorderLayout.CENTER);
		//Visualizzazione somministrazioni
		 JButton somministrazioni= new JButton("Visualizza Somministrazioni");
		somministrazioni.setMargin(new Insets(10, 25, 10, 25));
		//somministrazioni.addActionListener(new Listener());
		frm.add(somministrazioni, BorderLayout.CENTER);
		//Visualizza cartelle pregresse
		 JButton cartelle= new JButton("Visualizza Cartelle Pregresse");
		cartelle.setMargin(new Insets(10, 25, 10, 25));
		//cartelle.addActionListener(new Listener());
		frm.add(cartelle, BorderLayout.CENTER);
		//barra dei menu: visualizzazione parametri; logout
		 JMenuBar jb= new JMenuBar();
		frm.setJMenuBar(jb);
		 JMenu jmVisualizza= new JMenu("Visualizza");
		jb.add(jmVisualizza);
		 JMenu jmParametriVitali=new JMenu("ParametriVitali");
		jmVisualizza.add(jmParametriVitali);
		for(Paziente p:  Archivio.getArchivio().getLista()) {
			JMenuItem paz=new JMenuItem(p.getID());
			jmParametriVitali.add(paz);
			paz.addActionListener(p.getMonitor());
		}
		 JMenu utente= new JMenu("Utente");
		 JMenuItem logout=new JMenuItem("LogOut");
		logout.addActionListener(new LogoutListener(frm));
		utente.add(logout);
		 
		jb.add(logout);
		
		jb.add(addMenu());
		
		frm.setVisible(true);
		
	}

	abstract JPanel setHeaderPanel();

	abstract JPanel setPanel();
	abstract JMenu addMenu();
}
