package org.sid.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.sid.dao.ClasseInterface;
import org.sid.dao.EleveInterface;
import org.sid.dao.EnseignantInterface;
import org.sid.dao.EvaluationInterface;
import org.sid.dao.MatiereInterface;
import org.sid.dao.PeriodeInterface;
import org.sid.entites.Classe;
import org.sid.entites.Eleve;
import org.sid.entites.Enseignant;
import org.sid.entites.Evaluation;
import org.sid.entites.Matiere;
import org.sid.entites.PeriodeEvaluation;
import org.sid.entites.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.spel.ast.Elvis;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@Autowired
	private PeriodeInterface periodeinterface;
	@Autowired
	private EnseignantInterface enseignantinterface;
	//ce controleur doit renvoyer une page
	//et on precise url de la page qui sera charge
	
	//partie du controleur sur la gestion des eleves
	
	@RequestMapping(value = "/pageeleve")
	public String PageEleve1(Model model,@RequestParam(name="page", defaultValue = "0") int p,@RequestParam(name="size", defaultValue = "4") int size,@RequestParam(name="motcle",defaultValue = "")String mc) {
		
		
		//Page<Eleve> pageeleve=eleveinterface.findAll(PageRequest.of(p, size));
		Page<Eleve> pageeleve=eleveinterface.chercherparmotcle("%"+mc+"%",PageRequest.of(p, size));
		//le tableau de toute nos page
		int [] pages=new int[pageeleve.getTotalPages()];
		//on ajoute au model 
		
		model.addAttribute("motcle", mc);
		model.addAttribute("pages", pages);
		model.addAttribute("listEleve",pageeleve.getContent());
		model.addAttribute("pageCourante", p);
		
		//on recupere toute les classes de la base de donnee
				List<Classe> list=classeinterface.findAll();
				model.addAttribute("list_classe",list);
		return "eleves1";
	}
	//vue de suppression
	@RequestMapping(value="/deleteeleve", method = RequestMethod.GET)
	public String deleteeleve(long id,String motcle,int page) {
		eleveinterface.deleteById(id);
		return "redirect:/pageeleve?page="+page+"&motcle="+motcle;
	}
	
	//vue ajout
	@RequestMapping(value ="/ajouteleve",method =RequestMethod.POST )
	public String ajoutereleve(@RequestParam(name="nom")String nom,@RequestParam(name="prenom")String prenom,@RequestParam(name="nomtuteur")String nomtuteur,@RequestParam(name="teltuteur")String teltuteur,@RequestParam(name="sexe")String sexe,@RequestParam(name="datenais")String datenais,@RequestParam(name="classe")String classe) {
		
		eleveinterface.save(new Eleve(nom, prenom, datenais, sexe.charAt(0), nomtuteur, teltuteur,teltuteur,new Classe(classe)));
		
		return "redirect:/pageeleve";
	}
	
	//partie du controleur pour la gestion notes
	@RequestMapping(value = "/eleve")
	public String PageEleve(Model model,@RequestParam(name="page", defaultValue = "0") int p,@RequestParam(name="size", defaultValue = "4") int size,@RequestParam(name="classe",defaultValue = "6e")String classe) {
		
		
		//Page<Eleve> pageeleve=eleveinterface.findAll(PageRequest.of(p, size));
		Page<Eleve> pageeleve=eleveinterface.chercher(classe,PageRequest.of(p, size));
		//le tableau de toute nos page
		int [] pages=new int[pageeleve.getTotalPages()];
		//on ajoute au model 
		
		model.addAttribute("classe",classe);
		model.addAttribute("pages", pages);
		model.addAttribute("listEleve",pageeleve.getContent());
		model.addAttribute("pageCourante", p);
		//on recupere toute les classes de la base de donnee
		List<Classe> list=classeinterface.findAll();
		model.addAttribute("list_classe",list);
		
		return "visualisationnote";
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
		
	
		
		return "redirect:/eleve";
	}
	
	//vue pour la gestion des notes eleves
	@RequestMapping(value="/notes",method =RequestMethod.GET)
	public String notes(Model model) {
		

		//recuperation de la session authentification d'un utilisateur
		SecurityContext ctx = SecurityContextHolder.getContext();				
        UserDetails users= (UserDetails )ctx.getAuthentication().getPrincipal();
      //on recupere la liste des classes de cette enseignant
        List<Classe> listeclasse=classeinterface.listeclasseenseignant(users.getUsername());
        model.addAttribute("listeclasse", listeclasse);
        //ici on recupere la liste des matieres par defaud pour la premiere classe
        //liste des matieres de la classe pour cette enseignant
		List<Matiere> listematiere=matiereinterface.ListeMatiereClasseEnseignant(listeclasse.get(0).getNom_classe(), users.getUsername());
		
		 model.addAttribute("listematieredefaud", listematiere);
        Enseignant enseignant=enseignantinterface.findbyusername(users.getUsername());
		
		List<Matiere> matiereenseignant=matiereinterface.ListeMatiereEnseignant(users.getUsername());
	
		model.addAttribute("enseignant",enseignant);
		model.addAttribute("matiereClasse", matiereenseignant);
		
	
		
			return "acceuil";
	}
	//vue pour la gestion des notes eleves
		@RequestMapping(value="/login")
		public String login(Model model,String username,String password) {
				
			
				return "login";
		}
		@RequestMapping(value="/403")
		public String accesdenied() {
		
				return "accessdenied.html";
		}
		@RequestMapping(value="/")
		public String index(Model model) {
			
			//recuperation de la session authentification d'un utilisateur
			SecurityContext ctx = SecurityContextHolder.getContext();				
            UserDetails d= (UserDetails )ctx.getAuthentication().getPrincipal();
            
            
				return "AcceuilAdmin";
		}
		
		//etape 1 deja ok!
		@RequestMapping(value="/visuel",method =RequestMethod.POST)
		public String visuel(Model model,@RequestParam(name="classe")String nomclasse,@RequestParam(name="matiere")String libelle_matiere,@RequestParam(name="sequence")String sequence,@RequestParam(name="eleve",defaultValue = "0")int matricule,@RequestParam(name="aff") String action) {
			model.addAttribute("nomclasse",nomclasse);
			model.addAttribute("libelle", libelle_matiere);
			if(action.equals("ajouter")) {
					List<Eleve> liste_eleve=eleveinterface.ListeEleveClasse(nomclasse,libelle_matiere,sequence);
					Evaluation evaluation=new Evaluation();
					for(int i=0;i<liste_eleve.size();i++) {
						Evaluation eval=new Evaluation();
						//recuperation de id de eleve
						eval.setEleve(liste_eleve.get(i));
						//recuperation de la matiere
						eval.setMatiere(matiereinterface.ListeMatiereClasse(nomclasse, libelle_matiere));
						//recuperation de la periode evaluation
						eval.setPeriode_eval(periodeinterface.findBylibelle(sequence));
						evaluation.addEvaluation(eval);
					}
			
				model.addAttribute("form",evaluation);
				model.addAttribute("liste_evaluation",evaluation.getListe_evaluation());
				model.addAttribute("liste_eleve",liste_eleve);
				return "notes.html";
			}
			else {
				Evaluation evaluation=new Evaluation();
				evaluation.setListe_evaluation(evaluationinterface.ListeEvaluation(nomclasse, libelle_matiere,sequence));
				//List<Evaluation> liste_evaluation=evaluationinterface.ListeEvaluation(nomclasse, libelle_matiere,sequence);
				//model.addAttribute("liste_evaluation",liste_evaluation);
				model.addAttribute("form",evaluation);
				model.addAttribute("liste_evaluation",evaluation.getListe_evaluation());
				return "updatenote";
			}
			
			
			
				
		}
		@PostMapping("/save")
		public String sauvegarde(@ModelAttribute Evaluation form,Model model) {
			
			evaluationinterface.saveAll(form.getListe_evaluation());
//			for(Evaluation ev:form.getListe_evaluation()) {
//				System.out.println(ev.getNote());
//				//System.out.println(ev.eleve.getMatricule());
//				System.out.println(ev.getEleve().getNom());
//				System.out.println(ev.getMatiere().getLibelle());
//				System.out.println(ev.getPeriode_eval().getLibelle());
//				
//			}
		
			return "redirect:/notes";
			
		}
		@RequestMapping(value ="/updatenote")
		public String updatenoteupdatenote(Model model,@RequestParam(name="idevaluation") long id,@RequestParam(name="note") int note) {
			System.out.println(id);
			System.out.println(note);
			if(evaluationinterface.existsById(id)) {
				Evaluation evaluation=evaluationinterface.getOne(id);
				evaluation.setNote(note);
				evaluationinterface.save(evaluation);

			}
			return "redirect:/notes";
		}
		@RequestMapping(value ="/detailsnote")
		public String detailsnote(Model model,@RequestParam(name="matricule") long matricule) {
			
			List<Matiere> listematiere=matiereinterface.ListeMatiereEleve(matricule);
			List<List<Evaluation>> listedeliste=new ArrayList<List<Evaluation>>();
			for(Matiere m: listematiere) {
			//on recupere toute les notes de eleves suivant une matiere donnee
			List<Evaluation> listeevaluation=evaluationinterface.ListeEvaluationEleve(matricule,m.getLibelle());
			listedeliste.add(listeevaluation);
			}
			model.addAttribute("listematiere",listematiere);
			model.addAttribute("listedeliste",listedeliste);
			
			//on recupere eleve
			Eleve eleve=eleveinterface.findBymatricule(matricule);
//			model.addAttribute("matricule", matricule);
//			model.addAttribute("nom", nom);
//			model.addAttribute("prenom", prenom);
//			model.addAttribute("nom_tuteur", nom_tuteur);
//			model.addAttribute("classe", classe);
			model.addAttribute("eleve",eleve);
			
			
			return "detailnote";
		}
		
		//CONTROLEUR POUR AFFICHAGE DES NOTES D'UN ELEVE DEJA ENREGISTRES
		@RequestMapping(value ="/infonote")
		public String infonote(Model model,@RequestParam(name="matricule") long matricule,@RequestParam(name="sequence",defaultValue = "")String sequence) {
			//liste des evaluations de cette eleve
			List<Evaluation> listeevaluation=evaluationinterface.ListeEvaluationEleveperiode(matricule,sequence);
			
			model.addAttribute("listeevaluation",listeevaluation);
			
			//on recupere eleve
			Eleve eleve=eleveinterface.findBymatricule(matricule);
//			
			model.addAttribute("sequence",sequence);
			model.addAttribute("eleve",eleve);
			
			
			
			return "infonote.html";
		}
		
		
		
		@RequestMapping(value="/matiereclasse")
		@ResponseBody
		public String[] listematiere(Model model,String classe) {
			

			//recuperation de la session authentification d'un utilisateur
			SecurityContext ctx = SecurityContextHolder.getContext();				
	        UserDetails users= (UserDetails )ctx.getAuthentication().getPrincipal();
	        //liste des matieres de la classe pour cette enseignant
			List<Matiere> listematiere=matiereinterface.ListeMatiereClasseEnseignant(classe, users.getUsername());
			String[] noms=new String[listematiere.size()];
			
			int i=0;
			for(Matiere matiere:listematiere) {
				noms[i]=matiere.getLibelle();
				i++;
				System.out.println(matiere.getLibelle());
			}
			model.addAttribute("matiereclasse",listematiere);
			model.addAttribute("nommatiere",noms);
			return noms;
		}
		
		

		 

		
}
