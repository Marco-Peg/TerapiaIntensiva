import java.util.ArrayList;
/**
 * Simulatore della sorgente di allarmi
 * @author Marco
 *
 */
public class ConcreteSubject implements Subject {
	private ArrayList<Observer> observers=new ArrayList<>();
	private int state=0;
	
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
