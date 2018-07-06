import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.MaskFormatter;

/**
 * Finestra con i campi di inserimento dei dati paziente
 * @author Marco
 *
 */
public class PazienteListener implements ActionListener {
	private JFrame frm=new JFrame();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Archivio.getArchivio().isFull()) {
			JOptionPane.showMessageDialog(null, "Terapia intensiva al completo", "Registrazione paziente", JOptionPane.WARNING_MESSAGE); return;
		}
	 if(!frm.isShowing()) {
		frm= new JFrame("Registrazione nuovo paziente");
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setLayout(new FlowLayout());
		frm.setSize(400, 250);
		 JPanel nome= new JPanel();
		nome.add(new JLabel("Nome :"));
		nome.add(new JTextField(10));
		nome.setMaximumSize(new Dimension(300, 100));
		frm.add(nome);
		 JPanel cognome= new JPanel();
		cognome.add(new JLabel("Cognome :"));
		cognome.add(new JTextField(10));
		cognome.setMaximumSize(new Dimension(300, 100));
		frm.add(cognome);
		 JPanel codiceSanitario= new JPanel();
		codiceSanitario.add(new JLabel("Codice Sanitario :"));
		try {
			JFormattedTextField formatted=new JFormattedTextField(new MaskFormatter("LLLLLL##L##L###L"));
			formatted.setColumns(16);
			codiceSanitario.add(formatted);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		codiceSanitario.setMaximumSize(new Dimension(300, 100));
		frm.add(codiceSanitario);
		 JPanel luogoNascita= new JPanel();
		luogoNascita.add(new JLabel("Luogo di nascita :"));
		luogoNascita.add(new JTextField(10));
		luogoNascita.setMaximumSize(new Dimension(300, 100));
		frm.add(luogoNascita);
		   Calendar calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();
		calendar.add(Calendar.YEAR, -100);
		Date earliestDate = calendar.getTime();
		SpinnerDateModel model=new SpinnerDateModel();
		model = new SpinnerDateModel(initDate, earliestDate, initDate, Calendar.YEAR);
		 JSpinner spin= new JSpinner(model);
		spin.setEditor(new JSpinner.DateEditor(spin,"dd/MM/yyyy"));
		 JPanel dataNascita= new JPanel();
		dataNascita.add(new JLabel("Data di nascita :"));
		dataNascita.add(spin);
		frm.add(dataNascita);	
		 JButton button=new JButton("Registra nuovo paziente");
		button.setMargin(new Insets(10, 25, 10, 25));
		button.addActionListener(new InsertListener(frm, nome, cognome, codiceSanitario, luogoNascita, spin));
		frm.add(button);
		
		frm.setVisible(true);	
	  }	
	}
}
