package aplicacoes;

import java.util.LinkedList;
import grafo.*;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class AppTestaGrafoND {

    

    public static void main(String[] args) {
        //TADGrafoMadjND gnd = new TADGrafoMadjND();
        GrafoNDLad gnd = new GrafoNDLad();

        // Povoando o grafo gnd.
        Vertice v = gnd.insereVertice(null);
        System.out.println(v);
        v.setLabel("V");

        Vertice u = gnd.insereVertice(null);
        u.setLabel("U");

        Vertice x = gnd.insereVertice(null);
        x.setLabel("X");

        Vertice z = gnd.insereVertice(null);
        z.setLabel("Z");

        Vertice w = gnd.insereVertice(null);
        w.setLabel("W");

        Vertice y = gnd.insereVertice(null);
        y.setLabel("Y");

        Aresta a = gnd.insereAresta(v, u, null);
        a.setLabel("a");

        Aresta b = gnd.insereAresta(v, x, null);
        b.setLabel("b");

        Aresta c = gnd.insereAresta(u, w, null);
        c.setLabel("c");

        Aresta d = gnd.insereAresta(v, w, null);
        d.setLabel("d");

        Aresta e = gnd.insereAresta(x, w, null);
        e.setLabel("e");

        Aresta f = gnd.insereAresta(w, y, null);
        f.setLabel("f");

        Aresta g = gnd.insereAresta(x, y, null);
        g.setLabel("g");

        Aresta h = gnd.insereAresta(x, z, null);
        h.setLabel("h");

        //gnd.printmatriz();
        System.out.println();

        // Testando interface do grafo
        LinkedList<Vertice> lvs = gnd.vertices();
        LinkedList<Vertice> ladjs = gnd.vertices();
        int i, j;

        System.out.println("Adjacentes dos vértices:");
        for (i = 0; i < lvs.size(); i++) {
            ladjs = gnd.verticesAdjacentes(lvs.get(i));
            System.out.println("Adjacentes de " + lvs.get(i).getLabel() + ": ");

            for (j = 0; j < ladjs.size(); j++) {
                System.out.print(ladjs.get(j).getLabel() + ", ");
            }
            System.out.println("");
            //System.out.println(ladjs.get(j).getLabel());
        } // for(i..

        System.out.println();

        System.out.println("Graus dos vértices:");
        for (i = 0; i < lvs.size(); i++) {
            System.out.println("Vértice " + lvs.get(i).getLabel() + " grau " + gnd.grauVertice(lvs.get(i)));
        }

        gnd.areAdjacent(v, u);
        gnd.areAdjacent(x, z);
        gnd.areAdjacent(w, y);
        gnd.areAdjacent(v, z);
        gnd.areAdjacent(w, z);
        gnd.areAdjacent(u, y);

        System.out.println();

        System.out.println("Total de vertices: " + gnd.numVertices());
        System.out.println("Total de arestas: " + gnd.numArestas());

        // Construa a classe TPA2GS (Grafo TPA para grafo GraphStream). Nesta classe
        // construa o método exibeGrafo(grafo TPA). O método exibe o visual do grafo TPA
        // passado como parâmetro.
        //TPA2GS.exibeGrafo(gnd);
        System.out.println();

        System.out.println("Removendo todos os vértices:");
        for (i = 0; i < lvs.size(); i++) {
            gnd.removeVertice(lvs.get(i));
        }

        System.out.println();

        System.out.println("Total de vertices: " + gnd.numVertices());
        System.out.println("Total de arestas: " + gnd.numArestas());

        System.out.println();
        
        /*
        TPA2GS tpa = new TPA2GS();
        tpa.exibeGrafo(gnd);
        */
        PlotarGrafico.plotarGrafo(gnd, true); //pg = new PlotarGrafico();
        
        
        AlgoGrafo algo = new AlgoGrafo();
        algo.imprimeCaminho(algo.BFS(gnd, u));
        algo.imprimeCaminho(algo.DFS(gnd, u));
    } // fim main

} // fim de AppTestaGrafoND
