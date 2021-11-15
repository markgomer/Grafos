package algoritmos;

import model.Grafo;
import model.Vertice;
import util.GeraGrafo;

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
        System.out.println("Vértice Destino \t Minimum Distance from Source"); 
        for (int i = 0; i < g.getNumeroVertices(); i++) {
            Vertice v = g.getVertice(i);
            System.out.println(v.rotuloToString() + "\t\t " + path_array[i]); 
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


    public static int melhorCaminho(Grafo g, Vertice origem, Vertice destino) {
        int resp;
        int[] todosOsMelhoresCaminhos = algo_dijkstra(g, origem);
        resp = todosOsMelhoresCaminhos[destino.getIndice()];
        return resp;
    }



    public static void main(String[] args) {
        Grafo g = new Grafo(true);
        for (int i = 0; i < 9; i++) {
            g.criaVertice(i);
        }

		g.cria_adjacencia(0, 1); g.seta_peso(0, 1, 2);
		g.cria_adjacencia(0, 2); g.seta_peso(0, 2, 1);
		g.cria_adjacencia(1, 3); g.seta_peso(1, 3, 3);
		g.cria_adjacencia(1, 4); g.seta_peso(1, 4, 2);
		g.cria_adjacencia(2, 3); g.seta_peso(2, 3, 1);
		g.cria_adjacencia(2, 5); g.seta_peso(2, 5, 2);
		g.cria_adjacencia(3, 4); g.seta_peso(3, 4, 4);
		g.cria_adjacencia(3, 5); g.seta_peso(3, 5, 3);
		g.cria_adjacencia(4, 6); g.seta_peso(4, 6, 1);
		g.cria_adjacencia(5, 6); g.seta_peso(5, 6, 3);
		g.cria_adjacencia(6, 7); g.seta_peso(6, 7, 2);
		g.cria_adjacencia(6, 8); g.seta_peso(6, 8, 2);
		g.cria_adjacencia(7, 8); g.seta_peso(7, 8, 1);

        System.out.println("melhor caminho: ");
        Vertice origem = g.getVertice(0);
        Vertice destino = g.getVertice(7);
        int mc = melhorCaminho(g, origem, destino);

        System.out.println(mc);

    }

}

