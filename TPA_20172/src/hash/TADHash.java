package hash;

import java.io.IOException;
import java.util.LinkedList;

public abstract class TADHash {
	protected int tamanho;
	protected int N;
	HashEngine hashEngine;
	/*
	public TADHash() {
		hashEngine = new HashEngine();
		tamanho = 1000;
		qtdItens = 0;
	}
	
	public TADHash(HashEngine he) {
		hashEngine = he;
		tamanho = 1000;
		qtdItens = 0;
	}
	
	public TADHash(HashEngine he, int n) {
		hashEngine = he;
		//tamanho = (int) (n / 0.4);
		tamanho = n;
		qtdItens = 0;
	}
	*/
	public abstract boolean insertItem(Object key, Object elem);
	
	public abstract Object findElement(Object key);
	
	public abstract Object removeElement(Object key);
	
	public abstract LinkedList<Object> keys();
	
	public abstract LinkedList<Object> elements();
	
	public abstract void salvaColisoes() throws IOException;
	
	public abstract void printConteudo();
	
	
	public int size(){
		return tamanho;
	}
	
	public boolean empty(){
		return tamanho <= 0;
	}
}
