package org.sid.web;

import java.util.List;

import javax.swing.ComboBoxEditor;

import org.sid.dao.ClasseInterface;
import org.sid.dao.EnseignantInterface;
import org.sid.entites.Classe;
import org.sid.entites.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class classesControlleur {

	@Autowired
	private ClasseInterface classe;
	
	@Autowired
	private EnseignantInterface ens;
	
	@RequestMapping(value = "/classes")
	public String affichage(Model model,
			@RequestParam(name="page", defaultValue = "0") int pag,
			@RequestParam(name="taille", defaultValue = "15")int size,
			@RequestParam(name="rech", defaultValue = "")String motcle) {
		
		Page<Classe> pages=classe.findBynomClasse(motcle, PageRequest.of(pag, size));
		List<Enseignant> ll=ens.findAll();
		
		model.addAttribute("clamotcle", motcle);
		model.addAttribute("clapagetotal", pages.getTotalPages());
		model.addAttribute("clapagecourante",pag);
		model.addAttribute("ListeClasses",pages.getContent());
		model.addAttribute("list_enseignant",ll);
		return "classes";
	}
	
	@RequestMapping(value = "/deleteClasses")
	public String delete(@RequestParam(name="id") String nom) {
		classe.deleteById(nom);
		return "redirect:/classes";
	}
	
	
	@RequestMapping(value = "/ajoutClasse", method = RequestMethod.GET)
	public String AjoutClasse(@RequestParam(name="noms") String nom,
			@RequestParam(name="places", defaultValue = "0") long nb,
			@RequestParam(name="profs", defaultValue = "") String valeur) {
		System.out.println("test arriva 1g:   valeur du nom:"+nom+"  ; valeur du nombre: "+nb+"  ; valeur du prof: "+valeur);
		Classe clas;
		if(valeur.equals("Auccun")) {
			clas=new Classe(nom, nb);
		}else {
			clas=new Classe(nom, nb, valeur);
		}
		classe.save(clas);
		return "redirect:/classes";
	}
	

}
