package org.sid.dao;

import java.util.List;

import org.sid.entites.Eleve;
import org.sid.entites.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvaluationInterface extends JpaRepository<Evaluation, Long> {

	@Query("select eva from Evaluation eva join eva.eleve where eva.eleve.classe.nomClasse = :X and eva.matiere.libelle= :Y and eva.periode_eval.libelle= :Z and  eva.matiere.classe.nomClasse =eva.eleve.classe.nomClasse")
	public List<Evaluation> ListeEvaluation(@Param("X")String id_classe,@Param("Y")String libelle,@Param("Z")String sequence);
}
