package beans;

import java.util.ArrayList;

public class Tri {
	private ReaderExcel excel;
	private String dateTest[];
	private String heureTest[];
	private String statutTest[];
	private String projet[];
	private String campagne[];
	private String nomTest[];
	private String nomTesteur[];
	
	void tri(){
		excel = new ReaderExcel();
		excel.setNameFile("F:\\ESAIP\\Nouveau dossier\\gitHub\\05-IBP-RcP\\Suivi Recette\\Projet IBP-RcP\\src\\beans\\Listetests.xls");		
		excel.setNameFeuille("Query1");
		excel.initReader();	
		list(excel.ReadExcel());
		System.out.println(campagne);
		
	}
	
	public void list(ArrayList<ArrayList<String>> e){
		int i=1;
		int f=0;
		
		while(e.get(i)!=null){
			for(f=0;f<e.get(i).size();f++){
				
				switch(f){
				case '0':
					dateTest[f] = (String)e.get(i).get(f);
					break;
				case '1':
					heureTest[f]=e.get(i).get(f);
					break;
				case '2':
					statutTest[f]=e.get(i).get(f);
					break;
				case '3':
					projet[f]=e.get(i).get(f);
					break;
				case '4':
					campagne[f]=e.get(i).get(f);
					break;
				case '5':
					nomTest[f]=e.get(i).get(f);
					break;
				case '6':
					nomTesteur[f]=e.get(i).get(f);
					break;
				default:
					break;					
				}
			}
			i++;
		}
	}

}
