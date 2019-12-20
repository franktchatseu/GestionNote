package org.sid.dao;

import org.sid.entites.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseInterface extends JpaRepository<Classe, String>{

	public Classe findBynomClasse(String id);
}
