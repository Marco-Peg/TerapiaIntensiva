import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

public class Prova {
	private static JFrame frm;
	private static InsertListener ins;
	
		public static void main(String[] args) {
				insertPaz();
				

	}
	
	
	static void infermiere() {
		new Infermiere("Tom25", "i");
	}
	
	static void insertPaz() {
		
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
		//model = new SpinnerDateModel(initDate, earliestDate, initDate, Calendar.YEAR);
		 JSpinner spin= new JSpinner(model);
		spin.setEditor(new JSpinner.DateEditor(spin,"dd/MM/yyyy"));
		 JPanel dataNascita= new JPanel();
		dataNascita.add(new JLabel("Data di nascita :"));
		dataNascita.add(spin);
		frm.add(dataNascita);	
		 JButton button=new JButton("Registra nuovo paziente");
		button.setMargin(new Insets(10, 25, 10, 25));
		ins=new InsertListener(frm, spin);
		button.addActionListener(ins);
		frm.add(button);
		
		frm.setVisible(true);
	}
	
	void generatoreMonitor() {
		Random rn=new Random();
		float temp;
		FileWriter out=null;
		try {
			out=new FileWriter(new File("files/Monitoraggio/",  Signal.tipoSegnale.PRESSIONE.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<50; i++) {
			 temp=rn.nextFloat()*70+90;
			 System.out.println(temp);
			 try {
				out.write(temp+"\n");out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
		 out=new FileWriter(new File("files/Monitoraggio/",  Signal.tipoSegnale.TEMPERATURA.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<50; i++) {
			 temp=rn.nextFloat()*2+36;
			 System.out.println(temp);
			 try {
				out.write(temp+"\n");out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		try {
			 out=new FileWriter(new File("files/Monitoraggio/",  Signal.tipoSegnale.FREQUENZACARDIACA.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<50; i++) {
			 temp=rn.nextFloat()*30+60;
			 System.out.println(temp);
			 try {
				out.write(temp+"\n");out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static void allarme() {
		LocalTime end= (LocalTime.now()).plusMinutes(1);
		frm= new JFrame("Gestore allarme");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(700, 380);
		 JPanel intestazione=new JPanel();
		intestazione.setBorder(BorderFactory.createLineBorder(Color.black));
		intestazione.setLayout(new BoxLayout(intestazione, BoxLayout.Y_AXIS));
		intestazione.add(new JLabel("Paziente: Nome Cognome"));
		intestazione.add(new JLabel("Tipo allarme:nomeAllarme"));
		intestazione.add(new JLabel("Termine allarme: "+end.toString()));
		frm.add(intestazione, BorderLayout.NORTH);
		 JPanel center=new JPanel();
		 JPanel credenziali=new JPanel();
		 JPanel id= new JPanel();
		JTextField idField= new JTextField(10);
		id.add(new JLabel("ID utente :"));
		id.add(idField);
		id.setMaximumSize(new Dimension(300, 100));
		credenziali.add(id);
		 JPanel password= new JPanel();
		JPasswordField passField=new JPasswordField(10);
		password.add(new JLabel("Password : "));
		password.add(passField);
		password.setMaximumSize(new Dimension(300, 100));
		credenziali.add(password);
		center.add(credenziali);
		 JPanel input= new JPanel();
		JTextArea inputField= new JTextArea(10,45);
		input.add(new JLabel("Attività effettuate: "));
		input.add(inputField, BorderLayout.CENTER);
		input.setMaximumSize(new Dimension(300, 100));
		center.add(input);
		center.setBorder(BorderFactory.createLineBorder(Color.gray));
		frm.add(center, BorderLayout.CENTER);
		 JButton button=new JButton("Spegni Allarme");
		button.setMargin(new Insets(10, 25, 10, 25));
		button.addActionListener(new AlarmListener(null, idField,passField, inputField));
		frm.add(button, BorderLayout.SOUTH);
		
		frm.setVisible(true);
		try {
			Thread.sleep(1*30*1000);
		} catch (InterruptedException e) {
			return;
		}
		
		JOptionPane.showMessageDialog(null,
			    "L'allarme del paziente del tipo  non è statto gestito.",
			    "Allarme non gestito", JOptionPane.ERROR_MESSAGE);
		//?salvataggio dell'allarme non gestito
		frm.dispose();
	}
	
	public static class InsertListener implements ActionListener {
		private JFrame frm;
		private JPanel nome, cognome, codiceSanitario, luogoNascita;
		private  JSpinner spin;
		
		/**
		 * Costruttore
		 * @param frm
		 * @param nome
		 * @param cognome
		 * @param codiceSanitario
		 * @param luogoNascita
		 * @param spin
		 */
		public InsertListener(JFrame frm,JSpinner spin){
			this.frm=frm;
			
			this.spin=spin;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name,surname, codSan, lNascita;
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, -1);
			Date max=calendar.getTime();
			calendar.add(Calendar.YEAR, -100);
			Date earliestDate = calendar.getTime();
			
				Date d=(Date)(spin.getValue());
				if(d.after(max) || d.before(earliestDate) || d.equals(new Date())) System.out.println("Error");
				
				System.out.println(d);
			
		}

	}
	
}
