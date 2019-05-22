package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {
	Map<Integer, Author> aut= new HashMap<Integer, Author>();
	Map<Integer, Paper> pap= new HashMap<Integer, Paper>();

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				
				return autore;
			}
			conn.close();
			
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				pap.put(paper.getEprintid(), paper);
				return paper;
			}
			
			conn.close();
			
			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		

	}


public List<Author> tuttiAutori(){
	final String sql = "SELECT * FROM author";

	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		

		ResultSet rs = st.executeQuery();
		List<Author> autori =new ArrayList<Author>();

		while (rs.next()) {
			Author a = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				
			autori.add(a);
			aut.put(a.getId(), a);
			
		}
		conn.close();
		
		return autori;

	} catch (SQLException e) {
		 e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}
	

}

public List<Author> coautori(int authorid){
	final String sql = "SELECT c1.authorid AS autore1, c2.authorid AS autore2, c1.eprintid AS p " + 
			"FROM creator c1, creator c2 " + 
			"WHERE c1.eprintid= c2.eprintid AND c1.authorid=? AND c1.authorid!= c2.authorid";

	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, authorid);

		ResultSet rs = st.executeQuery();
		List<Author> coautori= new ArrayList<Author>();

		while (rs.next()) {
			//Paper p= pap.get(rs.getInt("p"));
			Author a1= aut.get(authorid);
			Author a2= aut.get(rs.getInt("autore2"));
			//a1.aggiungiPaper(p);
			//a2.aggiungiPaper(p);
			
			coautori.add(a2);
		}
		conn.close();
		
		return coautori;

	} catch (SQLException e) {
		 e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}
}

public Map<Integer, Author> getAut() {
	return aut;
}

}


