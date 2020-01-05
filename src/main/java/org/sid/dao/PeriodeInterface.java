package org.sid.dao;

import org.sid.entites.PeriodeEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodeInterface extends JpaRepository<PeriodeEvaluation, Long> {
			public PeriodeEvaluation findByidPeriode(Long id);
			public PeriodeEvaluation findBylibelle(String id);
}
