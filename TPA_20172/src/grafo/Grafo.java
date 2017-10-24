package grafo;

import grafo.Aresta;
import grafo.Vertice;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public abstract class Grafo {
	protected int qtdVertices = 0;
	protected int qtdArestas = 0;
	
	public int numVertices() {
		return qtdVertices;
	}

	public int numArestas() {
		return qtdArestas;
	}
	
	public abstract Vertice insereVertice(String label,String dado);
	
	public abstract void insereAresta(String v1, String v2, String label, String dado);
	
	public abstract void insereAresta(Vertice u, Vertice v,Object dado);
	
	public abstract LinkedList<Aresta> getArestasVertice(String vertice);
	
	public abstract int grauVertice(String vertice);
	
	public abstract Vertice oposite(String vertice, String aresta);
	
	public abstract LinkedList<Vertice> vertices();
	public abstract LinkedList<Aresta> arestas();
	
	public void carregaMMF(String nomeArq) throws IOException{
		try {
			 FileReader arq = new FileReader(nomeArq);
			 BufferedReader lerArq = new BufferedReader(arq);
			 
			 String linha = lerArq.readLine();
			 while(linha.contains("%") && linha != null){
				 linha = lerArq.readLine();
			 }	 
			
			 //String[] dimensoes = linha.split("\\s");
			// qtdVertices = Integer.parseInt(dimensoes[0]);
			 //qtdVertices = Integer.parseInt(dimensoes[1]);
			 
			 linha = lerArq.readLine();
			 while(linha != null){
				 String[] linhaConteudo = linha.split("\\s");
				 String u = Integer.toString((Integer.parseInt(linhaConteudo[0]) - 1));
				 String v = Integer.toString((Integer.parseInt(linhaConteudo[1]) - 1));
				 String a =  linhaConteudo[2];
				 insereVertice(u, "");
				 insereVertice(v, "");
				 if(!u.equals(v)){
					 insereAresta(u, v, a ,"");
				 }
				 //this.setElem(Integer.parseInt(linhaConteudo[0]) - 1, Integer.parseInt(linhaConteudo[1]) - 1, Float.parseFloat(linhaConteudo[2]));
				 linha = lerArq.readLine();
			 }	 
			 lerArq.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	
	
}
