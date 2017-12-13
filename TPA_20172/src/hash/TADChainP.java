package hash;

import java.util.LinkedList;

public class TADChainP {

    public static final ItemDic NO_SUCH_KEY = new ItemDic(null, null);
    private LinkedList<ItemDic>[] conteudos;
    private HashEngine hengine = null;
    private int N;
    private int quant = 0;

    public TADChainP(int n, HashEngine paramHengine) {
        // Fator de carga de 0,85 para tabelas de hashing com encadeamento. Livro texto página 327.
        N = (int) (n / 0.85);

        // As listas ainda não foram criadas.
        conteudos = (LinkedList<ItemDic>[]) new LinkedList[N];

        hengine = paramHengine;
    }

    public TADChainP(HashEngine paramHengine) {
        // Fator de carga de 0,85 para tabelas de hashing com encadeamento. Livro texto página 327.
        N = 2000;

        // As listas ainda não foram criadas.
        conteudos = (LinkedList<ItemDic>[]) new LinkedList[N];

        hengine = paramHengine;
    }

    public TADChainP() {
        // Fator de carga de 0,85 para tabelas de hashing com encadeamento. Livro texto página 327.
        N = 2000;

        // As listas ainda não foram criadas.
        conteudos = (LinkedList<ItemDic>[]) new LinkedList[N];

        hengine = new HashEngineDefault();
    }

    // IMPLEMENTE ESTE MÉTODO PARA A PROVA 1 TPA 2017-2
    public boolean insertItem(Object k, Object o) {
        int h = this.hengine.hashCode(k);
        int i = h % this.N;

        LinkedList<ItemDic> l = conteudos[i];
        if (l == null) {
            conteudos[i] = new LinkedList<ItemDic>();
            this.quant++;
        }

        for (ItemDic item : conteudos[i]) {
            if (item.getKey().equals(k)) {
                item.setElement(o);
                return true;
            }
        }

        ((LinkedList) conteudos[i]).add(new ItemDic(k, o, h));
        this.quant++;
        return true;
    } // fim insertItem	

    public Object findElement(Object k) {
        int pos = hengine.hashCode(k) % N;

        if (conteudos[pos] == null) {
            return NO_SUCH_KEY;
        }

        int i = 0;
        boolean achou = false;
        ItemDic aux = null;

        while (i < conteudos[pos].size() && !achou) {
            aux = conteudos[pos].get(i);
            achou = (aux.getKey()).equals(k);
            i = i + 1;
        }

        if (achou) {
            return aux.getElement();
        } else {
            return NO_SUCH_KEY;
        }
    } // fim findElement

    public Object removeElement(Object k) {
        int pos = hengine.hashCode(k) % N;

        if (conteudos[pos] == null) {
            return NO_SUCH_KEY;
        } else {
            int i = 0;
            boolean achou = false;
            ItemDic aux = null;

            while (i < conteudos[pos].size() && !achou) {
                aux = conteudos[pos].get(i);
                achou = (aux.getKey()).equals(k);
                i = i + 1;
            }

            if (achou) {
                Object dado = aux.getElement();
                conteudos[pos].remove(i - 1);
                quant = quant - 1;
                return dado;
            } else {
                return NO_SUCH_KEY;
            }
        }
    } // fim removeElement

    public boolean isEmpty() {
        return quant == 0;
    } // fim isEmpty

    public int size() {
        return quant;
    } // fim size

    public LinkedList<Object> keys() {
        LinkedList<Object> lstKeys = new LinkedList<Object>();

        int i = 0;
        ItemDic aux = null;

        while (i < conteudos.length) {
            if (conteudos[i] != null) {
                int k = 0;

                while (k < conteudos[i].size()) {
                    aux = conteudos[i].get(k);
                    lstKeys.add(aux.getKey());
                    k = k + 1;
                }
            }

            i = i + 1;
        } // while..

        return lstKeys;
    } // fim de keys

    public LinkedList<Object> elements() {
        LinkedList<Object> lstElements = new LinkedList<Object>();

        int i = 0;
        ItemDic aux = null;

        while (i < conteudos.length) {
            if (conteudos[i] != null) {
                int k = 0;

                while (k < conteudos[i].size()) {
                    aux = conteudos[i].get(k);
                    lstElements.add(aux.getElement());
                    k = k + 1;
                }
            }

            i = i + 1;
        } // while..

        return lstElements;
    } // fim elements

} // fim Class TabHChain
