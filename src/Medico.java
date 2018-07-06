import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

/**
 * Sessione per i medici
 * @author Lorenzo e Marco
 *
 */
public class Medico extends Personale{
	
	/**
	 * Costruttore: genera finestra della sessione
	 * @param id identificatore utente
	 * @param ruolo ruolo
	 */
	public Medico(String id, String ruolo) {
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
		JButton newDiagnosi= new JButton("Inserimento Diagnosi");
		newDiagnosi.setAlignmentX(Component.CENTER_ALIGNMENT);
		//newDiagnosi.addActionListener(new DiagnosiListener());
		newDiagnosi.setMargin(new Insets(10, 25, 10, 25));
		out.add(newDiagnosi);
		//Inserimento prescrizione
		JButton prescrizione= new JButton("Registra prescrizione");
		prescrizione.setAlignmentX(Component.CENTER_ALIGNMENT);
		prescrizione.addActionListener(new Prescrizione());
		prescrizione.setMargin(new Insets(10, 25, 10, 25));
		out.add(prescrizione);
		return out;
	}

	@Override
	/**
	 * Aggiunge menu caratteristici per medici
	 */
	JMenu addMenu() {
		// TODO Auto-generated method stub
		return new JMenu("");
	}


	@Override
	/**
	 * Crea l'intestazione per il medico
	 */
	JPanel setHeaderPanel() {
		JPanel out = new JPanel();
		out.setBorder(BorderFactory.createLineBorder(Color.green));
		JLabel jlabel = new JLabel("MEDICO  "+id);
	    jlabel.setFont(new Font("Verdana",1,20));
	    out.add(jlabel);
		return out;
	}
	
}
