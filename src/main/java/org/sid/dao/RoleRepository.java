package org.sid.dao;


import org.sid.entites.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, String> {
	public Roles findByRole(String role);
}
