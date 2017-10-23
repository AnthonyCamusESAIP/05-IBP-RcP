package beans;

import java.sql.Date;

/**
 * 
 * Nom de classe : Test
 * 
 * Description : Classe correspondant au stockage des données attribuées à la table test de la BDD
 *
 * Version : 1.0
 * 
 * Date : 23/10/2017
 * 
 * Copyright : Anthony Camus
 */
public class Test {

	private int idTest;
	private Date date;
	private String heure;
	private String statut;
	private Projet projet;
	private Campagne campagne;
	private Testeur testeur;
	
	public int getIdTest() 
	{
		return idTest;
	}
	public void setIdTest(int idTest) 
	{
		this.idTest = idTest;
	}
	public Date getDate() 
	{
		return date;
	}
	public void setDate(Date date) 
	{
		this.date = date;
	}
	public String getHeure() 
	{
		return heure;
	}
	public void setHeure(String heure) 
	{
		this.heure = heure;
	}
	public String getStatut() 
	{
		return statut;
	}
	public void setStatut(String statut) 
	{
		this.statut = statut;
	}
	public Projet getProjet() 
	{
		return projet;
	}
	public void setProjet(Projet projet) 
	{
		this.projet = projet;
	}
	public Campagne getCampagne() 
	{
		return campagne;
	}
	public void setCampagne(Campagne campagne) 
	{
		this.campagne = campagne;
	}
	public Testeur getTesteur() 
	{
		return testeur;
	}
	public void setTesteur(Testeur testeur) 
	{
		this.testeur = testeur;
	}

	
	
}
