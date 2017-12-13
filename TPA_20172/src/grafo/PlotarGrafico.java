package grafo;

import java.util.LinkedList;
import org.graphstream.graph.Edge;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

public class PlotarGrafico {

     private static String style = ""
        + "node {"
            +"shape: box;"
            +"size: 20px, 20px;"
            +"fill-mode: plain;   "
            +"fill-color: green;    "
            +"stroke-mode: plain; "
            +"stroke-color: blue; "
           
        +"} "
        + "edge {"
        + " "
        + "}";
     
    public static void plotarGrafo(Grafo g, boolean direcionado) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph graph = new SingleGraph("");//new MultiGraph("TPA", false, true);
       // graph.setAutoCreate(false);

        //inserindo os vertices no Grafo
        for (Vertice v : g.vertices()) {
            Node n = graph.addNode(v.getLabel());
            System.out.println(v.getLabel());
            n.addAttribute("label", v.getLabel());
        }
        
        int i = 0;
        LinkedList<Aresta> listaArestas = g.arestas();
        //Adcionando arestas
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
        

        graph.display();

    }
}
