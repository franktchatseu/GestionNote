package org.sid.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.sid.dao.ClasseInterface;
import org.sid.dao.EleveInterface;
import org.sid.dao.EvaluationInterface;
import org.sid.dao.MatiereInterface;
import org.sid.dao.PeriodeInterface;
import org.sid.entites.Classe;
import org.sid.entites.Eleve;
import org.sid.entites.Evaluation;
import org.sid.entites.Matiere;
import org.sid.entites.PeriodeEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.spel.ast.Elvis;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	@Autowired
	private PeriodeInterface periodeinterface;
	//ce controleur doit renvoyer une page
	//et on precise url de la page qui sera charge
	
	
	@RequestMapping(value = "/eleve")
	public String PageEleve(Model model,@RequestParam(name="page", defaultValue = "0") int p,@RequestParam(name="size", defaultValue = "4") int size,@RequestParam(name="classe",defaultValue = "")String classe) {
		
		
		//Page<Eleve> pageeleve=eleveinterface.findAll(PageRequest.of(p, size));
		Page<Eleve> pageeleve=eleveinterface.chercher(classe,PageRequest.of(p, size));
		//le tableau de toute nos page
		int [] pages=new int[pageeleve.getTotalPages()];
		//on ajoute au model 
		
		model.addAttribute("classe",classe);
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
	public String notes(Model model,@RequestParam(name="classe",defaultValue = "6e")String nomClasse,@RequestParam(name="matiere",defaultValue="francais")String libelle_matiere,@RequestParam(name="sequence",defaultValue = "sequence 1") String sequence) {
		
		List<Matiere> matiereClasse=matiereinterface.ListeMatiereClasse(nomClasse);
	
		
		model.addAttribute("matiereClasse", matiereClasse);
		
		model.addAttribute("nomclasse",nomClasse);
		model.addAttribute("libelle", libelle_matiere);
		model.addAttribute("sequence", sequence);
		
			return "acceuil.html";
	}
	//vue pour la gestion des notes eleves
		@RequestMapping(value="/ajouter_note",method =RequestMethod.POST)
		public String ajouter_note(Model model,@RequestParam(name="note") int note,@RequestParam(name="classe") String nomclasse,@RequestParam(name="matiere")String libelle_matiere,@RequestParam(name="sequence")String sequence) {
		
			
			
				return "redirect:/visuel?classe="+nomclasse+"&matiere="+libelle_matiere+"&sequence="+sequence+"&note="+note;
		}
		
		//etape 1 deja ok!
		@RequestMapping(value="/visuel",method =RequestMethod.GET)
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
		
			return "acceuil";
			
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
			return "acceuil.html";
		}
		@RequestMapping(value ="/detailsnote")
		public String detailsnote(Model model,@RequestParam(name="matricule") long matricule,@RequestParam(name="nom") String nom,String prenom,String nom_tuteur,String classe) {
			
			List<Matiere> listematiere=matiereinterface.ListeMatiereEleve(matricule);
			List<List<Evaluation>> listedeliste=new ArrayList<List<Evaluation>>();
			for(Matiere m: listematiere) {
			//on recupere toute les notes de eleves suivant une matiere donnee
			List<Evaluation> listeevaluation=evaluationinterface.ListeEvaluationEleve(matricule,m.getLibelle());
			listedeliste.add(listeevaluation);
			}
			model.addAttribute("listematiere",listematiere);
			model.addAttribute("listedeliste",listedeliste);
			
			model.addAttribute("matricule", matricule);
			model.addAttribute("nom", nom);
			model.addAttribute("prenom", prenom);
			model.addAttribute("nom_tuteur", nom_tuteur);
			model.addAttribute("classe", classe);
			return "detailnote";
		}
		
}
