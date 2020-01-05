package org.sid.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_roles")
public class UsersRoles implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	@ManyToOne
	@JoinColumn(name="username")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name="role")
	private Roles roles;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public UsersRoles(Users users, Roles roles) {
		super();
		this.users = users;
		this.roles = roles;
	}

	public UsersRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

}
