package grafo;

import grafo.Aresta;
import grafo.Vertice;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

    //insercao
    public abstract Vertice insereVertice(Object dado);

    public abstract Vertice insereVertice(String label, String dado);

    public abstract Aresta insereAresta(String v1, String v2, String label, Object dado);

    public abstract Aresta insereAresta(Vertice u, Vertice v, Object dado);

    //busca
    public abstract LinkedList<Aresta> getArestasVertice(String vertice);

    public abstract LinkedList<Vertice> getVerticesAresta(int id);

    public abstract Aresta getAresta(String vertU, String vertV);

    public abstract LinkedList<Vertice> endVertices(Aresta a);

    public abstract int grauVertice(Vertice vertice);

    public abstract Vertice oposite(Vertice vertice, Aresta aresta);

    public abstract LinkedList<Vertice> vertices();

    public abstract LinkedList<Vertice> verticesAdjacentes(Vertice v);

    public abstract LinkedList<Aresta> arestas();

    public abstract boolean areAdjacent(Vertice v, Vertice w);

    //remocao
    public abstract Object removeVertice(Vertice v);

    public abstract Object removeAresta(Aresta a);

    //manipulador de arquivo
    public void carregaMMF(String nomeArq) throws IOException {
        try {
            FileReader arq = new FileReader(nomeArq);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            while (linha.contains("%") && linha != null) {
                linha = lerArq.readLine();
            }

            linha = lerArq.readLine();
            while (linha != null) {
                String[] linhaConteudo = linha.split("\\s");
                String u = Integer.toString((Integer.parseInt(linhaConteudo[0]) - 1));
                String v = Integer.toString((Integer.parseInt(linhaConteudo[1]) - 1));
                String a = linhaConteudo[2];
                insereVertice(u, "");
                insereVertice(v, "");
                if (!u.equals(v)) {
                    insereAresta(u, v, a, "");
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

    public void carrega(String nomeArquivo) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), "ISO-8859-1"));

            while (br.ready()) {
                String[] array = br.readLine().split(" ", 2);
                if (array[0].equals("#")) {
                    break;
                }
                Vertice v = insereVertice(null);
                v.setLabel(array[1]);

            }
            LinkedList<Vertice> lista = vertices();
            while (br.ready()) {
                String[] array = br.readLine().split(" ", 3);
//                System.out.println(array[0]+" == "+array[1]);
                Vertice v = getVerticeLista(lista, Integer.parseInt(array[0]));
//                
                Vertice w = getVerticeLista(lista, Integer.parseInt(array[1]));
                Aresta a = insereAresta(v, w, null);
                a.setLabel("");
//
                if (array.length == 3) {
                    a.setLabel(array[2]);
                }

            }

            br.close();
        } catch (IOException ex) {
            System.out.println("erro ao carregar o arquivo");;
        }
    }

    private Vertice getVerticeLista(LinkedList<Vertice> lista, int id) {
        for (Vertice vertice : lista) {
            if (vertice.getId() == id) {
                return vertice;
            }
        }
        return null;
    }

    public void salva(String nomeArquivo) {
        try {
            FileWriter fileWriter = new FileWriter(nomeArquivo); //arquivo para escrita
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            LinkedList<Vertice> vertices = this.vertices();
            for (Vertice vertice : vertices) {
                bufferedWriter.write(vertice.getId() + " " + vertice.getLabel() + "\n");
            }

            bufferedWriter.write("#\n");

            LinkedList<Aresta> arestas = this.arestas();
            for (Aresta aresta : arestas) {
                LinkedList<Vertice> listaVertice = this.endVertices(aresta);
                Vertice v = listaVertice.get(0);
                Vertice w = listaVertice.get(1);
                bufferedWriter.write(v.getId() + " " + w.getId() + " " + aresta.getLabel() + "\n");

            }
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("erro ao escrever/salvar o arquivo");
        }

    }
}
