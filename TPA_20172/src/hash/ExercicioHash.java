
package hash;

/**
 *
 * @author PedroBook
 */
public class ExercicioHash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FuncaoHash f = new FuncaoHash("vix-ruas-ceps.txt");
        
        //int v = f.f4("29043-235");
        //System.out.println(v);
        
//        v = f.f2("29043-235");
//        
//        System.out.println(v);
//        
//        v = f.f2("29041-605");
//        
//        System.out.println(v);
//        
        int[][]lista = f.listaFrequenciaFuncoes();
        
        /*for (String is : f.getListaCep()) {
            System.out.println(f.f1(is)+" == "+is);
        }*/
        
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
