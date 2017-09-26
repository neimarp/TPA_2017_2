package hash;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class TADChain extends TADHash {

    private LinkedList<ItemDic>[] conteudo;
    
    private int qtdPosOcupadas;

    public TADChain(HashEngine he) {
        hashEngine = he;
        conteudo = (LinkedList<ItemDic>[]) new LinkedList[64];
        N=64;
    }

    public TADChain(HashEngine he, int tam) {
        hashEngine = he;
        conteudo = (LinkedList<ItemDic>[]) new LinkedList[tam];
        N=tam;
    }

    @Override
    public void insertItem(Object key, Object elem) {

        Object element = findElem(key);

        if (element != null) {
            atualizaValorItem(key, elem);
        } else {

            int hash = hashEngine.hashCode(key);
            int pos = Math.abs((int) (hash % tamanho));
            if (conteudo[pos] == null) {
                conteudo[pos] = new LinkedList<Item>();
                qtdPosOcupadas++;
            }
            Item item = new Item(key, elem, hash);
            conteudo[pos].add(item);
            qtdItens++;

            // Verifica se o vetor atingiu 75% da taxa de ocupa��o
            // Aumenta em 50% o tamanho do vetor
            double taxaOcupacao = (((double) (qtdPosOcupadas) / (double) tamanho));
            if (taxaOcupacao >= 0.95) {
                //printConteudo();
                //System.out.println("");
                redimensiona();
            }
        }

    }

    protected void atualizaValorItem(Object chave, Object valor) {
        int hash = hashEngine.hashCode(chave);
        int pos = Math.abs((int) (hash % tamanho));

        LinkedList<ItemDic> listaElem = conteudo[pos];

        if (listaElem != null) {
            for (int i = 0; i < listaElem.size(); i++) {
                if (listaElem.get(i) != null) {
                    if (listaElem.get(i).getKey().equals(chave)) {
                        listaElem.get(i).setElem(valor);
                        break;
                    }
                }
            }
        }

    }

    @Override
    public Object findElement(Object key) {
        int hash = hashEngine.hashCode(key);
        int pos = Math.abs((int) (hash % tamanho));
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
            return NO_SUCH_KEY.getElement();
        } else {
            return item.getElement();
        }

    }

    @Override
    public Object removeElement(Object key) {
        int hash = hashEngine.hashCode(key);
        int pos = (int) Math.abs(hash % tamanho);

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
                qtdItens--;
                return dado;
            } else {
                return NO_SUCH_KEY;
            }
        }

    }

    protected void redimensiona() {

        int novoTamanho = (int) (tamanho * 1.5);
        LinkedList<ItemDic>[] conteudoAux = (LinkedList<Item>[]) new LinkedList[novoTamanho];
        int hash = 0;
        int novaPos = 0;
        qtdPosOcupadas = 0;
        for (int i = 0; i < tamanho; i++) {
            if (conteudo[i] != null) {
                LinkedList<Item> listaItem = conteudo[i];
                for (Item item : listaItem) {
                    hash = item.getCacheHCode();
                    novaPos = (int) (hash % novoTamanho);

                    if (conteudoAux[novaPos] == null) {
                        conteudoAux[novaPos] = new LinkedList<Item>();
                        qtdPosOcupadas++;

                    }
                    conteudoAux[novaPos].add(item);
                }
            }
        }

        conteudo = conteudoAux;
        tamanho = novoTamanho;
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
