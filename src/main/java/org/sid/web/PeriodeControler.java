package org.sid.web;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.sid.dao.PeriodeInterface;
import org.sid.dao.TrimestreInterface;
import org.sid.entites.Matiere;
import org.sid.entites.PeriodeEvaluation;
import org.sid.entites.Trimestre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PeriodeControler {
	@Autowired
	public TrimestreInterface trim;
	
	@Autowired
	public PeriodeInterface per;
	
	private static boolean ouv=false;
	
	
	@RequestMapping(value = "/gestionPeriodeEvaluation")
	public String gestionPeriode(Model model,
			@RequestParam(name="rech", defaultValue = "") String motCle,
			@RequestParam(name="filtre", defaultValue = "") String filtre,
			@RequestParam(name="valide", defaultValue = "false") boolean val
			
			) {
		//creation des listes a stocker dans le model
		
		List<PeriodeEvaluation> listePeriode=per.periodeParTrimestreNom(motCle, filtre);
		List<Trimestre> listeTrimestre=trim.findAll();
		
		//different ajout dans le model
		model.addAttribute("listePeriode",listePeriode);
		model.addAttribute("listeTrimestre",listeTrimestre);
		model.addAttribute("motCle",motCle);
		model.addAttribute("valide",val);
		
		
		return "gestionPeriodeEvaluation";
	}
	
	
	
	
	
	@RequestMapping(value = "/ajoutT", method = RequestMethod.GET)
	public String saveTrimestre(@RequestParam(name="trimestre",defaultValue = "") String trimestre) {
		Trimestre t=new Trimestre(trimestre);
		System.out.println(trimestre+" valeur du trimestre \n ");
		if(!trimestre.equals(""))
			trim.save(t);
		return "redirect:/gestionPeriodeEvaluation";
	}
	
	
	@RequestMapping(value = "/ajoutSequence")
	public String savePeriode(
			@RequestParam(name="idTrimestre") String trimestre,
			@RequestParam(name="nomPeriode", defaultValue = "") String nom,
			@RequestParam(name="debut", defaultValue = "") Date debut,
			@RequestParam(name="fin", defaultValue = "") Date fin) {
		Trimestre t;
		if((t=trim.findBylibelle(trimestre))!=null) {
			PeriodeEvaluation p=new PeriodeEvaluation(nom, debut, fin, false,t);
			per.save(p);
		}
		return "redirect:/gestionPeriodeEvaluation";
	}
	
	@RequestMapping(value = "/ouvrir")
	public String ouvrir(
			@RequestParam(name="id") long id
			){
		PeriodeEvaluation p=per.findByidPeriode(id);
		System.out.println(p.getLibelle()+"libele de la periode");
			p.setStatut(true);
			System.out.println(p.isStatut()+"statut de la periode");
			per.save(p);
		return "redirect:/gestionPeriodeEvaluation";
	}
	
	
	@RequestMapping(value = "/fermer")
	public String fermer(
			@RequestParam(name="id") long id
			) {
		PeriodeEvaluation p=per.findByidPeriode(id);
			p.setStatut(false);
			per.save(p);
		return "redirect:/gestionPeriodeEvaluation";
	}
	
	@RequestMapping(value="/periodetrimestre")
	@ResponseBody
	public String[] listeperiode(Model model,String trimestre) {
		
        //liste des periodes evaluations pour un trimestre donné
		List<PeriodeEvaluation> listeperiode=per.listePeriodeTrimestre(trimestre);
		String[] noms=new String[listeperiode.size()];
		
		int i=0;
		for(PeriodeEvaluation periode:listeperiode) {
			noms[i]=periode.getLibelle();
			i++;
			System.out.println(periode.getLibelle());
		}
		model.addAttribute("periodetrimestre",listeperiode);
		model.addAttribute("nomperiode",noms);
		return noms;
	}

}
