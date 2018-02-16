package ecatfm.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Manteniment5 {

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
	
	private List<String> selectNoms() {
		
		// Crida a la funció que estableix la connexió
		Connection conn = getConnection();
		Statement statement;
		// Definir ArrayList de noms
		List<String> noms = new ArrayList<String>();
		try {
			statement = conn.createStatement();
			ResultSet personas = statement.executeQuery("SELECT * FROM prueba");
			
			// Afegim els noms al ArrayList noms
			while (personas.next()){
				noms.add(personas.getString("nom"));
			}
			return noms;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	private void update(String dni, String nom, int edat){
		
		// Crida a la funció que estableix la connexió
		Connection conn = getConnection();
		
		// Esborrar a la base de daes
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate("UPDATE prueba SET nom = '" + nom + "', edat = " + edat + " 									WHERE dni = '" + dni + "';");
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
		Manteniment5 manteniment = new Manteniment5();
		List<String> noms = new ArrayList<String>();
		
		// Entra les dades
		Scanner lector = new Scanner(System.in);
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

		// Crida a la funció de baixa
		manteniment.esborrar(dni);

		// Actualitzar base de dades per modificar nom i edat per dni
		System.out.println("Entra el DNI de la persona a modificar");
		dni = lector.next();
		System.out.println("Entra el nom nou");
		nom = lector.next();
		System.out.println("Entra l'edat nova");
		edat = lector.nextInt();

		// Crida funció UPDATE
		manteniment.update(dni, nom, edat);
		
		// Crida funció selecció tota la taula
		noms = manteniment.selectNoms();
		Iterator<String> it = noms.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		lector.close();
		
	}
}
