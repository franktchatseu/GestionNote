package org.sid.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Evaluation implements Serializable{
	
	@Id @GeneratedValue
	private Long id_evaluation; 
	private int note;

	@ManyToOne
	@JoinColumn(name="matricule")
	private Eleve eleve;
	
	@ManyToOne
	@JoinColumn(name="idMatiere")
	private Matiere matiere;
	
	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Evaluation(int note,Eleve eleve,Matiere matiere) {
		super();
		this.note = note;
		this.eleve=eleve;
		this.matiere=matiere;
	}
	
	
}
