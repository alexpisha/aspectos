package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SGBD {
	Connection connection;
	private Statement sentencia;
	private ResultSet rs;
	private static SGBD miSGBD = null;
	private String usuarioBD="";
	private String pswBD ="";

	private SGBD() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Open connection
		try {
			connection = DriverManager.getConnection("jdbc:mysql://galan.ehu.eus/Xohernandez018_bdBuscaminas",
					usuarioBD, pswBD);
			connection.setAutoCommit(true);
			sentencia = connection.createStatement();
			System.out.println("Conexion establecida");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * devuelve el atributo miSGBD
	 * 
	 * @return
	 */
	public static SGBD getSGBD() {
		if (miSGBD == null) {
			miSGBD = new SGBD();
		}
		return miSGBD;
	}

	/**
	 * metodo para realizar una insercion o actualizacion en la base de datos
	 * 
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public int actualizar(String strSQL) throws SQLException {
		return sentencia.executeUpdate(strSQL);
	}

	/**
	 * M�todo para ejecutar un seleccion en la base de datos
	 * 
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public ResultSet ejecutarQuery(String strSQL) throws SQLException {
		return this.sentencia.executeQuery(strSQL);
	}

	/**
	 * comprueba si el usuario existe o no en la base de datos
	 * 
	 * @param pNombre
	 * @return
	 */
	public boolean existeJugador(String pNombre) {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from jugador where nombre='" + pNombre + "';");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no existe el jugador
		return (contador > 0);
	}
	/**
	 * anade un usuario a la base de datos
	 * 
	 * @param pUsuario
	 * @param pContrasena
	 * @return
	 */
	public int ingresarJugador(String pNombre, String pContrasena) {
		int estado = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(
					"insert into jugador(nombre,contrasena) values('" + pNombre + "','" + pContrasena + "');");
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			estado = -1;
		}
		// Devuelvo un numero para saber si ha sido correcto o no el ingreso
		return estado;
	}

	/**
	 * Comprueba que los datos de acceso son validos
	 * 
	 * @param pUsuario
	 * @param pContrasena
	 * @return
	 */
	public boolean validarJugador(String pNombre, String pContrasena) {
		boolean resultado = false;
		try {
			rs = sentencia.executeQuery("Select contrasena from jugador where nombre='" + pNombre + "';");
			rs.next();
			resultado = rs.getString(1).equals(pContrasena);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return resultado;
	}

	
	
	/**
	 * Comprueba si en la BD hay jugadores
	 * 
	 * @return
	 */
	public boolean existenJugadores() {
		int contador = 0;
		try {
			rs = sentencia.executeQuery("Select count(*) from jugador;");
			rs.next();
			contador = rs.getInt("COUNT(*)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Si el contador es igual a 0 no hay jugadores en la BD
		return (contador > 0);
	}

	

}
