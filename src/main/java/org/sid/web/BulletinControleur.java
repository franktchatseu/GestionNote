package org.sid.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sid.bulletin.Bulletin;
import org.sid.bulletin.BulletinImpl;
import org.sid.dao.ClasseInterface;
import org.sid.dao.EleveInterface;
import org.sid.dao.TrimestreInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BulletinControleur {

	
	@Autowired
	private ClasseInterface classeinterface;
	@Autowired
	private TrimestreInterface trimestreinterface;
	@Autowired
	private EleveInterface eleveinterface;
	
	private BulletinImpl bulletinimpl=new BulletinImpl();
	@RequestMapping(value="/bull")
	public String bulletin(Model model,
			@RequestParam(name = "classe", defaultValue = "6e")String classe,
			@RequestParam(name = "trimestre", defaultValue = "trimestre1")String trimestre,
			@RequestParam(name = "matricule", defaultValue = "1")long matricule) {
		//construction du bulletion de eleve
		try {
		Bulletin bulletion=bulletinimpl.construirebulletin(matricule, classe, trimestreinterface.findBylibelle(trimestre));	
		//on introduit dans le model 
		model.addAttribute("bulletin",bulletion);
		model.addAttribute("classe", classeinterface.findBynomClassee(classe));
		model.addAttribute("eleve",eleveinterface.findBymatricule(matricule));
		model.addAttribute("trimestre",trimestre);
		
		//date du jour
		DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		Date date=new Date();
		String chainedate=dateFormat.format(date);
		model.addAttribute("datejour", chainedate);
		return "bulletin";
		}
		catch (NullPointerException e) {
			// TODO: handle exception
			return "accessdenied";
		}
	}
}
