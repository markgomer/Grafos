package algoritmos;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import model.Grafo;
import model.Vertice;
import util.GeraGrafo;

public class Centralidade {
	private static Grafo grafo;
  private static LinkedList<Vertice> vertices;
  private static int size;


	private static Vertice getUnvisitedChildVertex (Vertice v) {
		int index= v.getIndice();
		int j = 0;
		while(j<grafo.getNumeroVertices()) {
			if(grafo.getPeso(index, j) == 1 && (vertices.get(j)).getVisitado()==false) {
				return (Vertice)vertices.get(j);
			}
			j++;
		}

		return null;
	}

	
	public static void buscaLargura() {
		for (Vertice origin : vertices) {
			Queue<Vertice> fila = new LinkedList<Vertice>();
			int[] distance = new int[size+1];
			for (int i = 0; i < distance.length; i++) {
				distance[i] = -1;
			}
			distance[origin.getIndice()] = 0;
			fila.add(origin);
			System.out.println(origin.rotuloToString());
			origin.setVisitado(true);
			while(!fila.isEmpty()) {
				Vertice n=(Vertice)fila.remove();
				Vertice child=null;
				while((child=getUnvisitedChildVertex(n))!=null) {
					child.setVisitado(true);
					distance[child.getIndice()] = distance[n.getIndice()]+1;
					System.out.println(child.rotuloToString());
					fila.add(child);
				}
			}
			centralidade(origin, distance);
			clearNodes();
		}
	}

  public static void buscaLargura(Vertice origin) {
    Queue<Vertice> fila = new LinkedList<Vertice>();
    int[] distance = new int[size+1];
    for (int i = 0; i < distance.length; i++) {
      distance[i] = -1;
    }
    distance[origin.getIndice()] = 0;
    fila.add(origin);
    System.out.print(origin.getIndice() + "\r");
    origin.setVisitado(true);
    while(!fila.isEmpty()) {
      Vertice n=(Vertice)fila.remove();
      Vertice child=null;
      while((child=getUnvisitedChildVertex(n))!=null) {
        child.setVisitado(true);
        distance[child.getIndice()] = distance[n.getIndice()]+1;
        System.out.print(child.getIndice() + "\r");
        fila.add(child);
      }
    }
    System.out.println();
    centralidade(origin, distance);
    clearNodes();
		
	}
	
	public static void centralidade(Vertice v, int[] distances) {
		double centralidade = 0;
		for (int i = 0; i < distances.length; i++) {
			centralidade += Math.pow(2, -distances[i]);
		}
		v.setCentralidade(centralidade);
	}
	

	private static void clearNodes() {
		int i=0;
		while(i<size) {
			Vertice v = vertices.get(i);
			v.setVisitado(false);
			i++;
		}
	}
	

	public static void calculateCloseness(Grafo g) {
    grafo = g;
    vertices = g.getVertices();

		size = vertices.size();
		buscaLargura();
		vertices.sort(new Comparator<Vertice>() {
			@Override
			public int compare(Vertice o1, Vertice o2) {
				if(o1.getCentralidade() > o2.getCentralidade()) return -1;
				if(o1.getCentralidade() < o2.getCentralidade()) return 1;
				return 0;
			}
		});
	}
	public static void calculateCloseness(Grafo g, Vertice vertice) {
    grafo = g;
    vertices = g.getVertices();

		size = vertices.size();
		buscaLargura(vertice);
	}


  public static void main(String[] args) {
    Grafo grafo = GeraGrafo.fromPajek("src/dados/aleatorio.pajek");
    Vertice v = grafo.getVertice(50);
    calculateCloseness(grafo, v);

    System.out.println(v.rotuloToString() + "\t" + v.getCentralidade());

    /** * /
    for(Vertice v : grafo.getVertices()) {
      System.out.println(v.rotuloToString() + "\t" + v.getCentralidade());
    }
    /** */
  }

}