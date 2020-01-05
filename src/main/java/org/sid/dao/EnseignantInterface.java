package org.sid.dao;


import java.util.List;

import org.sid.entites.Enseignant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnseignantInterface extends JpaRepository<Enseignant, Long>{
	@Query("select e from Enseignant e where e.nom like %:X%")
	public Page<Enseignant> recherche(@Param("X") String id, Pageable page);
	
}
