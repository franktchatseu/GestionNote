package org.sid.entites;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;

@Entity
@PrimaryKeyJoinColumn(name="username")
public class Enseignant extends Users{

	
	@NotEmpty
	private String nom;
	
	private String prenom;
	@NotEmpty
	private String cni;
	@NotEmpty
	private String fonction;
	
	private String niveau;
	
	@OneToMany(mappedBy="enseignant", fetch=FetchType.LAZY)
	private List<Matiere> listeMatiere;
	
	
	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Enseignant(@NotEmpty String nom, String prenom, @NotEmpty String cni, @NotEmpty String fonction,
			String niveau) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cni = cni;
		this.fonction = fonction;
		this.niveau = niveau;
	}

	
	

	

	public Enseignant(String username, String password, @NotEmpty String nom, String prenom,
			@NotEmpty String cni, @NotEmpty String fonction, String niveau) {
		super(username, password);
		this.nom = nom;
		this.prenom = prenom;
		this.cni = cni;
		this.fonction = fonction;
		this.niveau = niveau;
		this.listeMatiere = listeMatiere;
	}




	public Enseignant(Long idEnseignant, @NotEmpty String nom, String prenom, @NotEmpty String cni,
			@NotEmpty String fonction, String niveau) {
		super();
		
		this.nom = nom;
		this.prenom = prenom;
		this.cni = cni;
		this.fonction = fonction;
		this.niveau = niveau;
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
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public List<Matiere> getListeMatiere() {
		return listeMatiere;
	}
	public void setListeMatiere(List<Matiere> listeMatiere) {
		this.listeMatiere = listeMatiere;
	}
	public String getCni() {
		return cni;
	}
	
	
}
