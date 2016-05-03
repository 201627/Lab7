package it.polito.tdp.dizionario.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.dizionario.dao.ParolaDAO;

public class DizionarioGrafo {

	SimpleGraph<String, DefaultEdge> graph;
	ParolaDAO dao = new ParolaDAO();

	public void generaGrafo(int length) {

		graph = new SimpleGraph<>(DefaultEdge.class);
		List<String> vertices = dao.getListaParole(length);

		Graphs.addAllVertices(graph, vertices);

		for (String s1 : vertices) {

			List<String> paroleSimili = dao.paroleSimili(s1);

			for (String s2 : paroleSimili)
				if (s1.equals(s2) == false)
					graph.addEdge(s1, s2);

		}

	}

	public List<String> trovaVicini(String parola) {

		return Graphs.neighborListOf(graph, parola);
	}

	public boolean isInGraph(String s) {

		if (graph.vertexSet().contains(s))
			return true;
		else
			return false;

	}

	public List<String> trovaConnessi(String parola) {
		List<String> res;
		res = new LinkedList<>();

		GraphIterator<String, DefaultEdge> dit = new DepthFirstIterator<>(graph, parola);

		while (dit.hasNext())
			res.add(dit.next());

		return res;
	}

	/**
	 * Cerca cammino minimo tra due vertici {@code s1} e {@code s2} con
	 * algoritmo di Djikstra
	 * 
	 * 
	 * @param s1
	 * @param s2
	 * @return elenco ordinato dei vertici del cammino oppure {@code null} se
	 *         non esiste tale cammino.
	 */

	public List<String> getCammino(String s1, String s2) {

		DijkstraShortestPath<String, DefaultEdge> dijkstra = new DijkstraShortestPath<String, DefaultEdge>(graph, s1,
				s2);

		if (dijkstra.getPath() == null)
			return null;

		return Graphs.getPathVertexList(dijkstra.getPath());

	}
}
