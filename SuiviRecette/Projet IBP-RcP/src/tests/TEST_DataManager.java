package tests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import beans.*;

public class TEST_DataManager {

	@Test
	public void testGetExistingProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testDataManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitExisting() {
		
	}

	@Test
	public void testInitExistingTesteurs() {

		try {
			
			DataManager dm = new DataManager(new FileInputStream("Test"));
			List<Testeur> lstTesteur = new ArrayList<Testeur>();
			lstTesteur.add(new Testeur(31, "bwenda"));
			lstTesteur.add(new Testeur(32, "su24659"));
			lstTesteur.add(new Testeur(33, "at21022"));
			lstTesteur.add(new Testeur(34, "atbar11"));
			lstTesteur.add(new Testeur(35, "ritonpa"));
			
			 dm.initExistingTesteurs();
			 
			assertEquals(lstTesteur,dm.getExistingTesteur());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testInitExistingVersions() {

		try {
			
			DataManager dm = new DataManager(new FileInputStream("Test"));
			List<Version> lstVersion = new ArrayList<Version>();
			lstVersion.add(new Version(1, "Version0"));
			
			 dm.initExistingVersions();
			 
			assertEquals(lstVersion,dm.getExistingVersion());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInitExistingProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitExistingCampagnes() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitExistingTest() {
		
		DataManager dm = new DataManager(new FileInputStream("Test"));
		Projet proj = new Projet(15, "GSP13044 - PPG - PARME - Sujets Prioritaires");
		Campagne camp = new Campagne(182, "BPS Génération PSR2 Transmission",proj);
		dm.initExistingTest(camp);
		
		
		assertEquals();
	}

	@Test
	public void testSaveData() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearData() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveImportedProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveImportedCampagnes() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveImportedTesteurs() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveImportedTest() {
		fail("Not yet implemented");
	}

}
