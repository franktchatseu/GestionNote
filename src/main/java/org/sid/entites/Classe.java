package org.sid.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Classe implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_classe;
	private String nom_classe;
	private int nbre_eleve;
	private String prof_titulaire;
	
	@OneToMany(mappedBy="classe", fetch=FetchType.LAZY)
	private List<Eleve> liste_eleve;
	
	@OneToMany(mappedBy="classe", fetch=FetchType.LAZY)
	private List<Matiere> liste_matiere;
	
	public Classe(String nom_classe, int nbre_eleve, String prof_titulaire) {
		super();
		this.nom_classe = nom_classe;
		this.nbre_eleve = nbre_eleve;
		this.prof_titulaire = prof_titulaire;
	}
	public Classe() {
		super();
	}
	public String getNom_classe() {
		return nom_classe;
	}
	public void setNom_classe(String nom_classe) {
		this.nom_classe = nom_classe;
	}
	public int getNbre_eleve() {
		return nbre_eleve;
	}
	public void setNbre_eleve(int nbre_eleve) {
		this.nbre_eleve = nbre_eleve;
	}
	public String getProf_titulaire() {
		return prof_titulaire;
	}
	public void setProf_titulaire(String prof_titulaire) {
		this.prof_titulaire = prof_titulaire;
	}
	public long getId_classe() {
		return id_classe;
	}
	public void setId_classe(long id_classe) {
		this.id_classe = id_classe;
	}
	
	
	//constructeur
	
}
