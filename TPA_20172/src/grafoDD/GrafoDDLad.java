package grafoDD;

import grafo.Aresta;
import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;
import grafo.Vertice;
import java.util.LinkedList;

public class GrafoDDLad extends Grafo{
	LinkedList<Aresta> listaArestas;
	LinkedList<Vertice> listaVertices;
	
    public GrafoDDLad() {
		listaArestas = new LinkedList<>();
		listaVertices = new LinkedList<>();
	}
	
	public Vertice insereVertice(String label, String dado) {
		for (Vertice vert : listaVertices) {
			if(vert.getLabel().equals(label)){
				return vert;
			}
		}
		Vertice obj = new Vertice(Float.parseFloat(label), label);
		listaVertices.add(obj);
		return obj;
	}

	@Override
	public void insereAresta(String v1, String v2, String label, Object dado) {
		int i = 0;
		Vertice u = null;
		Vertice v = null;
		
		while(i < listaVertices.size()){
			if(listaVertices.get(i).getLabel().equals(v1)){
				u = listaVertices.get(i);
			}
			if(listaVertices.get(i).getLabel().equals(v2)){
				v = listaVertices.get(i);
			}
			if(u != null && v != null){
				break;
			}
			i++;
		}
		
		if(u != null && v != null){
			
			float id = ((u.getId()) * 5) + ((v.getId()) * 7); 
			//float id = (Float.parseFloat(label));
			Aresta a = new Aresta(u, v, id, label,dado);
			listaArestas.add(a);
			u.addArestaOut(a);
			v.addArestaIn(a);
		
		}
		
	}

	@Override
	public void insereAresta(Vertice u, Vertice v,String label, Object dado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<Aresta> getArestasVertice(String vertice) {
		LinkedList<Aresta> listaArestas = null;
		for (Vertice v : listaVertices) {
			if(v.getLabel().equals(vertice)){
				listaArestas = v.getArestaOut();
				break;
			}
		}
		
		return listaArestas;
	}

	@Override
	public int grauVertice(String vertice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<Vertice> vertices() {
		return listaVertices;
	}

	@Override
	public LinkedList<Aresta> arestas() {
		return listaArestas;
	}

	@Override
	public Aresta getAresta(String vertU, String vertV) {
		
		boolean achou = false;
		Aresta aresta = null;
		int i = 0;
		while(i < listaArestas.size() && !achou){
			Aresta obj = listaArestas.get(i);
			if(obj.getVertU().getLabel().equals(vertU) && obj.getVertV().getLabel().equals(vertV) || 
				obj.getVertU().getLabel().equals(vertV) && obj.getVertV().getLabel().equals(vertU) ){
				achou = true;
				aresta = obj;
				break;
				}
			i++;
		}

		return aresta;
	}

	@Override
	public LinkedList<Vertice> getVerticesAresta(float id) {
		int i = 0;
		boolean achou = false;
		LinkedList<Vertice> listaVertices = null;
		while(i < listaArestas.size() && !achou){
			if(listaArestas.get(i).getId() == id){
				listaVertices = new LinkedList<>();
				listaVertices.add(new Vertice(listaArestas.get(i).getVertU().getId(), listaArestas.get(i).getVertU().getLabel()));
				listaVertices.add(new Vertice(listaArestas.get(i).getVertV().getId(), listaArestas.get(i).getVertV().getLabel()));
				achou = true;
			}
			i++;
		}
		return listaVertices;
				
	}
	
	public Vertice getVertice(String label){
		int i = 0;
		boolean achou = false;
		Vertice v = null;
		while(i < listaVertices.size() && !achou){
			if(listaVertices.get(i).getLabel().equals(label)){
				achou = true;
				v = listaVertices.get(i);
			}
			i++;
		}
		
		return v;
		
	}

	@Override
	public LinkedList<Vertice> getVerticesAdjacentes(Vertice v) {
		LinkedList<Vertice> verticesAdj = new LinkedList<>();
		
		Vertice vert = getVertice(v.getLabel());
		if(vert == null) return null;
		
		for (Aresta aresta : vert.getArestaOut()) {
			Vertice vertOposto = oposite(v.label, aresta.id);
			if(vertOposto != null){
				verticesAdj.add(vertOposto);
			}
		}
		
		return verticesAdj;
	}

	@Override
	public Vertice insereVertice(Vertice v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertice oposite(String vertice, float idAresta) {
		int i = 0;
		Vertice v = null;
		boolean achou = false;
		while(i < listaArestas.size() && !achou){
			Aresta a = listaArestas.get(i);
			if(a.getId() == idAresta){
				//System.out.println("VertU: " + a.vertU.label);
				//System.out.println("VertV: " + a.vertV.label);
				if(a.getVertU().getLabel().equals(vertice)){
					v = a.getVertV();
				}
				else if(a.getVertV().getLabel().equals(vertice)){
					v = a.getVertU();
				}
				achou = true;
			}
			i++;
		}
		
		return v;
	}
	
}
