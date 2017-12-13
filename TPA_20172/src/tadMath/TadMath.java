package tadMath;


import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import hash.TADChain;

class ItemMatriz {
	int linha;
	int coluna;
	float valor;
	
	public ItemMatriz(int i,int j,float valor) {
		linha = i;
		coluna = j;
		this.valor = valor;
	}
	
	public int getLinha() {
		return linha;
	}
	
	public int getColuna(){
		return coluna;
	}
	
	public float getValor(){
		return valor;
	}
}

public class TadMath {
	public int qtdLinhas;
	public int qtdColunas;
	TADChain conteudo;;
	
	public TadMath(int qtdlinhas,int qtdColunas) {
		this.qtdLinhas = qtdlinhas;
		this.qtdColunas = qtdColunas;
		conteudo = new TADChain();
	}
	
	public TadMath(){
		qtdLinhas = 0;
		qtdColunas = 0;
		conteudo = new TADChain();
	}
	
	public void addItemMatriz(int i,int j,float _valor){
		
		String chave = i + "-" + j;
		ItemMatriz item = new ItemMatriz(i, j, _valor);
		
		conteudo.insertItem(chave, item);
	}
	
	public int getValorPosIJ(int i,int j){
		String chave = i + "-" + j;
		ItemMatriz item = (ItemMatriz) conteudo.findElement(chave);
		if(item != null){
			return (int)item.getValor();
		}
		return 0;
	}
	
	public void printMatriz() throws IOException{
		for (int i = 0; i < qtdLinhas; i++) {
			for (int j = 0; j < qtdColunas; j++) {
				System.out.print(getValorPosIJ(i, j) + "  ");					
			}
			System.out.println("");
		}
	}
	
	public void leituraByte(String nomeArquivo) throws IOException{
		InputStream entrada = new FileInputStream(nomeArquivo);
	    int umByte = entrada.read();
	    int i=0;
        int j=0;
        
	    while(umByte != -1)
	    {
	    	String conteudo = "";
	    	if((char)umByte!=' ' && (char)umByte!='\n' ){
	        	while((char)umByte!=' ' && (char)umByte!='\n' ){
	        		if((char)umByte==','){
	        			umByte = '.';
	        		}
	        		conteudo = conteudo+""+(char)umByte;
	        		
	        		umByte = entrada.read();
	        	}
	        	
				j++;
	    	}
	    	if(!conteudo.equals("")){
	    		//System.out.println(conteudo);
	    		this.addItemMatriz(i, j-1,Float.parseFloat(conteudo) );
	    	}
	    	if((char)umByte=='\n'){
	    		i++;
	    		this.qtdColunas = j;
	    		j = 0;
	    	}
	    
	    	
	    	umByte = entrada.read();
	    }
	    this.qtdLinhas = i;
	    
	}
	
	
	public void carregaMatriz(String nomeArq) throws IOException{
		try {
			 FileReader arq = new FileReader(nomeArq);
			 BufferedReader lerArq = new BufferedReader(arq);
			 
			 String linha = lerArq.readLine();
			 while(linha.contains("%") && linha != null){
				 linha = lerArq.readLine();
			 }	 
			
			 String[] dimensoes = linha.split("\\s");
			 qtdLinhas = Integer.parseInt(dimensoes[0]);
			 qtdColunas = Integer.parseInt(dimensoes[1]);
			 
			 linha = lerArq.readLine();
			 while(linha != null){
				 String[] linhaConteudo = linha.split("\\s");
				 this.addItemMatriz(Integer.parseInt(linhaConteudo[0]) - 1, Integer.parseInt(linhaConteudo[1]) - 1, Float.parseFloat(linhaConteudo[2]));
				 linha = lerArq.readLine();
			 }	 
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	
	public void gerarMatrizAletoria() throws IOException{
		Random random = new Random();
		for(int i = 0; i < qtdLinhas; i++){
			for(int j = 0; j < qtdColunas; j++){
				float num = random.nextFloat() * 5;
				this.addItemMatriz(i, j, num);
			}
		}
	}
	

}

