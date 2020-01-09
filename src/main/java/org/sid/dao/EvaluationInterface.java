package org.sid.dao;

import java.util.List;

import org.sid.entites.BilanNote;
import org.sid.entites.Eleve;
import org.sid.entites.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvaluationInterface extends JpaRepository<Evaluation, Long> {

	@Query("select eva from Evaluation eva join eva.eleve where eva.eleve.classe.nomClasse = :X and eva.matiere.libelle= :Y and eva.periode_eval.libelle= :Z and  eva.matiere.classe.nomClasse =eva.eleve.classe.nomClasse")
	public List<Evaluation> ListeEvaluation(@Param("X")String id_classe,@Param("Y")String libelle,@Param("Z")String sequence);
	@Query("select eva from Evaluation eva join eva.eleve join eva.matiere where eva.eleve.matricule= :X and eva.matiere.libelle= :Y order by eva.periode_eval.idPeriode asc")
	public List<Evaluation> ListeEvaluationEleve(@Param("X") long id,@Param("Y") String matiere);
	
	//RECUPERATTION DES ELEVALUATION D'UN ELEVE POUR UNE PERIDE EVALUATION
	@Query("select eva from Evaluation eva join eva.eleve join eva.periode_eval join eva.matiere where eva.eleve.matricule= :X and eva.periode_eval.libelle= :Y")
	public List<Evaluation> ListeEvaluationEleveperiode(@Param("X") long matricule,@Param("Y") String sequence);

	
	
	

}
