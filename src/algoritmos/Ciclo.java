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
    int i = g.pesquisar(v);
    // Mark the current node as visited and
    // part of recursion stack
    if (recStack[i])
      return true;

    if (visited[i])
      return false;

    visited[i] = true;

    recStack[i] = true;
    LinkedList<Vertice> children = g.getVertice(i).getAdjacentes();

    for (Vertice a : children)
      if (isCyclicUtil(g, a, visited, recStack))
        return true;

    recStack[i] = false;

    return false;
  }

  // Returns true if the graph contains a
  // cycle, else false.
  public static boolean isCyclic(Grafo g) {

    // Mark all the vertices as not visited and
    // not part of recursion stack
    boolean[] visited = new boolean[g.getNumeroVertices()];
    boolean[] recStack = new boolean[g.getNumeroVertices()];

    // Call the recursive helper function to
    // detect cycle in different DFS trees
    for (int i = 0; i < g.getNumeroVertices(); i++) {
      Vertice v = g.getVertice(i);
      if (isCyclicUtil(g, v, visited, recStack))
        return true;
    }

    return false;
  }
  // This code is contributed by Sagar Shah.
}
