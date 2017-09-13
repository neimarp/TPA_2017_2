
package atividades;

public class FreqNgrama implements Comparable<FreqNgrama>{
	
	private String nGrama;
	private int qtd;
    
    public FreqNgrama(String nGrama) {
		this.nGrama = nGrama;
		qtd = 1;
	}
    public String getnGrama() {
		return nGrama;
	}
    
    public int getQtd() {
		return qtd;
	}
    
    public void atualizaQtd() {
		this.qtd += 1;
	}
    
    @Override
    public String toString() {
    	return nGrama+"	"+qtd;
    }
	@Override
	public int compareTo(FreqNgrama freq) {
		if (this.qtd < freq.getQtd()) {
            return 1;
        }
        if (this.qtd > freq.getQtd()) {
            return -1;
        }
		return 0;
	}
}
