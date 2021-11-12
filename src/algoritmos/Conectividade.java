package algoritmos;

import java.util.LinkedList;

import model.Grafo;
import model.Vertice;
import util.GeraGrafo;


public class Conectividade {



  public static void isConnected(Grafo graph) {

    int numVertices = graph.getNumeroVertices();
    LinkedList<Vertice> adjList = graph.getVertices();

    // created visited array
    boolean[] visitados = new boolean[numVertices];

    // start the DFS from vertex 0
    Vertice zero = graph.getVertice(0);
    DFS(zero, adjList, visitados);

    // check if all the vertices are visited, if yes then graph is connected
    int count = 0;
    for (int i = 0; i < visitados.length; i++) {
      if (visitados[i])
        count++;
    }
    if (numVertices == count) {
      System.out.println("Given graph is connected");
    } else {
      System.out.println("Given graph is not connected");
    }
  }

  public static void DFS(Vertice verticeOrigem, LinkedList<Vertice> adjList, boolean[] visitados) {
    int indiceVerticeOrigem = verticeOrigem.getIndice() - 1;
    // mark the vertex visited
    visitados[indiceVerticeOrigem] = true;

    // travel the neighbors
    for (int i = 0; i < verticeOrigem.numeroArestas(); i++) {
      Vertice neighbor = verticeOrigem.getAdjacentes().get(i);
      if (visitados[neighbor.getIndice()-1] == false) {
        // make recursive call from neighbor
        DFS(neighbor, adjList, visitados);
      }
    }
  }

  public static void main(String[] args) {
    /** * /
    Grafo graph = new Grafo();

    
    graph.cria_adjacencia("0", "1");
    graph.cria_adjacencia("1", "3");
    graph.cria_adjacencia("3", "2");
    graph.criaVertice("4");

    Conectividade c = new Conectividade();
    c.isConnected(graph);

    graph.cria_adjacencia("3", "4");
    c.isConnected(graph);
    /** */
    Grafo grafo = GeraGrafo.fromPajek("src/dados/grafoSalvo.pajek");
    Conectividade.isConnected(grafo);
  }
}