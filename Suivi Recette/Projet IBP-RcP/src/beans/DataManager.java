package beans;
import java.util.*;

/**
 * 
 * Nom de classe : DataManager
 * 
 * Description : Classe dites "Controleur" permettant le traitement de donn�es recueillies des flux
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
	private MysqlConnector mysqlConnect;
	private ReaderExcel excelReader;
	
	public DataManager() {
		
	}
	
	public void initExistingProjects() {
		// TODO: Select des projets en base
	}
	
	public void initImportedProjects() {
		// TODO: Lecture des projets du fichier excel
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