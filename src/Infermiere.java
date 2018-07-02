import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

public class Infermiere extends Personale{
	
	
	public Infermiere(String id, String ruolo) {
		super.id=id;
		this.ruolo=ruolo;
		window();
	}


	@Override
	JPanel setPanel() {
		JPanel out=new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		//Inserimento nuovo paziente
		 JButton newPaziente= new JButton("Registra nuovo paziente");
		 newPaziente.setAlignmentX(Component.CENTER_ALIGNMENT);
	    newPaziente.setMargin(new Insets(10, 25, 10, 25));
		newPaziente.addActionListener(new PazienteListener());
		newPaziente.setMargin(new Insets(10, 25, 10, 25));
		out.add(newPaziente);
		//Inserimento prescrizione
		 JButton prescrizione= new JButton("Registra prescizione");
		 prescrizione.setAlignmentX(Component.CENTER_ALIGNMENT);
		prescrizione.addActionListener(new Prescizione());
		prescrizione.setMargin(new Insets(10, 25, 10, 25));
		out.add(prescrizione);
		return out;
	}

	@Override
	JMenu addMenu() {
		// TODO Auto-generated method stub
		return new JMenu("");
	}


	@Override
	JPanel setHeaderPanel() {
		JPanel out = new JPanel();
		out.setBorder(BorderFactory.createLineBorder(Color.green));
		JLabel jlabel = new JLabel("INFERMIERE  "+id);
	    jlabel.setFont(new Font("Verdana",1,20));
	    out.add(jlabel);
		return out;
	}
	
}
