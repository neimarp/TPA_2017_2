package grafo;

import java.util.LinkedList;

public class GrafoNDLad extends Grafo {

    LinkedList<ArestaLad> listaArestas;
    LinkedList<VerticeLad> listaVertices;
    int idAresta = 0;

    public GrafoNDLad() {
        listaArestas = new LinkedList<>();
        listaVertices = new LinkedList<>();
    }

    @Override
    public Vertice insereVertice(String label, String dado) {
        for (Vertice vert : listaVertices) {
            if (vert.getLabel().equals(label)) {
                return vert;
            }
        }
        qtdVertices++;
        Vertice obj = new VerticeLad(qtdVertices, label);
        listaVertices.add((VerticeLad) obj);
        
        return obj;
    }

    public Vertice insereVertice(Object dado) {
        /*
        for (Vertice vert : listaVertices) {
            if (vert.getElem()== dado) {
                return vert;
            }
        }
        */
        qtdVertices++;
        VerticeLad vlad = new VerticeLad(qtdVertices, "",dado);
        listaVertices.add(vlad);
        
        return vlad;
    }

    @Override
    public Aresta insereAresta(Vertice u, Vertice v, Object dado) {
        qtdArestas++;
        Aresta a = new ArestaLad((VerticeLad)u, (VerticeLad)v, qtdArestas, "", dado);
        ((VerticeLad)u ).addArestaOut((ArestaLad) a);
        ((VerticeLad)v).addArestaIn((ArestaLad) a);
        listaArestas.add((ArestaLad) a);
        return a;
    }

    @Override
    public LinkedList<Aresta> getArestasVertice(String vertice) {
        LinkedList<Aresta> listaArestas = null;
        for (VerticeLad v : listaVertices) {
            if (v.getLabel().equals(vertice)) {
                for (ArestaLad arestaLad : v.getArestaIn()) {
                    listaArestas.add(arestaLad);
                }
                //listaArestas = v.getArestaIn();
                break;
            }
        }

        return listaArestas;
    }

    @Override
    public int grauVertice(Vertice v) {
        int grau = ((VerticeLad)v).grauTotal();
        return grau;
    }

    @Override
    public LinkedList<Vertice> vertices() {
        LinkedList<Vertice> lista = new LinkedList<>();
        for (VerticeLad vertice : listaVertices) {
            lista.add(vertice);
        }
        return lista;
    }

    @Override
    public LinkedList<Aresta> arestas() {
        LinkedList<Aresta> lista = new LinkedList<>();
        for (ArestaLad aresta : listaArestas) {
            lista.add(aresta);
        }
        return lista;
    }

    @Override
    public Aresta getAresta(String vertU, String vertV) {

        boolean achou = false;
        Aresta aresta = null;
        int i = 0;
        while (i < listaArestas.size() && !achou) {
           ArestaLad obj = listaArestas.get(i);
            if (obj.getVertU().getLabel().equals(vertU) && obj.getVertV().getLabel().equals(vertV)
                    || obj.getVertU().getLabel().equals(vertV) && obj.getVertV().getLabel().equals(vertU)) {
                achou = true;
                aresta = obj;
                break;
            }
            i++;
        }

        return aresta;
    }
    
    @Override
    public LinkedList<Vertice> endVertices(Aresta a) {
        return ((ArestaLad) a).vertices();
    }
    
    @Override
    public LinkedList<Vertice> getVerticesAresta(int id) {
        int i = 0;
        boolean achou = false;
        LinkedList<Vertice> listaVertices = null;
        while (i < listaArestas.size() && !achou) {
            if (listaArestas.get(i).getId() == id) {
                ArestaLad a = listaArestas.get(i);
                listaVertices = new LinkedList<>();
                listaVertices.add(a.getVertU());
                listaVertices.add(a.getVertV());
                achou = true;
            }
            i++;
        }
        return listaVertices;

    }

    public Vertice getVertice(String label) {
        int i = 0;
        boolean achou = false;
        Vertice v = null;
        while (i < listaVertices.size() && !achou) {
            if (listaVertices.get(i).getLabel().equals(label)) {
                achou = true;
                v = listaVertices.get(i);
            }
            i++;
        }

        return v;

    }

    @Override
    public LinkedList<Vertice> verticesAdjacentes(Vertice v) {
        LinkedList<Vertice> verticesAdj = new LinkedList<>();

        VerticeLad vert = (VerticeLad)v;
        
        for (Aresta aresta : vert.getArestaOut()) {
            Vertice vertOposto = oposite(v, aresta);
            if (vertOposto != null) {
                verticesAdj.add(vertOposto);
            }
        }
        for (Aresta aresta : vert.getArestaIn()) {
            Vertice vertOposto = oposite(v, aresta);
            if (vertOposto != null) {
                verticesAdj.add(vertOposto);
            }
        }

        return verticesAdj;
    }

    @Override
    public Vertice oposite(Vertice v, Aresta a) {
        LinkedList<Vertice> lista = endVertices(a);
        for (Vertice vertice : lista) {
            if (vertice.getId() != v.getId()) {
                return vertice;
            }
        }
        return null;
    }

    @Override
    public Aresta insereAresta(String v1, String v2, String label, Object dado) {
        int i = 0;
        VerticeLad u = null;
        VerticeLad v = null;
        ArestaLad a = null;
        while (i < listaVertices.size()) {
            if (listaVertices.get(i).getId() == (Integer.parseInt(v1))) {
                u = listaVertices.get(i);
            }
            if (listaVertices.get(i).getId() == (Integer.parseInt(v2))) {
                v = listaVertices.get(i);
            }
            if (u != null && v != null) {
                break;
            }
            i++;
        }

        if (u != null && v != null) {

            idAresta++;
            a = new ArestaLad(u, v, idAresta, label, dado);
            listaArestas.add(a);
            u.addArestaIn(a);
            u.addArestaOut(a);
            v.addArestaIn(a);
            v.addArestaOut(a);

        }
        return a;
    }
    
    @Override
    public boolean areAdjacent(Vertice v, Vertice w) {
        LinkedList<Vertice> lista = verticesAdjacentes(v); 
        for (Vertice vertice : lista) {
            if (vertice.getId() == w.getId()){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Object removeVertice(Vertice v) {
        qtdVertices--;
        //Object obj = v.getElem();

        LinkedList<ArestaLad> listaIn = ((VerticeLad) v).getArestaIn();
        LinkedList<ArestaLad> listaOut = ((VerticeLad) v).getArestaOut();
        //System.out.println("tam in e out: "+listaIn.size()+" "+listaOut.size());
        for (Aresta aresta : listaIn) {
            //System.out.println("in");
            removeAresta(aresta);
        }
        /*
        for (Aresta aresta : listaOut) {
            System.out.println("out");
            removeAresta(aresta);
        }
        */
        listaVertices.remove(v);
        return v;
    }
    
    @Override
    public Object removeAresta(Aresta a) {
        //Object obj = a.getElem();
        qtdArestas--;
        listaArestas.remove(a);
        
        return a;
    }

    
}
