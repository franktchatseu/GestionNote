package org.sid.bulletin;

import java.util.List;

import org.sid.entites.PeriodeEvaluation;

public class Bulletin {

	List<TupleBulletin> listebulletin;
	List<Float> listemoyenne;
	private int rang;
	private String decision;
	private String mention;
	public String getMention() {
		return mention;
	}
	public void setMention(String mention) {
		this.mention = mention;
	}
	private int totalcoef;
	public int getTotalcoef() {
		return totalcoef;
	}
	public void setTotalcoef(int totalcoef) {
		this.totalcoef = totalcoef;
	}
	private float moyennefinale;
	private List<PeriodeEvaluation> listeperiode;
	
	public List<PeriodeEvaluation> getListeperiode() {
		return listeperiode;
	}
	public void setListeperiode(List<PeriodeEvaluation> listeperiode) {
		this.listeperiode = listeperiode;
	}
	public float getMoyennefinale() {
		return moyennefinale;
	}
	public void setMoyennefinale(float moyennefinale) {
		this.moyennefinale = moyennefinale;
	}
	public List<TupleBulletin> getListebulletin() {
		return listebulletin;
	}
	public void setListebulletin(List<TupleBulletin> listebulletin) {
		this.listebulletin = listebulletin;
	}
	public List<Float> getListemoyenne() {
		return listemoyenne;
	}
	public void setListemoyenne(List<Float> listemoyenne) {
		this.listemoyenne = listemoyenne;
	}
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	
}
