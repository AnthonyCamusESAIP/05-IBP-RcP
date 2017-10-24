package beans;

import java.util.ArrayList;

public class Tri {
	private String heureTest[];
	private String statutTest[];
	private String projet[];
	private String campagne[];
	private String nomTest[];
	private String nomTesteur[];
	private String dateTest[];
	
	public String[] getDateTest() {
		return dateTest;
	}

	public void setDateTest(String[] dateTest) {
		this.dateTest = dateTest;
	}

	public String[] getHeureTest() {
		return heureTest;
	}

	public void setHeureTest(String[] heureTest) {
		this.heureTest = heureTest;
	}

	public String[] getStatutTest() {
		return statutTest;
	}

	public void setStatutTest(String[] statutTest) {
		this.statutTest = statutTest;
	}

	public String[] getProjet() {
		return projet;
	}

	public void setProjet(String[] projet) {
		this.projet = projet;
	}

	public String[] getCampagne() {
		return campagne;
	}

	public void setCampagne(String[] campagne) {
		this.campagne = campagne;
	}

	public String[] getNomTest() {
		return nomTest;
	}

	public void setNomTest(String[] nomTest) {
		this.nomTest = nomTest;
	}

	public String[] getNomTesteur() {
		return nomTesteur;
	}

	public void setNomTesteur(String[] nomTesteur) {
		this.nomTesteur = nomTesteur;
	}

	
	public void list(ArrayList<ArrayList<String>> e){
		int i=1;
		int f=0;
		int g =e.size();
		//System.out.println("___1___"+e.size());
		
		//
		dateTest = new String[g];
		heureTest = new String[g];
		statutTest = new String[g];
		projet = new String[g];
		campagne = new String[g];
		nomTest = new String[g];
		nomTesteur = new String[g];
		
		while(i<e.size()||e.get(i).get(f)==null){
			if(e.get(i).get(f)==null){
				break;
			}
			f= 0;
			while(f<e.get(i).size()){				
				switch(f){
				case 0:
					dateTest[f] = e.get(i).get(f);
					break;
				case 1:
					heureTest[f]=e.get(i).get(f);
					break;
				case 2:
					statutTest[f]=e.get(i).get(f);
					break;
				case 3:
					projet[f]=e.get(i).get(f);
					break;
				case 4:
					campagne[f]=e.get(i).get(f);
					break;
				case 5:
					nomTest[f]=e.get(i).get(f);
					break;
				case 6:
					nomTesteur[f]=e.get(i).get(f);
					break;
				default:
					System.out.println("ne passe dans rien");
					
					break;					
				}
				System.out.println("___4___"+f+"   "+e.get(i).get(f));
				f++;
			}
			i++;
		}
		System.out.println("___5___");
	}

}
