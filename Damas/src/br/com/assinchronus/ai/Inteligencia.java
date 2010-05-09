package br.com.assinchronus.ai;

import br.com.assinchronus.componentes.Tabuleiro;

public class Inteligencia {

	private static final int NIVEL = 4;

	Arvore arvore;

	RegraAI regraAI;

	public Inteligencia(Arvore arvore) {
		regraAI = new RegraAI(NIVEL);
		this.arvore = arvore;
	}

	public Tabuleiro jogar() {
		regraAI.verificaCasas(arvore, NIVEL);

		Tabuleiro tab = new Tabuleiro();
		tab.setTabuleiro(miniMax(arvore, NIVEL).getTabuleiro());
		return tab;
	}

	private Arvore miniMax(Arvore node, int nivel) {
		Arvore min = new Arvore(node.getTabuleiro());
		Arvore max = new Arvore(node.getTabuleiro());

		min.setValor(350);
		max.setValor(-350);

		if (node.getArvores().isEmpty()) {
			// nivel folha
			return node;
		} else {
			for (Arvore filho : node.getArvores()) {
				// Peca preta
				filho.setValor(miniMax(filho, nivel - 1).getValor());
				if (nivel % 2 == NIVEL % 2) {
					if (max.getValor() < filho.getValor()) {
						max = filho;
					}
					// Peca branca
				} else {
					if (min.getValor() > filho.getValor()) {
						min = filho;
					}

				}
			}

		}
		System.out.println(min.getValor() == 350 ? max : min);
		return min.getValor() == 350 ? max : min;
	}

	public static void main(String[] args) {
		long time = System.currentTimeMillis();

		Arvore arvore = new Arvore(new Tabuleiro().getTabuleiro());
		Inteligencia inteligencia = new Inteligencia(arvore);
		inteligencia.jogar();

		time = System.currentTimeMillis() - time;

		System.out.println(time);
	}
}