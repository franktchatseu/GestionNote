package org.sid.dao;



import java.util.List;

import org.sid.entites.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClasseInterface extends JpaRepository<Classe, String>{
	@Query("select c from Classe c where c.nomClasse like %:X%")
	public Page<Classe> findBynomClasse(@Param("X") String id, Pageable page);
	@Query("select c from Classe c where c.nomClasse = :X")
	public Classe findBynomClassee(@Param("X") String id);
	
	//liste de toute les classes d'un enseignant
	@Query("select distinct c from Matiere m join m.enseignant ens join m.classe c where  ens.username= :X")
	public List<Classe> listeclasseenseignant(@Param("X") String login);
}
