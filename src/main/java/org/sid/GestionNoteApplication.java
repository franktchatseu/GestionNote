package org.sid;



import org.sid.dao.EleveInterface;
import org.sid.entites.Eleve;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GestionNoteApplication {

	public static void main(String[] args) {
	 ApplicationContext ctx= SpringApplication.run(GestionNoteApplication.class, args);
	 EleveInterface eleveinterface=ctx.getBean(EleveInterface.class);
	 //ajoutons quelques eleves dans notre base de donnee
//	 eleveinterface.save(new Eleve("tchatseu","louenkam","04/14/2000", 'M', "louenkam edouard","690083767","edlouenk@gmail.com"));
//	 eleveinterface.save(new Eleve("louenkam","junior","04/14/2000", 'F', "titi francois","690083767","edlouenk@gmail.com"));
//	 eleveinterface.save(new Eleve("komegne","liliane","04/14/2000", 'F', "piekap celinePei","690083767","celine@gmail.com"));
//	 eleveinterface.findAll().forEach(e->System.out.println(e.getMail_tuteur()));
	
	 
	}

}
