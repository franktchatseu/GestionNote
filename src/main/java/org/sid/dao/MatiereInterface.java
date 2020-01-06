package org.sid.dao;

import java.util.List;

import org.sid.entites.Matiere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatiereInterface extends JpaRepository<Matiere,Long> {
	Matiere findByidMatiere(Long id);
	//methodes ecritent par frank
	@Query("select distinct m from Matiere m , Eleve e where m.classe.nomClasse=e.classe.nomClasse and e.matricule= :X")
	public List<Matiere> ListeMatiereEleve(@Param("X") long id);
	
	@Query("select m from Matiere m inner join m.enseignant where m.enseignant.username = :X ")
	public List<Matiere> ListeMatiereEnseignant(@Param("X")String username);
	
	//par ramses
	//liste des matieres d'une classe
	@Query("select m from Matiere m where m.classe.nomClasse = :X")
	public List<Matiere> ListeMatiereClasse(@Param("X")String id_classe);
	
	@Query("select m from Matiere m where m.classe.nomClasse = :X")
	public Page<Matiere> ListeMatiereClasse(@Param("X")String id_classe, Pageable p);
	
	@Query("select m from Matiere m where m.classe.nomClasse like %:X% and m.libelle like %:Y%")
	public Matiere ListeMatiereClasse(@Param("X")String id_classe,@Param("Y")String libelle);
	
	
	@Query("select m from Matiere m where m.classe.nomClasse like %:X% and m.libelle like %:Y%")
	public Page<Matiere> ListeMatiereClasse(@Param("X")String id_classe,@Param("Y")String libelle, Pageable p);
}
