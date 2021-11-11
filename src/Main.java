import model.Grafo;
import util.GeraGrafo;
import algoritmos.*;

public class Main {

    public static void main(String[] args) {
      //Grafo grafo = GeraGrafo.fromFile("src/dados/wiki.txt");
      //GeraGrafo.toPajek("src/dados/grafoSalvo.pajek", grafo);
      Grafo grafo = GeraGrafo.fromPajek("src/dados/grafoSalvo.pajek");
      grafo.imprimeSemRotulos();
    }

}
