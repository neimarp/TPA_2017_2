
package aplicacoes;

import hash.FuncaoHash;

public class ExercicioHash {

    public static void main(String[] args) {
       
        FuncaoHash f = new FuncaoHash("vix-ruas-ceps.txt");
        
       
        int[][]lista = f.listaFrequenciaFuncoes();
        
      
        for (int i = 0; i < lista.length; i++) {
            int[] is = lista[i];
            System.out.print(i+"\t");
            for (int j = 0; j < is.length; j++) {
                int k = is[j];
                System.out.print(k+"\t");
            }
            System.out.println("");
        }
    }
    
}
