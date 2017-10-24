package grafo;

public class ArestaLad extends Aresta{
	
	VerticeLad vertU;
	VerticeLad vertV;
	
	public ArestaLad(VerticeLad u, VerticeLad v, float id, String label, Object elem){
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
	
	
}
