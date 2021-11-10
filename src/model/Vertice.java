package model;
import java.util.LinkedList;

public class Vertice{

    private String rotulo;
    private LinkedList <Vertice> adjacentes;
    private LinkedList <Integer> pesosArestas;


    public Vertice(String rotulo){
        this.rotulo = rotulo;
        adjacentes = new LinkedList<>();
        pesosArestas = new LinkedList<>();
    }


    public LinkedList<Vertice> getAdjacentes() {
        return adjacentes;
    }


    public void cria_adjacencia(Vertice v) {
        boolean jaEhAdjacente = adjacentes.contains(v);
        if(jaEhAdjacente) {
            this.incrementaPeso(v);
        } else {
            pesosArestas.add(1);
            adjacentes.add(v);
        }
    }


    public boolean remove_adjacencia(Vertice v) {
        int indexToRemove = adjacentes.indexOf(v);
        if(indexToRemove != -1) {
            pesosArestas.remove(indexToRemove);
        } else {
            return false;
        }
        return adjacentes.remove(v);
    }


    /**
     * Retorna peso da aresta entre este e o vertice enviado
     * @param v outro vertice
     * @return peso da aresta entre os vertices
     */
    public int getPeso(Vertice v) {
        int resp = 0;
        int indiceDoOutro = adjacentes.indexOf(v);
        if(indiceDoOutro != -1) resp = pesosArestas.get(indiceDoOutro);
        
        return resp;
    }
    public int getPeso(int index) {
        int resp = 0;
        if(index < adjacentes.size()) resp = pesosArestas.get(index);
        return resp;
    }

    public void setPeso(int index, int peso) {
      if(index < adjacentes.size()) pesosArestas.get(index);
      pesosArestas.set(index, peso);
    }
    public void setPeso(Vertice v, int peso) {
      int index = adjacentes.indexOf(v);
      //if(index == -1) 
      setPeso(index, peso);
    }


    public int incrementaPeso(int index) {
        int novoPeso = pesosArestas.get(index) + 1;
        pesosArestas.set(index, novoPeso);
        return novoPeso;
    }
    public int incrementaPeso(Vertice v) {
        int novoPeso = -1;
        if(adjacentes.contains(v)) {
            int index = adjacentes.indexOf(v);
            novoPeso = pesosArestas.get(index) + 1;
            pesosArestas.set(index, novoPeso);
        } else {
            System.out.println("Vertices não são adjacentes para incrementar peso");
        }
        return novoPeso;
    }


    public int numeroArestas(){
      return adjacentes.size();
    }


    public int somaPesosDasArestas() {
      int resp = 0;
      for(int x : pesosArestas) resp += x;
      return resp;
    }


    public void setRotulo(String rotulo) {this.rotulo = rotulo;}


    public boolean comparaRotulo(String rotulo) {return this.rotulo.equals(rotulo);}


    public boolean ehAdjacente(Vertice outro) {
      return adjacentes.contains(outro);
    }
    public boolean ehAdjacente(String outroRotulo) {
      boolean resp = false;
      for(int i = 0; i<adjacentes.size(); i++) {
        Vertice v = this.adjacentes.get(i);
        if(v.comparaRotulo(outroRotulo)) {
          resp = true;
          i = adjacentes.size();
        }
      }
      return resp;
    }


    public LinkedList<Vertice> buscaProfundidade(LinkedList<Vertice> caminho, Vertice procurado) {
      if( ! (adjacentes.isEmpty()) ) {
        for(Vertice v : adjacentes) {
          caminho.add(v);
          if(v.equals(procurado)) {
            return caminho;
          }
          v.buscaProfundidade(caminho, procurado);
        }
      }
      return caminho;
    }
    public LinkedList<Vertice> caminhoEmProfundidade(LinkedList<Vertice> caminho) {
        if( ! (adjacentes.isEmpty()) ) {
            for(Vertice v : adjacentes) {
                caminho.add(v);
                v.caminhoEmProfundidade(caminho);
            }
        }
        return caminho;
    }


    public LinkedList<Vertice> buscaLargura(LinkedList<Vertice> caminho, Vertice procurado) {
      if( ! (adjacentes.isEmpty()) ) {
        for(int i = 0; i < adjacentes.size(); i++) {
          Vertice v = adjacentes.get(i);
          caminho.add(v);
          if(v.equals(procurado)) {
            i = adjacentes.size();
          }
        }
          for (Vertice v : adjacentes) {
              v.buscaLargura(caminho, procurado);
          }
      }
      return caminho;
    }


    public LinkedList<Vertice> buscaPorDistancia(LinkedList<Vertice> resp, int distancia) {
      if( distancia <= 1 ) {
        // adicionar todos os adjacentes a resposta
        resp.addAll(adjacentes);
      }
      else {
          for (Vertice v : adjacentes) {
              distancia--;
              v.buscaPorDistancia(resp, distancia);
          }
      }

      return resp;
    }

  
    public String rotuloToString() {
      return this.rotulo;
    }


    @Override
    public String toString() {
      String resp = "";
      resp += "Vertice=" + this.rotulo;
      resp += "\n\tadjacentes" + "(" + this.numeroArestas() + ") => {\n";
      for(int i = 0; i<adjacentes.size(); i++) {
        Vertice v = adjacentes.get(i);
        int x = pesosArestas.get(i);
        resp += "\t\t" + v.rotulo + ":peso=" + x + "\n";
      }
      resp += "}";
      return resp;
    }

    


    
}