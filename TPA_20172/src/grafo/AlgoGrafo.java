package grafo;

import java.util.LinkedList;
import java.util.Stack;

public class AlgoGrafo {

public static LinkedList<Vertice> DFS(Grafo g, Vertice source){
		
		LinkedList<Vertice> verticesVisitados = new LinkedList<>();
		
		//verticesVisitados.add(source);
		Stack<Vertice> pilhaVertices = new Stack<>();
		pilhaVertices.push(source);
		
		while(!pilhaVertices.isEmpty()){
			Vertice v = pilhaVertices.pop();
			LinkedList<Vertice> verticesAdj = g.getVerticesAdjacentes(v);
			
			if(!VerificarVerticesVisitados(verticesVisitados, v)){
				verticesVisitados.add(v);
			}
			
			for (Vertice vertAdj : verticesAdj) {
				if(!VerificarVerticesVisitados(verticesVisitados, vertAdj)){
					//verticesVisitados.add(vertAdj);
					pilhaVertices.push(vertAdj);
				}
			}
			//pilhaVertices.pop();
		}
		return verticesVisitados;
		
	}
	

	private static boolean VerificarVerticesVisitados(LinkedList<Vertice> lista, Vertice v){
		boolean achou = false;
		int i = 0;
		while(i < lista.size() && !achou){
			if(lista.get(i).getLabel().equals(v.getLabel())){
				achou = true;
			}
			i++;
		}
		return achou;
	}
		
	public static void imprimeCaminho(LinkedList<Vertice> caminho){
		for (Vertice Vertice : caminho) {
			System.out.print(Vertice.getLabel() + " ");
		}
		System.out.println();
	}

	//busca em largura
	public static LinkedList<Vertice> BFS(Grafo g, Vertice source){
		
		LinkedList<Vertice> verticesVisitados = new LinkedList<>();
		verticesVisitados.add(source);
		LinkedList<Vertice> filaVertices = new LinkedList<>();
		filaVertices.add(source);
		
		while(!filaVertices.isEmpty()){
			LinkedList<Vertice> verticesAdj = g.getVerticesAdjacentes(filaVertices.get(0));
			
			for (Vertice vertAdj : verticesAdj) {
				if(!VerificarVerticesVisitados(verticesVisitados, vertAdj)){
					verticesVisitados.add(vertAdj);
					filaVertices.add(vertAdj);
				}
			}
			filaVertices.remove(0);
		}
		return verticesVisitados;
		
	}
}
