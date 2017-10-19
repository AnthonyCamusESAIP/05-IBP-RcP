package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class MysqlConnect implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public List<Tuple> getTuple() throws ClassNotFoundException, SQLException{
		
		Connection connect = null;
		String url = "jdbc:mysql://localhost:3306/testjava";
		//System.out.println("Connection test");

		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(url, "root", "");
			//System.out.println("Connection established"+connect);
		} catch (Exception ex) {
			System.out.println("get Tuple Error :");
			System.out.println(ex.getMessage());
		}

		List<Tuple> donnees = new ArrayList<Tuple>();
		PreparedStatement pstmt = connect.prepareStatement("select NbrBrand, ValeurTotal from Donnee;");
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			
			int nbrB = rs.getInt("NbrBrand");
			int vT = rs.getInt("ValeurTotal");

			donnees.add(new Tuple(nbrB, vT));
		}
		rs.close();
		pstmt.close();
		connect.close();
		
		return donnees;
	}
	
	public static void setDonnee(int nbrBrand, int valeurTotal) throws ClassNotFoundException, SQLException{
		
		Connection connect = null;
		String url = "jdbc:mysql://localhost:3306/testjava";
		//System.out.println("Connection test");

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, "root", "");
			//System.out.println("Connection established"+connect);
		} catch (Exception ex) {
			System.out.println("set Donnee Error :");
			System.out.println(ex.getMessage());
		}

		PreparedStatement pstmt = connect.prepareStatement("INSERT INTO Donnee (NbrBrand, ValeurTotal) VALUES ("+nbrBrand+","+valeurTotal+");");
		pstmt.executeUpdate();
		
		pstmt.close();
		connect.close();
	}
	
	public static void setCsvData(ArrayList<String[]> CsvData) throws ClassNotFoundException, SQLException{
		

		System.out.println("SetCSV Data entree ");
		Connection connect = null;
		String url = "jdbc:mysql://localhost:3306/testjava";
		//System.out.println("Connection test");

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, "root", "");
			//System.out.println("Connection established"+connect);
		} catch (Exception ex) {
			System.out.println("set Donnee Error :");
			System.out.println(ex.getMessage());
		}
		ArrayList<String[]> tabCsv = CsvReader.getTabCsv();
		for(String[] ligne : tabCsv){
			PreparedStatement pstmt = connect.prepareStatement("INSERT INTO datacsv (`DateExecTest`, `HeureExecTest`, `Statut`, `Projet`, `Campagne`, `NomTest`, `NomTesteur`) VALUES ('"+ligne[0]+"','"+ligne[1]+"','"+ligne[2]+"','"+ligne[3]+"','"+ligne[4]+"','"+ligne[5]+"','"+ligne[6]+"');");
			pstmt.executeUpdate();
			pstmt.close();
		}
		
		connect.close();
	
	}
}