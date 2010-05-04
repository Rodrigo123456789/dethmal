package br.com.assinchronus.ai;

import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;

public class Arvore {
	
	private List<Arvore> arvores = new ArrayList<Arvore>();
	
	private Casa[][] tabuleiro;
	
	private int valor;
	
	public Arvore(Casa[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public List<Arvore> getArvores() {
		return arvores;
	}

	public void setArvores(List<Arvore> arvores) {
		this.arvores = arvores;
	}
	
	public Casa[][] getTabuleiro() {
		return tabuleiro;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	/**
	 * adiciona um filho na arvore
	 * @param filho nó filho do objeto atual
	 */
	public void addNode(Arvore filho) {
		this.arvores.add(filho);
	}
	
	@Override
	public String toString() {
		return String.valueOf(valor);
	}
}
