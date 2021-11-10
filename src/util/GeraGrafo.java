package util;
import model.Grafo;

public class GeraGrafo 
{
  private static String ARQUIVO = "src/dados/data.txt";

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

  public static Grafo fromPajek() {
    return fromPajek(ARQUIVO);
  }
  
  public static Grafo fromPajek(String nomeArq) {
    Grafo grafo = new Grafo();
    Arq.openRead(nomeArq);
    // 1ª linha: qtd vertices
    String line = Arq.readLine();
    String[] arrLinha = line.split(" ");
    int qtdVertices = Integer.parseInt(arrLinha[1]);
    
              System.out.println(qtdVertices);
    for(int i = 0; i<qtdVertices; i++) {

    }
    return grafo;  
  }



  public static void main(String[] args) {
    
  }

}
