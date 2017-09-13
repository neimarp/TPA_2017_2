package hash;

import java.io.IOException;
import java.util.LinkedList;

public abstract class TADTabH {
	protected int tamanho;
	protected int qtdItens;
	HashEngine hashEngine;
	
	public TADTabH() {
		hashEngine = new HashEngine();
		tamanho = 1000;
		qtdItens = 0;
	}
	
	public TADTabH(HashEngine he) {
		hashEngine = he;
		tamanho = 1000;
		qtdItens = 0;
	}
	
	public TADTabH(HashEngine he, int n) {
		hashEngine = he;
		//tamanho = (int) (n / 0.4);
		tamanho = n;
		qtdItens = 0;
	}
	
	public abstract void insertItem(Object key, Object elem);
	
	public abstract Object findElem(Object key);
	
	public abstract Object removeElem(Object key);
	
	public abstract LinkedList<Object> keys();
	
	public abstract LinkedList<Object> elements();
	
	public abstract void salvaColisoes() throws IOException;
	
	public abstract void printConteudo();
	
	
	public int size(){
		return tamanho;
	}
	
	public boolean empty(){
		return tamanho > 0 ? false : true;
	}
}
