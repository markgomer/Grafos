package model;
import java.util.LinkedList;
import java.util.Random;

import util.Bolha;

public class Grafo {
    /**Conjunto de vértices contido no grafo */
    private LinkedList<Vertice> vertices;
    /**Tipo de ligação presente no grafo */
    private boolean grafoDirecionado;
    
    Random r = new Random();

    /**
     * cria um grafo vazio e direcionado
     */
    public Grafo() {
        this.grafoDirecionado = true;
        vertices = new LinkedList<>();
    }
    /**
     * cria um grafo vazio e não direcionado
     * @param grafoDirecionado indica se o grafo a ser criado será direcionado
     */
    public Grafo(boolean grafoDirecionado) {
        this.grafoDirecionado = grafoDirecionado;
        vertices = new LinkedList<>();
    }


    /**
     * Cria um vértice e insere no grafo.
     * Não checa se vértice existe, então pode
     * criar vértices duplicados.
     * @param rotulo rótulo do vértice a ser inserido no grafo
     * @return Vértice criado
     */
    public Vertice criaVertice(String rotulo) {
        Vertice resp;
        int indice = this.getNumeroVertices();
        resp = new Vertice(rotulo, indice);
        vertices.add(resp);
        
        return resp;
    }
    /**
     * Cria um vértice e insere no grafo apenas se
     * um vértice com o mesmo rótulo ainda não existe no grafo,
     * senão, retorna vértice que já existe.
     * @param rotulo rótulo do vértice a ser inserido no grafo
     * @return Vértice criado
     */
    public Vertice criaVerticeSeNaoExistir(String rotulo) {
        Vertice resp;
        int index = this.pesquisar(rotulo);
        boolean jaExisteVerticeComEsseRotulo = (index != -1);
        
        if(jaExisteVerticeComEsseRotulo) {
            resp = vertices.get(index);
        } else {
            int indice = this.getNumeroVertices();
            resp = new Vertice(rotulo, indice);
            vertices.add(resp);
        }
        
        return resp;
    }


    /**
     * cria vértice com rótulo equivalente ao inteiro fornecido
     * @param x rótulo do novo vértice
     * @return vértice criado
     */
    public Vertice criaVertice(int x) {
        String rotulo = Integer.toString(x);
        return criaVertice(rotulo);
    }

    /**
     * fornece vértice correspondente ao índice enviado
     * @param index índice do vértice dentro da lista "vertices"
     * @return vértice da lista de vértices
     */
    public Vertice getVertice(int index) {
        return this.vertices.get(index);
    }

    public Vertice getRandomVertice() {
        int random = this.r.nextInt(vertices.size());
        return vertices.get(random);
    }

    /**
     * @return LinkedList de todos os vértices contidos no grafo
     */
    public LinkedList<Vertice> getVertices() {
        return this.vertices;
    }


    /**
     * altera rótulo do vértice no índice i
     * @param i: índice do vértice na lista
     * @param novoRotulo: novo valor que o vértice irá tomar
     */
    public void seta_informacao(int i, String novoRotulo) {vertices.get(i).setRotulo(novoRotulo);}
    public void seta_informacao(Vertice v, String novoRotulo) {v.setRotulo(novoRotulo);}


    /**
     * Cria adjacencia entre dois vértices fornecidos
     * se o grafo não for direcionado, também cria adjacência reversa
     * @param v vértice origem
     * @param w vértice destino
     */
    public void cria_adjacencia(Vertice v, Vertice w) {
        v.cria_adjacencia(w);
        if(!grafoDirecionado) w.cria_adjacencia(v);
    }

    /** * /
    public void cria_adjacencia(int i, int j) {
        Vertice v = criaVertice(Integer.toString(i));
        Vertice w = criaVertice(Integer.toString(j));
        cria_adjacencia(v, w);
    }
    
    /** 
     * Cria adjacência a partir dos índices de vértices já criados
     * @param i índice do vértice de origem
     * @param j índice do vértice de destino
    */
    public void cria_adjacencia(int i, int j) {
        Vertice v = vertices.get(i);
        Vertice w = vertices.get(j);
        cria_adjacencia(v, w);
    }
    /**
     * cria adjacência entre dois vértices a partir dos rótulos.
     * se um rótulo não existir em algum vértice do grafo, 
     * um novo vértice é criado.
     * @param a rótulo do vértice de origem
     * @param b rótulo do vértice de destino
     */
    public void cria_adjacencia(String a, String b) {
        Vertice v = criaVerticeSeNaoExistir(a);
        Vertice w = criaVerticeSeNaoExistir(b);
        cria_adjacencia(v, w);
    }

    /**
     * remove adjacência entre dois vértices fornecidos, presentes no grafo
     * @param v vértice origem
     * @param w vértice destino
     * @return sinal de sucesso
     */
    public boolean remove_adjacencia(Vertice v, Vertice w) {return v.remove_adjacencia(w);}
    public void remove_adjacencia(int i, int j) {
        Vertice v = vertices.get(i);
        Vertice w = vertices.get(j);
        v.remove_adjacencia(w);
        if(!grafoDirecionado) w.remove_adjacencia(v);
    }


    /**
     * procura um vértice dentro da lista de vértices do grafo
     * @param rotulo rotulo a ser procurado
     * @return índice do vértice encontrado, -1 se não for encontrado
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
    /**
     * fornece o índice de um vértice do grafo
     * @param v vértice procurado
     * @return índice do vértice procurado
     */
    public int pesquisar(Vertice v) {
        return this.vertices.indexOf(v);
    }

    /**
     * @return número de vértices contidos no grafo
     */
    public int getNumeroVertices() {
        return vertices.size();
    }

    /**
     * @return número de arestas do grafo
     */
    public int getNumeroArestas() {
        int resp = 0;
        for (Vertice v : vertices) resp += v.numeroArestas();
        if(!grafoDirecionado) resp = resp/2;
        return resp;
    }


    /**
     * retorna o peso da adjacência entre i e j
     * @param i índice do vértice de origem
     * @param j índice do vértice de destino
     * @return peso da adjacência
     */
    public int getPeso(int i, int j) {
        Vertice v = this.vertices.get(i);
        Vertice w = this.vertices.get(j);
        return v.getPeso(w);
    }
    /**
     * retorna o peso da adjacência entre v e w
     * @param v vértice de origem
     * @param w vértice de destino
     * @return peso da adjacência
     */
    public int getPeso(Vertice v, Vertice w) {
        return v.getPeso(w);
    }


    /**
     * 
     * @param i indice do primeiro vertice
     * @param j indice do segundo vertice
     * @param peso novo peso entre os dois vertices
     * /
    public void seta_peso(int i, int j, int peso) {
        Vertice v = vertices.get(pesquisar(Integer.toString(i)));
        Vertice w = vertices.get(pesquisar(Integer.toString(j)));
        v.setPeso(w, peso);
        if(!grafoDirecionado) w.setPeso(v, peso);
    }
    /**
     * Seta o peso de dois vértices adjacentes
     * @param i índice do vértice origem 
     * @param j índice do vértice destino
     * @param peso
     */
    public void seta_peso(int i, int j, int peso) {
        Vertice v = vertices.get(i);
        Vertice w = vertices.get(j);
        v.setPeso(w, peso);
        if(!grafoDirecionado) w.setPeso(v, peso);
    }


    /**
     * altera o peso entre dois vértices de rótulos fornecidos
     * @param a rótulo do vértice de origem 
     * @param b rótulo do vértice de destino
     * @param peso novo peso entre os dois vértices
     */
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
     * imprime a lista de adjacências do grafo G
     */
    public void imprime_adjacencias() {for(Vertice v: vertices) System.out.println(v.toString());}

    /**
     * 
     */
    public void imprimeSemRotulos() {
        for(Vertice v : vertices) {
            System.out.println(v.adjacenciasToStr());
        }
    }
    
    //=====================================================================//
    
    /**
     * ordena lista vértices de acordo com a soma dos pesos de suas arestas
     */
    private void sortVertices() {new Bolha(vertices).sort();}


    /**
     * exibe os vértices com X maiores graus
     * @param x quantidade de vértices a serem exibidos
     */
    public void xMaioresGraus(int x){
        this.sortVertices();
        for(int i = vertices.size()-1; i > vertices.size() - x - 1; i--) {
            Vertice v = vertices.get(i);
            System.out.println(v.toString());
        }
    }
    /**
     * exibe os vértices com X menores graus
     * @param x quantidade de vértices a serem exibidos
     */
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


}


