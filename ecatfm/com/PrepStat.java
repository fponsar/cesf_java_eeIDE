package ecatfm.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import java.sql.PreparedStatement;


class Persona {
	
	/* Declarem la class Persona, amb getters i setters.
	 Ho necessitem per emplenar un array list de persones  */
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


public class PrepStat {
// Exercici amb PreparedStatements
	
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
	
	private void insertar(Persona persona){
		
		// Crida a la funció que estableix la connexió
		Connection conn = getConnection();
		
		// Insertar a la base de dades
		PreparedStatement pstmt = null;;
		try {
			pstmt = conn.prepareStatement("INSERT INTO prueba (dni, nom, edat) VALUES (?,?, ?);");
			pstmt.setString(1, persona.getDni());
			pstmt.setString(2, persona.getNom());
			pstmt.setInt(3, persona.getEdat());
			
			// Executa la insersió
			pstmt.execute();
			
			// Tancar consulta inserció de SQL
			pstmt.close();
			// Tancar connexió a BBDD
			conn.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private List<Persona> selectPersona() {
		// Aquesta funció selecciona totes les dades SELECT de les persones
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM prueba");
			
			ResultSet personasRS = pstmt.executeQuery("SELECT * FROM prueba");
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PrepStat pStat = new PrepStat();
		
		Persona persona = new Persona();
		
		List<Persona> personas = new ArrayList<Persona>();
		
		// Entra les dades
		Scanner lector = new Scanner(System.in);
		System.out.println("Entra el DNI ");
		persona.setDni(lector.next());
		System.out.println("Entra el nom ");
		persona.setNom(lector.next());
		System.out.println("Entra l'edat ");
		persona.setEdat(lector.nextInt());

		// Fem l'alta a la BBDD
		pStat.insertar(persona);

		// Donar de baixa usuari per dni
		System.out.println("Entra el DNI de la persona a donar de baixa");
		persona.setDni(lector.next());

		// Crida a la funció de baixa
	//	pStat.esborrar(persona);

		// Actualitzar base de dades per modificar nom i edat per dni
		System.out.println("Entra el DNI de la persona a modificar");
		persona.setDni(lector.next());
		System.out.println("Entra el nom nou");
		persona.setNom(lector.next());
		System.out.println("Entra l'edat nova");
		persona.setEdat(lector.nextInt());

		// Crida funció UPDATE
	//	pStat.update(persona);
		
		
		// Crida funció selecció de persones de tota la taula
		personas = pStat.selectPersona();
		Iterator<Persona> itp = personas.iterator();
		
		Persona p2 ;
		while (itp.hasNext()) {
			p2 = itp.next();
			System.out.println(p2.getNom() + " - " + p2.getDni() + " - " + 	p2.getEdat());
		}
		
		
		lector.close();
	}
}
