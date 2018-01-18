package beans;
import java.sql.*;

public class BDD {

    private Connection com = null;
    private Statement stat;
	
	public void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/ibp-rcp?useSSL=false";
			String user ="root";
			String passwd ="";
			com = DriverManager.getConnection(url,user,passwd);
			
			//creer un statement
			stat = com.createStatement();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nConnecter à la BDD");
	}
	
	public void deconnection(){
		try {
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addValues(String e, int f){
		String insert ="INSERT INTO `data_Brand` (`Brand`,`Total`) VALUES ('"+e+"','"+f+"')";
		try {
			//stat.executeQuery(insert);
			stat.executeUpdate(insert);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("\najout à la BDD");
	}

}
