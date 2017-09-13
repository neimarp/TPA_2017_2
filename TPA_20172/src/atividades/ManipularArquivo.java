package atividades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManipularArquivo {
	
	public ArrayList<String> leArquivo(String nomeArq) {
		ArrayList<String> lista = null;
        try {
            FileReader arq = new FileReader(nomeArq);
            BufferedReader lerArq = new BufferedReader(arq);
            
            while(lerArq.ready()) {
            	String linha = lerArq.readLine();
                String palavras[] = linha.trim().split("[\\s.,]");
                for (String palavra : palavras) {
                	lista.add(palavra);
				}
            }
            
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
        	//System.out.println("erro ao abrir arquivo");
        }
        return lista;
    }

}
