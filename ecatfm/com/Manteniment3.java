package ecatfm.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Manteniment3 {

	private void insertar(String dni, String nom, int edat){
		
		// Crida a la funció que estableix la connexió
		Connection conn = getConnection();
		
		// Insertar a la base de dades
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO prueba (dni, nom, edat) VALUES ('" + dni + 									"','"+ nom + "', " + edat + ");");
			// Tancar consulta inserció de SQL
			statement.close();
			// Tancar connexió a BBDD
			conn.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void esborrar(String dni){
		
		// Crida a la funció que estableix la connexió
		Connection conn = getConnection();
		
		// Esborrar a la base de daes
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM prueba WHERE dni = '" + dni + "';");
			// Tancar consulta inserció de SQL
			statement.close();
			// Tancar connexió a BBDD
			conn.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Connection getConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Define the data source for the driver - La BBDD és persones.
			String sourceURL = "jdbc:mysql://localhost/personas";
			// Create a connection through the DriverManager
			Connection databaseConnection = DriverManager.getConnection(sourceURL, "root", "");
			
			return databaseConnection;
		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manteniment3 manteniment = new Manteniment3();
		
		// Entra les dades
		Scanner lector =  new Scanner(System.in);
		System.out.println("Entra el DNI ");
		String dni = lector.next();
		System.out.println("Entra el nom ");
		String nom = lector.next();
		System.out.println("Entra l'edat ");
		int edat = lector.nextInt();
		
		
		// Fem l'alta a la BBDD
		manteniment.insertar(dni, nom, edat);

		// Donar de baixa usuari per dni
		System.out.println("Entra el DNI de la persona a donar de baixa");
		dni = lector.next();
		lector.close();
		// Crida a la funció de baixa
		manteniment.esborrar(dni);
		
	}
}
