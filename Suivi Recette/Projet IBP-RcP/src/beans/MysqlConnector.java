/*
 *	Nom de la Classe : MysqlConnector 
 * 
 * 	Description : Connexion � la base de donn�e Mysl 
 * 
 *  Version : 1.0
 *  
 *  Date : 23/10/2017
 *  
 *  Copyright : Alban ECOBICHON
 */

package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MysqlConnector {
	
	private Connection connect;	//Note (Alban): Instance de la connexion
	//Note (Alban): Methode de connexion � la bdd
	public MysqlConnector(){

		//Note (Alban) : Voir si probleme enlever useSSL=false
		connect = null;
		String url = "jdbc:mysql://localhost:3306/ibp-rcp?useSSL=false";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, "root", "");
		} catch (Exception ex) {
			
			System.out.println("Connexion Error :");
			System.out.println(ex.getMessage());
		}
	}
	//Note (Alban): Methode de fermeture de la connexion � la bdd
	public void MysqlClose(){

		try {
			
			connect.close();
		} catch (Exception e) {

			System.out.println("Connexion Close Error : ");
			System.out.println(e.getMessage());
		}
	}
	
	//Note (Alban): Cr�ation d'une vue
	public void MysqlCreate(String nomTable, String[] listeVariable){

		String sqlQuery = "CREATE VIEW [ Current " +nomTable+ " List ] AS "
				+ "SELECT " + nomTable + " FROM ";
		for(int i = 0; i < listeVariable.length; i++) {
			sqlQuery += listeVariable[i];
			if( i != listeVariable.length ) {
				sqlQuery += ", ";
			}
		}
		
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlCreate Error : ");
			System.out.println(e.getMessage());
		}
	}
	//Note (Alban): Insert de donn�es dans la base
	public void MysqlInsert(String nomTable, String[] listeDonnee){
		
		String sqlQuery = "INSERT INTO " +nomTable+ " VALUES (";
		for(int i = 0; i <= listeDonnee.length; i++) {
			sqlQuery += listeDonnee[i];
			if( i != listeDonnee.length -1) {
				sqlQuery += ", ";
			}
		}
		
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Error : ");
			System.out.println(e.getMessage());
		}
	}
	//Note (Alban): Selection de donn�es dans la base
	public ArrayList<ArrayList<String>> MysqlSelect(String nomTable, String[] listeVariable){
		
		ArrayList<ArrayList<String>> donnees = new ArrayList<ArrayList<String>>();
		String sqlQuery = "SELECT " +nomTable+ " FROM ";
		for(int i = 0; i < listeVariable.length; i++) {
			sqlQuery += listeVariable[i];
			if( i != listeVariable.length ) {
				sqlQuery += ", ";
			}
		}
		
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			/***
			 * implementation d'un arraylist en boucle � demander
			 */
			
			while (rs.next()) {
				ArrayList<String> ligne = null;
				
				//Note (Alban) : Pour chaque listeVariable on ajoute � un Tableau la valeur de la cellule
				for(int i = 0; i < listeVariable.length; i++) {
					ligne.add(rs.getString(listeVariable[i]));
				}
				//Note (Alban) : On ajoute le tableau aux donn�es
				donnees.add(ligne);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlSelect Error : ");
			System.out.println(e.getMessage());
		}
		return donnees;
		
	}

}
