/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacoes;

import atividades.ManipularArquivo;
import hash.TADChain;
import hash.TADDEA;
import hash.TADHash;


public class AplFinanceira {
    public TADHash numeros = new TADDEA();
    public TADHash moedas = new TADDEA();

    public AplFinanceira() {
        numeros.insertItem(0,"zero");
        numeros.insertItem(1,"um");
        numeros.insertItem(2,"dois");
        numeros.insertItem(3,"tres");
        numeros.insertItem(4,"quatro");
        numeros.insertItem(5,"cinco");
        numeros.insertItem(6,"seis");
        numeros.insertItem(7,"sete");
        numeros.insertItem(8,"oito");
        numeros.insertItem(9,"nove");
        numeros.insertItem(10,"dez");
        numeros.insertItem(11,"onze");
        numeros.insertItem(12,"doze");
        numeros.insertItem(13,"treze");
        numeros.insertItem(14,"catorze");
        numeros.insertItem(15,"quinze");
        numeros.insertItem(16,"dezesseis");
        numeros.insertItem(17,"dezesete");
        numeros.insertItem(18,"dezoito");
        numeros.insertItem(19,"dezenove");
        numeros.insertItem(20,"vinte");
        numeros.insertItem(30,"trinta");
        numeros.insertItem(40,"quarenta");
        numeros.insertItem(50,"cinquenta");
        numeros.insertItem(60,"sessenta");
        numeros.insertItem(70,"setenta");
        numeros.insertItem(80,"oitenta");
        numeros.insertItem(90,"noventa");
        
        moedas.insertItem("e","Euros");
        moedas.insertItem("R$","Reais");
        moedas.insertItem("Rs","Rúpias");
        moedas.insertItem("US$","Dolares americanos");
        moedas.insertItem("Mex$","Pesos Mexicanos");
        moedas.insertItem("kr","Coroas Dinamarquesas");
        moedas.insertItem("Fr","Francos");
    }
    
    public String getMoeda(String str){
        return moedas.findElement(str).toString();
    }
    public String intParaStr(int valor){
        String extenso = numeros.findElement(valor).toString();
        switch (extenso) {
            case "NO_SUCH_KEY":
                return composicao(valor);
            default:
                return extenso;
        }
    }
    private String composicao(int valor){
        int valorTam = String.valueOf(valor).length();
        
        int dezena = valor/(int)Math.pow(10, valorTam -1);
        int unidade = valor%(int)Math.pow(10, valorTam -1);
        String extenso = numeros.findElement(dezena*10).toString()+" e "+numeros.findElement(unidade).toString();
        return extenso;
    }
    public static void main(String[] args) {
        
        AplFinanceira apl = new AplFinanceira();
        
        ManipularArquivo arquivo = new ManipularArquivo();
        arquivo.lerArquivo("bdquantias.txt",apl);
        
        
    }
}
