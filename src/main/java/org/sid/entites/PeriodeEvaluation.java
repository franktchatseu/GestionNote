package org.sid.entites;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PeriodeEvaluation {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPeriode;
	private String libelle;
	private Date Date_debut;
	private Date date_fin;
	//permet de savoir si la periode est ferme ou pas
	private boolean statut;
	
	@ManyToOne
	@JoinColumn(name="idTrimestre")
	private Trimestre trimestre;
	
	
	public PeriodeEvaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PeriodeEvaluation(String libelle, Date date_debut, Date date_fin,Boolean statut, Trimestre trimestre) {
		super();
		this.libelle = libelle;
		Date_debut = date_debut;
		this.date_fin = date_fin;
		this.statut=statut;
		this.trimestre = trimestre;
	}



	public long getId_periode() {
		return idPeriode;
	}
	public void setId_periode(long id_periode) {
		this.idPeriode = id_periode;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getDate_debut() {
		return Date_debut;
	}
	public void setDate_debut(Date date_debut) {
		Date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}



	public boolean isStatut() {
		return statut;
	}



	public void setStatut(boolean statut) {
		this.statut = statut;
	}



	public Trimestre getTrimestre() {
		return trimestre;
	}



	public void setTrimestre(Trimestre trimestre) {
		this.trimestre = trimestre;
	}
	
}
