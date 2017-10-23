package beans;

/**
 * 
 * Nom de classe : Testeur
 * 
 * Description : Classe correspondant au stockage des données attribuées à la table testeur de la BDD
 *
 * Version : 1.0
 * 
 * Date : 23/10/2017
 * 
 * Copyright : Anthony Camus
 */

public class Testeur {

	private int idTesteur;
	private String nomTesteur;
	
	public int getIdTesteur() 
	{
		return idTesteur;
	}
	public void setIdTesteur(int idTesteur) 
	{
		this.idTesteur = idTesteur;
	}
	public String getNomTesteur() 
	{
		return nomTesteur;
	}
	public void setNomTesteur(String nomTesteur) 
	{
		this.nomTesteur = nomTesteur;
	}
	
	
}
