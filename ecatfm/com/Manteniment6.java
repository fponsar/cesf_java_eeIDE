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

public class Manteniment6 {

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
	
	class Persona {
		
		/* Declarem la class Persona, amb getters i setters.
		 Ho necessitem per emplenar un array list de persones
		 per guradar temporalment els valors del SELECT  */
		private String dni ;
		private String nom ;
		private int edat ;
		
		
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public int getEdat() {
			return edat;
		}
		public void setEdat(int edat) {
			this.edat = edat;
		}
				
	}
	
	private List<Persona> selectPersona() {
		// Aquesta funció selecciona totes les dades SELECT de les persones
		Connection conn = getConnection();
		Statement statement;
		
		try {
			statement = conn.createStatement();
			
			ResultSet personasRS = statement.executeQuery("SELECT * FROM prueba");
			List<Persona> personas = new ArrayList<Persona>();
			
			// Afegim noms, dnis i edats al ArrayList de Persones persona
			while (personasRS.next()){
				Persona p = new Persona();
				
				p.setNom(personasRS.getString("nom"));
				p.setDni(personasRS.getString("dni"));
				p.setEdat(personasRS.getInt("edat"));
				
				personas.add(p);
			}
			
			return personas;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null ;

	}
	
	
	private List<String> selectNoms() {
		
		// Crida a la funció que estableix la connexió
		Connection conn = getConnection();
		Statement statement;
		// Definir ArrayList de noms
		List<String> noms = new ArrayList<String>();
		try {
			statement = conn.createStatement();
			ResultSet persones = statement.executeQuery("SELECT * FROM prueba");
			
			// Afegim els noms al ArrayList noms
			while (persones.next()){
				noms.add(persones.getString("nom"));
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
		Manteniment6 manteniment = new Manteniment6();
		List<String> noms = new ArrayList<String>();
		
		List<Persona> personas = new ArrayList<Persona>();
		
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
		
		// Crida funció selecció de noms de tota la taula
		noms = manteniment.selectNoms();
		Iterator<String> it = noms.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		// Crida funció selecció de persones de tota la taula
		personas = manteniment.selectPersona();
		Iterator<Persona> itp = personas.iterator();
		
		Persona p2 ;
		while (itp.hasNext()) {
			p2 = itp.next();
			System.out.println(p2.getNom() + " - " + p2.getDni() + " - " + 	p2.getEdat());
		}
		
		
		lector.close();
		
	}
}
