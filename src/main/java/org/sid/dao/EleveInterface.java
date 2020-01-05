package org.sid.dao;

import java.util.List;

import org.sid.entites.Eleve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EleveInterface extends JpaRepository<Eleve, Long> {

	// lorsque fait cet heritage, cela veut dire que à partir de la on a deja toute les methode de base(ajout, insertion, modification, recherche, suppression) qui concerne la base de donnee
	
	public Eleve findBymatricule(Long id);
	//mes methodes en supplementaires
	
	@Query("select e from Eleve e where e.nom like :x")
	Page<Eleve> chercherparmotcle(@Param("x")String motcle,Pageable pa);
	
	@Query("select e from Eleve e ,Personne p inner join e.classe where e.classe.nomClasse= :x and p.matricule=e.matricule")
	Page<Eleve> chercher(@Param("x")String classe,Pageable pa);
	
	@Query("select distinct e from Eleve e,Personne pe,Matiere m,Evaluation eva,PeriodeEvaluation p  where pe.matricule=e.matricule and e.classe.nomClasse = :X and m.libelle= :Y and  m.classe.nomClasse =e.classe.nomClasse and e.matricule not in (select eva.eleve.matricule from Evaluation eva where eva.matiere.libelle= :Y and eva.periode_eval.libelle= :Z )")
	public List<Eleve> ListeEleveClasse(@Param("X")String id_classe,@Param("Y")String libelle,@Param("Z")String sequence);
}
