package org.sid.entites;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long matricule;
	public long getMatricule() {
		return matricule;
	}
	public void setMatricule(long matricule) {
		this.matricule = matricule;
	}
	protected String nom;
	protected String prenom;
	protected String date_naissance;
	protected char sexe;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	public char getSexe() {
		return sexe;
	}
	public void setSexe(char sexe) {
		this.sexe = sexe;
	}
	//constructeur
	public Personne(String nom, String prenom, String date_naissance, char sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.sexe = sexe;
	}
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
