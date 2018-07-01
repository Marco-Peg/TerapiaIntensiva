
public class UserInitializer {
	
	public Personale getUser(String user, String ruolo) {
		switch(ruolo) {
		case "m": //?caso medico
			return new Medico();
		case "i":  //?caso infermiere
			return new Infermiere(user, ruolo);
		case "p": //?caso primario
			return new Primario();
		}
		return null;
	}
}

