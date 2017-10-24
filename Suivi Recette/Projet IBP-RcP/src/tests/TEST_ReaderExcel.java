package tests;

import org.junit.Test;
import beans.*;
import junit.framework.TestCase;

public class TEST_ReaderExcel extends TestCase {

	beans.ReaderExcel excel = new ReaderExcel();
	
	
	@Test
	public void testSetNameFile() {
		String expected = "nom";
		excel.setNameFile(expected);
		assertEquals(expected,excel.getNameFile());
		expected = "double test";
		excel.setNameFile(expected);
		assertEquals(expected,excel.getNameFile());
	}

	@Test
	public void testGetNameFile() {
		String expected = "file";
		excel.setNameFile(expected);
		assertEquals(expected,excel.getNameFile());
	}

	@Test
	public void testGetNameFeuille() {
		String expected = "doc";
		excel.setNameFeuille(expected);
		assertEquals(expected,excel.getNameFeuille());
	}

	@Test
	public void testSetNameFeuille() {
		String expected = "setting";
		excel.setNameFeuille(expected);
		assertEquals(expected,excel.getNameFeuille());
	}
	
/*
	@Test
	public void testInitReader() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testRead() {
		excel.setNameFeuille("Query1");
		excel.setNameFile("Listetest.xls");
		excel.initReader();
		ReaderExcel expected = new ReaderExcel();
		expected.equals(excel);
		
		//asssertEquals(expected.read(),excel.read());
	}
*/
	@Test
	public void testClose(){
		excel.setNameFeuille("Query1");
		excel.setNameFile("F:\\ESAIP\\Nouveau dossier\\gitHub\\05-IBP-RcP\\Suivi Recette\\Projet IBP-RcP\\src\\beans\\Listetests.xls");
		excel.initReader();
		ReaderExcel expected = new ReaderExcel();
		expected=excel;
		System.out.println(excel.ReadExcel()+"1");
		System.out.println(expected.getWorkbook1().getAllNames()+" "+expected.getFile().toString()+" pour expected");
		expected.close();
		excel.close();
		System.out.println(excel.ReadExcel());
		System.out.println(expected.getWorkbook1().getAllNames()+" "+expected.getFile().toString()+"null pour expected");
		assertEquals(expected.getFile(),excel.getFile()+"2pp");
		assertEquals(expected.getWorkbook1(),excel.getWorkbook1());
	}

	public void testReadExcel() {
		excel.setNameFeuille("Query1");
		excel.setNameFile("F:\\ESAIP\\Nouveau dossier\\gitHub\\05-IBP-RcP\\Suivi Recette\\Projet IBP-RcP\\src\\beans\\Listetests.xls");
		excel.initReader();
		ReaderExcel expected = new ReaderExcel();
		expected = excel;
		
		assertEquals(expected.ReadExcel(),excel.ReadExcel());
	}

}
