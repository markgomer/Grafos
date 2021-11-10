package algoritmos;

import java.util.LinkedList;
import java.util.Stack;

import model.Grafo;
import model.Vertice;

public class Componentes {
  /***************************
   * Métodos para achar os componentes fortemente conectados do grafo
   ***************************/

  // A recursive function to print DFS starting from v
  private static void DFSUtil(Grafo g, int v, boolean[] visited) {
    // Mark the current node as visited and print it
    visited[v] = true;
    Vertice vertice = g.getVertice(v);
    System.out.print(vertice.rotuloToString() + " ");

    Vertice n;

    // itera sobre os vertices adjacentes a 'vertice'
    LinkedList<Vertice> adjacentes = vertice.getAdjacentes();

    for (Vertice adjacente : adjacentes) {
      n = adjacente;
      int indexOfN = g.pesquisar(n);
      if (!visited[indexOfN])
        DFSUtil(g, indexOfN, visited);
    }
  }

  // Function that returns reverse (or transpose) of this graph
  private static Grafo getTranspose(Grafo g) {
    Grafo grafo = new Grafo();
    for (int i = 0; i < g.getNumeroVertices(); i++) {
      Vertice v = g.getVertice(i);
      LinkedList<Vertice> adjacentes = v.getAdjacentes();
      for (int j = 0; j < v.numeroArestas(); j++) {
        // pega cada vértice adjacente e faz a ligação inversa
        Vertice w = adjacentes.get(j);
        grafo.cria_adjacencia(w, v);
      }
    }
    return grafo;
  }

  private static void fillOrder(Grafo g, int v, boolean[] visited, Stack<Integer> stack) {
    // Mark the current node as visited and print it
    visited[v] = true;
    Vertice vertice = g.getVertice(v);

    // itera sobre os vertices adjacentes a 'vertice'
    LinkedList<Vertice> adjacentes = vertice.getAdjacentes();
    for (int i = 0; i < adjacentes.size(); i++) {
      if (!visited[i]) {
        fillOrder(g, i, visited, stack);
      }
    }

    // All vertices reachable from v are processed by now,
    // push v to Stack
    stack.push(v);
  }

  /**
   *
   */
  public static void printSCCs(Grafo g) {
    Stack<Integer> stack = new Stack<Integer>();

    // Mark all the vertices as not visited (For first DFS)
    boolean[] visited = new boolean[g.getNumeroVertices()];
    for (int i = 0; i < g.getNumeroVertices(); i++)
      visited[i] = false;

    // Fill vertices in stack according to their finishing
    // times
    for (int i = 0; i < g.getNumeroVertices(); i++)
      if (!visited[i])
        fillOrder(g, i, visited, stack);

    // Create a reversed graph
    Grafo gr = getTranspose(g);
    // gr.imprime_adjacencias();

    // Mark all the vertices as not visited (For second DFS)
    for (int i = 0; i < g.getNumeroVertices(); i++)
      visited[i] = false;

    // Now process all vertices in order defined by Stack
    while (!stack.empty()) {
      // Pop a vertex from stack
      int v = (int) stack.pop();

      // Print Strongly connected component of the popped vertex
      if (!visited[v]) {
        DFSUtil(gr, v, visited);
        System.out.println();
      }
    }
  }

  /**
   * Testes
   * @param args
   */
  public static void main(String[] args) {
    
  }
  
}
