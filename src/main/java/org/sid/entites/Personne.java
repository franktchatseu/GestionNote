package org.sid.entites;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;

public class Personne implements Serializable {
	
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
	
}
