package org.sid.dao;



import org.sid.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long>{
	public Users findByUsername(String username);
	
}
