package org.sid.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class GestionNoteSecurity extends WebSecurityConfigurerAdapter {


	
	@Autowired
	public void globalConfiguration(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{
		
		auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");
		/*auth.inMemoryAuthentication().withUser("res").password("{noop}123").roles("res");
		auth.inMemoryAuthentication().withUser("user").password("{noop}123").roles("user");
		*/
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.usersByUsernameQuery("select username as principal, password as credentials, true from users where username =?")
		.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username= ?")
		.rolePrefix("ROLE_");
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.usersByUsernameQuery("select email as principal,mot_De_Passe as credentials, true from individu where email =?")
			.authoritiesByUsernameQuery("select individu_email as principal, roles_role as role from utilisateur_roles where individu_email= ?")
			.rolePrefix("ROLE_");
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(	"/eleve","/delete","/css/**","/visuel","/detailsnote","/save","/image/**").permitAll()
				.antMatchers("/visuel").hasRole("ADMIN")
			.anyRequest()
				.authenticated()
					.and();
//		http
//		.authorizeRequests()
//			.antMatchers("/visuel").hasRole("ADMIN")
//			.anyRequest()
//			.authenticated()
//				.and();
		http.formLogin()
		 .loginPage("/login")
		 	.permitAll()
		 		.and()
		 .logout()
		 	.permitAll();
		http.exceptionHandling().accessDeniedPage("/403");
		
	}
}
