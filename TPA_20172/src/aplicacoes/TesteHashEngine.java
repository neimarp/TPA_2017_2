
package aplicacoes;

import atividades.ManipularArquivo;
import hash.HashEngineDefault;
import java.util.ArrayList;


public class TesteHashEngine {
    
    public static void main(String[] args) {
        HashEngineDefault he = new HashEngineDefault();
        ManipularArquivo arquivo = new ManipularArquivo();
        ArrayList<String> lista = arquivo.lerArquivo("bdquantias.txt");
        //arquivo.imprime(lista);
        for (String string : lista) {
            System.out.println(he.hashCode(string));
        }
    }
}
