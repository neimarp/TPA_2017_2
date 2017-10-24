package aplicacoes;

import grafo.Grafo;
import java.io.IOException;
import grafo.GrafoNDMat;
import grafo.PlotarGrafico;


public class AppTesteGrafo {
	public static void main(String[] args) throws IOException {
		
		//Teste GRAFO n�o Direcionado
		Grafo grafo = new GrafoNDMat();
		grafo.carregaMMF("base\\personagens.txt");
		PlotarGrafico.plotarGrafo(grafo, false);
		
		//Teste GRAFO Direcionado
		//Grafo grafo = new GrafoDMat();
		//grafo.carregaMMF("teste.mtx");
		///PlotarGrafico.plotarGrafo(grafo, true);
		
	}
}
