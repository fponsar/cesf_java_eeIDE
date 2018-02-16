package ecatfm.com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ecatfm.com.Manteniment6.Persona;

public class PrepStat {
// Exercici amb PreparedStatements
	
	
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PrepStat pStat = new PrepStat();
		
		Persona persona = new Persona();
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
		pStat.insertar(dni, nom, edat);

		// Donar de baixa usuari per dni
		System.out.println("Entra el DNI de la persona a donar de baixa");
		dni = lector.next();

		// Crida a la funció de baixa
		pStat.esborrar(dni);

		// Actualitzar base de dades per modificar nom i edat per dni
		System.out.println("Entra el DNI de la persona a modificar");
		dni = lector.next();
		System.out.println("Entra el nom nou");
		nom = lector.next();
		System.out.println("Entra l'edat nova");
		edat = lector.nextInt();

		// Crida funció UPDATE
		pStat.update(persona);
		
		
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
