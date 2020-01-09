package org.sid.dao;

import java.util.List;

import org.sid.entites.BilanNote;
import org.sid.entites.Classe;

public interface StatistiqueInterface {

	
	//retourne la mention 
		public String mention(float note);
	//retourne la mo=yenee generale de la classe
		public float moyenneGenerale(List<BilanNote> listebilan);
		//retourne la moyenne du premier
		public float moyennePremier(Classe classe);
		public float moyenneDernier(Classe classe);
		public List<BilanNote> getListBilan(String nomClasse, String sequence);
		public int nombreAdmis(List<BilanNote> liste);
}
