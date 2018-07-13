import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerDateModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

	/**
	 * Listener del bottone per inserimento somministrazione
	 *
	 */
	public class SomministrazioneListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			String[] paz = Archivio.getArchivio().getArray();
			
			JFrame frm = new JFrame("Somministrazioni");
			frm.setSize(400,400);
			frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frm.setVisible(true);
			
			
			JComboBox pazList = new JComboBox(paz);
			
	        JButton salva= new JButton("Salva Dati");
			
			
			JLabel label = new JLabel("SOMMINISTRAZIONI RICEVUTE:");

			JTextArea area = new JTextArea();
	        area.setColumns(20);
	        area.setLineWrap(true);
	        area.setRows(5);
	        area.setWrapStyleWord(true);
			
	        JScrollPane scroll = new JScrollPane(area);
	        
	        JPanel gPanel = new JPanel();
	        GroupLayout layout = new GroupLayout(gPanel);
	        gPanel.setLayout(layout);
	                
	        ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	        SequentialGroup h1 = layout.createSequentialGroup();
	        ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
	        h2.addComponent(scroll, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
	        h2.addComponent(salva, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
	        
	        h1.addContainerGap();
	        h1.addGroup(h2);
	        h1.addContainerGap();
	        hGroup.addGroup(Alignment.TRAILING,h1);
	        layout.setHorizontalGroup(hGroup);
	        
	        
	        ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	        SequentialGroup v1 = layout.createSequentialGroup();
			v1.addContainerGap();
	        v1.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
	        v1.addContainerGap();
	        v1.addComponent(salva, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE);
	        v1.addContainerGap();
	        vGroup.addGroup(v1);
			layout.setVerticalGroup(vGroup);
			
	        JPanel fPanel = new JPanel();
	        fPanel.setLayout(new FlowLayout());
	        
	        fPanel.add(label);
	        
	        fPanel.add(pazList);
	       
			
			Calendar calendar = Calendar.getInstance();
			Date initDate = calendar.getTime();
			calendar.add(Calendar.YEAR, -100);
			Date earliestDate = calendar.getTime();
			SpinnerDateModel model=new SpinnerDateModel();
			model = new SpinnerDateModel(initDate, earliestDate, initDate, Calendar.YEAR);
			
			JSpinner spin= new JSpinner(model);
			spin.setEditor(new JSpinner.DateEditor(spin,"dd/MM/yyyy"));
			
			JPanel dataNascita= new JPanel();
			dataNascita.add(new JLabel("Data: "));
			dataNascita.add(spin);
			fPanel.add(dataNascita); 
			
			
			SpinnerDateModel model2 = new SpinnerDateModel();
			model2.setValue(calendar.getTime());

			JSpinner spinner = new JSpinner(model2);
			spinner.setEditor(new JSpinner.DateEditor(spinner,"HH:mm      "));
			
			JPanel dataNascita2= new JPanel();
			dataNascita2.add(new JLabel("Ora: "));
			dataNascita2.add(spinner);
			fPanel.add(dataNascita2); 
			
			
			fPanel.add(new JLabel("Dosi:"));
			fPanel.add(new JTextField(5));
			
			frm.setPreferredSize(new Dimension(300, 400));
			
			frm.add(fPanel, BorderLayout.CENTER);
			frm.add(gPanel, BorderLayout.SOUTH);
	        frm.pack();
		
			/*try(FileReader file = new FileReader("Somministrazioni.txt")){
		        BufferedReader reader =  new BufferedReader(file);
		        area.read(reader, null);
			}catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
	        salva.addActionListener(new SalvaSomministrazioni(area, frm, pazList));
			

			
		}

	}
	

