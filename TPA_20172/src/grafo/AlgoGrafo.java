package grafo;

import java.util.LinkedList;
import java.util.Stack;

public class AlgoGrafo {

    //busca em profundidade
    public static LinkedList<Vertice> DFS(Grafo g, Vertice source) {

        LinkedList<Vertice> verticesVisitados = new LinkedList<>();

        //verticesVisitados.add(source);
        Stack<Vertice> pilhaVertices = new Stack<>();
        pilhaVertices.push(source);

        while (!pilhaVertices.isEmpty()) {
            Vertice v = pilhaVertices.pop();
            LinkedList<Vertice> verticesAdj = g.verticesAdjacentes(v);//lista de vertices adj a v

            if (!VerificarVerticesVisitados(verticesVisitados, v)) {
                verticesVisitados.add(v);
            }

            for (Vertice vertAdj : verticesAdj) {
                if (!VerificarVerticesVisitados(verticesVisitados, vertAdj)) {
                    //verticesVisitados.add(vertAdj);
                    pilhaVertices.push(vertAdj);
                }
            }
            //pilhaVertices.pop();
        }
        return verticesVisitados;

    }

    private static boolean VerificarVerticesVisitados(LinkedList<Vertice> lista, Vertice v) {
        boolean achou = false;
        int i = 0;
        while (i < lista.size() && !achou) {
            if (lista.get(i).getLabel().equals(v.getLabel())) {
                achou = true;
            }
            i++;
        }
        return achou;
    }

    //busca em largura
    public static LinkedList<Vertice> BFS(Grafo g, Vertice source) {

        LinkedList<Vertice> verticesVisitados = new LinkedList<>();
        verticesVisitados.add(source);
        LinkedList<Vertice> filaVertices = new LinkedList<>();
        filaVertices.add(source);

        while (!filaVertices.isEmpty()) {
            LinkedList<Vertice> verticesAdj = g.verticesAdjacentes(filaVertices.get(0));

            for (Vertice vertAdj : verticesAdj) {
                if (!VerificarVerticesVisitados(verticesVisitados, vertAdj)) {
                    verticesVisitados.add(vertAdj);
                    filaVertices.add(vertAdj);
                }
            }
            filaVertices.remove(0);
        }
        return verticesVisitados;

    }
    /*
    public LinkedList<Vertice> dijkstra(GrafoTPA g, String labelOrigem, String labelDestino) {

        LinkedList<Vertice> listaCaminho = new LinkedList<Vertice>();
        Vertice origem = getVerticeByLabel(g, labelOrigem);
        Vertice destino = getVerticeByLabel(g, labelDestino);

        if (origem != null && destino != null) {

            LinkedList<Vertice> listaCaminhoVisitado = new LinkedList<Vertice>();
            TabHEA listaTabelaCalculoVertice = new TabHEA();

            LinkedList<Vertice> listaVertices = g.vertices();
            //Inicializando a lista de Vertices
            listaTabelaCalculoVertice.insertItem(origem.getId(), new TabelaCalculoVertice(origem, 0, origem));//para ir para ele proprio custo 0
            for (Vertice v : listaVertices) {
                if (v.getId() != origem.getId()) {
                    listaTabelaCalculoVertice.insertItem(v.getId(), new TabelaCalculoVertice(v, Double.POSITIVE_INFINITY));//para ir para outros custo infinito
                }
            }

            while (listaCaminhoVisitado.size() < g.numVertices()) {

                TabelaCalculoVertice tcv = getVerticeMenorDistancia(listaTabelaCalculoVertice, listaCaminhoVisitado);
                Vertice vertice = tcv.vertice;
                listaCaminhoVisitado.add(vertice);

                LinkedList<Vertice> listaVerticesAdjacente = g.adjacenteVertices(vertice);
                for (Vertice verticeAdjacente : listaVerticesAdjacente) {

                    if (!checkVerticeListaVisitado(listaCaminhoVisitado, verticeAdjacente)) {

                        TabelaCalculoVertice tabelaCalculoVertice = (TabelaCalculoVertice) listaTabelaCalculoVertice.findElement(verticeAdjacente.getId());
                        Edge e = getEdgeVertices(g, vertice, tabelaCalculoVertice.vertice);

                        //relaxamento
                        double soma = tcv.estimativa + ((int) e.getDado());
                        if (soma < tabelaCalculoVertice.estimativa) {
                            tabelaCalculoVertice.estimativa = soma;
                            tabelaCalculoVertice.precedente = vertice;
                        }
                    }
                }
            }

            //print(listaTabelaCalculoVertice);
            //procura caminho, vai voltando do destino os procedente até achar o vertice inicial
            //lista do caminho será montada invertida
            LinkedList<TabelaCalculoVertice> listaCaminhoInvertido = new LinkedList<>();
            TabelaCalculoVertice tcv = (TabelaCalculoVertice) listaTabelaCalculoVertice.findElement(destino.getId());
            listaCaminhoInvertido.add(tcv);

            while (tcv.vertice.getId() != origem.getId()) {
                int precedente = tcv.precedente.getId();
                tcv = (TabelaCalculoVertice) listaTabelaCalculoVertice.findElement(precedente);
                listaCaminhoInvertido.add(tcv);
            }
            //Fim procura caminho

            //inversão do caminho
            for (int i = listaCaminhoInvertido.size() - 1; i >= 0; i--) {
                TabelaCalculoVertice v = (TabelaCalculoVertice) listaCaminhoInvertido.get(i);
                listaCaminho.add(v.vertice);
            }
        }

        return listaCaminho;
    }
    
     private TabelaCalculoVertice getVerticeMenorDistancia(TabHEA listaTabelaCalculoVertice, LinkedList<Vertice> listaCaminhoVisitado) {

        double menorDistancia = Double.POSITIVE_INFINITY;
        TabelaCalculoVertice tcvRetorno = null;

        LinkedList<Object> lista = listaTabelaCalculoVertice.elements();

        for (Object object : lista) {
            TabelaCalculoVertice tcv = ((TabelaCalculoVertice) object);
            if (!checkVerticeListaVisitado(listaCaminhoVisitado, tcv.vertice) && tcv.estimativa < menorDistancia) {//verifica q não for visitado e possuir menor estimativa
                tcvRetorno = tcv;
                menorDistancia = tcv.estimativa;
            }
        }
        return tcvRetorno;
    }
    */
    private boolean checkVerticeListaVisitado(LinkedList<Vertice> lista, Vertice v) {
        for (Vertice vertice : lista) {
            if (v.getId() == vertice.getId()) {
                return true;
            }
        }
        return false;
    }

    public static void imprimeCaminho(LinkedList<Vertice> caminho) {
        for (Vertice Vertice : caminho) {
            System.out.print(Vertice.getLabel() + " ");
        }
        System.out.println();
    }
}
