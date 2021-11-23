import model.*;
import util.*;
import algoritmos.*;

public class Main {

    public static void main(String[] args) {
      //Grafo grafo = GeraGrafo.fromFile("src/dados/wiki.txt");
      //GeraGrafo.toPajek("src/dados/grafoSalvo.pajek", grafo);
      Grafo grafo = GeraGrafo.fromPajek("src/dados/wiki.net");
      System.out.println("num arestas =" + grafo.getNumeroArestas());
      //GeraGrafo.toPajek("src/dados/temp.net", grafo);
    }

}
