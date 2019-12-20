package org.sid.entites;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Enseignant {
	@Id @GeneratedValue	
	private Long idEnseignant;
	@NotEmpty
	private String nom;
	private String prenom;
	private String cni;
	private String fonction;
	
	@OneToMany(mappedBy="enseignant", fetch=FetchType.LAZY)
	private List<Matiere> listeMatiere;
	
	
	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Enseignant(String nom, String prenom, String cni, String fonction) {
		super();
		
		this.nom = nom;
		this.prenom = prenom;
		this.cni = cni;
		this.fonction = fonction;
	}
	
	
}
