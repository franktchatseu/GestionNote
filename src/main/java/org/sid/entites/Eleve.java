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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;


@Entity
@PrimaryKeyJoinColumn(name="matricule")
public class Eleve extends Personne implements Serializable {
	
	
	@NotEmpty
	private String nom_tuteur;
	@NotEmpty
	private String tel_tuteur;
	@NotEmpty
	private String mail_tuteur;
	
	@ManyToOne
	@JoinColumn(name="nomClasse")
	private Classe classe;
	
	@OneToMany(mappedBy="eleve", fetch=FetchType.LAZY)
	private List<Evaluation> liste_evaluation;
	//constructeur d'un eleve

	
	
	
	
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public List<Evaluation> getListe_evaluation() {
		return liste_evaluation;
	}
	public void setListe_evaluation(List<Evaluation> liste_evaluation) {
		this.liste_evaluation = liste_evaluation;
	}
	public Eleve(String nom, String prenom, String date_naissance, char sexe, String nom_tuteur,
			String tel_tuteur, String mail_tuteur,Classe classe) {
		super(nom, prenom, date_naissance, sexe);
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.sexe = sexe;
		this.nom_tuteur = nom_tuteur;
		this.tel_tuteur = tel_tuteur;
		this.mail_tuteur = mail_tuteur;
		this.classe=classe;
		
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
	public Eleve(String nom, String prenom, String date_naissance, char sexe) {
		super(nom, prenom, date_naissance, sexe);
		// TODO Auto-generated constructor stub
	}
	public Eleve() {
		super();
		// TODO Auto-generated constructor stub
	}




	
	
	
	
}
