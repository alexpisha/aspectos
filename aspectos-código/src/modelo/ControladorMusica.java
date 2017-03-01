package modelo;

import java.awt.Desktop;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.mysql.fabric.xmlrpc.base.Array;

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

		public void compartirTwitter(String pTxt){
			if(comprobarConexion()){
			 	try{
			 		if(java.awt.Desktop.isDesktopSupported()){
			 			Desktop dk = Desktop.getDesktop();
		 				dk.browse(new URI("www.twitter.com/home?status="+pTxt));

					}
				 }catch(Exception e1){
				 	JOptionPane.showMessageDialog(null,  "Error: "+e1);
				}
			}
		}
	
		
		public boolean comprobarConexion(){
			String dirWeb = "www.twitter.com";
			boolean conectado=false;
			int puerto = 80;
			try{
				Socket s = new Socket(dirWeb, puerto);
				if(s.isConnected()){
					 conectado=!conectado;
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Es necesario tener conexión a internet para compartir tus puntuaciones.");
			}
			return conectado;
		}
		
		public ArrayList<Cancion> getTodasLasCanciones(){
			return GestorCanciones.getGestorCanciones().getTodasLasCanciones();
		}

		public String obtenerRuta(String cancionABuscar) {
			ArrayList<Cancion> canciones = getTodasLasCanciones();
			Iterator<Cancion> it = canciones.iterator();
			Cancion c;
			boolean yaEsta = false;
			String ruta = "";
			while (it.hasNext() && !yaEsta){
				 c = it.next();
				 if (cancionABuscar.equals(c.getTitulo()) || cancionABuscar.equals(c.getAutor())){
					 ruta = c.getRuta();
					 yaEsta=true;
				 }
			}
			return ruta;
		}
		
	}


