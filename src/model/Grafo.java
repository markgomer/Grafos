package model;
import java.util.LinkedList;

import util.Bolha;

public class Grafo {
    private LinkedList<Vertice> vertices;
    private boolean grafoDirecionado;


    public Grafo() {
        this.grafoDirecionado = true;
        vertices = new LinkedList<>();
    }
    public Grafo(boolean grafoDirecionado) {
        this.grafoDirecionado = grafoDirecionado;
        vertices = new LinkedList<>();
    }


    public Vertice criaVertice(String rotulo) {
        Vertice resp;
        int index = this.pesquisar(rotulo);
        boolean jaExisteVerticeComEsseRotulo = (index != -1);
        
        if(jaExisteVerticeComEsseRotulo) {
            resp = vertices.get(index);
        } else {
            resp = new Vertice(rotulo);
            vertices.add(resp);
        }
        
        return resp;
    }
    public Vertice criaVertice(int x) {
        String rotulo = Integer.toString(x);
        return criaVertice(rotulo);
    }

    public Vertice getVertice(int index) {
        return this.vertices.get(index);
    }

    public LinkedList<Vertice> getVertices() {
        return this.vertices;
    }


    /**
     * atualiza a informação do nó i com o valor V
     * (que deve ser uma string) no grafo G
     * @param i: índice do vértice
     * @param novoRotulo: novo valor que o vértice irá tomar
     */
    public void seta_informacao(int i, String novoRotulo) {vertices.get(i).setRotulo(novoRotulo);}
    public void seta_informacao(Vertice v, String novoRotulo) {v.setRotulo(novoRotulo);}



    public void cria_adjacencia(Vertice v, Vertice w) {
        v = criaVertice(v.rotuloToString());
        w = criaVertice(w.rotuloToString());
        v.cria_adjacencia(w);
        if(!grafoDirecionado) w.cria_adjacencia(v);
    }
    public void cria_adjacencia(int i, int j) {
        Vertice v = criaVertice(Integer.toString(i));
        Vertice w = criaVertice(Integer.toString(j));
        cria_adjacencia(v, w);
    }
    public void cria_adjacencia(String a, String b) {
        Vertice v = criaVertice(a);
        Vertice w = criaVertice(b);
        cria_adjacencia(v, w);
    }


    public boolean remove_adjacencia(Vertice v, Vertice w) {return v.remove_adjacencia(w);}
    public void remove_adjacencia(int i, int j) {
        Vertice v = vertices.get(i);
        Vertice w = vertices.get(j);
        v.remove_adjacencia(w);
        if(!grafoDirecionado) w.remove_adjacencia(v);
    }


    /**
     * procura rótulo e retorna indice do vertice
     */
    public int pesquisar(String rotulo) {
        int resp = -1;
        for(int i = 0; i<vertices.size(); i++) {
            Vertice v = vertices.get(i);
            if(v.comparaRotulo(rotulo)) {
                resp = i;
                i = vertices.size();
            }
        }
        return resp;
    }
    public int pesquisar(Vertice v) {
        return this.vertices.indexOf(v);
    }


    public int getNumeroVertices() {
        return vertices.size();
    }


    public int getNumeroArestas() {
        int resp = 0;
        for (Vertice v : vertices) resp += v.numeroArestas();
        return resp;
    }


    /**
     * retorna o peso da adjacência entre i e j
     */
    public int getPeso(int i, int j) {
        Vertice v = this.vertices.get(i);
        Vertice w = this.vertices.get(j);
        return v.getPeso(w);
    }
    public int getPeso(Vertice v, Vertice w) {
        return v.getPeso(w);
    }


    /**
     * 
     * @param i indice do primeiro vertice
     * @param j indice do segundo vertice
     * @param peso novo peso entre os dois vertices
     */
    public void seta_peso(int i, int j, int peso) {
        Vertice v = vertices.get(pesquisar(Integer.toString(i)));
        Vertice w = vertices.get(pesquisar(Integer.toString(j)));
        v.setPeso(w, peso);
        if(!grafoDirecionado) w.setPeso(v, peso);
    }
    public void seta_peso(String a, String b, int peso) {
        Vertice v = vertices.get(pesquisar(a));
        Vertice w = vertices.get(pesquisar(b));
        v.setPeso(w, peso);
        if(!grafoDirecionado) w.setPeso(v, peso);
    }


    /**
     * informa número de adjacentes ao vértice com índice i
     * @param i índice do vértice
     * @return número de adjacentes ao vértice
     */
    public int numeroDeAdjacentes(int i) {
        Vertice v = vertices.get(i);
        return v.numeroArestas();
    }
    public int numeroDeAdjacentes(Vertice v) {return v.numeroArestas();}


    /**
     * imprime a matriz de adjacências do grafo G
     */
    public void imprime_adjacencias() {for(Vertice v: vertices) System.out.println(v.toString());}
    
    //=====================================================================//
    
    private void sortVertices() {new Bolha(vertices).sort();}


    public void xMaioresGraus(int x){
        this.sortVertices();
        for(int i = vertices.size()-1; i > vertices.size() - x - 1; i--) {
            Vertice v = vertices.get(i);
            System.out.println(v.toString());
        }
    }
    public void xMenoresGraus(int x){
        this.sortVertices();
        for(int i = 0; i < x; i++) {
            Vertice v = vertices.get(i);
            System.out.println(v.toString());
        }
    }


    /**
     * verifica se um indivíduo X pode alcançar um indivíduo Y 
     * retornando e mostrando o caminho percorrido em uma lista
     */
    public LinkedList<Vertice> buscaProfundidade(Vertice origem, Vertice destino){
        LinkedList<Vertice> caminho = new LinkedList<>();
        origem.buscaProfundidade(caminho, destino);
        return caminho;
    }
    public LinkedList<Vertice> buscaProfundidade(String origem, String destino){
        Vertice o = vertices.get(pesquisar(origem));
        Vertice d = vertices.get(pesquisar(destino));
        return buscaProfundidade(o, d);
    }
    
    /**
     * verifica se um indivíduo X pode alcançar um indivíduo Y 
     * retornando e mostrando o caminho percorrido (nós visitados) 
     * em uma lista
     */
    public LinkedList<Vertice> buscaLargura(Vertice origem, Vertice destino) {
        LinkedList<Vertice> caminho = new LinkedList<>();
        origem.buscaLargura(caminho, destino);
        return caminho;
    }
    public LinkedList<Vertice> buscaLargura(String origem, String destino) {
        Vertice o = vertices.get(pesquisar(origem));
        Vertice d = vertices.get(pesquisar(destino));
        return buscaLargura(o, d);
    }


    /**
     * retorne uma lista com os nós que estão a uma distância de D arestas 
     * (D saltos) de um nó N.
     */
    public LinkedList<Vertice> buscaPorDistancia(Vertice origem, int distancia) {
        LinkedList<Vertice> resp = new LinkedList<>();
        resp = origem.buscaPorDistancia(resp, distancia);
        return resp;
    }
    public LinkedList<Vertice> buscaPorDistancia(String origem, int distancia) {
        Vertice o = vertices.get(pesquisar(origem));
        return buscaPorDistancia(o, distancia);
    }


    //========================================================================


    //===========================================================



    




}


