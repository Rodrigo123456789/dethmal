package br.com.assinchronus.ai;

import java.util.List;

import br.com.assinchronus.componentes.Tabuleiro;

public class Arvore {
	
	private List<Arvore> arvores;
	
	private Tabuleiro tabuleiro;
	
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
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}
