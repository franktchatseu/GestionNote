package org.sid.dao;

import org.sid.entites.Eleve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EleveInterface extends JpaRepository<Eleve, Long> {

	// lorsque fait cet heritage, cela veut dire que à partir de la on a deja toute les methode de base(ajout, insertion, modification, recherche, suppression) qui concerne la base de donnee
	//mes methodes en supplementaires
	@Query("select e from Eleve e where e.nom like :x")
	Page<Eleve> chercher(@Param("x")String motcle,Pageable pa);
}
