package algoritmos;

import model.Grafo;
import model.Vertice;
import util.GeraGrafo;

public class Dijkstra {

    /************************************************** 
     * ALGORITMO DE DIJKISTRA
    ***************************************************/


    // find a vertex with minimum distance
    private static int minDistance(Grafo g, int menorCaminhoPara[], Boolean jaCalculado[])   { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, indexMaisPerto = -1; 
        for (int vertIndex = 0; vertIndex < g.getNumeroVertices(); vertIndex++) 
            if (jaCalculado[vertIndex] == false && menorCaminhoPara[vertIndex] <= min) { 
                min = menorCaminhoPara[vertIndex]; 
                indexMaisPerto = vertIndex; 
            }
        return indexMaisPerto; 
    } 

   
    // print the array of distances (menorCaminhoPara)
    private static void printMinpath(Grafo g, int menorCaminhoPara[])   { 
        System.out.println("Vértice Destino \t Minimum Distance from Source"); 
        for (int i = 0; i < g.getNumeroVertices(); i++) {
            Vertice v = g.getVertice(i);
            System.out.println(v.rotuloToString() + "\t\t " + menorCaminhoPara[i]); 
        }
    }

    
    // Implementation of Dijkstra's algorithm for graph (adjacency matrix) 
    public static int[] algo_dijkstra(Grafo g, Vertice origem)  {
        int indexOrigem = g.pesquisar(origem); 
        int menorDistanciaPara[] = new int[g.getNumeroVertices()]; // The output array. dist[i] will hold 
        // the shortest distance from src to i 
   
        // spt (shortest path set) contains vertices that have shortest path 
        Boolean jaCalculado[] = new Boolean[g.getNumeroVertices()]; 
   
        // Initially all the distances are INFINITE and stpSet[] is set to false 
        for (int i = 0; i < g.getNumeroVertices(); i++) { 
            menorDistanciaPara[i] = Integer.MAX_VALUE;
            jaCalculado[i] = false; 
        } 
   
        // Path between vertex and itself is always 0 
        menorDistanciaPara[indexOrigem] = 0; 

        // now find shortest path for all vertices  
        for (int count = 0; count < g.getNumeroVertices() - 1; count++) { 
            // call minDistance method to find the vertex with min distance
            int indexVertMaisPerto = minDistance(g, menorDistanciaPara, jaCalculado); 
              // the current vertex u is processed
            jaCalculado[indexVertMaisPerto] = true; 
              // process adjacent nodes of the current vertex
            for (int v = 0; v < g.getNumeroVertices(); v++) 
   
                // if vertex v not in sptset then update it  
                if (!jaCalculado[v] && g.getPeso(indexVertMaisPerto, v) != 0 && 
                        menorDistanciaPara[indexVertMaisPerto] != Integer.MAX_VALUE && 
                        menorDistanciaPara[indexVertMaisPerto] + g.getPeso(indexVertMaisPerto, v) < menorDistanciaPara[v]) 
                    menorDistanciaPara[v] = menorDistanciaPara[indexVertMaisPerto] + g.getPeso(indexVertMaisPerto, v); 
        } 
   
        // print the path array 
        printMinpath(g, menorDistanciaPara); 
        return menorDistanciaPara;
    } 


    // Implementation of Dijkstra's algorithm for graph (adjacency matrix) 
    public static int algo_dijkstra(Grafo g, Vertice origem, Vertice destino)  {
        int indexOrigem = g.pesquisar(origem); 
        int indexDestino = g.pesquisar(destino);
        int menorDistanciaPara[] = new int[g.getNumeroVertices()]; // The output array. dist[i] will hold 
   
        // spt (shortest path set) contains vertices that have shortest path 
        Boolean jaCalculado[] = new Boolean[g.getNumeroVertices()]; 
   
        // Initially all the distances are INFINITE and stpSet[] is set to false 
        for (int i = 0; i < g.getNumeroVertices(); i++) { 
            menorDistanciaPara[i] = Integer.MAX_VALUE;
            jaCalculado[i] = false; 
        } 
        // Path between vertex and itself is always 0 
        menorDistanciaPara[indexOrigem] = 0; 

        // now find shortest path for all vertices  
        for (int count = 0; count < g.getNumeroVertices() - 1; count++) { 
            // call minDistance method to find the vertex with min distance
            int indexMaisPerto = minDistance(g, menorDistanciaPara, jaCalculado); 
              // the current vertex u is processed
            jaCalculado[indexMaisPerto] = true; 
              // process adjacent nodes of the current vertex
            for (int v = 0; v < g.getNumeroVertices(); v++) 
   
                // if vertex v not in sptset then update it  
                if (!jaCalculado[v] && g.getPeso(indexMaisPerto, v) != 0 && 
                        menorDistanciaPara[indexMaisPerto] != Integer.MAX_VALUE && 
                        menorDistanciaPara[indexMaisPerto] + g.getPeso(indexMaisPerto, v) < menorDistanciaPara[v]) 
                    menorDistanciaPara[v] = menorDistanciaPara[indexMaisPerto] + g.getPeso(indexMaisPerto, v); 
        } 
   
        return menorDistanciaPara[indexDestino];
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

