package org.sid;



import java.util.List;

import org.sid.dao.ClasseInterface;
import org.sid.dao.EleveInterface;
import org.sid.dao.EnseignantInterface;
import org.sid.dao.EvaluationInterface;
import org.sid.dao.InterfaceProduit;
import org.sid.dao.MatiereInterface;
import org.sid.entites.Classe;
import org.sid.entites.Eleve;
import org.sid.entites.Enseignant;
import org.sid.entites.Evaluation;
import org.sid.entites.Matiere;
import org.sid.entites.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GestionNoteApplication {

	public static void main(String[] args) {
	
	 ApplicationContext ctx= SpringApplication.run(GestionNoteApplication.class, args);
	 InterfaceProduit interfaceproduit=ctx.getBean(InterfaceProduit.class);
	 interfaceproduit.save(new Produit("core i7", 10000, 10));
	 interfaceproduit.save(new Produit("dual core", 500000, 10));
	 interfaceproduit.deleteById(1L);
	 List<Produit> liste=interfaceproduit.findByprix(500000);
	 for (Produit produit : liste) {
		 System.out.println(produit.getNom_prod());
	}
//	 EleveInterface eleveinterface=ctx.getBean(EleveInterface.class);
//	 MatiereInterface matiereinterface=ctx.getBean(MatiereInterface.class);
//	 EvaluationInterface evaluationinterface=ctx.getBean(EvaluationInterface.class);
//	 ClasseInterface classeinterface = ctx.getBean(ClasseInterface.class);
//	 EnseignantInterface enseignantinterface=ctx.getBean(EnseignantInterface.class);
//	 //insertion des enseignants
//	 enseignantinterface.save(new Enseignant("tendjon", "alex","2454", "SG"));
//	 enseignantinterface.save(new Enseignant("Fepi", "Ubert","24512", "Cenceur"));
//	 enseignantinterface.save(new Enseignant("Sooh", "Ivan","f2454", "SG"));
//	 enseignantinterface.save(new Enseignant("CAsano", "ROa","f2454B", "SG"));
//	 //insertion des classes
//	 classeinterface.save(new Classe("6e", 50, "FRANCOIS"));
//	 classeinterface.save(new Classe("5e", 20, "Toni"));
//	 classeinterface.save(new Classe("4e", 30, "Ivan"));
//	 //insertion des matieres
//	 matiereinterface.save(new Matiere("francais", 2, classeinterface.findBynomClasse("6e"), enseignantinterface.findByidEnseignant(1L)));
//	 matiereinterface.save(new Matiere("francais", 3, classeinterface.findBynomClasse("5e"), enseignantinterface.findByidEnseignant(2L)));
//	 matiereinterface.save(new Matiere("francais", 4, classeinterface.findBynomClasse("4e"), enseignantinterface.findByidEnseignant(3L)));
//	 matiereinterface.save(new Matiere("maths", 6, classeinterface.findBynomClasse("6e"), enseignantinterface.findByidEnseignant(1L)));
//	 matiereinterface.save(new Matiere("maths", 5, classeinterface.findBynomClasse("5e"), enseignantinterface.findByidEnseignant(2L)));
//	 matiereinterface.save(new Matiere("maths", 4, classeinterface.findBynomClasse("4e"), enseignantinterface.findByidEnseignant(3L)));
//	// ajoutons quelques eleves dans notre base de donnee
//	 eleveinterface.save(new Eleve("tchatseu","louenkam","04/14/2000", 'M', "louenkam edouard","690083767","edlouenk@gmail.com",classeinterface.findBynomClasse("6e")));
//	 eleveinterface.save(new Eleve("shaa","prombos","04/14/2000", 'M', "frise","690083767","edfrise.com",classeinterface.findBynomClasse("5e")));
//	 eleveinterface.save(new Eleve("komegne","liliane","04/14/2000", 'F', "piekap celinePei","690083767","celine@gmail.com",classeinterface.findBynomClasse("4e")));
//	 
//	// ajoutons quelques eleves dans notre base de donnee
//		 eleveinterface.save(new Eleve("rita","simeu","04/14/2000", 'M', "Bertinbe","69054412","berte@gmail.com",classeinterface.findBynomClasse("6e")));
//		 eleveinterface.save(new Eleve("Adamou","Aliyou","04/14/2000", 'F', "Adam","690083767","adada@gmail.com",classeinterface.findBynomClasse("5e")));
//		 eleveinterface.save(new Eleve("Tchamou","ramses","04/14/2000", 'M', "brice","6945778","pbrice@gmail.com",classeinterface.findBynomClasse("4e")));
////	 eleveinterface.findAll().forEach(e->System.out.println(e.getMail_tuteur()));
	}

}
