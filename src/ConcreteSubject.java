import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 * Simulatore della sorgente di allarmi
 * @author Marco
 *
 */
public class ConcreteSubject extends Thread  implements Subject {
	private ArrayList<Observer> observers=new ArrayList<>();
	private int state=0;
	
	public void run() {
		Random rnd=new Random();
		int t=rnd.nextInt(60);
		try(BufferedReader r=new BufferedReader(new FileReader(new File("Test/Allarmi")))) {
			Thread.sleep(t*1000);
			t=rnd.nextInt(20);
			for(; t>0; t--)  r.readLine();
			state=Integer.parseInt(r.readLine());
			NotifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void NotifyAll() {
		for(Observer o:observers)
			o.update(this);
	}

	@Override
	public void setState(int state) {
		this.state=state;
	}

	@Override
	public int getSubjectState() {
		return state;
	}

}
