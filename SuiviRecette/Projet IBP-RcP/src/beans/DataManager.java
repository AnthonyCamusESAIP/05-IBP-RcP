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
	private static List<Testeur> importedTesteurs = new ArrayList<Testeur>();
	
	private static List<Projet> existingProjects = new ArrayList<Projet>();
	private static List<Version> existingVersions = new ArrayList<Version>();
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
		
		initExistingVersions();
		
		initExistingTesteurs();
		
		for(Version version : existingVersions){
			initExistingProjects(version);
		}
		
		for(Version version : existingVersions){
			for(Projet projet : version.getProjets()){
				initExistingCampagnes(projet);
			}
		}
		
		for(Version version : existingVersions){
			for(Projet projet : version.getProjets()){
				for(Campagne campagne : projet.getCampagnes()){
					initExistingTest(campagne);
				}
			}
		}
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
	
	public void initExistingVersions() {
		
		List<String> nomTables = new ArrayList<String>();
		nomTables.add("version");
		List<String> listeVariable = new ArrayList<String>();
		listeVariable.add("idVersion");
		listeVariable.add("nomVersion");
		
		String condition = "";
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		result = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
		
		for(ArrayList<String> ligne : result){
			existingVersions.add(new Version(Integer.parseInt(ligne.get(0)), ligne.get(1)));
		}
	}
	
	public void initExistingProjects(Version version) {
		// TODO: Select des projets en base
		List<Projet> lstProjet = new ArrayList<Projet>();
		List<String> nomTables = new ArrayList<String>();
		nomTables.add("projet");
		nomTables.add("version");
		List<String> listeVariable = new ArrayList<String>();
		listeVariable.add("idProjet");
		listeVariable.add("nomProjet");
		
		String condition = "version.nomVersion = '"+version.getNomVersion()+"'";
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		result = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
		
		for(ArrayList<String> ligne : result){
			lstProjet.add(new Projet(Integer.parseInt(ligne.get(0)), ligne.get(1)));
			existingProjects.add(new Projet(Integer.parseInt(ligne.get(0)), ligne.get(1)));
		}
		version.setProjets(lstProjet);
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
				//System.out.println(ligne.get(0)+" "+ligne.get(2)+""+ligne.get(3)+" "+ligne.get(4));
			}
			
			
		}
		campagne.setTests(lstTest);
		
	}

	
	// Note (Alban) : Sauvegarde des objets importés
	
	public void sauvegardeImportedData() {

		initExisting();
		
		initImportedProjects(excel.ReadExcel());
		initImportedTesteurs(excel.ReadExcel());
		initImportedCampagnes(excel.ReadExcel());
		initImportedTest(excel.ReadExcel());
		
		importedTesteurs.remove(0);
		importedProjects.remove(0);
		for (Testeur impTesteur : importedTesteurs) {
			boolean alreadyExistTesteurs = false; 
			for (Testeur exiTesteur : existingTesteurs) {  
				if (impTesteur.getNomTesteur().equals(exiTesteur.getNomTesteur())) {
					alreadyExistTesteurs = true;
				}
			}
			if (!alreadyExistTesteurs) {
				existingTesteurs.add(impTesteur);
				mysqlConnect.MysqlInsert(impTesteur);
			}
		}
		
		// Note (Alban) : Ajout Version création association dans la bdd (attendre verification)
		
		// Note (Alban) : Sauvegarde des projets inexistants
		for (Projet impProject : importedProjects) {
			boolean alreadyExistProject = false;
			for (Projet exiProject : existingProjects) {
				
				if (impProject.getLabel().equals(exiProject.getLabel())) {
					alreadyExistProject = true;
				}
			}
			if (!alreadyExistProject) {
				
				existingProjects.add(impProject);
				
				mysqlConnect.MysqlInsert(impProject);
			}
		}
		
		
		// Note (Alban) : Sauvegarde des campagnes inexistantes
		for (Version version : existingVersions){
			for (Projet impProject : importedProjects) {
				for (Projet exiProject : version.getProjets()) {
					if (impProject.getLabel().equals(exiProject.getLabel())) {
					
						for (Campagne impCampagne : impProject.getCampagnes()) {
							boolean alreadyExistCampagne = false;
							for (Campagne exiCampagne : exiProject.getCampagnes()) {
	
								if (impCampagne.getLabel().equals(exiCampagne.getLabel())) {
									alreadyExistCampagne = true; 
								}
							}
							if (!alreadyExistCampagne) {
								
								impCampagne.setProjet(exiProject);
								
								List<Campagne> lstCamp = exiProject.getCampagnes();
								lstCamp.add(impCampagne);
								exiProject.setCampagnes(lstCamp);
								
								mysqlConnect.MysqlInsert(impCampagne);
							}	
							
						}					
					}
				}
			}
		}
		
		
		// Note (Alban) : Sauvegarde des tests inexistants
		//System.out.println("Entrer save Test");
		for (Version version : existingVersions) {
			for (Projet impProject : importedProjects) {
				for (Projet exiProject : version.getProjets()) {
					
					if (impProject.getLabel().equals(exiProject.getLabel())) {
						
						for (Campagne impCampagne : impProject.getCampagnes()) {
							for (Campagne exiCampagne : exiProject.getCampagnes()) {
								
								if (impCampagne.getLabel().equals(exiCampagne.getLabel())) {

									int cmp = 0;
									for (Test impTest : impCampagne.getTests()) {
										cmp++;
										//System.out.println(cmp);
										boolean alreadyExistTest = false;
										for (Test exiTest : exiCampagne.getTests()) {
											if ((impTest.getNomTest().equals(exiTest.getNomTest()))&&(impTest.getDate().equals(exiTest.getDate()))&&(impTest.getHeure().equals(exiTest.getHeure()))&&(impTest.getStatut().equals(exiTest.getStatut()))) {
												alreadyExistTest = true; 
											}
										}
										if (!alreadyExistTest) {
											
											impTest.setCampagne(exiCampagne);
											
											List<Test> lstTest = exiCampagne.getTests();
											lstTest.add(impTest);
											exiCampagne.setTests(lstTest);
											
											mysqlConnect.MysqlInsert(impTest);
										}
									}
								}
							}
						}					
					}
				}
			}
		}

		//System.out.println("Sortie save Test");
		//Vider l'existing ? existingProjects = null;
	}

	// Note (Alban) : Association CSV Classes

	/*
	Note (Alban) : En attente de verification automatisation en base 
	public static void initImportedVersions(){
		
	}
	*/
	
<<<<<<< HEAD
	public void initExistingTesteurs() {
		// TODO: Select des testeurs en base
	}
	
	public void initImportedTesteurs() {
		// TODO: Lecture des testeurs du fichier excel
	}
	
	public void dataInsertion(String typeObject) {
		switch (typeObject) {
			case "Projet":
				// TODO: Insertion des projets importés
			case "Campagne":
				// TODO: Insertion des campagnes importées
			case "Test":
				// TODO: Insertion des test importés
			case "Testeur":
				// TODO: Insertion des testeurs importés
		}	
=======
	//Note (Alban) : Initialisation des projets importés
	public static void initImportedProjects(ArrayList<ArrayList<String>> tabExcel) {
		// Note (Alban) : Lecture des projets du fichier excel
		
		// TODO : Comment definir la version ?
		
		boolean alreadyExists = false;
		int cmpt = 0;
		
		for(ArrayList<String> ligne : tabExcel){
			for(Projet projet : importedProjects){
				if(projet.getLabel().equals(ligne.get(3))){
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
	public static void initImportedCampagnes(ArrayList<ArrayList<String>> tabExcel){ 
		
		for(Projet Project : importedProjects){

			List<Campagne> lstCamp = new ArrayList<Campagne>();
			int cmp = 0;
			for(ArrayList<String> ligne : tabExcel){
				
	            if(Project.getLabel().equals(ligne.get(3))){
	            	boolean alreadyExist = false; 
	            	for(Campagne campProject : Project.getCampagnes()){
	            		if(campProject.getLabel().equals(ligne.get(4))){
	            			alreadyExist = true;
	            		}
	            	}
	            	if(!(alreadyExist)){
            			lstCamp.add(new Campagne(cmp, ligne.get(4), Project));
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
			for(Testeur Testeur : importedTesteurs){
				if(Testeur.getNomTesteur().equals(ligne.get(6))){
					alreadyExists = true;
				}
			}
			if(!alreadyExists){
				importedTesteurs.add(new Testeur( cmpt , ligne.get(6)));
				cmpt ++;
			}
			alreadyExists = false;
		}		
	}

	//Note (Alban) : Initialisation des tests importés
	public static void initImportedTest(ArrayList<ArrayList<String>> tabExcel){
		int cmpt = 0;
		for(Projet project : importedProjects){
			for(Campagne campagne : project.getCampagnes()){
				List<Test> lstTest = new ArrayList<Test>();
				for(ArrayList<String> ligne : tabExcel){
					if(campagne.getLabel().equals(ligne.get(4))){ 
						for( Testeur testeur : existingTesteurs){
							if(testeur.getNomTesteur().equals(ligne.get(6))){
								lstTest.add(new Test(cmpt ,ligne.get(0) ,ligne.get(1) ,ligne.get(2) ,ligne.get(5), campagne, testeur));
								
							}
						}
						cmpt++;
					}					
				}
				campagne.setTests(lstTest);
			}
		}
		
		
			
	}
	
	//Recuperation de l'excel
	public void createReaderExcel(){
		// TODO : Parametrer le filename et le sheetName a revoir
		String fileName = "E:\\Documents\\GitHub\\05-IBP-RcP\\SuiviRecette\\Projet IBP-RcP\\src\\beans\\Listetests.xls";		
		String sheetName ="Query1";

		excel.initReader(fileName, sheetName);
		ArrayList<ArrayList<String>> tabDonnee = excel.ReadExcel();
		initImportedProjects(tabDonnee);
		excel.close();
>>>>>>> master
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
