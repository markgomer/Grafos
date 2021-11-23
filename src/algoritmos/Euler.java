package algoritmos;

import model.Grafo;

public class Euler {

  // testar o método ehEuleriano()
  public static void circuitoEuleriano(Grafo g) {
    int result = ehEuleriano(g);
    if (result == 0)
      System.out.println("Grafo não é Euleriano");
    else if (result == 1)
      System.out.println("Grafo contém um caminho Euleriano");
    else // 2
      System.out.println("Grafo contém um ciclo Euleriano");
  }

  
  /**
   * @return 0: not eulerian, 
   * @return 1: euler path, 
   * @return 2: euler cycle
   */
  static int ehEuleriano(Grafo g) {
    // checar se grafo é conexo
    if (Conectividade.ehConexo(g) == false)
      return 0;

    // contar vértices com grau ímpar
    int odd = 0;
    for (int i = 0; i < g.getNumeroVertices(); i++)
      // checar grau do vértice
      if (g.numeroDeAdjacentes(i) % 2 != 0)
        odd++;

    // checar número de vértices ímpares 
    if (odd > 2) { // não-euleriano
      return 0;
    } else if (odd == 2) { // semi-euleriano
      return 1;
    } else { // euleriano
      return 2;
    }
  }


  /** */
  public static void main(String[] args) {
    /* Eulerian Path Example */
    Grafo g = new Grafo(false);

    System.out.println("Grafo:");
    // add Edges
    g.cria_adjacencia("0", "1");
    g.cria_adjacencia("0", "2");
    g.cria_adjacencia("0", "3");
    g.cria_adjacencia("1", "2");
    g.cria_adjacencia("3", "4");

    // print Grafo
    g.imprimeSemRotulos();

    // Eulerian Circuit Algorithm
    circuitoEuleriano(g);

    /* Eulerian Cycle Example */
    Grafo g1 = new Grafo(false);

    System.out.println("\nGraph:");
    // add Edges
    g1.cria_adjacencia("0", "1");
    g1.cria_adjacencia("0", "2");
    g1.cria_adjacencia("0", "3");
    g1.cria_adjacencia("1", "2");
    g1.cria_adjacencia("3", "4");
    g1.cria_adjacencia("4", "0"); // I simply added one Edge

    // print Grafo
    g1.imprimeSemRotulos();

    // Eulerian Circuit Algorithm
    circuitoEuleriano(g1);
  }
}
