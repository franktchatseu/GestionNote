package org.sid.entites;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Trimestre {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idTrimestre;
	private String libelle;
	
	@OneToMany(mappedBy="trimestre", fetch=FetchType.LAZY)
	private List<PeriodeEvaluation> list_periode;
	
	public Trimestre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Trimestre(String libelle) {
		super();
		this.libelle = libelle;
	}


	public long getId_trimestre() {
		return idTrimestre;
	}
	public void setId_trimestre(long id_trimestre) {
		this.idTrimestre = id_trimestre;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}
