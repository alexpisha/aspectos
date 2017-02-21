package modelo;

public class ControladorMusica {
	
		private static ControladorMusica miControladorMusica = new ControladorMusica();;
	

		private ControladorMusica() {

		}

		public static ControladorMusica getControladorMusica() {
			return miControladorMusica;
		}

	}


