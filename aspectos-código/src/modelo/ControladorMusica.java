package modelo;

public class ControladorMusica {
	
		private static ControladorMusica miControladorMusica = new ControladorMusica();
		private Usuario usuario; 

		private ControladorMusica() {

		}

		public static ControladorMusica getControladorMusica() {
			return miControladorMusica;
		}

		public Usuario getUsuario(){
			return usuario;
		}
		
		public void establecerDatosUsuario(String user, String pass,String email) {
			usuario = new Usuario(user, pass, email); 		
		}
		
		public void actualizarNombre(String pNombre){
			usuario.setNombre(pNombre);
		}

		public void actualizarContrasena(String pPass){
			usuario.setContrasena(pPass);
		}
		
		public void actualizarEmail(String pEmail){
			usuario.setEmail(pEmail);
		}
		
		public void resetearDatosUsuario(){
			usuario = new Usuario("","","");
		}
		
		public void resetearListasUsuario(){
			usuario.resetearLista();
		}

	}


