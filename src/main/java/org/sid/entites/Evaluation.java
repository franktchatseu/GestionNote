package org.sid.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Evaluation implements Serializable{
	
	@Id @GeneratedValue
	private long id_evaluation; 
	
	private int note;

	@ManyToOne
	@JoinColumn(name="matricule")
	public Eleve eleve;
	
	@ManyToOne
	@JoinColumn(name="idMatiere")
	private Matiere matiere;
	
	@ManyToOne
	@JoinColumn(name="idPeriode")
	private PeriodeEvaluation periode_eval;
	//refus de faire la persistance de cette attribut dans la base de donnee
	@Transient
	private List<Evaluation> liste_evaluation=new ArrayList<Evaluation>();
	
	public void addEvaluation(Evaluation eva) {
		this.liste_evaluation.add(eva);
	}
	
	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evaluation(int note,Eleve eleve,Matiere matiere) {
		super();
		this.note = note;
		this.eleve=eleve;
		this.matiere=matiere;
	}

	public Evaluation(int note, Eleve eleve, Matiere matiere, PeriodeEvaluation periode_eval) {
		super();
		this.note = note;
		this.eleve = eleve;
		this.matiere = matiere;
		this.periode_eval = periode_eval;
	}

	public List<Evaluation> getListe_evaluation() {
		return liste_evaluation;
	}

	public void setListe_evaluation(List<Evaluation> liste_evaluation) {
		this.liste_evaluation = liste_evaluation;
	}

	public long getId_evaluation() {
		return id_evaluation;
	}

	public void setId_evaluation(long id_evaluation) {
		this.id_evaluation = id_evaluation;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public PeriodeEvaluation getPeriode_eval() {
		return periode_eval;
	}

	public void setPeriode_eval(PeriodeEvaluation periode_eval) {
		this.periode_eval = periode_eval;
	}
	
	
	
	
}
