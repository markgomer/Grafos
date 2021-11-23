package util;
import java.io.File;
import java.io.IOException;

import algoritmos.Conectividade;
import model.Grafo;
import model.Vertice;

public class GeraGrafo 
{
  private static String ARQUIVO = "src/dados/data.txt";

  /**
   * Cria um grafo direcionado a partir do arquivo "src/dados/data.txt"
   * @return grafo criado
   */
  public static Grafo fromFile(String PATH) {
    Grafo grafo = new Grafo();
    Arq.openRead(PATH);
    //Arq.readLine(); // pular primeira linha...
    int count = 0;
    while(Arq.hasNext()) {
      String linha = Arq.readLine();
      String arrLinha[] = linha.split(",");
      String origem = arrLinha[0];
      for(int i = 1; i < arrLinha.length; i++) {
        String destino = arrLinha[i];
        grafo.cria_adjacencia(origem, destino);
      }
      System.out.print("Lendo arquivo e criando adjacências: " + count + "\r");
      count++;
    }
    System.out.println("Grafo criado!!                                           ");
    return grafo;
  }

  public static Grafo fromFile() {
    return fromFile(ARQUIVO);
  }

  /**
   * Cria grafo a partir de um arquivo padrão "src/dados/data.txt"
   * @return grafo criado
   */
  public static Grafo fromPajek() {
    return fromPajek("src/dados/exemplo.pajek");
  }
  /**
   * Cria um grafo a partir de um arquivo no formato pajek
   * @param nomeArq caminho e nome do arquivo 
   * @return grafo criado
   */
  public static Grafo fromPajek(String nomeArq) {
    Grafo grafo = new Grafo();
    Arq.openRead(nomeArq);
    // 1ª linha: qtd vertices
    String line = Arq.readLine().replaceAll("[*Vertices ]", "");
    int qtdVertices = Integer.parseInt(line);
    // criar os vértices
    for(int i = 0; i<qtdVertices; i++) {
      Arq.readInt(); Arq.readChar();
      String label = Arq.readLine().replaceAll("[\"]", "");        
      grafo.criaVertice(label);
      System.out.print("Criando vértices: " + i + "/" + qtdVertices + "             \r");
    }
    // 2º passo criar arestas
    Arq.readLine();
    while(Arq.hasNext()) {
      int indexOrigem = Arq.readInt() - 1;
      int indexDestino = Arq.readInt() - 1;
      int peso = Arq.readInt();
      grafo.cria_adjacencia(indexOrigem, indexDestino);
      grafo.seta_peso(indexOrigem, indexDestino, peso);
      System.out.print("Criando arestas: " + indexOrigem + "->" + indexDestino + "                     \r");
    }
    System.out.println("Grafo carregado!                      ");
    
    return grafo;  
  }

  /**
   * Salva grafo em um arquivo no formato pajek
   * @param fileName caminho e nome do arquivo a ser salvo
   * @param grafo grafo a ser salvo em arquivo
   */
  public static void toPajek(String fileName, Grafo grafo) {
    try {
      new File(fileName).createNewFile();
    } catch(IOException e) {e.printStackTrace();}
    
    Arq.openWrite(fileName);
    int numVertices = grafo.getNumeroVertices();
    
    Arq.print("*Vertices "); Arq.println(numVertices);
    for(int i = 0 ; i<numVertices; i++) {
      System.out.print("Escrevendo vértice " + i + "/" + numVertices + "\r");
      Vertice v = grafo.getVertice(i);
      Arq.println(Integer.toString(i + 1) + " \"" + v.rotuloToString() + "\"");
    }
    
    Arq.println("*Edges ");
    for(int i = 0; i<numVertices; i++) {
      System.out.print("Escrevendo arestas " + i + "\r");
      Vertice v = grafo.getVertice(i);
      Arq.print(v.adjacenciasToStr());
    }
    System.out.println("Grafo salvo!                         ");

    Arq.close();
  }


  private static Grafo geraConexo(int numVertices, int numArestas, boolean direcionado) {
    Grafo grafo = new Grafo(direcionado);
    grafo.criaVertice(0);
    for(int i = 1; i<numVertices; i++) {
      grafo.criaVertice(i);
      grafo.cria_adjacencia(i, i-1);
    }
    for (int i = 0; i < numArestas-numVertices; i++) {
      grafo.cria_adjacencia(grafo.getRandomVertice(), grafo.getRandomVertice());
    }
    
    return grafo;
  }
  

  private static Grafo geraNaoConexo(int numVertices, int numArestas, boolean direcionado) {
    Grafo grafo = new Grafo(direcionado);
    for(int i = 0; i<numVertices-1; i++) grafo.criaVertice(i);
    
    for(int i = 0; i<numArestas; i++) grafo.cria_adjacencia(grafo.getRandomVertice(), grafo.getRandomVertice());
    
    int ultimoVertice = numVertices;
    grafo.criaVertice(ultimoVertice);

    return grafo;
  }

  
  public static Grafo aleatorio(int numVertices, int numArestas, boolean conexo, boolean direcionado) {
    Grafo grafo;

    if(conexo) {
      grafo = geraConexo(numVertices, numArestas, direcionado);
    }
    else {
      grafo = geraNaoConexo(numVertices, numArestas, direcionado);
    }
    return grafo;
  }


  /**
   * Testes
   * @param args
   */
  public static void main(String[] args) {
    Grafo grafo = fromPajek("src/dados/exemplo.pajek");
    //Grafo grafo = aleatorio(100, 20000, true, false);
    //Grafo novo = fromPajek("src/dados/grafoSalvo.pajek");
    //grafo.imprime_adjacencias();
    grafo.cria_adjacencia(3, 4);
    System.out.println("Numero de arestas: " + grafo.getNumeroArestas());
    //System.out.println(Conectividade.ehConexo(grafo)? "Conexo":"Não conexo");

    toPajek("src/dados/EXEMPLO2.pajek", grafo);
  }

}
