import java.awt.Component;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Primario extends Medico{

	JPanel setPanel() {
		JPanel out=new JPanel();
		
		out.add(super.setPanel());
		
		JButton report= new JButton("Visualizza Report");
		report.setAlignmentX(Component.CENTER_ALIGNMENT);
//		report.addActionListener(new Report());
		report.setMargin(new Insets(10, 25, 10, 25));
		out.add(report);
		
		JButton chiusura= new JButton("Termine Ricovero");
		chiusura.setAlignmentX(Component.CENTER_ALIGNMENT);
		chiusura.addActionListener(new Chiusura());
		chiusura.setMargin(new Insets(10, 25, 10, 25));
		out.add(chiusura);
		
		return out;
	}
	
}
