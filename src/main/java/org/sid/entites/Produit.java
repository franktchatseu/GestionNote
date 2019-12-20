package org.sid.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produit {
		@Id @GeneratedValue
		private long id_prod;
		private String nom_prod;
		private float prix;
		private int quantite;
		public Produit(String nom_prod, float prix, int quantite) {
			super();
			this.nom_prod = nom_prod;
			this.prix = prix;
			this.quantite = quantite;
		}
		public long getId_prod() {
			return id_prod;
		}
		public void setId_prod(long id_prod) {
			this.id_prod = id_prod;
		}
		public String getNom_prod() {
			return nom_prod;
		}
		public void setNom_prod(String nom_prod) {
			this.nom_prod = nom_prod;
		}
		public float getPrix() {
			return prix;
		}
		public void setPrix(float prix) {
			this.prix = prix;
		}
		public int getQuantite() {
			return quantite;
		}
		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}
		public Produit() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
