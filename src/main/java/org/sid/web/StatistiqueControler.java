package org.sid.web;

import java.util.List;

import org.sid.dao.ClasseInterface;
import org.sid.dao.PeriodeInterface;
import org.sid.dao.TrimestreInterface;
import org.sid.entites.BilanNote;
import org.sid.entites.Classe;
import org.sid.entites.StatistiqueImpl;
import org.sid.entites.Trimestre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatistiqueControler {
	
	StatistiqueImpl statistique=new StatistiqueImpl();
	@Autowired
	ClasseInterface clas;
	
	@Autowired
	TrimestreInterface trim;
	@Autowired
	PeriodeInterface periodeinterface;
	
	@RequestMapping(value="/statistique")
	public String pagestatistique(Model model,
			@RequestParam(name="classe",defaultValue = "6e")String classe,
			@RequestParam(name="sequence",defaultValue = "sequence1")String sequence
			
			) {
		List<BilanNote> listebilan=statistique.getListBilan(classe,sequence);
		float moyennegenerale=statistique.moyenneGenerale(listebilan);
		Classe c=clas.findBynomClassee(classe);
		String nom="Prof titulaire: Mr/Mme "+c.getProf_titulaire();
		//introduction des elments dans le model
		List<Trimestre> listetrimestre=trim.findAll();
		model.addAttribute("listetrimestre",listetrimestre);
		model.addAttribute("listeperiode",periodeinterface.listePeriodeTrimestre(listetrimestre.get(0).getLibelle()));
		
		model.addAttribute("listebilan", listebilan);
		model.addAttribute("profTitulaire",nom);
		model.addAttribute("moyennegenerale",moyennegenerale);
		model.addAttribute("bilanpremier", listebilan.get(0));
		model.addAttribute("bilandernier", listebilan.get(listebilan.size()-1));
		model.addAttribute("listeclasse",clas.findAll());
		model.addAttribute("nombreDadmis",statistique.nombreAdmis(listebilan));
		return "statistique";
	}
}
