package org.sid.dao;

import java.util.List;

import org.sid.entites.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatiereInterface extends JpaRepository<Matiere,Long> {
	Matiere findByidMatiere(Long id);
	
	//liste des matieres d'une classe
	@Query("select m from Matiere m where m.classe.nomClasse = :X")
	public List<Matiere> ListeMatiereClasse(@Param("X")String id_classe);
	
	@Query("select m from Matiere m where m.classe.nomClasse = :X and m.libelle= :Y")
	public Matiere ListeMatiereClasse(@Param("X")String id_classe,@Param("Y")String libelle);
}