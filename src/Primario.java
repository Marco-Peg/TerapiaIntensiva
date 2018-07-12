import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Lorenzo
 *
 */
public class Primario extends Medico{

	public Primario(String id, String ruolo) {
		super(id, ruolo);
	}

	JPanel setPanel() {
		JPanel out=new JPanel();
		
		out.add(super.setPanel());
		
		JButton report= new JButton("Visualizza Report");
		report.setAlignmentX(Component.CENTER_ALIGNMENT);
		report.addActionListener(new ReportRiassuntivo());
		report.setMargin(new Insets(10, 25, 10, 25));
		out.add(report);
		
		JButton chiusura= new JButton("Termine Ricovero");
		chiusura.setAlignmentX(Component.CENTER_ALIGNMENT);
		chiusura.addActionListener(new Chiusura());
		chiusura.setMargin(new Insets(10, 25, 10, 25));
		out.add(chiusura);
		
		return out;
	}
	
	@Override
	/**
	 * Crea l'intestazione per il medico
	 */
	JPanel setHeaderPanel() {
		JPanel out = new JPanel();
		out.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		JLabel jlabel = new JLabel("PRIMARIO "+id);
	    jlabel.setFont(new Font("Verdana",1,20));
	    out.add(jlabel);
		return out;
	}
	
}
