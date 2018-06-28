import java.awt.*;

import javax.swing.*;

public class Login {
	private JFrame frm;
	
	public Login() {
		frm= new JFrame("Terapia intesiva");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(300, 250);
		
		JPanel center= new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		JLabel ID= new JLabel("ID utente :");
		 JPanel id= new JPanel();
		id.add(ID);
		id.add(new JTextField(10));
		id.setMaximumSize(new Dimension(300, 100));
		center.add(id);
		 JPanel password= new JPanel();
		password.add(new JLabel("Password : "));
		password.add(new JPasswordField(10));
		password.setMaximumSize(new Dimension(300, 100));
		center.add(password);
		 JButton button=new JButton("Login");
		button.setMargin(new Insets(10, 25, 10, 25));
		button.addActionListener(new LoginListener(id, password));
		 JPanel butt=new JPanel();
		butt.add(button);
		center.add(butt);
		
		 JPanel top= new JPanel();
		top.add(new JLabel("Terapia Intensiva"));
		top.setPreferredSize(new Dimension(300,50));
		
		frm.add(center, BorderLayout.CENTER);
		frm.add(top, BorderLayout.NORTH);
		
		JMenuBar jb= new JMenuBar();
		frm.setJMenuBar(jb);
		JMenu jm= new JMenu("Visualizza");
		jb.add(jm);
		jm.add(new JMenuItem("ParametriVitali"));
		//permetti di aprire la visualizzazione dei parametri vitali....
		//.....
		
		frm.setVisible(true);
	}
}
