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
import java.util.List;

public class MysqlConnector {
	
	private Connection connect;	//Note (Alban): Instance de la connexion
	//Note (Alban): Methode de connexion � la bdd
	public MysqlConnector(){

		connect = null;
		//Note (Alban): Lien de la bdd 
		String url = "jdbc:mysql://localhost:3306/ibp-rcp";

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

	//Note (Alban): Insert de donn�es dans la base
	public int MysqlInsert(Projet projet){
		
		String sqlQuery = "INSERT INTO projet ('idProjet','label') VALUES ("+projet.getIdProjet()+",'"+ projet.getLabel() +"');";
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
	public int MysqlInsert(Campagne campagne){
		return 0;
	}
	public int MysqlInsert(Test test){
		return 0;
	}
	public int MysqlInsert(Testeur testeur){
		return 0;
	}
	/*
	public int MysqlInsert(String nomTable, String[] listeDonnee){
		
		//Note (Alban) : Appelle de la requete
		//Note (Alban) : Creation de la requete
				String sqlQuery = "INSERT INTO " +nomTable+ " VALUES (";
				for(int i = 0; i <= listeDonnee.length; i++) {
					sqlQuery += listeDonnee[i];
					if( i != listeDonnee.length -1) {
						sqlQuery += ", ";
					}
				}
			 	sqlQuery += " ;";
		
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
	*/
	//Note (Alban): Selection de donn�es dans la base
	public ArrayList<ArrayList<String>> MysqlSelect(List<String> nomTables, List<String> listeVariable, List<String> listeCondition){
		
		//Note (Alban) : Tableau de donn�es s�ectionner
		ArrayList<ArrayList<String>> donnees = new ArrayList<ArrayList<String>>();
		//Note (Alban) : Requete Sql de Select
		String sqlQuery = MysqlSelectRequete(nomTables, listeVariable, listeCondition);
		
		//Note (Alban) : Execution de la requete
		try {
			
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> ligne = new ArrayList<String>();
				//Note (Alban) : Pour chaque listeVariable on ajoute � un Tableau la valeur de la cellule
				for(String variable : listeVariable) {

					ligne.add(rs.getString(variable));
	 
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
	public String MysqlSelectRequete(List<String> nomTables, List<String> listeVariable, List<String> listeCondition){
				
		//Note (Alban) : Creation de la requete
		String sqlQuery = "SELECT ";
		for(String variable : listeVariable) {
			sqlQuery += variable;

			if(!(variable.equals(listeVariable.get(listeVariable.size()-1)))) {
			 
			 	sqlQuery += ", ";
			 }
			 
		}
		sqlQuery +=  " FROM ";
		for(String table : nomTables) {
			sqlQuery += table;

			if(!(table.equals(nomTables.get(nomTables.size()-1)))) {
			 
			 	sqlQuery += " NATURAL JOIN ";
			 }
			 
		}
		if (listeCondition != null){
			
			sqlQuery += " WHERE ";
			for(String condition : listeCondition) {
				sqlQuery += condition;
				
				if(!(condition.equals(listeCondition.get(listeCondition.size()-1)))) {
					 
				 	sqlQuery += " AND ";
				 }
			}
		}
	 	sqlQuery += " ;";
	 	
		return sqlQuery;
	}
}
