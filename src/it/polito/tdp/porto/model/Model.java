package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	SimpleGraph<Author, DefaultEdge> grafo;
	PortoDAO dao;
	
	public SimpleGraph<Author, DefaultEdge> getGrafo() {
		return grafo;
	}
	

	
	public Model() {
		grafo= new SimpleGraph<>(DefaultEdge.class);
		dao= new PortoDAO();
		
	}
	public void creaGrafo() {
		Graphs.addAllVertices(grafo, dao.tuttiAutori());
		for(Author a1: grafo.vertexSet()) {
			List<Author> coautori = dao.coautori(a1.getId());
			for(Author a2 : coautori) {
				grafo.addEdge(a1, a2);
			}
	}}
	public List<Author> tuttiAutori(){
		if(grafo.vertexSet().isEmpty())
			creaGrafo();
		
		return dao.tuttiAutori();
	}
	public List<Author> coautori(int authorid){
		return dao.coautori(authorid);
	}
	public List<Author> nonCoautori(int authorid){
		List<Author> nonCoautori= new ArrayList<Author>();
		for(Author a: grafo.vertexSet())
			if(!coautori(authorid).contains(a)) {
				nonCoautori.add(a);
			}
		return nonCoautori;
		}
	}



