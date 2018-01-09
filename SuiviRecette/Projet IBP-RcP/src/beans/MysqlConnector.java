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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlConnector {
	
	private Connection connect;	//Note (Alban): Instance de la connexion
	//Note (Alban): Methode de connexion à la bdd
	
	public MysqlConnector(String url, String login, String mdp) {

		connect = null;
		//Note (Alban): Lien de la bdd 
		//String url = "jdbc:mysql://localhost:3306/ibp-rcp";
		//String login = "root";
		//String ldp = "";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, mdp);
			// TODO : Faire le deploiement de la bdd si elle existe pas
		} catch (Exception ex) {
			

			// TODO : Faire le deploiement de la bdd si elle existe pas
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
	public int MysqlInsert(Projet projet){
		String sqlQuery = "INSERT INTO projet (nomProjet, idVersion) VALUES ('"+ projet.getLabel().replaceAll("['`\"]", " ")+"',1);";
		
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Projet Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
	public int MysqlInsert(Campagne campagne){
		
		String sqlQuery = "INSERT INTO campagne (nomCampagne,idProjet) VALUES ('"+ campagne.getLabel().replaceAll("['`\"]", " ")+"',"+campagne.getProjet().getIdProjet()+");";
		
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Campagne Error : ");
			System.out.println(e.getMessage()); 
			System.out.println(sqlQuery);
		}
		return result;
	}
	public int MysqlInsert(Testeur testeur){

		String sqlQuery = "INSERT INTO testeur (nomTesteur) VALUES ('"+ testeur.getNomTesteur().replaceAll("['`\"]", " ")+"');";
		
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Testeur Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
	public int MysqlInsert(Test test){

		String sqlQuery = "INSERT INTO test (date,heure,statut, nomTest, idCampagne,idTesteur) "
				+ "VALUES ('"+ test.getDate().replaceAll("['`\"]", " ") +"','"+ test.getHeure().replaceAll("['`\"]", " ") +"','"+ test.getStatut().replaceAll("['`\"]", " ") +"',"
				+ "'"+ test.getNomTest().replaceAll("['`\"]", " ") +"',"+ test.getCampagne().getIdCampagne() +", "
				+ test.getTesteur().getIdTesteur() +");";
		
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Test Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	//Note (Alban): Selection de données dans la base
	public ArrayList<ArrayList<String>> MysqlSelect(List<String> nomTables, List<String> listeVariable, String condition){
		
		//Note (Alban) : Tableau de données séectionner
		ArrayList<ArrayList<String>> donnees = new ArrayList<ArrayList<String>>();
		//Note (Alban) : Requete Sql de Select
		String sqlQuery = MysqlSelectRequete(nomTables, listeVariable, condition);
		
		//Note (Alban) : Execution de la requete
		try {
			
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			/***
			 * implementation d'un arraylist en boucle à demander
			 */
			
			while (rs.next()) {
				ArrayList<String> ligne = new ArrayList<String>();
				//Note (Alban) : Pour chaque listeVariable on ajoute à un Tableau la valeur de la cellule
				for(String variable : listeVariable) {

					ligne.add(rs.getString(variable));
	 
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
	public String MysqlSelectRequete(List<String> nomTables, List<String> listeVariable, String condition){
				
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
		if (condition != ""){
			
			sqlQuery += " WHERE "+ condition;
		}
	 	sqlQuery += " ;";
	 	
	 	//System.out.println(sqlQuery); 
		return sqlQuery;
	}
	public int getValueAutoIncrement(String tableName) {
    	int currentValue = -1;
    	String sqlQuery = "Select AUTO_INCREMENT From INFORMATION_SCHEMA.TABLES Where TABLE_SCHEMA = 'ibp-rcp' And TABLE_NAME = '"+tableName+"';";
    	try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
	    		currentValue = rs.getInt(1);
	    	}
		} catch (SQLException e) {
			System.out.println("MysqlSelect Error : ");
			System.out.println(e.getMessage());
		}
    	
    	return currentValue;
    }
}
