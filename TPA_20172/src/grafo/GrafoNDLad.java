package grafo;

import java.util.LinkedList;

public class GrafoNDLad extends Grafo {
	
	LinkedList<Aresta> listaArestas;
	LinkedList<Vertice> listaVertices;
	int idAresta = 0;
	
    public GrafoNDLad() {
		listaArestas = new LinkedList<>();
		listaVertices = new LinkedList<>();
	}
    @Override
	public Vertice insereVertice(String label, String dado) {
		for (Vertice vert : listaVertices) {
			if(vert.getLabel().equals(label)){
				return vert;
			}
		}
		Vertice obj = new VerticeLad(Integer.parseInt(label), label);
		listaVertices.add(obj);
		qtdVertices++;
		return obj;
	}
	
	
	public Vertice insereVertice(Vertice v) {
		for (Vertice vert : listaVertices) {
			if(vert.getId() == v.getId()){
				return vert;
			}
		}
		listaVertices.add(v);
		qtdVertices++;
		return v;
	}

	

	@Override
	public void insereAresta(Vertice u, Vertice v, Object dado) {
		ArestaLad aresta = (ArestaLad)dado;
		int i = 0;
		idAresta++;
		Vertice vertU = null;
		Vertice vertV = null;
		
		while(i < listaVertices.size()){
			if(listaVertices.get(i).getId() == u.getId()){
				vertU = listaVertices.get(i);
			}else
			if(listaVertices.get(i).getId() == v.getId()){
				vertV = listaVertices.get(i);
			}
			if(vertU != null && vertV != null){
				break;
			}
			i++;
		}
		
		if(vertU != null && vertV != null){
			
			//float id = ((vertU.getId() + 1) * 5) + ((vertV.getId() + 1) * 7); 
			
			//float id = idAresta;
			System.out.println(idAresta);
			Aresta a = new Aresta(u, v, idAresta, label,dado);
			listaArestas.add(a);
			vertU.addArestaIn(a);
			vertU.addArestaOut(a);
			vertV.addArestaIn(a);
			vertV.addArestaOut(a);
		
		}
		
	}

	@Override
	public LinkedList<Aresta> getArestasVertice(String vertice) {
		LinkedList<Aresta> listaArestas = null;
		for (Vertice v : listaVertices) {
			if(v.getLabel().equals(vertice)){
				listaArestas = v.getArestaIn();
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
			obj = listaArestas.get(i);
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
		//System.out.println(v.getLabel());
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
	public Vertice oposite(String vertice, String aresta) {
		int i = 0;
		Vertice v = null;
		boolean achou = false;
		int idAresta;
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

	@Override
	public void insereAresta(String v1, String v2, String label, Object dado) {
		int i = 0;
		Vertice u = null;
		Vertice v = null;
		
		while(i < listaVertices.size()){
			if(listaVertices.get(i).getId() == (Float.parseFloat(v1))){
				u = listaVertices.get(i);
			}
			if(listaVertices.get(i).getId() == (Float.parseFloat(v2))){
				v = listaVertices.get(i);
			}
			if(u != null && v != null){
				break;
			}
			i++;
		}
		
		if(u != null && v != null){
			
			//float id = ((u.getId()) * 5) + ((v.getId()) * 7); 
			float id = idAresta++;
			Aresta a = new Aresta(u, v, id, label, dado);
			listaArestas.add(a);
			u.addArestaIn(a);
			u.addArestaOut(a);
			v.addArestaIn(a);
			v.addArestaOut(a);
		
		}
		
	}

	

}
