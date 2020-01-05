package org.sid.dao;

import java.util.List;

import org.sid.entites.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterfaceProduit extends JpaRepository<Produit, Long> {
	//autres methodes de la classe produit
	@Query("select e from Produit e where e.nom_prod like :x")
	List<Produit> ma_recherche(@Param("x")String mot);
	
	List<Produit> findByprix(float prix);
	
}
