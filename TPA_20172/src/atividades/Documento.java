package atividades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Documento {

	public Documento(String arquivo) {
		this.nomeArq = arquivo;
	}

	void imprime(ArrayList lista) {
		
		System.out.println("tamanho da lista = " + lista.size());
		for (Object lista1 : lista) {
			System.out.println(lista1);
		}
	}

	ArrayList<String> lerArquivo(String arquivo) {
		ArrayList lista = new ArrayList();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(arquivo));
			while (br.ready()) {
				String linha = br.readLine();
				String[] split = linha.split("[\\s,.();]");
				for (String palavra : split) {
					// System.out.println(palavra);
					if (!palavra.isEmpty())
						lista.add(palavra);
				}
				//System.out.println(linha);
			}
			br.close();
		} catch (FileNotFoundException ex) {System.out.println("Arquivo nao encontrado");}
                  catch (IOException ex) {System.out.println("Excecao I/O");}
		return lista;
	}

	String nomeArq;

	// retorna uma lista com as palavras do texto
	public ArrayList palavras() {
		ArrayList lista = new ArrayList();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(nomeArq));
			while (br.ready()) {
				String linha = br.readLine();
				String[] split = linha.split("[\\s,.();]");
				for (String palavra : split) {
					// System.out.println(palavra);
					if (!palavra.isEmpty())
						lista.add(palavra);
				}
				//System.out.println(linha);
			}
			br.close();
		} catch (FileNotFoundException ex) {System.out.println("Arquivo nao encontrado");}
                  catch (IOException ex) {System.out.println("Excecao I/O");}
		return lista;
	}

	// retorna uma lista de ngramas do tamanho tam
	public ArrayList ngramas(int tam) {
		ArrayList listaPalavras = palavras();
		ArrayList listaNgramas = new ArrayList();

		for (int i = 0; i <= listaPalavras.size() - tam; i++) {
			String strNgrama = "";
			for (int j = 0; j < tam; j++) {
				strNgrama = strNgrama + "," + listaPalavras.get(i + j);
			}
			listaNgramas.add(strNgrama.substring(1, strNgrama.length()));//retira o ultimo caracter (,)
		}

		return listaNgramas;
	}

	// retorna uma lista do tipo tabela frequencia ngramas
	public ArrayList freqNgramas(int tam) {
		ArrayList<String> listaNgrama = ngramas(tam);
		ArrayList<FreqNgrama> listaFreq = new ArrayList<FreqNgrama>();
		
		for (String nGrama : listaNgrama) {
			int i = 0;
			for (FreqNgrama freqNgrama : listaFreq) {
				if (freqNgrama.getnGrama().equals(nGrama)) {
					freqNgrama.atualizaQtd();
					break;
				}
				i++;
			}
			if (i==listaFreq.size()) {
				listaFreq.add(new FreqNgrama(nGrama));
			}
			
		}
		
		return listaFreq;
	}
        
        public float simCosseno(String arq){
            ArrayList doc1 = palavras();
            ArrayList doc2 = lerArquivo(arq);
            int i = doc1.size()>doc2.size() ? doc1.size() : doc2.size();
            float cosTeta = 0;
            
            
            return cosTeta;
        }
        
	public static void main(String[] args) {
		Documento doc = new Documento("texto2.txt");

		ArrayList lista = doc.palavras();//doc.freqNgramas(2//doc.ngramas(2);
                ArrayList lista1 = doc.ngramas(2);
                ArrayList lista2 = doc.freqNgramas(2);
		Collections.sort(lista);
		doc.imprime(lista);
	}
}