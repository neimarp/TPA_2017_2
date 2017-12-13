/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.LinkedList;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class TPA2GS {

        private static String style = ""
        + "node {"
            +"shape: box;"
            +"size: 10px, 10px;"
            +"fill-mode: plain;   "
            +"fill-color: blue;    "
            +"stroke-mode: plain; "
            +"stroke-color: red; "
           
        +"} "
        + "edge {"
        + " "
        + "}";
        private Graph convert(Grafo g) {

            Graph graph = new SingleGraph("");
            graph.addAttribute("ui.stylesheet", style);

            LinkedList<Vertice> listaVertices = g.vertices();
            System.out.println("Tamanho lista vertice "+listaVertices.size());
            for (Vertice v : listaVertices) {
                System.out.println(v);
                Node n = graph.addNode(v.getLabel());
                n.addAttribute("label", v.getLabel());
            }

            int i = 0;
            LinkedList<Aresta> listaArestas = g.arestas();
            System.out.println("Tamanho lista aresta "+listaArestas.size());
            
            for (Aresta aresta : listaArestas) {
                LinkedList<Vertice> lista = g.endVertices(aresta);
                Vertice v = lista.get(0);
                Vertice w = lista.get(1);
                String label = aresta.getLabel();
                if (label.trim().equals("")) {
                    label = v.getLabel() + "" + w.getLabel() + " " + i;
                    i++;
                }
                Edge e = graph.addEdge(label, v.getLabel(), w.getLabel());//graph.addEdge(label, (Node)v, (Node)w);
                e.addAttribute("label", label);
              
            }

            return graph;
        }

        //public void exibeGrafo(GrafoNDLad gnd){}
        public void exibeGrafo(Grafo g) {
            Graph graph = convert(g);
            graph.display();
        }
    }
