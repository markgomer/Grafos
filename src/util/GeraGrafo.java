package util;
import java.io.File;
import java.io.IOException;

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
        grafo.cria_adjacencia(grafo.criaVertice(origem), grafo.criaVertice(destino));
      }
      System.out.print("Criando vértices: " + count + "\r");
      count++;
    }
    System.out.println("Pronto!                     ");
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
    return fromPajek(ARQUIVO);
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
      System.out.print("Criando vértices: " + i + "/" + qtdVertices + "\r");
    }
    // 2º passo criar arestas
    Arq.readLine();
    while(Arq.hasNext()) {
      int indexOrigem = Arq.readInt() - 1;
      int indexDestino = Arq.readInt() - 1;
      int peso = Arq.readInt();
      grafo.cria_adjacencia(indexOrigem, indexDestino);
      grafo.seta_peso(indexOrigem, indexDestino, peso);
      System.out.print("Criando arestas: " + indexOrigem + "/" + indexDestino + "\r");
    }
    System.out.println("Pronto!                      ");
    
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
    System.out.println("Pronto!                         ");

    Arq.close();
  }


  /**
   * Testes
   * @param args
   */
  public static void main(String[] args) {
    Grafo grafo = fromPajek("src/dados/exemplo.pajek");
    //Grafo grafo = fromFile();
    //Grafo novo = fromPajek("src/dados/grafoSalvo.pajek");
    grafo.imprime_adjacencias();

    toPajek("src/dados/grafoSalvo.pajek", grafo);
  }

}
