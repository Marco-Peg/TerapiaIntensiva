
public interface Subject {
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	void NotifyAll();
	void setState(int state);
	int getSubjectState();
}
