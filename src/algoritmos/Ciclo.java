package algoritmos;

import java.util.LinkedList;

import model.Grafo;
import model.Vertice;

public class Ciclo {
  /*************************************************
   * Métodos para checar se existe um ciclo no grafo
   *************************************************/

  // https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
  // A Java Program to detect cycle in a graph

  private static boolean isCyclicUtil(Grafo g, Vertice v, boolean[] visited, boolean[] recStack) {
    
    int i = g.pesquisar(v); // colocar índice do vértice atual na variável i
    
    if (recStack[i]) return true; // se o vértice atual está na pilha recursiva, existe um ciclo
    
    if (visited[i]) return false; // se o vértice atual não foi visitado, ainda não foi encontrado um ciclo
    
    visited[i] = true; // marcar vértice atual como visitado e
    
    recStack[i] = true; // parte da pilha recursiva

    LinkedList<Vertice> children = g.getVertice(i).getAdjacentes(); // pegar os vértices adjacentes ao vértice atual
    
    for (Vertice a : children) // chamar método em cada vértice adjacente (DFS)
      if (isCyclicUtil(g, a, visited, recStack)) // se detectou um ciclo, retorna true
        return true;

    // se chegar aqui, não encontrou ciclo na árvore de busca em profundidade, retorna false
    recStack[i] = false; 

    return false;
  }

  // Returns true if the graph contains a
  // cycle, else false.
  public static boolean isCyclic(Grafo g) {

    // marca todos os vértices como não visitados e
    boolean[] visited = new boolean[g.getNumeroVertices()];
    // não fazendo parte da pilha recursiva
    boolean[] recStack = new boolean[g.getNumeroVertices()];

    // Chama função auxiliar a partir de cada vértice do grafo
    for (int i = 0; i < g.getNumeroVertices(); i++) {
      Vertice v = g.getVertice(i);
      if (isCyclicUtil(g, v, visited, recStack))
        return true;
    }

    return false;
  }
  
  // Driver code
  public static void main(String[] args) {
    Grafo grafo = new Grafo();
    grafo.cria_adjacencia("0", "1");
    grafo.cria_adjacencia("0", "2");
    grafo.cria_adjacencia("1", "2");
    grafo.cria_adjacencia("2", "0");
    grafo.cria_adjacencia("2", "3");
    grafo.cria_adjacencia("3", "3");
    
    if (isCyclic(grafo))
    System.out.println("Este grafo contém ciclo");
    else
    System.out.println("Este grafo não contém ciclo");
  }
  
  // This code is contributed by Sagar Shah.
}
