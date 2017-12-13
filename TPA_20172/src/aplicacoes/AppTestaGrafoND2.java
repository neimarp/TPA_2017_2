package aplicacoes;

import java.util.LinkedList;

import grafo.*;
import hash.TADDEA;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppTestaGrafoND2 {

    public static void main(String[] args) {
        GrafoNDMat gndMat = new GrafoNDMat();
        GrafoNDLad gndLad = new GrafoNDLad();

        // ****************************************************
        // *  FAZ A CLONAGEM DO GRAFO gndLad NO GRAFO gndMat. *
        // *                                                  *
        // *  AO FINAL, gndMat É UM CLONE DE gndLad,          *
        // ****************************************************
        // O conteúdo do grafo original, gndLad, é lido do arquivo meses.txt.
        // O conteúdo do grafo clone, gndMat, é salvo em meses2.txt.
        //String cwd = System.getProperty("user.dir");
        String grafoIN = "meses.txt";
        String grafoOUT = "meses2.txt";

        try {
            // Lê o grafo original do disco e o exibe via GraphStream.
            gndLad.carrega(grafoIN);
        } catch (IOException ex) {
            Logger.getLogger(AppTestaGrafoND2.class.getName()).log(Level.SEVERE, null, ex);
        }
        TPA2GS tpa = new TPA2GS();
        tpa.exibeGrafo(gndLad);
        // Se for o caso, substitua a linha acima pela sua classe de exibição GraphStream.

        // Cria um dicionário de vértices com o label sendo a chave do dicionário.
        // SUbstitua esta linha e as linhas de utilização do dicionário pelas suas 
        // classes e chamadas equivalentes.
        TADDEA dicVclonados = new TADDEA();
        LinkedList<Vertice> lst_vs_glad = gndLad.vertices();
        LinkedList<Aresta> lst_es_glad = gndLad.arestas();

        // Monitora a quantidade de vértices do grafo clone, antes e depois da clonagem.
        LinkedList<Vertice> lst_vs_gmat = gndMat.vertices();
        System.out.println("ANTES DA CLONAGEM:");
        System.out.println("gndLad possui " + lst_vs_glad.size() + " vértices.");
        System.out.println("gndMat possui " + lst_vs_gmat.size() + " vértices.");

        int i = 0;

        // Variáveis de trabalho do processo de clonagem.
        Vertice v, w, bkpV;
        Object dado;
        Aresta e;

        // Vértices terminais da aresta corrente sendo processada.
        LinkedList<Vertice> lst_end_vertices_gnLad;

        // Enquanto não clonar todas as arestas do grafo original, faça:
        while (i < lst_es_glad.size()) {
            // Leia a i-ésima aresta do grafo oriignal e obtenha os seus endVertices.
            lst_end_vertices_gnLad = gndLad.endVertices(lst_es_glad.get(i));

            // Faça um bkp do primeiro end vertice da aresta.
            bkpV = lst_end_vertices_gnLad.get(0);

            // Se este vértice ainda não foi clonado então cloná-lo: criar um novo vértice,
            // copiar os campos dado e label, inserí-lo no grafo clone (gndMat).
            if (dicVclonados.findElement(bkpV.getLabel()).equals(TADDEA.NO_SUCH_KEY)) {
                dado = bkpV.getElem();
                v = gndMat.insereVertice(dado);
                v.setLabel(bkpV.getLabel());
                dicVclonados.insertItem(v.getLabel(), v);
            } else // Se o vértice já foi clonado, resgatá-lo do dicionário de clonados para posterior
            // clonagem da aresta (porque a clonagem da aresta precisa de 2 end vértices, linha 96).
            {
                v = (Vertice) dicVclonados.findElement(bkpV.getLabel());
            }

            // Faça um bkp do segundo end vertice da aresta.
            bkpV = lst_end_vertices_gnLad.get(1);

            // Se este vértice ainda não foi clonado então cloná-lo: criar um novo vértice,
            // copiar os campos dado e label, inserí-lo no grafo clone (gndMat).
            if (dicVclonados.findElement(bkpV.getLabel()).equals(TADDEA.NO_SUCH_KEY)) {
                dado = bkpV.getElem();
                w = gndMat.insereVertice(dado);
                w.setLabel(bkpV.getLabel());
                dicVclonados.insertItem(w.getLabel(), w);
            } else // Se o vértice já foi clonado, resgatá-lo do dicionário de clonados para posterior
            // clonagem da aresta (porque a clonagem da aresta precisa de 2 end vértices, linha 96).
            {
                w = (Vertice) dicVclonados.findElement(bkpV.getLabel());
            }

            // FInalmente, faz a clonagem da aresta do grafo origem.
            Aresta bkpE = lst_es_glad.get(i);
            e = gndMat.insereAresta(v, w, bkpE.getElem());
            e.setLabel(bkpE.getLabel());

            // Próxima aresta do grafo origem a ser clonada.
            i++;
        } // while..	

        // Exibe os status dos grafos após a clonagem.
        lst_vs_gmat = gndMat.vertices();
        System.out.println("\nAPÓS A CLONAGEM:");
        System.out.println("gndLad possui " + lst_vs_glad.size() + " vértices.");
        System.out.println("gndMat possui " + lst_vs_gmat.size() + " vértices.");

        // Exibe o grafo clonado, gndMat.
        gndMat.salva(grafoOUT);
        TPA2GS tpa2gs = new TPA2GS();
        tpa2gs.exibeGrafo(gndMat);
    } // fim de main

} // fim de AppTestaGrafoND2
