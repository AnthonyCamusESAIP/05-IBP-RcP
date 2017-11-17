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
	
	private static List<Projet> importedProjects = new ArrayList<Projet>();
	private static List<Projet> existingProjects = new ArrayList<Projet>();
	//private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/ibp-rcp", "root", "");
	private ReaderExcel excel = new ReaderExcel();
	
	public DataManager() {
		
	}
	
	public void initExistingProjects() {
		// TODO: Select des projets en base
	}
	
	public static void initImportedProjects(ArrayList<ArrayList<String>> tabExcel) {
		// TODO: Lecture des projets du fichier excel
		
		boolean projetPresent = false;
		int cmpt = 0;
		
		for(ArrayList<String> ligne : tabExcel){
			for(Projet projet : importedProjects){
				if(projet.getLabel() == ligne.get(3)){
					projetPresent = true;
				}
			}
			if(!projetPresent){
				importedProjects.add(new Projet( cmpt , ligne.get(3)));
				cmpt ++;
			}
		}
		
		
	}
	
	//Recuperation de l'excel
	public void createReaderExcel(){

		String fileName = "C:/Users/AlbanEcobichon/Dropbox/PCPI-05_IBP-RCP_2017/RE PCPI-05_IBP-RcP_2017  Recueil des besoins/Liste des tests exécutés MOA VPS05-01 (Conflit lié au codage Unicode).xls";		
		String sheetName ="Query1";

		excel.initReader(fileName, sheetName);
		ArrayList<ArrayList<String>> tabDonnee = excel.ReadExcel();
		initImportedProjects(tabDonnee);
		excel.close();
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
