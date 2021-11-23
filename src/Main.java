import model.*;
import util.*;
import algoritmos.*;

public class Main {

    public static void main(String[] args) {
      //Grafo grafo = GeraGrafo.fromFile("src/dados/wiki.txt");
      //GeraGrafo.toPajek("src/dados/grafoSalvo.pajek", grafo);
      Grafo grafo = GeraGrafo.fromPajek("src/dados/wiki.net");
      System.out.println("num arestas =" + grafo.getNumeroArestas());
      System.out.println(Ciclo.isCyclic(grafo)? "CICLICO" : "NAO CICLICO");
      Euler.circuitoEuleriano(grafo);
    }

}
