package org.sid.web;

import java.util.List;

import org.sid.dao.EleveInterface;
import org.sid.entites.Eleve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.spel.ast.Elvis;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EleveControler {
	//ici on fait injection des  dependandces.
	
	@Autowired
	private EleveInterface eleveinterface;
	
	//ce controleur doit renvoyer une page
	//et on precise url de la page qui sera charge
	
	
	@RequestMapping(value = "/eleve")
	public String PageEleve(Model model,@RequestParam(name="page", defaultValue = "0") int p,@RequestParam(name="size", defaultValue = "4") int size,@RequestParam(name="motcle",defaultValue = "")String mc) {
		
		
		//Page<Eleve> pageeleve=eleveinterface.findAll(PageRequest.of(p, size));
		Page<Eleve> pageeleve=eleveinterface.chercher("%"+mc+"%",PageRequest.of(p, size));
		//le tableau de toute nos page
		int [] pages=new int[pageeleve.getTotalPages()];
		//on ajoute au model 
		model.addAttribute("motcle", mc);
		model.addAttribute("pages", pages);
		model.addAttribute("listEleve",pageeleve.getContent());
		model.addAttribute("pageCourante", p);
		return "eleves";
	}
	//vue de suppression
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(long id,String motcle,int page) {
		eleveinterface.deleteById(id);
		return "redirect:/eleve?page="+page+"&motcle="+motcle;
	}
	//vue ajout
	@RequestMapping(value ="/ajout",method =RequestMethod.POST )
	public String ajouter(@RequestParam(name="nom")String nom,@RequestParam(name="prenom")String prenom,@RequestParam(name="nomtuteur")String nomtuteur,@RequestParam(name="teltuteur")String teltuteur,@RequestParam(name="sexe")String sexe,@RequestParam(name="datenais")String datenais) {
		
		eleveinterface.save(new Eleve(nom, prenom, datenais, sexe.charAt(0), nomtuteur, teltuteur,teltuteur));
		
		return "redirect:/eleve";
	}
}
