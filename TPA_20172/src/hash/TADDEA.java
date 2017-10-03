
package hash;

import java.io.IOException;
import java.util.LinkedList;

public class TADDEA extends TADHash{
    
    private ItemDic[] vetBuckets;
    
    public TADDEA() {
        vetBuckets = new ItemDic[64];
        N = 64;
        hashEngine = new HashEngineDefault();
    }
    
    public TADDEA(HashEngine he) {
        vetBuckets = new ItemDic[64];
        N = 64;
        hashEngine = he;
    }

    public TADDEA(int tam,HashEngine he) {
        vetBuckets = new ItemDic[tam];
        N = tam;
        hashEngine = he;
    }
    
    @Override
    public Object findElement(Object k){
        int i = hashEngine.hashCode(k);
        int p = 0;
        do {            
            ItemDic c = vetBuckets[i];
            if (c==null) {
                return NO_SUCH_KEY;
            } else {
                if (c.getKey()==k) {
                    return c.getElement();
                } else {
                    i = (i+1)%N;
                    p++;
                }
            }
        } while (p!=N);
        return NO_SUCH_KEY;
    }
    @Override
    public boolean insertItem(Object k, Object o){
        int i = hashEngine.hashCode(k);
        int p = 0;
        do {            
            ItemDic item = vetBuckets[i];
            if (item ==null) {
                vetBuckets[i] = new ItemDic(k, o);
                return true;
            } else {
                i = (i+1)%N;
                p++;
            }
        } while (p!=N);
        return false;
    }
    @Override
    public Object removeElement(Object k){
        int i = hashEngine.hashCode(k);
        int p = 0;
        do {            
            ItemDic c = vetBuckets[i];
            if (c==null) {
                return NO_SUCH_KEY;
            } else {
                if (c.getKey()==k) {
                    vetBuckets[i]= null;
                    return c.getElement();
                } else {
                    i = (i+1)%N;
                    p++;
                }
            }
        } while (p!=N);
        return NO_SUCH_KEY;
    }

    
    @Override
    public LinkedList<Object> keys() {
        LinkedList lista = new LinkedList();
        for (ItemDic vetBucket : vetBuckets) {
            if (vetBucket!=null) {
                lista.add(vetBucket.getKey());
             }
        }
        return lista;
    }

    @Override
    public LinkedList<Object> elements() {
        LinkedList lista = new LinkedList();
        for (ItemDic vetBucket : vetBuckets) {
            if (vetBucket!=null) {
                lista.add(vetBucket.getElement());
             }
        }
        return lista;
    }

    @Override
    public void salvaColisoes() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printConteudo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
