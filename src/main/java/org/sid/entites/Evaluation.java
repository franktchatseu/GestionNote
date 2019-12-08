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
	@JoinColumn(name="id_eleve")
	private Eleve eleve;
	
	@ManyToOne
	@JoinColumn(name="id_matiere")
	private Matiere matiere;
	
	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Evaluation(int note) {
		super();
		this.note = note;
	}
	
	
}
