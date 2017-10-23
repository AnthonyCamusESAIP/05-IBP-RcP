/*
 *	Nom de la Classe : MysqlConnector 
 * 
 * 	Description : Connexion à la base de donnée Mysl 
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
import java.util.List;

public class MysqlConnector {
	
	private Connection connect;	//Note (Alban): Instance de la connexion
	//Note (Alban): Methode de connexion à la bdd
	public MysqlConnector(){

		connect = null;
		//Note (Alban): Lien de la bdd 
		String url = "jdbc:mysql://localhost:3306/ibp-rcp?useSSL=false";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, "root", "");
		} catch (Exception ex) {
			
			System.out.println("Connexion Error :");
			System.out.println(ex.getMessage());
		}
	}
	//Note (Alban): Methode de fermeture de la connexion à la bdd
	public void MysqlClose(){

		try {
			
			connect.close();
		} catch (Exception e) {

			System.out.println("Connexion Close Error : ");
			System.out.println(e.getMessage());
		}
	}
	
	//Note (Alban): Insert de données dans la base
	public int MysqlInsert(String nomTable, String[] listeDonnee){
		
		//Note (Alban): Creation de la requete
		String sqlQuery = "INSERT INTO " +nomTable+ " VALUES (";
		for(int i = 0; i <= listeDonnee.length; i++) {
			sqlQuery += listeDonnee[i];
			if( i != listeDonnee.length -1) {
				sqlQuery += ", ";
			}
		}
		
		//Note (Alban): Execution de la requete
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
	//Note (Alban): Selection de données dans la base
	public ArrayList<ArrayList<String>> MysqlSelect(String nomTable, List<String> listeVariable, List<String> listeCondition){
		
		//Note (Alban) : Tableau de données séectionner
		ArrayList<ArrayList<String>> donnees = new ArrayList<ArrayList<String>>();
		
		//Note (Alban) : Creation de la requete
		String sqlQuery = "SELECT ";
		for(String Variable : listeVariable) {
			sqlQuery += Variable;
			
			/*
			 * if(Variable.hasNext()) {
			 *
			 *	sqlQuery += ", ";
			 *}
			 */
		}
		sqlQuery +=  " FROM "+ nomTable;
		if (listeCondition != null){
			
		}

		
		//Note (Alban) : Execution de la requete
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {

				ArrayList<String> ligne = null;
				//Note (Alban) : Pour chaque listeVariable on ajoute à un Tableau la valeur de la cellule
				for(int i = 0; i < listeVariable.length; i++) {
					ligne.add(rs.getString(listeVariable[i]));
				}
				//Note (Alban) : On ajoute le tableau aux données
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
