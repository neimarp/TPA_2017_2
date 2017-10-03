package atividades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManipularArquivo {

    public ArrayList<String> lerArquivo(String arquivo) {
        ArrayList lista = new ArrayList();

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(arquivo));
            while (br.ready()) {
                String linha = br.readLine();
                String[] split = linha.split("[\\s,.();]");
                for (String palavra : split) {
                    // System.out.println(palavra);
                    if (!palavra.isEmpty()) {
                        lista.add(palavra);
                    }
                }
                //System.out.println(linha);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado");
        } catch (IOException ex) {
            System.out.println("Excecao I/O");
        }
        return lista;
    }
    
    public void imprime(ArrayList lista) {

        System.out.println("tamanho da lista = " + lista.size());
        for (Object lista1 : lista) {
            System.out.println(lista1);
        }
    }

}
