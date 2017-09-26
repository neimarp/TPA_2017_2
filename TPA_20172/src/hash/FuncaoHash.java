/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author PedroBook
 */
public class FuncaoHash {

    private int quantidadeVetor = 250;
    private int quantidadeFuncao = 5;
    private String[] listaCep;
    private String nomeArquivo;
    private int[][] listaFrequencia;

    public FuncaoHash(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.listaCep = new String[quantidadeVetor];
        this.listaFrequencia = new int[quantidadeVetor][quantidadeFuncao];
    }

    public String[] getListaCep() {

        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo), "ISO-8859-1"));
            while (br.ready()) {
                String[] array = br.readLine().split(";");
                this.listaCep[i] = array[0].trim();
                i++;
            }

            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this.listaCep;
    }

    public int[][] listaFrequenciaFuncoes() {

        getListaCep();
        int indice = 0;
        for (String valor : this.listaCep) {
            indice = f1(valor);
            listaFrequencia[indice][0] += 1;
            indice = f2(valor);
            listaFrequencia[indice][1] += 1;
            indice = f3(valor);
            listaFrequencia[indice][2] += 1;
            indice = f4(valor);
            listaFrequencia[indice][3] += 1;
            indice = f5(valor);
            listaFrequencia[indice][4] += 1;
        }

        return listaFrequencia;
    }

    private int mod(int v) {
        return v % quantidadeVetor;
    }

    public int f1(String valor) {
        int novoValor = Integer.parseInt(valor.replace("-", ""));
        return mod(novoValor);
    }

    public int f2(String valor) {
        String v = valor.replace("-", "");
        int novoValor = 0;
        for (int i = 0; i < v.length(); i++) {
            novoValor += Integer.parseInt(String.valueOf(v.charAt(i)));
        }
        return mod((novoValor * 10));
    }

    public int f3(String valor) {
        String v = valor.replace("-", "");
        int novoValor = 0;
        for (int i = 0; i < v.length(); i++) {
            novoValor += v.codePointAt(i);
        }
        return mod(novoValor);
    }
    

    public int f4(String valor) {
        String v = valor.replace("-", "");
        double novoValor = 0;
        int base = 33;

        for (int i = 0; i < v.length(); i++) {
            int result = Integer.parseInt(String.valueOf(v.charAt(i)));
            novoValor += Math.pow(base, i) * result;
        }
        return (int) (novoValor % quantidadeVetor);
    }
    
    public int f5(String valor) {
        String v = valor.replace("-", "");
        double novoValor = 0;
        int base = 33;

        for (int i = 0; i < v.length(); i++) {
            int result = v.codePointAt(i);
            novoValor += Math.pow(base, i) * result;
        }
        return (int) (novoValor % quantidadeVetor);
    }
    
}
