package org.sid.dao;

import org.sid.entites.Trimestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrimestreInterface extends JpaRepository<Trimestre, Long>{
		public Trimestre findByidTrimestre(Long id);
}
