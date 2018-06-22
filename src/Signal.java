import java.io.*;

public class Signal extends Thread{
	public static enum tipoSegnale{PRESSIONE, FREQUENZACARDIACA,TEMPERATURA};
	private BufferedReader input;
	private int time;
	private File path;
	private String value=null;
	public Signal(tipoSegnale sig, int time, File path){
		this.path=path;
		set(sig, time);
	}
	
	public void set(tipoSegnale sig, int time){
		this.time=time;
		try {
			switch(sig){
			case PRESSIONE: 
				input=new BufferedReader(new FileReader("files/Monitoraggio/Pressione"));
				break;
			case FREQUENZACARDIACA:
				input=new BufferedReader(new FileReader("files/Monitoraggio/Frequenza"));
				break;
			case TEMPERATURA:
				input=new BufferedReader(new FileReader("files/Monitoraggio/timeeratura"));
				break;
			default:	break;
			}
				
			} catch (FileNotFoundException e) {
			}
	}
	
	public void run(){
		try {
			FileWriter out=new FileWriter(path);
			do{
				value=input.readLine();
				out.append(value+"\n");
				Thread.sleep(time*60*1000);
			}while(! this.isInterrupted());
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
	}
	
	public File getPath(){
		return path;
	}
	public String getValue(){
		return value;
	}
}
