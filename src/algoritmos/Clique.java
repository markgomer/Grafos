package algoritmos;

import java.util.LinkedList;

import model.Grafo;
import model.Vertice;

public class Clique {
  /**
   * checar se um conjunto de vértices é clique no grafo
   */
  public static boolean ehClique(Grafo g, LinkedList<Vertice> conjVertices) {
    boolean resp = true;
    LinkedList<Vertice> vertices = g.getVertices();
    for (Vertice v : conjVertices) {
      for (Vertice w : vertices) {
        if (!v.ehAdjacente(w)) {
          resp = false;
        }
      }
    }
    return resp;
  }

  /**
   * checar se clique é maximal no grafo Com um clique, se existe um vértice fora
   * desse clique que se conecte a todos os vértices desse clique, este seu clique
   * não é mais maximal.
   *
   * 
   */
  public static boolean ehCliqueMaximal(Grafo g, LinkedList<Vertice> clique) {
    boolean resp = true;
    LinkedList<Vertice> vertices = g.getVertices();
    // para todo o vertice do grafo que não pertence ao clique
    for (Vertice v : vertices) {
      if (!clique.contains(v)) {
        clique.add(v);
        if (ehClique(g, clique))
          resp = false;
        clique.remove(v);
      }
    }
    return resp;
  }

  /** */
}
