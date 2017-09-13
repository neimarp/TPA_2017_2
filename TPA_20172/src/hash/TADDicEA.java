
package hash;

public class TADDicEA {
    public static ItemDic NO_SUCH_KEY = new ItemDic(null, null);
    private ItemDic[] vetBuckets;
    private int tam;
    private int N;
    private HashEngine hashEngine;
    public TADDicEA(HashEngine he) {
        vetBuckets = new ItemDic[64];
        tam = 0;
        N = 64;
        hashEngine = he;
    }

    public TADDicEA(int tam,HashEngine he) {
        vetBuckets = new ItemDic[tam];
        tam = 0;
        N = tam;
        hashEngine = he;
    }
    
    
    public Object findElement(Object k){
        int i = hashEngine.h(k);
        int p = 0;
        do {            
            ItemDic c = vetBuckets[i];
            if (c==null) {
                return NO_SUCH_KEY;
            } else {
                if (c.getK()==k) {
                    return c.getElement();
                } else {
                    i = (i+1)%N;
                    p++;
                }
            }
        } while (p!=N);
        return null;
    }
    public Object insertItem(Object k, Object o){return null;}
    public Object removeElement(Object k){return null;}
    public int size(){return tam;}
    public boolean isEmpty(){return false;}
    public Object keys(){return null;}
    public Object elements(){return null;}
    
}
