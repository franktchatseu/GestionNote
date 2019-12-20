package org.sid.dao;

import org.sid.entites.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantInterface extends JpaRepository<Enseignant, Long>{
	public Enseignant findByidEnseignant(Long id);
}
