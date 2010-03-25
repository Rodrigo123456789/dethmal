package br.com.assinchronus.componentes;

/**
 * 
 * @author Pilon
 * @version 1.0.1
 */

public class Tabuleiro {

	// Array com todas as casas validas do jogo
	private Casa[][] tabuleiro = new Casa[10][10];

	/**
	 * Nome: Construtor Descrio: Monta o tabuleiro
	 */
	public Tabuleiro() {

		for (int i = 1; i < tabuleiro.length - 1; i++) {
			for (int j = 1; j < tabuleiro[i].length - 1; j++) {
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
			linhaInicio = 6;
			linhaFim = 8;
		} else if (cor == Peao.PRETA) {
			linhaInicio = 1;
			linhaFim = 3;
		}

		for (; linhaInicio <= linhaFim; linhaInicio++) {
			for (int j = 1; j < tabuleiro[linhaInicio].length - 1; j++) {
				if ((linhaInicio % 2 == 0 && j % 2 == 0) || (linhaInicio % 2 == 1 && j % 2 == 1)) {
					peca = new Peao();

					casa = tabuleiro[linhaInicio][j];

					peca.setCor(cor);

					casa.setPeca(peca);
				}
			}
		}
	}

	/**
	 * Nome: printTabuleiro Descrio: Imprimi o tabuleiro
	 */
	public void printTabuleiro() {
		for (int i = 1; i < tabuleiro.length - 1; i++) {
			for (int j = 1; j < tabuleiro[i].length - 1; j++) {

				if (tabuleiro[i][j] != null) {
					if (i % 2 == 0) {

						if (tabuleiro[i][j].getPeca() != null)
							System.out.print("\t" + tabuleiro[i][j] + "-" + tabuleiro[i][j].getPeca().getCor() + "\t");
						else
							System.out.print("\t" + tabuleiro[i][j] + "\t");
					} else {
						if (tabuleiro[i][j].getPeca() != null)
							System.out.print(tabuleiro[i][j] + "-" + tabuleiro[i][j].getPeca().getCor() + "\t" + "\t");
						else
							System.out.print(tabuleiro[i][j] + "\t" + "\t");
					}
				}
			}

			System.out.println();
		}
	}

	public Casa[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Casa[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
}
