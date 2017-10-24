package grafo;

import java.util.LinkedList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

public class PlotarGrafico {
	public static void plotarGrafo(Grafo g, boolean direcionado){
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new MultiGraph("TPA",false,true);
		graph.setAutoCreate(false);
		
		//inserindo os vertices no Grafo
		for (Vertice v : g.vertices()) {
			graph.addNode(v.getLabel());
		}
		
		//Adcionando arestas
		for (org.graphstream.graph.Node node : graph) {
			//node.addAttribute("ui.label", node.getId());
			
			LinkedList<Aresta> arestasVertice =  g.getArestasVertice(node.getId().toString());
			for (Aresta aresta : arestasVertice) {
				Vertice v = g.oposite(node.getId().toString(), aresta.getLabel());
				
				if(v != null){
					//System.out.println("U: " + node.getId() + " V: " + v.getLabel() + " A: " + aresta.getId());
					if(direcionado){
						graph.addEdge(Float.toString(aresta.getId()), node.getId().toString(), v.getLabel(),true);
					}
					else{
						graph.addEdge(Float.toString(aresta.getId()), node.getId().toString(), v.getLabel());
					}
					
				}
			}
			
		}
		
		
		
		graph.display();
		
	}
}
