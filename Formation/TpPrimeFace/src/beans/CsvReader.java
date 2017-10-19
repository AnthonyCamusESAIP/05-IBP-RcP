package beans;

import javax.faces.bean.ManagedBean;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;


@ManagedBean
public class CsvReader implements Serializable{

	private static final long serialVersionUID = -43368416849640569L;
	public static ArrayList<String[]> tabCsv;
	
	public static ArrayList<String[]> getTabCsv()
	{ 
		return tabCsv;
	}
	
	//@SuppressWarnings("resource")
	public static ArrayList<String[]> ReadCSV(){
		
		String LienCsv = "C:/Users/AlbanEcobichon/workspace/TpPrimeFace/src/beans/test.csv";
		String spliter = ";";
		
		try{
			BufferedReader bfCsv = new BufferedReader(new FileReader(LienCsv));
			String thisLine; 
			tabCsv = new ArrayList<String[]>();
			while ((thisLine = bfCsv.readLine()) != null) {	
				//Virer la premi
				String[] Elem = thisLine.split(spliter);
				tabCsv.add(Elem);	
			}
			bfCsv.close();
		}catch (Exception e){
			System.out.println("Probleme in ReadCSV() : "+e);
		}
		
		return tabCsv;
		
		
		     
	}
}