package org.sid.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Matiere implements Serializable {
	@Id @GeneratedValue
	private long id_matiere;
	private String libelle;
	private int coef;
	
	@OneToMany(mappedBy="matiere", fetch=FetchType.LAZY)
	private List<Evaluation> le;
	
	@ManyToOne
	@JoinColumn(name="id_classe")
	private Classe classe;
	
	@ManyToOne
	@JoinColumn(name="id_enseignant")
	private Enseignant enseignant;
	
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	public Matiere(String libelle, int coef) {
		super();
		this.libelle = libelle;
		this.coef = coef;
	}
	
}