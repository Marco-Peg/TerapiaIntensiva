
public class UserInitializer {
	
	public Personale getUser(String user, String ruolo) {
		switch(ruolo) {
		case "m": //?caso medico
			return new Medico(user, ruolo);
		case "i":  //?caso infermiere
			return new Infermiere(user, ruolo);
		case "p": //?caso primario
			return new Primario(user, ruolo);
		}
		return null;
	}
}

