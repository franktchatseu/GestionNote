package org.sid.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Users implements Serializable{
	@Id
	private String username;
	private String password;
	
	@OneToMany(mappedBy="users", fetch=FetchType.LAZY)
	private Collection<UsersRoles> usersRoles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users(String username, String password, Collection<Roles> roles) {
		super();
		this.username = username;
		this.password = password;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

}
