package grafo;

import java.util.LinkedList;
import hash.TADChain;
import tadMath.TadMath;

public class GrafoNDMat extends Grafo {

    TadMath matriz;
    TADChain vertices;
    TADChain arestas;

    public GrafoNDMat() {
        matriz = new TadMath();
        vertices = new TADChain();
        arestas = new TADChain();
    }

    @Override
    public LinkedList<Vertice> vertices() {
        LinkedList<Object> lista = vertices.elements();
        LinkedList<Vertice> listaVertices = new LinkedList<>();

        for (Object object : lista) {
            listaVertices.add((Vertice) object);
        }

        return listaVertices;
    }

    @Override
    public LinkedList<Aresta> arestas() {
        LinkedList<Object> lista = arestas.elements();
        LinkedList<Aresta> listaArestas = new LinkedList<>();

        for (Object object : lista) {
            listaArestas.add((Aresta) object);
        }
        return listaArestas;
    }

    @Override
    public Vertice insereVertice(String label, String dado) {

        if (!verificaVerticeExiste(new Vertice(0, label, ""))) {
            Vertice novoVertice = new Vertice(matriz.qtdLinhas, label, dado);
            vertices.insertItem(matriz.qtdLinhas, novoVertice);
            matriz.qtdLinhas++;
            matriz.qtdColunas++;
            qtdVertices++;

            return novoVertice;
        }

        return null;

    }

    @Override
    public Aresta insereAresta(String v1, String v2, String label, Object dado) {
        Aresta novaAresta = null;
        // Obtendo o id dos dois vertices
        int idVert1 = getIdVertice(v1);
        int idVert2 = getIdVertice(v2);

        if (idVert1 == -1 || idVert2 == -1) {
            if (idVert1 == -1 && idVert2 != -1) {
                System.out.println("O vertice " + v1 + " nao existe");
            } else if ((idVert1 != -1 && idVert2 == -1)) {
                System.out.println("O vertice " + v2 + " nao existe");
            } else {
                System.out.println("Os vertices " + v1 + " e " + v2 + " nao existem");
            }
        } else {
            // Criando a chave da aresta
            int chave = ((idVert1 + 1) * 5) + ((idVert2 + 1) * 7);

            // Criando a nova Aresta
            novaAresta = new Aresta(chave, label, dado);
            arestas.insertItem(chave, novaAresta);

            // Inserir na matriz de Adjacencia
            matriz.addItemMatriz(idVert1, idVert2, chave);
            matriz.addItemMatriz(idVert2, idVert1, chave);

            qtdArestas++;
        }
        return novaAresta;
    }

    @Override
    public Aresta insereAresta(Vertice u, Vertice v, Object dado) {

        // Criando a chave da aresta
        int chave = (int)(0.5 * (u.getId() + v.getId()) * (u.getId() + v.getId() + 1) + v.getId());

        // Criando a nova Aresta
        String label = u.getLabel() + v.getLabel();

        Aresta novaAresta = new Aresta(chave, label, dado);
        arestas.insertItem(chave, novaAresta);

        //if(matriz.getValorPosIJ(u.getId(), u.getId()))
        // Inserir na matriz de Adjacencia
        matriz.addItemMatriz(u.getId(), v.getId(), chave);
        matriz.addItemMatriz(v.getId(), u.getId(), chave);

        qtdArestas++;
        return novaAresta;
    }

    @Override
    public LinkedList<Aresta> getArestasVertice(String vertice) {
        int idVert1 = getIdVertice(vertice);
        if (idVert1 == -1) {
            return null;
        } else {
            LinkedList<Aresta> listaArestas = new LinkedList<>();
            for (int j = 0; j < matriz.qtdColunas; j++) {
                float chaveAresta = matriz.getValorPosIJ(idVert1, j);
                //System.out.println(idVert1);
                Aresta aresta = (Aresta) arestas.findElement(chaveAresta);
                if (aresta != null) {
                    listaArestas.add(aresta);
                }
            }
            return listaArestas;
        }
    }

    private int getIdVertice(String label) {

        LinkedList<Object> listaVertices = vertices.elements();

        int i = 0;
        boolean achou = false;
        int id = -1;
        while (i < listaVertices.size() && !achou) {
            Vertice obj = (Vertice) listaVertices.get(i);
            if (obj.getLabel().equals(label)) {
                achou = true;
                id = obj.getId();
            }
            i++;
        }
        return id;
    }

    @Override
    public int grauVertice(Vertice vertice) {
        LinkedList<Aresta> listaArestas = getArestasVertice(vertice.getLabel());
        return listaArestas.size();
    }

    @Override
    public Vertice oposite(Vertice vertice, Aresta aresta) {
        
        if (vertice.getId() == -1) {
            return null;
        } else {
            Vertice verticeOposite = null;
            int j = 0;
            boolean achou = false;
            while (j < matriz.qtdColunas && !achou) {
                int chaveAresta = matriz.getValorPosIJ(vertice.getId(), j);
                Aresta arestaAux = (Aresta) arestas.findElement(chaveAresta);
                if (arestaAux != null) {
                    if (arestaAux.getLabel().equals(aresta)) {
                        Vertice vertAux = (Vertice) vertices.findElement(j);
                        verticeOposite = vertAux;
                        achou = true;
                    }
                }
                j++;
            }
            return verticeOposite;
        }

    }

    private boolean verificaVerticeExiste(Vertice v) {
        boolean achou = false;
        LinkedList<Vertice> vertices = vertices();
        int i = 0;
        while (i < vertices.size() && !achou) {
            if (vertices.get(i).getLabel().equals(v.getLabel())) {
                achou = true;
            }
            i++;
        }
        return achou;
    }

    @Override
    public Vertice insereVertice(Object o) {
        int id = vertices.size()+1;
        Vertice v = new Vertice(id,"",o);
        vertices.insertItem(id, v);
        return v;
    }

    @Override
    public LinkedList<Vertice> getVerticesAresta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Aresta getAresta(String vertU, String vertV) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Vertice> endVertices(Aresta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Vertice> verticesAdjacentes(Vertice v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean areAdjacent(Vertice v, Vertice w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object removeVertice(Vertice v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object removeAresta(Aresta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
