package hash;

import java.io.IOException;
import java.util.LinkedList;

public class TADChain_ extends TADHash {

    private LinkedList<ItemDic>[] conteudo;
    private int tamElementos;

    public TADChain_() {
        hashEngine = new HashEngineDefault();
        conteudo = new LinkedList[64];
        N = 64;
    }

    public TADChain_(HashEngine he) {
        hashEngine = he;
        conteudo = new LinkedList[64];
        N = 64;
    }

    public TADChain_(HashEngine he, int tam) {
        hashEngine = he;
        conteudo = new LinkedList[tam];
        N = tam;
    }

    public Object findElement(Object k) {
        int i = hashEngine.hashCode(k);
        int pos = i % N;
        //  LinkedList<ItemDic> l = (LinkedList<ItemDic>) conteudo[i];
        LinkedList<ItemDic> l = conteudo[pos];
        if (l == null) {
            return NO_SUCH_KEY;
        } else {

            for (ItemDic itemTabHash : l) {
                if (itemTabHash.getKey().equals(k)) {
                    return itemTabHash.getElement();
                }
            }
            return NO_SUCH_KEY;
        }
    }

    public boolean insertItem(Object k, Object o) {
        int i = hashEngine.hashCode(k);
        int pos = i % N;
        LinkedList<ItemDic> l = conteudo[pos];
        if (l == null) {
            if (l == null || l.isEmpty()) {
                conteudo[i] = new LinkedList<ItemDic>();
                this.tamanho++;
            }

            ((LinkedList) conteudo[i]).add(new ItemDic(k, o));

            this.tamElementos++;
            return true;

        } else {//caso alteração

            for (ItemDic itemTabHash : l) {
                if (itemTabHash.getKey().equals(k)) {
                    itemTabHash.setElement(o);
                    return true;
                }
            }
            return false;
        }
    }

    public Object removeElement(Object k) {
        int i = hashEngine.hashCode(k);
        int pos = i % N;
        LinkedList<ItemDic> l = conteudo[pos];
        if (l == null) {
            return false;
        } else {

            for (int j = 0; j < l.size(); j++) {
                ItemDic itemTabHash = l.get(j);
                if (itemTabHash.getKey().equals(k)) {
                    ((LinkedList<ItemDic>) conteudo[pos]).remove(j);
                    return itemTabHash;
                }
            }

            return NO_SUCH_KEY;
        }
    }

    public LinkedList<Object> keys() {
        LinkedList vet = new LinkedList();

        for (LinkedList<ItemDic> vetBucket : this.conteudo) {
            if (vetBucket != null) {
                for (ItemDic itemTabHash : vetBucket) {
                    vet.add(itemTabHash.getKey());
                }
            }
        }

        return vet;
    }

    public LinkedList<Object> elements() {
        LinkedList vet = new LinkedList();

        for (LinkedList<ItemDic> vetBucket : this.conteudo) {
            if (vetBucket != null) {
                for (ItemDic itemTabHash : vetBucket) {
                    vet.add(itemTabHash.getElement());
                }
            }
        }

        return vet;
    }

    public void redimensionar() {
        int novoTamanho = (int)(this.N*1.5);
        //copia para vetor auxiliar
        LinkedList<ItemDic>[] conteudoAux = new LinkedList[novoTamanho];
        for (int i = 0; i < this.conteudo.length; i++) {
            conteudoAux[i] = this.conteudo[i];
        }

        this.N = novoTamanho;
        this.conteudo = new LinkedList[this.N];
        this.tamanho = 0;
        this.tamElementos = 0;

        //recalcula os hash
        for (LinkedList<ItemDic> linkedList : conteudoAux) {
            if (linkedList != null) {
                for (ItemDic itemTabHash : linkedList) {
                    insertItem(itemTabHash.getKey(), itemTabHash.getElement());
                }
            }
        }

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
