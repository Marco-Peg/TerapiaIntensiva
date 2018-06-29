import java.awt.BorderLayout;
import java.awt.Dimension;
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
		
		
		
		return out;
	}

	@Override
	JMenu addMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
