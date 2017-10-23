package beans;

/**
 * 
 * Nom de classe : Campagne
 * 
 * Description : Classe correspondant au stockage des données attribuées à la table campagne de la BDD
 *
 * Version : 1.0
 * 
 * Date : 23/10/2017
 * 
 * Copyright : Anthony Camus
 */

public class Campagne {

	private int idCampagne;
	private String label;
	private Projet projet;
	
	public int getIdCampagne() 
	{
		return idCampagne;
	}
	
	public void setIdCampagne(int idCampagne) 
	{
		this.idCampagne = idCampagne;
	}
	
	public String getLabel() 
	{
		return label;
	}
	
	public void setLabel(String label) 
	{
		this.label = label;
	}

	public Projet getProjet() 
	{
		return projet;
	}

	public void setProjet(Projet projet) 
	{
		this.projet = projet;
	}

	
	
	
}
