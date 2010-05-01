package br.com.assinchronus.ai;

import java.util.List;

import br.com.assinchronus.componentes.Casa;

public class Arvore {
	
	private List<Arvore> arvores;
	
	private Casa[][] tabuleiro;
	
	private int valor;
	
	public Arvore() {
		// TODO Auto-generated constructor stub
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

	public void setTabuleiro(Casa[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	/***
	 * adiciona um filho na arvore
	 * @param filho nó filho do objeto atual
	 */
	public void addNode(Arvore filho) {
		this.arvores.add(filho);
	}
}
