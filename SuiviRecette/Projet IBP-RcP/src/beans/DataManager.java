package beans;
import java.io.FileInputStream;
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
	
	private static List<Projet> importedProjects;
	private static List<Testeur> importedTesteurs;
	
	private static List<Projet> existingProjects;
	private static List<Version> existingVersions;
	private static List<Testeur> existingTesteurs;
	
	private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/ibp-rcp", "root", "");
	private ReaderExcel excel;
	private ArrayList<ArrayList<String>> tabExcel;
	
	public List<Projet> getExistingProjects() {
		return existingProjects; 
	}

	public DataManager(FileInputStream file) {
		importedProjects = new ArrayList<Projet>();
		importedTesteurs = new ArrayList<Testeur>();
		existingProjects = new ArrayList<Projet>();
		existingVersions = new ArrayList<Version>();
		existingTesteurs = new ArrayList<Testeur>();
		excel = new ReaderExcel();
		excel.initReader(file);
		this.tabExcel = excel.ReadExcel();
		excel.close();
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

	// Note (Anthony) : Sauvegarde des données importés
	public void saveData() {
		clearData();
		initExisting();
		saveImportedTesteurs(tabExcel);
		saveImportedProjects(tabExcel);
		saveImportedCampagnes(tabExcel);
		saveImportedTest(tabExcel);
		for (Testeur testeur : importedTesteurs) {
			mysqlConnect.MysqlInsert(testeur);
		}
		for (Projet projet : importedProjects) {
			mysqlConnect.MysqlInsert(projet);
			for (Campagne campagne : projet.getCampagnes()) {
				mysqlConnect.MysqlInsert(campagne);
				for (Test test : campagne.getTests()) {
					mysqlConnect.MysqlInsert(test);
				}
			}
		}
	}

	public void clearData() {
		importedProjects.clear();
		importedTesteurs.clear();
		existingProjects.clear();
		existingTesteurs.clear();
		existingVersions.clear();
	}
	
	public void saveImportedProjects(ArrayList<ArrayList<String>> tabExcel) {
		boolean alreadyExist = false;
		int compteurId = mysqlConnect.getValueAutoIncrement("projet");
		for (ArrayList<String> line : tabExcel) {
			for (Projet project : existingProjects) {
				if (project.getLabel().equals(line.get(3))) {
					alreadyExist = true;
					break;
				}
			}
			if (!alreadyExist&&!line.get(3).equals("Projet")) {
				Projet p = new Projet(compteurId, line.get(3));
				importedProjects.add(p);
				existingProjects.add(p);
				compteurId++;
			}
			alreadyExist = false;
		}
	}
	
	public void saveImportedCampagnes(ArrayList<ArrayList<String>> tabExcel){
		boolean alreadyExist = false;
		int compteurId;
		List<Campagne> lstCampagnes = new ArrayList<Campagne>();
		for (ArrayList<String> line : tabExcel) {
			for (Projet project : existingProjects) {
				if (project.getLabel().equals(line.get(3))) {
					lstCampagnes = project.getCampagnes();
					compteurId = mysqlConnect.getValueAutoIncrement("campagne");
					for (Campagne campagne : project.getCampagnes()) {
						if (campagne.getLabel().equals(line.get(4))) {
							alreadyExist = true;
							break;
						}
					}
					if (!alreadyExist&&!line.get(3).equals("Projet")) {
						Campagne c = new Campagne(compteurId, line.get(4), project);
						lstCampagnes.add(c);
						compteurId++;
						project.setCampagnes(lstCampagnes);
					}
					alreadyExist = false;
				}
			}
		}
	}
	
	public void saveImportedTesteurs(ArrayList<ArrayList<String>> tabExcel){
		boolean alreadyExist = false;
		int compteurId = mysqlConnect.getValueAutoIncrement("testeur");
		for (ArrayList<String> line : tabExcel) {
			for (Testeur testeur : existingTesteurs) {
				if (testeur.getNomTesteur().equals(line.get(6))) {
					alreadyExist = true;
					break;
				}
			}
			if (!alreadyExist&&!line.get(3).equals("Projet")) {
				Testeur t = new Testeur(compteurId, line.get(6));
				importedTesteurs.add(t);
				existingTesteurs.add(t);
				compteurId++;
			}
			alreadyExist = false;
		}
	}
	
	public void saveImportedTest(ArrayList<ArrayList<String>> tabExcel){
		boolean alreadyExist = false;
		List<Test> lstTests = new ArrayList<Test>();
		for (ArrayList<String> line : tabExcel) {
			for (Projet project : existingProjects) {
				if (project.getLabel().equals(line.get(3))) {
					for (Campagne campagne : project.getCampagnes()) {
						if (campagne.getLabel().equals(line.get(4))) {
							lstTests = campagne.getTests();
							for(Test test : campagne.getTests()) {
								if (test.getNomTest().equals(line.get(5))&&test.getDate().equals(line.get(0))&&test.getHeure().equals(line.get(1))&&test.getStatut().equals(line.get(2))) {
									alreadyExist = true;
									break;
								}
							}
							if (!alreadyExist&&!line.get(3).equals("Projet")) {
								for (Testeur testeur : existingTesteurs) {
									if (testeur.getNomTesteur().equals(line.get(6))) {
										Test t = new Test(0, line.get(0) ,line.get(1) ,line.get(2) ,line.get(5), campagne, testeur);
										lstTests.add(t);
										campagne.setTests(lstTests);
									}
								}
								
							}
							alreadyExist = false;
						}
					}
				}
			}
		}
	}

	/*
	Note (Alban) : En attente de verification automatisation en base 
	public static void initImportedVersions(){
		
	}
	*/
}
