
public interface Subject {
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	void NotifyAll();
	int setState(int state);
	int getState();
}
