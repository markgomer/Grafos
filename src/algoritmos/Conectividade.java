// https://algorithms.tutorialhorizon.com/check-if-given-undirected-graph-is-connected-or-not/

package algoritmos;

import java.util.LinkedList;

import model.Grafo;
import model.Vertice;
import util.GeraGrafo;


public class Conectividade {

  public static boolean ehConexo(Grafo grafo) {
    boolean resp;

    int numVertices = grafo.getNumeroVertices();
    LinkedList<Vertice> vertices = grafo.getVertices();

    // array que indica índices dos vértices visitados
    boolean[] visitados = new boolean[numVertices];

    // faz busca em profundidade a partir do vértice 0
    Vertice zero = grafo.getVertice(0);
    buscaProfundidade(zero, vertices, visitados);

    // checa se todos os vértices foram visitados, se sim, então o grafo é conexo
    int count = 0;
    for (int i = 0; i < visitados.length; i++) {
      if (visitados[i])
        count++;
    }
    if (numVertices == count) {
      resp = true;
    } else {
      resp = false;
    }
    return resp;
  }


  public static void buscaProfundidade(Vertice verticeOrigem, LinkedList<Vertice> vertices, boolean[] visitados) {
    int indiceVerticeOrigem = verticeOrigem.getIndice();
    // marca o vértice como visitado
    visitados[indiceVerticeOrigem] = true;

    // iteração sobre os adjacentes
    for (int i = 0; i < verticeOrigem.numeroArestas(); i++) {
      Vertice adjacente = verticeOrigem.getAdjacente(i);
      if (visitados[adjacente.getIndice()] == false) {
        // faz chamada recursiva a partir do adjacente
        buscaProfundidade(adjacente, vertices, visitados);
      }
    }
  }

  public static void main(String[] args) {
    /** */
    Grafo grafo = new Grafo();

    
    grafo.cria_adjacencia("0", "1");
    grafo.cria_adjacencia("1", "3");
    grafo.cria_adjacencia("3", "2");
    grafo.criaVertice("4");

    System.out.println(Conectividade.ehConexo(grafo)? "Grafo é conexo" : "Grafo não é conexo" );    
    
    grafo.cria_adjacencia("3", "4");
    System.out.println(Conectividade.ehConexo(grafo)? "Grafo é conexo" : "Grafo não é conexo" );
    
    /** * /
    Grafo grafo = GeraGrafo.fromPajek("src/dados/grafoSalvo.pajek");
    System.out.println(Conectividade.ehConexo(grafo)? "Grafo é conexo" : "Grafo não é conexo" );
    /** */
  }
}