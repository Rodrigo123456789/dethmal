package br.com.assinchronus;

public class Tabuleiro {
	
	//Array com todas as casas validas do jogo
	private Casa[][] tabuleiro = new Casa[8][4];
	
	/**
	 * Nome: Construtor
	 * Descrição: Monta o tabuleiro
	 */
	public Tabuleiro() {
		int count = 1;

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				Casa casa = new Casa();
				casa.setNumero(count++);
				tabuleiro[i][j] = casa;
			}
		}

		colocarPecas(Pecas.BRANCA);
		colocarPecas(Pecas.PRETA);
	}
	
	/** 
	 * Nome: colocarPecas
	 * Descrição: Coloca a peça no tabuleiro
	 * @param Tipo da Peça
	 */
	private void colocarPecas(int tipo) {
		Pecas peca = null;
		Casa casa = null;

		int linhaInicio = 0;
		int linhaFim = 0;

		if (tipo == Pecas.BRANCA) {
			linhaInicio = 0;
			linhaFim = 2;
		} else if (tipo == Pecas.PRETA) {
			linhaInicio = 5;
			linhaFim = 7;
		}

		for (; linhaInicio <= linhaFim; linhaInicio++) {
			for (int j = 0; j < tabuleiro[linhaInicio].length; j++) {
				peca = new Pecas();
				casa = tabuleiro[linhaInicio][j];

				peca.setCasa(casa.getNumero());
				peca.setCor(tipo);
				peca.setTipo("Peao");

				tabuleiro[linhaInicio][j].setPeca(peca);
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
