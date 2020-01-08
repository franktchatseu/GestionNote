package org.sid.web;

import java.util.List;
import java.util.Optional;

import org.sid.dao.ClasseInterface;
import org.sid.dao.EnseignantInterface;
import org.sid.dao.MatiereInterface;
import org.sid.entites.Classe;
import org.sid.entites.Enseignant;
import org.sid.entites.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class matiereController {
	@Autowired
	private MatiereInterface mat;
	@Autowired
	private EnseignantInterface ens;
	@Autowired
	private ClasseInterface clas;
	
	@RequestMapping(value = "/matiere")
	public String matiere(Model model,
			@RequestParam(name="page", defaultValue = "0")int page,
			@RequestParam(name="size" , defaultValue = "15")int size,
			@RequestParam(name="classes" , defaultValue = "")String classe,
			@RequestParam(name="rech", defaultValue = "")String motCle) {
		
		model.addAttribute("matpagecourante", page);
		model.addAttribute("mot_courant", motCle);
		model.addAttribute("classeCourante", classe);
		Page<Matiere> allmatiere;
		//chargement des listes
		if(!classe.equals("Tous"))
			allmatiere=mat.ListeMatiereClasse(classe, motCle,  PageRequest.of(page,size));
		else
			allmatiere=mat.ListeMatiereClasse(classe,  PageRequest.of(page,size));
		
		System.out.println("longueur de la liste de matiere: "+allmatiere.getSize());
		
		model.addAttribute("malistMatiere", allmatiere.getContent());
		model.addAttribute("matTotalPage",new int[allmatiere.getTotalPages()]);
		List<Enseignant> ll=ens.findAll();
		model.addAttribute("list_enseignant",ll);
		List<Classe> list=clas.findAll();
		model.addAttribute("list_classe",list);
		return "matiere";
	}
	
	
	@RequestMapping(value = "/ajoutMatiere")
	public String addMatiere(@RequestParam(name="libele") String libele,
			@RequestParam(name="coefficient") int coef,
			@RequestParam(name="profs")String id,
			@RequestParam(name="classes")String classe) {
		
		Optional<Enseignant> e=ens.findById(id);
		Optional<Classe> c=clas.findById(classe);
		Matiere matiere=new Matiere(libele, coef, c.get(), e.get());
		mat.save(matiere);
		return "redirect:/matiere";
	}
	
	
	
	@RequestMapping(value="/deleteMatiere",method = RequestMethod.GET)
	public String delete(@RequestParam(name="id") long id) {
		mat.deleteById(id);
		return "redirect:/matiere";
	}
	

}
