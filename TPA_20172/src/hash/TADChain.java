package hash;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class TADChain extends TADHash {

    private LinkedList<ItemDic>[] conteudo;
    
    public TADChain() {
        hashEngine = new HashEngineDefault();
        conteudo = new LinkedList[2000];
        N = 2000;
    }
    
    public TADChain(HashEngine he) {
        hashEngine = he;
        conteudo = new LinkedList[64];
        N = 64;
    }

    public TADChain(HashEngine he, int tam) {
        hashEngine = he;
        conteudo = new LinkedList[tam];
        N = tam;
    }

    @Override
    public boolean insertItem(Object key, Object elem) {
        boolean teste = false;
        Object element = findElement(key);
        /*
        if (element != null) {
            teste = atualizaValorItem(key, elem);
        } else {*/

            int hash = hashEngine.hashCode(key);
            int pos = hash % N;//Math.abs((hash % N));
            if (conteudo[pos] == null) {
                conteudo[pos] = new LinkedList<ItemDic>();
                tamanho++;
            }
            ItemDic item = new ItemDic(key, elem);
            conteudo[pos].add(item);
            if (!conteudo[pos].isEmpty()) {
                teste = true;
            
            }
            // Verifica se o vetor atingiu 80% da taxa de ocupacao
            // Aumenta em 50% o tamanho do vetor
            double taxaOcupacao = (((double) (tamanho) / (double) N));
            if (taxaOcupacao >= 0.80) {
                redimensionar();
            }
        //}
        return teste;
    }

    protected boolean atualizaValorItem(Object chave, Object valor) {
        int hash = hashEngine.hashCode(chave);
        int pos = hash % N;

        LinkedList<ItemDic> listaElem = conteudo[pos];
        boolean teste = false;
        for (ItemDic itemDic : listaElem) {
            if (itemDic != null) {
                if (itemDic.getKey().equals(chave)) {
                    itemDic.setElement(valor);
                    teste = true;
                    break;
                }
            }
        }/*
        for (int i = 0; i < listaElem.size(); i++) {
            if (listaElem.get(i) != null) {
                if (listaElem.get(i).getKey().equals(chave)) {
                    listaElem.get(i).setElement(valor);
                    teste = true;
                    break;
                }
            }
        }*/
        return teste;
    }

    @Override
    public Object findElement(Object key) {
        int hash = hashEngine.hashCode(key);
        int pos = Math.abs((int) (hash % N));
        ItemDic item = null;
        LinkedList<ItemDic> listaElem = conteudo[pos];

        if (listaElem != null) {
            for (ItemDic itemTabHash : listaElem) {
                if (itemTabHash != null) {
                    if (itemTabHash.getKey().equals(key)) {
                        item = itemTabHash;
                        break;
                    }
                }
            }
        }
        if (item == null) {
            return NO_SUCH_KEY;//.getElement();
        } else {
            return item.getElement();
        }

    }

    @Override
    public Object removeElement(Object key) {
        int hash = hashEngine.hashCode(key);
        int pos = (int) Math.abs(hash % N);

        if (conteudo[pos] == null) {
            return NO_SUCH_KEY;
        } else {
            int i = 0;
            boolean achou = false;
            ItemDic aux = null;

            while (i < conteudo[pos].size() && !achou) {
                aux = conteudo[pos].get(i);
                achou = aux.getKey().equals(key);
                i++;
            }

            if (achou) {
                Object dado = aux.getElement();
                conteudo[pos].remove(i - 1);
                return dado;
            } else {
                return NO_SUCH_KEY;
            }
        }

    }

    protected void redimensionar() {

        int novoTamanho = (int) (N * 1.5);
        LinkedList<ItemDic>[] conteudoAux = new LinkedList[novoTamanho];
        int hash = 0;
        int novaPos = 0;
        int qtdPosOcupadas = 0;
        for (int i = 0; i < tamanho; i++) {
            if (conteudo[i] != null) {
                LinkedList<ItemDic> listaItem = conteudo[i];
                for (ItemDic item : listaItem) {
                    hash = hashEngine.hashCode(item.getKey());
                    novaPos = (int) (hash % novoTamanho);

                    if (conteudoAux[novaPos] == null) {
                        conteudoAux[novaPos] = new LinkedList<ItemDic>();
                        qtdPosOcupadas++;

                    }
                    conteudoAux[novaPos].add(item);
                }
            }
        }

        conteudo = conteudoAux;
        N = novoTamanho;
        //tamanho  = novoTamanho;
    }

    public void printConteudo() {

        for (int i = 0; i < tamanho; i++) {
            if (conteudo[i] != null) {
                //System.out.println(i);
                for (ItemDic itemTab : conteudo[i]) {
                    if (itemTab != null) {
                        System.out.println(itemTab.getKey() + " : " + itemTab.getElement().toString());
                    }
                }
            }
        }
    }

    @Override
    public LinkedList<Object> keys() {
        LinkedList<Object> listaKeys = new LinkedList<Object>();
        for (int i = 0; i < tamanho; i++) {
            if (conteudo[i] != null) {
                for (ItemDic itemTab : conteudo[i]) {
                    if (itemTab != null) {
                        listaKeys.add(itemTab.getKey());
                    }
                }
            }
        }
        return listaKeys;
    }

    @Override
    public LinkedList<Object> elements() {
        LinkedList<Object> listaElements = new LinkedList<Object>();
        for (int i = 0; i < tamanho; i++) {
            if (conteudo[i] != null) {
                for (ItemDic itemTab : conteudo[i]) {
                    if (itemTab != null) {
                        listaElements.add(itemTab.getElement());
                    }
                }
            }
        }
        return listaElements;
    }

    public void salvaColisoes() throws IOException {

        FileWriter arq = new FileWriter("Saida.csv");
        PrintWriter gravarArq = new PrintWriter(arq);

        for (int i = 0; i < tamanho; i++) {
            int qtdItemPos = 0;
            if (conteudo[i] != null) {
                qtdItemPos = conteudo[i].size();
            }
            gravarArq.printf("%d,%d%n", i, qtdItemPos);

        }
        arq.close();
    }

}
