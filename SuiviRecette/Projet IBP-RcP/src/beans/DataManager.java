package beans;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private static List<Testeur> importedTesteurs = new ArrayList<Testeur>();
	
	private static List<Projet> existingProjects = new ArrayList<Projet>();
	private static List<Testeur> existingTesteurs = new ArrayList<Testeur>();
	
	private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/ibp-rcp", "root", "");
	private ReaderExcel excel = new ReaderExcel();
	
	public List<Projet> getExistingProjects() {
		return existingProjects;
	}

	public DataManager() {
		
	}
	
	// Note (Alban) : Récupération des données existante
	public void initExisting() {
		initExistingProjects();
		initExistingTesteurs();
		
		for(Projet projet : existingProjects){
			initExistingCampagnes(projet);
		}
		
		for(Projet projet : existingProjects){
			for(Campagne campagne : projet.getCampagnes()){
				initExistingTest(campagne);
			}
		}
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
	
	public void initExistingCampagnes(Projet projet){
		// TODO: Select des campagnes par projet
		List<Campagne> lstCampagne = new ArrayList<Campagne>();
		List<String> nomTables = new ArrayList<String>();
		nomTables.add("campagne");
		nomTables.add("projet");
		List<String> listeVariable = new ArrayList<String>();
		listeVariable.add("idCampagne");
		listeVariable.add("nomCampagne");
		
		String condition = "projet.nomProjet = '"+projet.getLabel()+"'";
		 
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		result = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
		
		for(ArrayList<String> ligne : result){
			lstCampagne.add(new Campagne(Integer.parseInt(ligne.get(0)), ligne.get(1), projet));
			
		}
		projet.setCampagnes(lstCampagne);
		
	}
	
	public void initExistingTest(Campagne campagne){
		// TODO: Select des campagnes par projet
		List<Test> lstTest = new ArrayList<Test>();
		List<String> nomTables = new ArrayList<String>();
		nomTables.add("campagne");
		nomTables.add("test");
		List<String> listeVariable = new ArrayList<String>();
		listeVariable.add("idTest");
		listeVariable.add("date");
		listeVariable.add("heure");
		listeVariable.add("statut");
		listeVariable.add("nomTest");
		listeVariable.add("idTesteur");
		
		String condition = "campagne.nomCampagne = '"+campagne.getLabel()+"'";
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		result = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
		
		for(ArrayList<String> ligne : result){
			
	        lstTest = new ArrayList<Test>();
			nomTables = new ArrayList<String>();
			nomTables.add("testeur");
			listeVariable = new ArrayList<String>();
			listeVariable.add("idTesteur");
			listeVariable.add("nomTesteur");
			condition = "testeur.idTesteur = "+ligne.get(5);
			ArrayList<ArrayList<String>> resTesteur = new ArrayList<ArrayList<String>>();

			resTesteur = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
			for(ArrayList<String> lgnTesteur : resTesteur){
				Testeur testeur = new Testeur(Integer.parseInt(lgnTesteur.get(0)),lgnTesteur.get(1));
				lstTest.add(new Test(Integer.parseInt(ligne.get(0)), ligne.get(1) , ligne.get(2),  ligne.get(3), ligne.get(4), campagne, testeur));
				System.out.println(ligne.get(0)+" "+ligne.get(2)+""+ligne.get(3)+" "+ligne.get(4));
			}
			
			
		}
		campagne.setTests(lstTest);
		
	}

	public void initExistingTesteurs() {
		// TODO: Select des projets en base
		List<String> nomTables = new ArrayList<String>();
		nomTables.add("testeur");
		List<String> listeVariable = new ArrayList<String>();
		listeVariable.add("idTesteur");
		listeVariable.add("nomTesteur");
		
		String condition = "";
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		result = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
		
		for(ArrayList<String> ligne : result){
			existingTesteurs.add(new Testeur(Integer.parseInt(ligne.get(0)), ligne.get(1)));
		}
	}
	
	// Note (Alban) : Sauvegarde des objets importés
	
	public void sauvegardeImportedData() {
		
		
		initExisting();
		
		// TODO : Ajout Testeur
		for (Testeur impTesteur : importedTesteurs) {
			boolean alreadyExistProject = false;
			for (Testeur exiTesteur : existingTesteurs) {
				
				if (impTesteur.getNomTesteur() == exiTesteur.getNomTesteur()) {
					alreadyExistProject = true;
				}
			}
			if (!alreadyExistProject) {
				
				 mysqlConnect.MysqlInsert(impTesteur);
			}
		}
		
		// TODO : Ajout Projet
		for (Projet impProject : importedProjects) {
			boolean alreadyExistProject = false;
			for (Projet exiProject : existingProjects) {
				
				if (impProject.getLabel() == exiProject.getLabel()) {
					alreadyExistProject = true;
				}
			}
			if (!alreadyExistProject) {
				
				 mysqlConnect.MysqlInsert(impProject);
			}
		}
		
		// TODO : Ajout Campagne
		
		// TODO : Ajout Test
		
	}

	// Note (Alban) : Association CSV Classes

	//Note (Alban) : Initialisation des projets importés
	public static void initImportedProjects(ArrayList<ArrayList<String>> tabExcel) {
		// Note (Alban) : Lecture des projets du fichier excel
		
		boolean alreadyExists = false;
		int cmpt = 0;
		
		for(ArrayList<String> ligne : tabExcel){
			for(Projet projet : importedProjects){
				if(projet.getLabel() == ligne.get(3)){
					alreadyExists = true;
				}
			}
			if(!alreadyExists){
				importedProjects.add(new Projet( cmpt , ligne.get(3)));
				cmpt ++;
			}
			alreadyExists = false;
		}		
	}

	//Note (Alban) : Initialisation des campagnes importées
	public static void initImportedCampagnes(ArrayList<ArrayList<String>> tabExcel, List<Projet> importedProjects){ 
		
		for(Projet Project : importedProjects){

			List<Campagne> lstCamp = new ArrayList<Campagne>();
			int cmp = 0;
			for(ArrayList<String> ligne : tabExcel){
				
	            if(Project.getLabel() == ligne.get(3)){
	            	boolean alreadyExist = false; 
	            	for(Campagne campProject : Project.getCampagnes()){
	            		if(campProject.getLabel() == ligne.get(4)){
	            			alreadyExist = true;
	            		}
	            		
	            		if(!(alreadyExist)){
	            			lstCamp.add(new Campagne(cmp, ligne.get(4), Project));
	            		}
	            	}
	            }
			}
			Project.setCampagnes(lstCamp);
            cmp++;
		}
				
	}
	
	//Note (Alban) : Initialisation des testeurs importées
	public static void initImportedTesteurs(ArrayList<ArrayList<String>> tabExcel){
		
		boolean alreadyExists = false;
		int cmpt = 0;
		
		for(ArrayList<String> ligne : tabExcel){
			for(Testeur Testeur : importedTesteur){
				if(Testeur.getNomTesteur() == ligne.get(6)){
					alreadyExists = true;
				}
			}
			if(!alreadyExists){
				importedTesteur.add(new Testeur( cmpt , ligne.get(6)));
				cmpt ++;
			}
			alreadyExists = false;
		}		
	}

	//Note (Alban) : Initialisation des tests importés
	public static void initImportedTest(ArrayList<ArrayList<String>> tabExcel, List<Projet> importedProjects){
		int cmpt = 0;
		for(Projet project : importedProjects){
			for(Campagne campagne : project.getCampagnes()){
				List<Test> lstTest = new ArrayList<Test>();
				for(ArrayList<String> ligne : tabExcel){
					if(campagne.getLabel() == ligne.get(6)){
						lstTest.add(new Test(cmpt ,ligne.get(0) ,ligne.get(1) ,ligne.get(2) ,ligne.get(5), campagne, null ));
					}					
				}	
			}
		}
			
	}
	
	//Recuperation de l'excel
	public void createReaderExcel(){
		// TODO : Parametrer le filename et le sheetName a revoir
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
