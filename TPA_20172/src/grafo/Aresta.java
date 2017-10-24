package grafo;


public class Aresta {
	protected float id;
	protected String label;
	protected Object elem;
	
	public Aresta(float id,String label, Object elem){
		this.id = id;
		this.label = label;
		this.elem = elem;
	}

	public float getId() {
		return id;
	}

	public void setId(float id) {
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
	
	
}
