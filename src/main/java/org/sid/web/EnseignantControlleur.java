package org.sid.web;

import java.util.List;

import org.sid.dao.EnseignantInterface;
import org.sid.dao.RoleRepository;
import org.sid.dao.UsersRepository;
import org.sid.dao.UsersRolesRepository;
import org.sid.entites.Enseignant;
import org.sid.entites.Roles;
import org.sid.entites.Users;
import org.sid.entites.UsersRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnseignantControlleur {
	/* -----------------------------------------------------------------*
	 * -----------------------------------------------------------------*
	 * -----------------------------------------------------------------* 
	 * -----------------------------------------------------------------* 
	 * -----------------------------------------------------------------* 
	 * */		
			
			
			//vu de la gestion des enseignants

			
			@Autowired
			private EnseignantInterface enseignantType;
			@Autowired
			private UsersRolesRepository usersrole;
			@Autowired
			private RoleRepository roleinterface;
			private UsersRepository usersinterface;
			
			//methodes de renvoie des enseignants
			@RequestMapping(value = "/enseignant")
			public String ListEnseignant(Model model,@RequestParam(name="page", defaultValue = "0") int pag,
					@RequestParam(name="taille", defaultValue = "15") int taille,
					@RequestParam(name="rech", defaultValue = "") String motClef) {
				
				
				//ici, nous allons geree la pagination
				List<Enseignant> list=enseignantType.findAll();
				Page<Enseignant> page=enseignantType.recherche(motClef, PageRequest.of(pag, taille));
				int pagesTableau []= new int[page.getTotalPages()];
				
				//ajout des elements dans notre model
				model.addAttribute("enspagetotal",pagesTableau);
				model.addAttribute("enspagecourante",pag);
				model.addAttribute("ensmotcle",motClef);
				model.addAttribute("ListeEnseignant",page.getContent());
				return "enseignant";
			}
			
			
			
			
			//methode d'ajout des enseignants
			@RequestMapping(value="/ajouter",method = RequestMethod.GET)
			public String saveEnseignant(
					@RequestParam(name="username")String username,
					@RequestParam(name="password") String password,
					@RequestParam(name="nom")String nom,
					@RequestParam(name="prenom") String prenom,
					@RequestParam(name="fonction") String fonction,
					@RequestParam(name="cni") String cni,@RequestParam(name="niveau") String niveau
					) {
				//insertion de enseignants
				Enseignant e = new Enseignant(username,password,nom, prenom, cni, fonction, niveau);
				enseignantType.save(e);
				//attribution du role USER a cette enseignant
				usersrole.save(new UsersRoles(e, new Roles("ADMIN","NA PAS TOUT LES DROITS")));
				//message pour la notification d'enregistrement
				return "redirect:/enseignant";
			}
			
			//methode de modification d'un enseignant
			
			@RequestMapping(value="/update",method = RequestMethod.POST)
			public String update(
					@RequestParam(name="nom")String nom,
					@RequestParam(name="prenom") String prenom,
					@RequestParam(name="fonction") String fonction,
					@RequestParam(name="cni") String cni,
					@RequestParam(name="niveau") String niveau,
					@RequestParam(name="id") long id) {
				Enseignant e=new Enseignant(id, nom, prenom, cni, fonction, niveau);
				return "enseignant";
			}
//			
			
			@RequestMapping(value="/deleteEnseignant",method = RequestMethod.GET)
			public String delete(@RequestParam(name="id") String id) {
				enseignantType.deleteById(id);
				return "redirect:/enseignant";
			}
}
