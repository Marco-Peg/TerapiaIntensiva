import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

/**
 * Sessione per gli infermieri
 * @author Marco
 *
 */
public class Infermiere extends Personale{
	
	/**
	 * Costruttore: genera finestra della sessione
	 * @param id identificatore utente
	 * @param ruolo ruolo
	 */
	public Infermiere(String id, String ruolo) {
		super.id=id;
		this.ruolo=ruolo;
		window();
	}


	@Override
	/**
	 * Aggiunge le opzioni caratteristiche per infermieri
	 */
	JPanel setPanel() {
		JPanel out=new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		//Inserimento nuovo paziente
		 JButton newPaziente= new JButton("Registra nuovo paziente");
		 newPaziente.setAlignmentX(Component.CENTER_ALIGNMENT);
		newPaziente.addActionListener(new PazienteListener());
		newPaziente.setMargin(new Insets(10, 25, 10, 25));
		out.add(newPaziente);
		//Inserimento somministrazione
		 JButton somministrazione= new JButton("Registra somministrazione");
		 somministrazione.setAlignmentX(Component.CENTER_ALIGNMENT);
		somministrazione.addActionListener(new Somministrazione());
		somministrazione.setMargin(new Insets(10, 25, 10, 25));
		out.add(somministrazione);
		return out;
	}

	@Override
	/**
	 * Aggiunge menu caratteristici per infermieri
	 */
	JMenu addMenu() {
		// TODO Auto-generated method stub
		return new JMenu("");
	}


	@Override
	/**
	 * Crea l'intestazione per l'infermiere
	 */
	JPanel setHeaderPanel() {
		JPanel out = new JPanel();
		out.setBorder(BorderFactory.createLineBorder(Color.green));
		JLabel jlabel = new JLabel("INFERMIERE  "+id);
	    jlabel.setFont(new Font("Verdana",1,20));
	    out.add(jlabel);
		return out;
	}
	
}
