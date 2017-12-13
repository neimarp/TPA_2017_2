package grafo;

import java.util.LinkedList;

public class ArestaLad extends Aresta {

    VerticeLad vertU;
    VerticeLad vertV;

    public ArestaLad(VerticeLad u, VerticeLad v, int id, String label, Object elem) {
        super(id, label, elem);
        vertU = u;
        vertV = v;
    }

    public VerticeLad getVertU() {
        return vertU;
    }

    public void setVertU(VerticeLad vertU) {
        this.vertU = vertU;
    }

    public VerticeLad getVertV() {
        return vertV;
    }

    public void setVertV(VerticeLad vertV) {
        this.vertV = vertV;
    }

    public LinkedList<Vertice> vertices() {
        LinkedList<Vertice> lista = new LinkedList<Vertice>();
        lista.add(getVertU());
        lista.add(getVertV());
        return lista;
    }
    
    /*
	public float getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
     */
}
