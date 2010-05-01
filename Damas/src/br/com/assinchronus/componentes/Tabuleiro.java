package br.com.assinchronus.componentes;

/**
 * 
 * @author Pilon
 * @version 1.0.1
 */

public class Tabuleiro {

	// Array com todas as casas validas do jogo
	private Casa[][] tabuleiro = new Casa[8][8];

	/**
	 * Nome: Construtor Descrio: Monta o tabuleiro
	 */
	public Tabuleiro() {

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					Casa casa = new Casa();
					casa.setLinha(i);
					casa.setColuna(j);
					tabuleiro[i][j] = casa;
				}
			}
		}
		colocarPecas(Peao.BRANCA);
		colocarPecas(Peao.PRETA);
	}

	/**
	 * Nome: colocarPecas Descrio: Coloca a pea no tabuleiro
	 * 
	 * @param cor
	 *            - Cor da Pea
	 */
	private void colocarPecas(int cor) {
		Pecas peca = null;
		Casa casa = null;

		int linhaInicio = 0;
		int linhaFim = 0;

		if (cor == Peao.BRANCA) {
			linhaInicio = 5;
			linhaFim = 7;
		} else if (cor == Peao.PRETA) {
			linhaInicio = 0;
			linhaFim = 2;
		}

		for (; linhaInicio <= linhaFim; linhaInicio++) {
			for (int j = 0; j < tabuleiro[linhaInicio].length; j++) {
				if ((linhaInicio % 2 == 0 && j % 2 == 0) || (linhaInicio % 2 == 1 && j % 2 == 1)) {
					peca = new Peao();

					casa = tabuleiro[linhaInicio][j];

					peca.setCor(cor);

					casa.setPeca(peca);
				}
			}
		}
	}
	
	public Casa[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Casa[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
}