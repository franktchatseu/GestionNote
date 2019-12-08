package org.sid.entites;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;


@Entity
public class Eleve implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long matricule;
	@NotEmpty
	private String nom;
	private String prenom;
	private String date_naissance;
	@NotEmpty
	private char sexe;
	@NotEmpty
	private String nom_tuteur;
	@NotEmpty
	private String tel_tuteur;
	@NotEmpty
	private String mail_tuteur;
	
	@ManyToOne
	@JoinColumn(name="id_classe")
	private Classe classe;
	
	@OneToMany(mappedBy="eleve", fetch=FetchType.LAZY)
	private List<Evaluation> liste_evaluation;
	//constructeur d'un eleve

	
	
	
	public long getMatricule() {
		return matricule;
	}
	public Eleve(String nom, String prenom, String date_naissance, char sexe, String nom_tuteur,
			String tel_tuteur, String mail_tuteur) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.sexe = sexe;
		this.nom_tuteur = nom_tuteur;
		this.tel_tuteur = tel_tuteur;
		this.mail_tuteur = mail_tuteur;
	}
	
	public Eleve() {
		super();
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	public String getNom_tuteur() {
		return nom_tuteur;
	}
	public void setNom_tuteur(String nom_tuteur) {
		this.nom_tuteur = nom_tuteur;
	}
	public String getTel_tuteur() {
		return tel_tuteur;
	}
	public void setTel_tuteur(String tel_tuteur) {
		this.tel_tuteur = tel_tuteur;
	}
	public String getMail_tuteur() {
		return mail_tuteur;
	}
	public void setMail_tuteur(String mail_tuteur) {
		this.mail_tuteur = mail_tuteur;
	}



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



	public void setMatricule(long matricule) {
		this.matricule = matricule;
	}
	
	
	
	
}
