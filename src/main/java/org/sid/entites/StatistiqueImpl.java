package org.sid.entites;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.sid.dao.StatistiqueInterface;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class StatistiqueImpl  implements StatistiqueInterface {


	@Override
	public float moyenneGenerale(List<BilanNote> listebilan) {
		// TODO Auto-generated method stub
		float somme=0;
		for(BilanNote bilanNote:listebilan) {
			somme=somme+bilanNote.getMoyenne();
			
		}
		
		return somme/listebilan.size();
	}

	@Override
	public float moyennePremier(Classe classe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float moyenneDernier(Classe classe) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
  
    @Override
    public List<BilanNote> getListBilan(String nomclasse, String sequence) {

        String vSQL = "SELECT el.matricule as matricule,p.nom as nom,p.prenom as prenom,SUM(e.note*m.coef)/SUM(m.coef) as moy FROM"
        		+ "  eleve el, evaluation e, personne p ,matiere m,classe c,periode_evaluation seq WHERE el.matricule=e.matricule and el.matricule=p.matricule "
        		+ "and m.id_matiere=e.id_matiere and c.nom_classe=m.nom_classe  and seq.id_periode=e.id_periode and seq.libelle='"+sequence+"' and c.nom_classe='"+nomclasse+"' GROUP by el.matricule ORDER by moy DESC";


        JdbcTemplate vJdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());

        
        RowMapper<BilanNote> vRowMapper = new RowMapper<BilanNote>() {

            public BilanNote mapRow(ResultSet pRS, int pRowNum) throws SQLException {

            	BilanNote bilannote = new BilanNote();
            	
                //vBilanNote.setLibelle(pRS.getString("libelle"));
            	bilannote.setRang(pRowNum+1);
            	bilannote.setMatricule(pRS.getInt("matricule"));
            	bilannote.setNom(pRS.getString("nom")+" "+pRS.getString("prenom"));
            	bilannote.setMoyenne(pRS.getFloat("moy"));
            	bilannote.setMention(mention(bilannote.getMoyenne()));
                return bilannote;

            }
           
        };


        List<BilanNote> listebilan = vJdbcTemplate.query(vSQL, vRowMapper);


        return listebilan;

    }

	@Override
	public int nombreAdmis(List<BilanNote> liste) {
		int count=0;
		for(BilanNote bilan: liste) {
			if(bilan.getMoyenne()>=10)
				count++;
		}
		
		return count;
	}

	@Override
	public String mention(float note) {
		// TODO Auto-generated method stub
		String mention="";
		if(note>=0 && note<5)
			mention="Tres faible";
		else if(note>=5 && note<7)
			mention="Faible";
		else if(note>=7 && note<10)
			mention="Mediocre";
		else if(note>=10 && note<12)
			mention="Passable";
		else if(note>=12 && note<14)
			mention="Bien";
		else if(note>=14 && note<16)
			mention="Assez Bien";
		else if(note>=16 && note<18)
			mention="Tres Bien";
		else if(note>=18 && note<19)
			mention="Excellent";
		else if(note==20)
			mention="Parfait";
		
		return mention;
	}
	

}
