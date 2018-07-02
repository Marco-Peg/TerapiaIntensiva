import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
import javax.swing.text.MaskFormatter;

public class Prova {
	private static JFrame frm;
	
	public static void main(String[] args) {
		new Paziente("nome", "cognome", "codiceSanitario", "luogoNascita", new Date());
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
}
