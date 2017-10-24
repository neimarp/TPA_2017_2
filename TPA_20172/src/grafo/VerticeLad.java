package grafo;

import java.util.LinkedList;

public class VerticeLad extends Vertice {
	
	LinkedList<ArestaLad> arestaIn;
	LinkedList<ArestaLad> arestaOut;
	
	public VerticeLad(int id, String label){
		super(id, label, label);
		arestaIn = new LinkedList<>();
		arestaOut = new LinkedList<>();
	}
	/*
	public VerticeLad(int i, String string, String string2) {
		// TODO Auto-generated constructor stub
	}*/

	public void addArestaIn(ArestaLad a){
		arestaIn.add(a);
	}
	
	public void addArestaOut(ArestaLad a){
		arestaOut.add(a);
	}

	public LinkedList<ArestaLad> getArestaIn() {
		return arestaIn;
	}

	public void setArestaIn(LinkedList<ArestaLad> arestaIn) {
		this.arestaIn = arestaIn;
	}

	public LinkedList<ArestaLad> getArestaOut() {
		return arestaOut;
	}

	public void setArestaOut(LinkedList<ArestaLad> arestaOut) {
		this.arestaOut = arestaOut;
	}
	
	public int grauIn(){
		return arestaIn.size();
	}
	
	public int grauOut(){
		return arestaOut.size();
	}
	
}
