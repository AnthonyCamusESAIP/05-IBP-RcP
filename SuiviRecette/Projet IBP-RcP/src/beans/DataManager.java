package beans;
import java.util.*;

/**
 * 
 * Nom de classe : DataManager
 * 
 * Description : Classe dites "Controleur" permettant le traitement de données recueillies des flux
 *
 * Version : 1.0
 * 
 * Date : 25/10/2017
 * 
 * Copyright : Anthony Camus
 */

public class DataManager {
	
	private List<Projet> importedProjects;
	private List<Projet> existingProjects;
	private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/ibp-rcp", "root", "");
	private ReaderExcel excelReader;
	
	public DataManager() {
		
	}
	
	public void initExistingProjects() {
		// TODO: Select des projets en base
	}
	
	public static void initImportedProjects(ArrayList<ArrayList<String>> tabExel) {
		// TODO: Lecture des projets du fichier excel
		
		System.out.println("Test Arriver initImportedProjects");
		for(ArrayList<String> p: tabExel)
	        {
	            for(int i=0;i<p.size();i++)
	        System.out.println("["+p.get(i)+"]");
	         
	        ;}
		
		
	}
	
	
    public <T> List<T> listsIntersect(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
}
