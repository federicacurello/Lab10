package it.polito.tdp.porto.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		model.creaGrafo();
		System.out.println("Creato grafo con "+ model.getGrafo().vertexSet().size()+ " vertici e "+ model.getGrafo().edgeSet().size()+" archi");
		
	}

}
