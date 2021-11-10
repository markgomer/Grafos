package util;
import java.util.LinkedList;

import model.Vertice;

import java.util.Collections;

/**
 * Metodo de ordenacao da bolha
 * @author Max do Val Machado
 * @version 3 08/2020
 */
public class Bolha {
	private LinkedList<Vertice> array;
	private int n;

	/**
	 * Construtor.
	 */
	public Bolha(LinkedList <Vertice> array){
			this.array = array;
			n = array.size();
	}

	/**
	* Troca o conteudo de duas posicoes do array
	* @param i int primeira posicao
	* @param j int segunda posicao
	*/
	public void swap(int i, int j) {
		Collections.swap(array, i, j);
	}


	/**
	 * Algoritmo de ordenacao Bolha.
	 * Ordena os vértices em um LinkedList de acordo com 
	 * o número de arestas. 
	 */
	public void sort() {
		for (int i = (n - 1); i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array.get(j).somaPesosDasArestas() > array.get(j + 1).somaPesosDasArestas()) {
					swap(j, j+1);
				}
			}
		}
	}

}
