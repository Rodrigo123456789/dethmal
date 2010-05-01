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

					if(i==0 || i==7 || j==0 || j==7){
						casa.setValor(4);
					}else if(i==1 || i==6 || j==1 || j==6){
						casa.setValor(3);
						}else if(i==2 || i==5 || j==2 || j==5){
							casa.setValor(2);
							}else if(i==3 || i==4 || j==3 || j==4){
								casa.setValor(1);
							}

					// setando valores das casas para heuristica posicional
					if (i == 0 || i == 7 || j == 0 || j == 7) {
						casa.setValor(4);
					} else if (i == 1 || i == 6 || j == 1 || j == 6) {
						casa.setValor(3);
					} else if (i == 2 || i == 5 || j == 2 || j == 5) {
						casa.setValor(2);
					} else if (i == 3 || i == 4 || j == 3 || j == 4) {
						casa.setValor(1);
					}
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