package algoritmos;
// A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
// The program is for adjacency matrix representation of the graph

import model.Grafo;
import model.Vertice;

public class AGM {
    /****************************************************
     * ALGORITMO DE PRIM
    ****************************************************/
	
    // A utility function to find the vertex with minimum key
	// value, from the set of vertices not yet included in MST
	private static int minKey(Grafo g, int key[], Boolean mstSet[])
	{
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < g.getNumeroVertices(); v++)
			if (mstSet[v] == false && key[v] < min) {
				min = key[v];
				min_index = v;
			}

		return min_index;
	}


	// A utility function to print the constructed MST stored in
	// parent[]
	private static void printMST(Grafo g, int parent[])
	{
		System.out.println("Edge \tWeight");
		for (int i = 1; i < g.getNumeroVertices(); i++) {
            Vertice v = g.getVertice(parent[i]);
            Vertice w = g.getVertice(i);
            int peso = v.getPeso(w);
            System.out.println(v.rotuloToString() + " - " + w.rotuloToString() + "\t" + peso);
        }
	}


	// Function to construct and print MST for a graph represented
	// using adjacency matrix representation
	public static void primMST(Grafo g)
	{
		// Array to store constructed MST
		int parent[] = new int[g.getNumeroVertices()];

		// Key values used to pick minimum weight edge in cut
		int key[] = new int[g.getNumeroVertices()];

		// To represent set of vertices included in MST
		Boolean mstSet[] = new Boolean[g.getNumeroVertices()];

		// Initialize all keys as INFINITE
		for (int i = 0; i < g.getNumeroVertices(); i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}

		// Always include first 1st vertex in MST.
		key[0] = 0; // Make key 0 so that this vertex is
		// picked as first vertex
		parent[0] = -1; // First node is always root of MST

		// The MST will have getNumeroVertices() vertices
		for (int count = 0; count < g.getNumeroVertices() - 1; count++) {
			// Pick thd minimum key vertex from the set of vertices
			// not yet included in MST
			int u = minKey(g, key, mstSet);
            Vertice vertice1 = g.getVertice(u);

			// Add the picked vertex to the MST Set
			mstSet[u] = true;

			// Update key value and parent index of the adjacent
			// vertices of the picked vertex. Consider only those
			// vertices which are not yet included in MST
			for (int v = 0; v < g.getNumeroVertices(); v++){
				Vertice vertice2 = g.getVertice(v);
                int peso = vertice1.getPeso(vertice2);
                // graph[u][v] is non zero only for adjacent vertices of m
				// mstSet[v] is false for vertices not yet included in MST
				// Update the key only if graph[u][v] is smaller than key[v]
				if (peso != 0 && mstSet[v] == false && peso < key[v]) {
					parent[v] = u;
					key[v] = peso;
				}
            }
		}

		// print the constructed MST
		printMST(g, parent);
	}

	public static void main(String[] args)
	{
		
	}
}
// This code is contributed by Aakash Hasija
