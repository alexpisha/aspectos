package modelo;

import java.util.ArrayList;

public class Usuario {

	private String nombre;
	private String contrasena;
	private String email;
	private ArrayList<ListaReproduccion> lista;
	
	public Usuario(String pNombre, String pContrasena, String pEmail) {
		setNombre(pNombre);
		setContrasena(pContrasena);
		setEmail(pEmail);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<ListaReproduccion> getLista() {
		return lista;
	}	
	
}
