import model.Grafo;
import algoritmos.*;

public class Main {

    public static void main(String[] args) {
      Grafo g = new Grafo(false);

      // grafo para produzir árvore geradora mínima
      g.cria_adjacencia(0, 1); g.seta_peso(0, 1, 2);
      g.cria_adjacencia(0, 2); g.seta_peso(0, 2, 1);
      g.cria_adjacencia(1, 3); g.seta_peso(1, 3, 3);
      g.cria_adjacencia(1, 4); g.seta_peso(1, 4, 2);
      g.cria_adjacencia(2, 3); g.seta_peso(2, 3, 1);
      g.cria_adjacencia(2, 5); g.seta_peso(2, 5, 2);
      g.cria_adjacencia(3, 4); g.seta_peso(3, 4, 4);
      g.cria_adjacencia(3, 5); g.seta_peso(3, 5, 3);
      g.cria_adjacencia(4, 6); g.seta_peso(4, 6, 1);
      g.cria_adjacencia(5, 6); g.seta_peso(5, 6, 3);
      g.cria_adjacencia(6, 7); g.seta_peso(6, 7, 2);
      g.cria_adjacencia(6, 8); g.seta_peso(6, 8, 2);
      g.cria_adjacencia(7, 8); g.seta_peso(7, 8, 1);
          
      // Print the solution
      AGM.primMST(g);
    }

}
