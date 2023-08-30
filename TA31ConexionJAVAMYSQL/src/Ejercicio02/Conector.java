package Ejercicio02;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conector {

	private Connection conexion;
	private String url;
	private String usuario;
	private String password;
	
	public Conector(String url, String usuario, String password) {
		
		this.url = url;
		this.usuario = usuario;
		this.password = password;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
            JOptionPane.showMessageDialog(null, "Conexión al servidor MySQL exitosa.");
            
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "No se ha podido conectar con el servidor MySQL.");
            System.out.println(e);
        }
	}

	public Connection getConexion() {
		return conexion;
	}

	public String getUrl() {
		return url;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}
	
	public void cerrarConexion() {
		try {
            conexion.close();
            JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor MySQL.");
            
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public void crearDB(String nombreBD) {
        try {
            String query = "CREATE DATABASE " + nombreBD;
            Statement st = conexion.createStatement();
            st.execute(query);

            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + nombreBD + " de forma exitosa.");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
	public void crearTabla(String nombreBD, String query) {
        try {
            String queryDb = "USE " + nombreBD + ";";
            Statement stdb = conexion.createStatement();
            stdb.executeUpdate(queryDb);

            Statement st = conexion.createStatement();
            st.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Tabla creada con éxito.");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
	public void insertarDatos(String nombreBD, String query) {
		try {
			String queryDb = "USE " + nombreBD + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(queryDb);
			
			Statement st = conexion.createStatement();
            st.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente.");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void obtenerDatos(String nombreBD, String nombreTabla, String[] listaColumnas) {
		try {
			String queryDb = "USE " + nombreBD + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(queryDb);
			
			String query = "SELECT * FROM " + nombreTabla;
			Statement st = conexion.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while (resultSet.next()) {
				StringBuilder registro = new StringBuilder();
				
	            for (String columna : listaColumnas) {
	            	registro.append(columna).append(": ").append(resultSet.getString(columna)).append(" | ");
	            }
	            System.out.println(registro.toString());
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void modificarRegistro(String nombreBD, String query) {
		try {
			String queryDb = "USE " + nombreBD + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(queryDb);
			
			Statement st = conexion.createStatement();
            st.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Datos modificados correctamente.");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void eliminarRegistro(String nombreBD, String query) {
		try {
			String queryDb = "USE " + nombreBD + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(queryDb);
			
			Statement st = conexion.createStatement();
            st.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Datos eliminados correctamente.");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}