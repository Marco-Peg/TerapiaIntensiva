
public class UserInitializer {
	
	public Personale getUser(String user, String ruolo) {
		switch(ruolo) {
		case "m": //?caso medico
			return new Medico();
			break;
		case "i":  //?caso infermiere
			return new Infermiere();
		case "p": //?caso primario
			return new Primario();
		}
	}
}

