package br.com.assinchronus.componentes;

/**
 * 
 * @author Pilon
 * @version 1.0.0
 */

public class Tabuleiro {
	
	//Array com todas as casas validas do jogo
	private Casa[][] tabuleiro = new Casa[8][4];
	
	/**
	 * Nome: Construtor
	 * Descrição: Monta o tabuleiro
	 */
	public Tabuleiro() {

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				Casa casa = new Casa();
				casa.setLinha(i);
				casa.setColuna(j);
				tabuleiro[i][j] = casa;
			}
		}

		colocarPecas(Peao.BRANCA);
		colocarPecas(Peao.PRETA);
	}
	
	/** 
	 * Nome: colocarPecas
	 * Descrição: Coloca a peça no tabuleiro
	 * @param cor - Cor da Peça
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
				peca = new Peao();
				casa = tabuleiro[linhaInicio][j];
				
				peca.setCor(cor);

				casa.setPeca(peca);
			}
		}
	}
	
	/**
	 * Nome: printTabuleiro
	 * Descrição: Imprimi o tabuleiro
	 */
	public void printTabuleiro() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (i % 2 == 1) {
					if (tabuleiro[i][j].getPeca() != null)
						System.out.print("\t" + tabuleiro[i][j] + "-"
								+ tabuleiro[i][j].getPeca().getCor() + "\t");
					else
						System.out.print("\t" + tabuleiro[i][j] + "\t");
				} else {
					if (tabuleiro[i][j].getPeca() != null)
						System.out.print(tabuleiro[i][j] + "-"
								+ tabuleiro[i][j].getPeca().getCor() + "\t"
								+ "\t");
					else
						System.out.print(tabuleiro[i][j] + "\t" + "\t");
				}
			}

			System.out.println();
		}
	}
}
