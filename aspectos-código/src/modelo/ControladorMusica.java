package modelo;

public class ControladorMusica {
	
		private static ControladorMusica miControladorMusica = new ControladorMusica();;
		private Usuario usuario; 

		private ControladorMusica() {

		}

		public static ControladorMusica getControladorMusica() {
			return miControladorMusica;
		}

		public void establecerDatosUsuario(String user, String pass,String email) {
			usuario = new Usuario(user, pass, email); 		
		}

	}


