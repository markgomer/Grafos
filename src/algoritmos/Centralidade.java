package algoritmos;

import model.Grafo;
import model.Vertice;
import util.GeraGrafo;

public class Centralidade {

	// numero de vertices -1 / soma dos melhores caminhos até todos os outros vértices
	public static double centralidadeDeProximidade(Grafo grafo, Vertice v) {
		double centralidade;
		int numVertices = grafo.getNumeroVertices();
		int[] melhoresCaminhos = Dijkstra.algo_dijkstra(grafo, v);
		//soma dos melhores caminhos:
		int soma = 0;
		for (int i = 0; i < melhoresCaminhos.length; i++) {
			if(melhoresCaminhos[i] != Integer.MAX_VALUE){
				soma += melhoresCaminhos[i];
			}
		}
		centralidade = (double)numVertices/(double)soma;

		return centralidade;
	}

  




  
  public static void main(String[] args) {
    Grafo grafo = GeraGrafo.fromPajek("src/dados/grafoSalvo.pajek");
    Vertice v = grafo.getVertice(50);
    //calculateCloseness(grafo, v);
		double centProx = centralidadeDeProximidade(grafo, v);
    System.out.println(v.rotuloToString() + "\t" + centProx);

    /** * /
    for(Vertice v : grafo.getVertices()) {
      System.out.println(v.rotuloToString() + "\t" + v.getCentralidade());
    }
    /** */
  }

}