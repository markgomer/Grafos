package model;
import java.util.LinkedList;

public class Vertice{

    private String rotulo;
    private int indice; // começa com 0
    private boolean visitado = false;
    private double centralidade;
    private LinkedList <Vertice> adjacentes;
    private LinkedList <Integer> pesosArestas;


    public Vertice(String rotulo, int indice){
        this.rotulo = rotulo;
        this.indice = indice;
        adjacentes = new LinkedList<>();
        pesosArestas = new LinkedList<>();
    }

    
    public int getIndice() {
       return this.indice;
    }


    public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
    public boolean getVisitado() {
		return this.visitado;
	}


    public double getCentralidade() {
        return centralidade;
    }


    public void setCentralidade(double centralidade) {
        this.centralidade = centralidade;
    }


    public Vertice getAdjacente(int index) {
        return adjacentes.get(index);
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
        if( !adjacentes.isEmpty()  && !caminho.contains(this) ) {
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
        if (!adjacentes.isEmpty() && !caminho.contains(this) ) {
            for (int i = 0; i < adjacentes.size(); i++) {
                Vertice v = adjacentes.get(i);
                caminho.add(v);
                if (v.equals(procurado)) {
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
        if (distancia <= 1) {
            // adicionar todos os adjacentes a resposta
            resp.addAll(adjacentes);
        } else {
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


    public String adjacenciasToStr() {
        StringBuilder builder = new StringBuilder();
        for (Vertice v : this.adjacentes) {
            builder.append(this.indice).append(" ");
            builder.append(v.indice).append(" ").append(this.getPeso(v)).append("\n");
        }
        String resp = builder.toString();
        return resp;
    }


    @Override
    public String toString() {
        String resp = "";
        for (int i = 0; i < adjacentes.size(); i++) {
            Vertice v = adjacentes.get(i);
            int peso = pesosArestas.get(i);
            resp += this.rotulo + "\t";
            resp += v.rotulo + "\t" + peso + "\n";
        }
        return resp;
    }

    


    
}