package org.sid.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class Classe implements Serializable {
	@Id
	private String nomClasse;

	private long nbre_eleve;
	
	private String prof_titulaire;
	
	@OneToMany(mappedBy="classe", fetch=FetchType.LAZY)
	private List<Eleve> liste_eleve;
	
	@OneToMany(mappedBy="classe", fetch=FetchType.LAZY)
	private List<Matiere> liste_matiere;
	
	public Classe(String nomClasse, long nb, String prof_titulaire) {
		super();
		this.nomClasse = nomClasse;
		this.nbre_eleve = nb;
		this.prof_titulaire = prof_titulaire;
	}


	public Classe(String nomClasse, long nbre_eleve) {
		super();
		this.nomClasse = nomClasse;
		this.nbre_eleve = nbre_eleve;
	}


	


	public Classe() {
		super();
	}
	public String getNom_classe() {
		return nomClasse;
	}
	public void setNom_classe(String nom_classe) {
		this.nomClasse = nom_classe;
	}
	public long getNbre_eleve() {
		return nbre_eleve;
	}
	public void setNbre_eleve(long nbre_eleve) {
		this.nbre_eleve = nbre_eleve;
	}
	public String getProf_titulaire() {
		return prof_titulaire;
	}
	public void setProf_titulaire(String prof_titulaire) {
		this.prof_titulaire = prof_titulaire;
	}
	public Classe(String nom_classe) {
		super();
		this.nomClasse = nom_classe;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public List<Eleve> getListe_eleve() {
		return liste_eleve;
	}
	public void setListe_eleve(List<Eleve> liste_eleve) {
		this.liste_eleve = liste_eleve;
	}
	public List<Matiere> getListe_matiere() {
		return liste_matiere;
	}
	public void setListe_matiere(List<Matiere> liste_matiere) {
		this.liste_matiere = liste_matiere;
	}
	
	
	//constructeur
	
	
}
