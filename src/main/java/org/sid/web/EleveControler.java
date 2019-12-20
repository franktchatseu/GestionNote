package org.sid.web;

import java.util.List;

import org.sid.dao.ClasseInterface;
import org.sid.dao.EleveInterface;
import org.sid.dao.EvaluationInterface;
import org.sid.dao.MatiereInterface;
import org.sid.entites.Classe;
import org.sid.entites.Eleve;
import org.sid.entites.Evaluation;
import org.sid.entites.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.spel.ast.Elvis;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EleveControler {
	//ici on fait injection des  dependandces.
	
	@Autowired
	private EleveInterface eleveinterface;
	@Autowired
	private EvaluationInterface evaluationinterface;
	@Autowired
	private MatiereInterface matiereinterface;
	@Autowired
	private ClasseInterface classeinterface;
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
		
		eleveinterface.save(new Eleve(nom, prenom, datenais, sexe.charAt(0), nomtuteur, teltuteur,teltuteur,new Classe("6e")));
		
		return "redirect:/eleve";
	}
	
	//vue pour la gestion des notes eleves
	@RequestMapping(value="/notes",method =RequestMethod.GET)
	public String notes(Model model,@RequestParam(name="classe",defaultValue = "6e")String nomClasse,@RequestParam(name="matiere",defaultValue="francais")String libelle_matiere) {
		List<Eleve> liste_eleve=eleveinterface.ListeEleveClasse(nomClasse,"maths");
		List<Matiere> matiereClasse=matiereinterface.ListeMatiereClasse(nomClasse);
	
		
		model.addAttribute("matiereClasse", matiereClasse);
		model.addAttribute("liste_eleve",liste_eleve);
		model.addAttribute("nomclasse",nomClasse);
		model.addAttribute("libelle", libelle_matiere);
		
			return "notes.html";
	}
	//vue pour la gestion des notes eleves
		@RequestMapping(value="/ajouter_note",method =RequestMethod.POST)
		public String ajouter_note(Model model,@RequestParam(name="note") int note,@RequestParam(name="eleve")Long id,@RequestParam(name="classe")String nomclasse,@RequestParam(name="matiere")String libelle_matiere) {
		Eleve eleve=eleveinterface.findBymatricule(id);
		
			Matiere matiere=matiereinterface.ListeMatiereClasse(nomclasse, libelle_matiere);
			evaluationinterface.save(new Evaluation(note, eleve,matiere));
			model.addAttribute("note",note);
				return "redirect:/notes?classe="+nomclasse;
		}
}
