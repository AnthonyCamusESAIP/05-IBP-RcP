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
	private static List<Campagne> importedCampagnes = new ArrayList<Campagne>();
	private static List<Projet> existingProjects = new ArrayList<Projet>();
	private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/ibp-rcp", "root", "");
	
	private ReaderExcel excel = new ReaderExcel();
	
	public DataManager() {
		
	}
	
	public void initExistingProjects() {
		// TODO: Select des projets en base
		List<String> nomTables = new ArrayList<String>();
		nomTables.add("projet");
		List<String> listeVariable = new ArrayList<String>();
		listeVariable.add("idProjet");
		listeVariable.add("nomProjet");
		
		String condition = "";
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		result = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
		
		for(ArrayList<String> ligne : result){
			existingProjects.add(new Projet(Integer.parseInt(ligne.get(0)), ligne.get(1)));
		}
		
		
		
	}
	
	public static void initImportedProjects(ArrayList<ArrayList<String>> tabExcel) {
		// Note (Alban) : Lecture des projets du fichier excel
		
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
			projetPresent = false;
		}
		
		
	}
	
	// Notes (Alban) : Code a Maryan
	public static void initImportedCampagnes(ArrayList<ArrayList<String>> tabExcel){
	    // TODO: Remanier pour reprendre depuis projet 
	    boolean campagnePresent = false;
	    int cmpt = 0;
	    
	    for(ArrayList<String> ligne : tabExcel){
	        for(Campagne campagne : importedCampagnes){
	            if(campagne.getLabel() == ligne.get(4)){
	                campagnePresent =true;
	            }
	        }
	        if(!campagnePresent){
	            //importedCampagnes.add(new Campagne(cmpt,ligne.get(4)), /*projet*/ );
	            cmpt++;
	        }
	        campagnePresent = false;
	    }
	}
	
	// Notes (Alban) : Code a Maryan
	//Ajout des listes dans chaque projets
	public static void initListsCampagne(ArrayList<ArrayList<String>> tabExcel){
	    boolean campagnePresent = false;
	    
	    for(int j = 0 ; j < importedProjects.size() ; j++){
	        //List<Campagne> list = new ArrayList<Campagne>();
	        for(int k = 0 ; k < importedCampagnes.size() ; k++){
	            for(ArrayList<String> ligne : tabExcel){
	                if(ligne.get(3) == importedProjects.get(j).getLabel() || ligne.get(4) == importedCampagnes.get(k).getLabel()){
	                    campagnePresent = true;
	                }
	            }
	            if(!campagnePresent){
	                //list.add(importedCampagnes);
	            }
	            campagnePresent = false;
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
