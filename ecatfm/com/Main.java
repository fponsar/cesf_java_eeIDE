package ecatfm.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Define the data source for the driver - La BBDD és persones.
			String sourceURL = "jdbc:mysql://localhost/personas";
			// Create a connection through the DriverManager
			Connection databaseConnection = DriverManager.getConnection(sourceURL, "root", "");
			
			//
			
			// Entrar dades de persones
			Scanner lector =  new Scanner(System.in);
			System.out.println("Entra el DNI ");
			String dni = lector.next();
			System.out.println("Entra el nom ");
			String nom = lector.next();
			System.out.println("Entra l'edat ");
			int edat = lector.nextInt();
			lector.close();
			
			// Insertar a la base de dades
			Statement statement = databaseConnection.createStatement();
			statement.executeUpdate("INSERT INTO prueba (dni, nom, edat) VALUES 									('" + dni + "', '" + nom + "', " + edat + ");");
			statement.close();
			
			// Tanquem la connexió amb la base de dades (forma part del connector)
			databaseConnection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	} 

}
