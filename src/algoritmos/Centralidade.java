package algoritmos;

import model.Grafo;
import model.Vertice;
import util.GeraGrafo;

public class Centralidade {

	// numero de vertices -1 / soma dos melhores caminhos até todos os outros vértices
	public static double centralidadeDeProximidade(Grafo grafo, Vertice v) {
		double centralidade; // variável a ser calculada
		int numVertices = grafo.getNumeroVertices();
		int[] melhoresCaminhos = Dijkstra.algo_dijkstra(grafo, v); // array com todos os melhores caminhos a partir do vértice v
		int soma = 0; //soma dos melhores caminhos:
		for (int i = 0; i < melhoresCaminhos.length; i++) {
			if(melhoresCaminhos[i] != Integer.MAX_VALUE){
				soma += melhoresCaminhos[i];
			}
		}
		// centralidade de prox é a razão entre o total de vértices e a soma dos melhores caminhos
		centralidade = (double)numVertices/(double)soma;

		return centralidade;
	}

  
	// melhores caminhos que possuem v como intermediário
	public static double centralidadeDeIntermediacao(Grafo grafo, Vertice v) {
		int qntsVezesIntermediario = 0; // variável a ser calculada
		int numVertices = grafo.getNumeroVertices();

		for (int vertice = 0; vertice < numVertices; vertice++) {
			Vertice origem = grafo.getVertice(vertice);
			qntsVezesIntermediario += Dijkstra.algo_dijkstra(grafo, origem, v);
		}
		
		return qntsVezesIntermediario;
	}



  
  public static void main(String[] args) {
    Grafo grafo = GeraGrafo.aleatorio(100,2000, true, true);
		GeraGrafo.toPajek("src/dados/aleatorio.net", grafo);
		
    Vertice v = grafo.getVertice(50);
    //calculateCloseness(grafo, v);
		double centProx = centralidadeDeProximidade(grafo, v);
    System.out.println(v.rotuloToString() + "\t" + centProx);

		System.out.println(centralidadeDeIntermediacao(grafo, v));
    /** * /
    for(Vertice v : grafo.getVertices()) {
      System.out.println(v.rotuloToString() + "\t" + v.getCentralidade());
    }
    /** */
  }

}
