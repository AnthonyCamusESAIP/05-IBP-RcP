package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import beans.*;

import org.junit.Test;

public class TEST_MysqlConnector {

	@Test
	public void testMysqlConnector() {
		MysqlConnector bdd = new MysqlConnector();
		MysqlConnector expected = new MysqlConnector();
		assertEquals(expected, bdd);
		bdd.MysqlClose();
	}
	@Test
	public void testMysqlClose() {

		MysqlConnector bdd = new MysqlConnector();
		bdd.MysqlClose();
		assertEquals(null, bdd);
	}
	
	@Test
	public void testMysqlInsert() {
		

		Projet projet = new Projet(1, "Test Projet");
		Campagne campagne = new Campagne(1, "Test Campagne 1", projet);
		Testeur testeur = new Testeur(1, "Test Testeur 1");
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		beans.Test test = new beans.Test(1, date , "11:51:00","N/A", "Test Test 1", campagne, testeur );
		
		MysqlConnector bdd = new MysqlConnector();
		/* Test 1 */
		//assertEquals(1,bdd.MysqlInsert(projet));
		
		/* Test 2 */
		//assertEquals(1,bdd.MysqlInsert(campagne));
		
		/* Test 3 */
		//assertEquals(1,bdd.MysqlInsert(testeur));		
		
		/* Test 4 */
		//assertEquals(1,bdd.MysqlInsert(test));
		
		bdd.MysqlClose();
	}

	@Test
	public void testMysqlSelectRequete() {
		

		MysqlConnector bdd = new MysqlConnector();
		
		/* test 1 Requete */
		String req = "SELECT projet.idProduit, projet.label, campagne.label FROM campagne NATURAL JOIN projet WHERE idProduit == 1 AND label LIKE '%P' ;";
		List<String> table = new ArrayList<String>();
		List<String> variable = new ArrayList<String>();
		String condition = "idProduit == 1 AND label LIKE '%P'";
		table.add("campagne");
		table.add("projet");
		variable.add("projet.idProduit");
		variable.add("projet.label");
		variable.add("campagne.label");
		
		assertEquals(req, bdd.MysqlSelectRequete(table, variable, condition));
		
		/* test 2 */
		req = "SELECT projet.idProduit FROM produit ;";
		table = new ArrayList<String>();
		variable = new ArrayList<String>();
		condition = "";
		variable.add("projet.idProduit");
		table.add("produit");
		assertEquals(req, bdd.MysqlSelectRequete(table, variable, condition));
		
		

		bdd.MysqlClose();
	}
	@Test
	public void testMysqlSelect() {
		

		MysqlConnector bdd = new MysqlConnector();
		
		/* test 1 Select */
		List<String> table = new ArrayList<String>();
		List<String> variable = new ArrayList<String>();
		String condition =  "";
		variable.add("projet.idProjet");
		variable.add("projet.nomProjet");
		table.add("projet");
		
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		ArrayList<String> d = new ArrayList<String>();
		ArrayList<String> d2 = new ArrayList<String>();
		d.add("1");
		d.add("Projet test 1");
		expected.add(d);
		d2.add("2");
		d2.add("Projet test 2");
		expected.add(d2);
		
		assertEquals(expected, bdd.MysqlSelect(table, variable, condition));
		
		/* test 2 */
		table = new ArrayList<String>();
		variable = new ArrayList<String>();
		condition = "idProjet = 1";
		table.add("projet");
		variable.add("projet.idProjet");
		variable.add("projet.nomProjet");
		
		expected = new ArrayList<ArrayList<String>>();
		d = new ArrayList<String>();
		d2 = new ArrayList<String>();
		d.add("1");
		d.add("Projet test 1");
		expected.add(d);
		
		assertEquals(expected, bdd.MysqlSelect(table, variable, condition));
		
		/* test 3 */
		table = new ArrayList<String>();
		variable = new ArrayList<String>();
		condition = "";
		table.add("campagne");
		table.add("projet");
		variable.add("projet.idProjet");
		variable.add("projet.nomProjet");
		variable.add("campagne.nomCampagne");
		
		expected = new ArrayList<ArrayList<String>>();

		d = new ArrayList<String>();
		d.add("1");
		d.add("Projet test 1");
		d.add("Campagne 1");		
		expected.add(d);
		d = new ArrayList<String>();
		d.add("2");
		d.add("Projet test 2");
		d.add("Campagne 2");
		expected.add(d);
		d = new ArrayList<String>();
		d.add("1");
		d.add("Projet test 1");
		d.add("Campagne 3");
		expected.add(d);
		
		assertEquals(expected, bdd.MysqlSelect(table, variable, condition));
		bdd.MysqlClose();
	}

}
