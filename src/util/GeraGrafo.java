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
  public static Grafo fromFile() {
    Grafo grafo = new Grafo();
    Arq.openRead(ARQUIVO);
    Arq.readLine(); // pular primeira linha...
    while(Arq.hasNext()) {
      String linha = Arq.readLine();
      String arrLinha[] = linha.split(",");
      String remetente = arrLinha[0];
      for(int i = 1; i < arrLinha.length; i++) {
        String destinatario = arrLinha[i];
        grafo.cria_adjacencia(grafo.criaVertice(remetente), grafo.criaVertice(destinatario));
      }
    }
    return grafo;
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
    }
    // 2º passo criar arestas
    Arq.readLine();
    while(Arq.hasNext()) {
      int indexOrigem = Arq.readInt() - 1;
      int indexDestino = Arq.readInt() - 1;
      int peso = Arq.readInt();
      grafo.cria_adjacencia(indexOrigem, indexDestino);
      grafo.seta_peso(indexOrigem, indexDestino, peso);
    }
    
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
      Vertice v = grafo.getVertice(i);
      Arq.println(Integer.toString(i + 1) + " \"" + v.rotuloToString() + "\"");
    }
    
    Arq.println("*Edges ");
    for(int i = 0; i<numVertices; i++) {
      Vertice v = grafo.getVertice(i);
      Arq.print(v.adjacenciasToStr());
    }

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
