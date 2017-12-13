package grafo;

public class Vertice {

    protected int id;
    protected String label;
    protected Object elem;

    public Vertice(int id, String label, Object elem) {
        this.id = id;
        this.label = label;
        this.elem = elem;
    }

    public int getId() {
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

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    @Override
    public String toString() {
        return "Vertice: " + this.getLabel()+" id: "+this.getId(); 
    }
}
