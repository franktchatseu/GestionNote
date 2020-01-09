package org.sid.entites;

import java.util.List;

public class Statistique {

	
	private float premier_classe;
	private float dernier_classe;
	private float moyenne_generale;
	private List<BilanNote> listebilan;
	private String prof_titulaire;
	private Classe classe;
	
	//nos methodes supplementaires
	
	
	
	
	public Float getPremier_classe() {
		return premier_classe;
	}
	public void setPremier_classe(Float premier_classe) {
		this.premier_classe = premier_classe;
	}
	public Float getDernier_classe() {
		return dernier_classe;
	}
	public void setDernier_classe(Float dernier_classe) {
		this.dernier_classe = dernier_classe;
	}
	public Float getMoyenne_generale() {
		return moyenne_generale;
	}
	public void setMoyenne_generale(Float moyenne_generale) {
		this.moyenne_generale = moyenne_generale;
	}

	public List<BilanNote> getListebilan() {
		return listebilan;
	}
	public void setListebilan(List<BilanNote> listebilan) {
		this.listebilan = listebilan;
	}
	public void setPremier_classe(float premier_classe) {
		this.premier_classe = premier_classe;
	}
	public void setDernier_classe(float dernier_classe) {
		this.dernier_classe = dernier_classe;
	}
	public void setMoyenne_generale(float moyenne_generale) {
		this.moyenne_generale = moyenne_generale;
	}
	public String getProf_titulaire() {
		return prof_titulaire;
	}
	public void setProf_titulaire(String prof_titulaire) {
		this.prof_titulaire = prof_titulaire;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	
}
