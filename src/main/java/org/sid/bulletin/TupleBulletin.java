package org.sid.bulletin;

import java.util.List;

import org.sid.entites.Evaluation;

public class TupleBulletin {
	// les differents attributs de cette classe
		private String matiere;
		private List<Evaluation> listeevaluation;
		private float note_final;
		private int coef;
		private String mention;
		private String prof_matiere;
		public String getMatiere() {
			return matiere;
		}
		public void setMatiere(String matiere) {
			this.matiere = matiere;
		}
		public List<Evaluation> getListeevaluation() {
			return listeevaluation;
		}
		public void setListeevaluation(List<Evaluation> listeevaluation) {
			this.listeevaluation = listeevaluation;
		}
		public float getNote_final() {
			return note_final;
		}
		public void setNote_final(float note_final) {
			this.note_final = note_final;
		}
		public int getCoef() {
			return coef;
		}
		public void setCoef(int coef) {
			this.coef = coef;
		}
		public String getMention() {
			return mention;
		}
		public void setMention(String mention) {
			this.mention = mention;
		}
		public String getProf_matiere() {
			return prof_matiere;
		}
		public void setProf_matiere(String prof_matiere) {
			this.prof_matiere = prof_matiere;
		}
		
		
}
