package algoritmos;

import model.Grafo;
import model.Vertice;
import util.GeraGrafo;

import java.util.LinkedList;

public class Dijkstra {

        /************************************************** 
     * ALGORITMO DE DIJKISTRA
    ***************************************************/


    // find a vertex with minimum distance
    private static int minDistance(Grafo g, int path_array[], Boolean sptSet[])   { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
        for (int v = 0; v < g.getNumeroVertices(); v++) 
            if (sptSet[v] == false && path_array[v] <= min) { 
                min = path_array[v]; 
                min_index = v; 
            } 
   
        return min_index; 
    } 
   
    // print the array of distances (path_array)
    private static void printMinpath(Grafo g, int path_array[])   { 
        System.out.println("Vertex# \t Minimum Distance from Source"); 
        for (int i = 0; i < g.getNumeroVertices(); i++) {
            Vertice v = g.getVertice(i);
            System.out.println(v.rotuloToString() + " \t\t\t " + path_array[i]); 
        }
    }
    
    // Implementation of Dijkstra's algorithm for graph (adjacency matrix) 
    public static int[] algo_dijkstra(Grafo g, Vertice src_node)  {
        int index = g.pesquisar(src_node); 
        int path_array[] = new int[g.getNumeroVertices()]; // The output array. dist[i] will hold 
        // the shortest distance from src to i 
   
        // spt (shortest path set) contains vertices that have shortest path 
        Boolean sptSet[] = new Boolean[g.getNumeroVertices()]; 
   
        // Initially all the distances are INFINITE and stpSet[] is set to false 
        for (int i = 0; i < g.getNumeroVertices(); i++) { 
            path_array[i] = Integer.MAX_VALUE;
            sptSet[i] = false; 
        } 
   
        // Path between vertex and itself is always 0 
        path_array[index] = 0; 
        // now find shortest path for all vertices  
        for (int count = 0; count < g.getNumeroVertices() - 1; count++) { 
            // call minDistance method to find the vertex with min distance
            int u = minDistance(g, path_array, sptSet); 
              // the current vertex u is processed
            sptSet[u] = true; 
              // process adjacent nodes of the current vertex
            for (int v = 0; v < g.getNumeroVertices(); v++) 
   
                // if vertex v not in sptset then update it  
                if (!sptSet[v] && g.getPeso(u, v) != 0 && path_array[u] != 
                            Integer.MAX_VALUE && path_array[u] 
                            + g.getPeso(u, v) < path_array[v]) 
                            path_array[v] = path_array[u] + g.getPeso(u, v); 
        } 
   
        // print the path array 
        printMinpath(g, path_array); 
        return path_array;
    } 

    public static int[] algo_dijkstra(Grafo g, String rotulo) {
        int index = g.pesquisar(rotulo);
        Vertice v = g.getVertice(index);
        return algo_dijkstra(g, v);
    }


    public static void main(String[] args) {
        Grafo grafo = GeraGrafo.fromFile();
        //grafo.imprime_adjacencias();

        System.out.print("Numero de vertices do grafo: ");
        System.out.println(grafo.getNumeroVertices());
        
        System.out.print("Numero de arestas do grafo: ");
        System.out.println(grafo.getNumeroArestas());
        
        //grafo.imprime_adjacencias();

        System.out.println("20 maiores: ");
        grafo.xMaioresGraus(20);
        System.out.println("20 menores: ");
        grafo.xMenoresGraus(20);

        System.out.println("=======================\nBusca em profundidade: ");
        LinkedList<Vertice> caminho = grafo.buscaProfundidade("sandra.brawner@enron.com", "tk.lohman@enron.com");
        for(Vertice v : caminho) {
            System.out.println(v.rotuloToString());
        }

        System.out.println("=======================\nBusca em largura: ");
        LinkedList<Vertice> caminho2 = grafo.buscaLargura("sandra.brawner@enron.com", "tk.lohman@enron.com");
        for(Vertice v : caminho2) {
            System.out.println(v.rotuloToString());
        }
        
        System.out.println("=======================\nBusca por distancia: ");
        LinkedList<Vertice> grupo = grafo.buscaPorDistancia("sandra.brawner@enron.com", 1);
        for(Vertice v : grupo) {
            System.out.println(v.rotuloToString());
        }


        System.out.println("melhor caminho: ");
        algo_dijkstra(grafo, "sandra.brawner@enron.com");

    }

}

