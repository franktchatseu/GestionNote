package org.sid.dao;

import java.util.List;

import org.sid.entites.PeriodeEvaluation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PeriodeInterface extends JpaRepository<PeriodeEvaluation, Long> {
			public PeriodeEvaluation findByidPeriode(Long id);
			public PeriodeEvaluation findBylibelle(String id);
			
			@Query("select p from PeriodeEvaluation p where p.libelle like %:X% and p.trimestre.libelle like %:Y%")
			public List<PeriodeEvaluation> periodeParTrimestreNom(@Param("X") String libelleP,@Param("Y") String libelleT);
			
			//liste des periodes evaluation qui sont deja ouverts
			@Query("select p from PeriodeEvaluation p where p.statut=true")
			public List<PeriodeEvaluation> periodeouvert();
			
			@Query("select distinct p from PeriodeEvaluation p inner join p.trimestre where p.trimestre.libelle= :X")
			public List<PeriodeEvaluation> listePeriodeTrimestre(@Param("X") String libelleP);
			
}
