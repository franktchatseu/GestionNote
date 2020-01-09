package org.sid.bulletin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sid.dao.EvaluationInterface;
import org.sid.dao.MatiereInterface;
import org.sid.dao.PeriodeInterface;
import org.sid.entites.BilanNote;
import org.sid.entites.Classe;
import org.sid.entites.Enseignant;
import org.sid.entites.Evaluation;
import org.sid.entites.Matiere;
import org.sid.entites.PeriodeEvaluation;
import org.sid.entites.SpringJdbcConfig;
import org.sid.entites.StatistiqueImpl;
import org.sid.entites.Trimestre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BulletinImpl {

	
	
	private StatistiqueImpl stat=new StatistiqueImpl();
	public TupleBulletin tupleeleve(long id_eleve,Matiere matiere,String trimestre) {
		StatistiqueImpl statistique=new StatistiqueImpl();
		TupleBulletin tuple=new TupleBulletin();
		tuple.setListeevaluation(getListeEvaluation(id_eleve, matiere.getLibelle(),trimestre));
		
		System.out.println("pass"+tuple.getListeevaluation().size());
		//on recupere la note totale
		float total=0;
		for(Evaluation eva:tuple.getListeevaluation()) {
			total=total+eva.getNote();
		}
		tuple.setNote_final(total/tuple.getListeevaluation().size());
		//recuperation du nom de la matiere;
		tuple.setMatiere(matiere.getLibelle());
		tuple.setProf_matiere(matiere.getEnseignant().getNom());
		tuple.setCoef(matiere.getCoef());
		tuple.setMention(statistique.mention(tuple.getNote_final()));
		return tuple;
	}
	//fonction qui retourne un bulletin
	public Bulletin construirebulletin(long id_eleve,String classe,Trimestre trimestre) {
		
		//notre type bulletin
		Bulletin bulletin=new Bulletin();
		System.out.println("test1");
		//recupereation de la liste de matiere
		List<Matiere> listematiere=getListeMatiere(classe);
		
		System.out.println("bonne liste de matiree");
		//construction de la liste de tuplebulletin
		int totalcoef=0;
		List<TupleBulletin> listetuplebulletin=new ArrayList<TupleBulletin>();
		for(Matiere matiere:listematiere) {
			System.out.println("rempli");
			totalcoef=totalcoef+matiere.getCoef();
			listetuplebulletin.add(this.tupleeleve(id_eleve, matiere,trimestre.getLibelle()));
		}
		
		//construction de la liste de moyenne
		List<Float> listemoyenne=new ArrayList<Float>();
		//recuperation de toute les sequence du trimestre
		List<PeriodeEvaluation> listeperiode=getListePeriode(trimestre.getLibelle());
		System.out.println("good");
		float somme=0;
		for(PeriodeEvaluation periode:listeperiode) {
			float moy=this.getmoyenneelevesequence(id_eleve, periode.getLibelle());
			listemoyenne.add(moy);
			somme=somme+moy;
		}
		
		//
		bulletin.setMoyennefinale(somme/listemoyenne.size());
		bulletin.setListemoyenne(listemoyenne);
		bulletin.setListebulletin(listetuplebulletin);
		bulletin.setDecision(this.decision(bulletin.getMoyennefinale()));
		bulletin.setListeperiode(listeperiode);
		bulletin.setTotalcoef(totalcoef);
		bulletin.setDecision(decision(bulletin.getMoyennefinale()));
		bulletin.setMention(stat.mention(bulletin.getMoyennefinale()));
		return bulletin;
	}
	
	public String decision(float moy) {
		String valeur="";
		if(moy<10)
			valeur="Redouble";
		else
			valeur="Admis";
		
		
		return valeur;
	}
	
	
	

	    public float getmoyenneelevesequence(long id_eleve,String sequence) {

	        JdbcTemplate vJdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());

	       float moyenne = vJdbcTemplate.queryForObject(

	            "SELECT SUM(eva.note*m.coef)/SUM(m.coef) as moy "
	            + "from evaluation eva,eleve el,periode_evaluation pe,"
	            + "matiere as m where el.matricule=eva.matricule and m.id_matiere=eva.id_matiere"
	            + " and pe.id_periode=eva.id_periode and pe.libelle='"+sequence+"' AND el.matricule="+id_eleve,

	            Float.class);


	        return moyenne;

	    }
	    
	    public List<Matiere> getListeMatiere(String nomclasse) {

	        String vSQL =" SELECT * FROM matiere m,classe c,enseignant e where e.username=m.username and "
	        		+ "c.nom_classe=m.nom_classe and c.nom_classe='"+nomclasse+"'";

	        JdbcTemplate vJdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());

	        
	        RowMapper<Matiere> vRowMapper = new RowMapper<Matiere>() {

	            public Matiere mapRow(ResultSet rs, int pRowNum) throws SQLException {

	            	Matiere matiere=new Matiere();
	            	
	                //vBilanNote.setLibelle(pRS.getString("libelle"));
	            	Enseignant enseignant=new Enseignant();
	            	enseignant.setNom(rs.getString("e.nom"));
	            	matiere.setLibelle(rs.getString("libelle"));
	            	matiere.setCoef(rs.getInt("coef"));
	            	matiere.setEnseignant(enseignant);
	                return matiere;

	            }
	           
	        };


	        List<Matiere> listebilan = vJdbcTemplate.query(vSQL, vRowMapper);


	        return listebilan;

	    }

	    //
	    public List<Evaluation> getListeEvaluation(long id,String libelle,String trimestre) {

	        String vSQL ="select * from evaluation eva,eleve e,matiere m,trimestre t, periode_evaluation pe "
	        		+ " where eva.matricule=e.matricule and t.libelle='"+trimestre+"' and t.id_trimestre=pe.id_trimestre and pe.id_periode=eva.id_periode "
	        		+ " and m.id_matiere=eva.id_matiere and  e.matricule="+id+" and m.libelle='"+libelle+"' order by eva.id_periode asc";

	        JdbcTemplate vJdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());

	        
	        RowMapper<Evaluation> vRowMapper = new RowMapper<Evaluation>() {

	            public Evaluation mapRow(ResultSet rs, int pRowNum) throws SQLException {

	            	Evaluation evaluation=new Evaluation();
	            	
	                //vBilanNote.setLibelle(pRS.getString("libelle"));
	            	evaluation.setNote(rs.getInt("note"));
	                return evaluation;

	            }
	           
	        };


	        List<Evaluation> listebilan = vJdbcTemplate.query(vSQL, vRowMapper);


	        return listebilan;

	    }
	    //
	    public List<PeriodeEvaluation> getListePeriode(String trimestre) {

	        String vSQL ="select distinct p.libelle as libel from periode_evaluation p ,trimestre t where p.id_trimestre=t.id_trimestre and t.libelle='"+trimestre+"'";

	        JdbcTemplate vJdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());

	        
	        RowMapper<PeriodeEvaluation> vRowMapper = new RowMapper<PeriodeEvaluation>() {

	            public PeriodeEvaluation mapRow(ResultSet rs, int pRowNum) throws SQLException {

	            	PeriodeEvaluation periode=new PeriodeEvaluation();
	            	
	                //vBilanNote.setLibelle(pRS.getString("libelle"));
	            	periode.setLibelle(rs.getString("libel"));
	                return periode;

	            }
	           
	        };


	        List<PeriodeEvaluation> listebilan = vJdbcTemplate.query(vSQL, vRowMapper);


	        return listebilan;

	    }
	    
	
}
