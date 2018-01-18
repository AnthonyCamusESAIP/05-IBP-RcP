package beans;

public class Projet {
	
	private String Nom;
	private int Nombre;
	
	public Projet(String x , int y)
	{
		this.Nom = x;
		this.Nombre= y;
	} 

	public String getNom() {
		return Nom;
	}

	public void setNom(String x) {
		this.Nom = x;
	}

	public int getNbr() {
		return Nombre;
	}

	public void setNombre(int y) {
		this.Nombre = y;
	}

}
