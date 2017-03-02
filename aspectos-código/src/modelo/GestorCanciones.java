package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.sql.Statement;


public class GestorCanciones {
	private static GestorCanciones miGestorCanciones;
	private static ArrayList<Cancion> listaCanciones = new ArrayList<Cancion>();

	private GestorCanciones() {
	}

	public static GestorCanciones getGestorCanciones() {
		if (miGestorCanciones == null) {
			return new GestorCanciones();
		} else {
			return miGestorCanciones;
		}
	}	
		
	public ArrayList<Cancion> getTodasLasCanciones(){
		return listaCanciones;
	}
	
	public void cargarCanciones() throws IOException {
		//As� deberia de ser pero me da problemas :(
		//FileReader fr = new FileReader("/canciones/ListadoCanciones.txt");
		
		//Y asi no me da problemas :(
		FileReader fr = new FileReader("C:/Users/alex/Desktop/ListadoCanciones.txt");
		
		BufferedReader br = new BufferedReader(fr);
		String rutaOriginal = br.readLine();
		String ruta;
		Cancion c;
		int i = 1;
		String[] r;
	    while(rutaOriginal!=null) {
	    	ruta = rutaOriginal;
	    	ruta = ruta.split("/")[2];
	    	r = ruta.split("_");
	    	c = new Cancion(i, r[0], r[1], rutaOriginal);
	    	System.out.println("titulo: " + c.getTitulo() + 
	    			" autor: " + c.getAutor() + " id: " + c.getId()
	    			+ " ruta: " + c.getRuta());
	    	listaCanciones.add(c);
	    	rutaOriginal = br.readLine();
	    	
	    }
	    br.close();
	    fr.close();
	}

	public Cancion buscarCancionPorTitulo(String titulo) {
		Iterator<Cancion> it = listaCanciones.iterator();
		Cancion c = new Cancion(0, "", "", "");
		boolean encontrada = false;
		while(it.hasNext() && !encontrada){
			c = it.next();
			if (c.getTitulo().equals(titulo)){
				encontrada = true;
			}
		}
		if(!encontrada){
			return null;
		} else {
			return c;
		}
	}

	public Cancion buscarCancionPorAutores(String autores) {
		Iterator<Cancion> it = listaCanciones.iterator();
		Cancion c = new Cancion(0, "", "", "");
		boolean encontrada = false;
		while(it.hasNext() && !encontrada){
			c = it.next();
			if (c.getAutor().equals(autores)){
				encontrada = true;
			}
		}
		if(!encontrada){
			return null;
		} else {
			return c;
		}
	}	
	
}
