package org.sid.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Enseignant {
	@Id @GeneratedValue	
	private Long id_enseignant;
	@NotEmpty
	private String nom;
	private String prenom;
	private String cni;
	private String fonction;
	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Enseignant(Long id_enseignant, @NotEmpty String nom, String prenom, String cni, String fonction) {
		super();
		this.id_enseignant = id_enseignant;
		this.nom = nom;
		this.prenom = prenom;
		this.cni = cni;
		this.fonction = fonction;
	}
	
	
}
