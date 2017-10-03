package hash;

import java.io.IOException;
import java.util.LinkedList;

public abstract class TADHash {
        public static ItemDic NO_SUCH_KEY = new ItemDic(null, null);
	protected int tamanho = 0;
	protected int N;
	HashEngine hashEngine;
        
        
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
