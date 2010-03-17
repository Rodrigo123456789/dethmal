package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;

public class Regras {

	public void validar(Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) {

		if ((casaInicial.getPeca() instanceof Peao)
				&& (casaInicial.getPeca().getCor() == Pecas.BRANCA)) {
			moverPeaoBranca(tabuleiro, casaInicial, casaFinal);
		} else if ((casaInicial.getPeca() instanceof Peao)
				&& (casaInicial.getPeca().getCor() == Pecas.PRETA)) {
			moverPeaoPreta(tabuleiro, casaInicial, casaFinal);
		} else if ((casaInicial.getPeca() instanceof Dama)
				&& (casaInicial.getPeca().getCor() == Pecas.BRANCA)) {
			moverDamaBranca(tabuleiro, casaInicial, casaFinal);
		} else if ((casaInicial.getPeca() instanceof Dama)
				&& (casaInicial.getPeca().getCor() == Pecas.PRETA)) {
			moverDamaBranca(tabuleiro, casaInicial, casaFinal);
		}
	}

	public void moverPeaoBranca(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal) {
		if ((casaFinal.getColuna() - casaInicial.getColuna() == 1 || casaFinal
				.getColuna()
				- casaInicial.getColuna() == -1)
				&& (casaFinal.getLinha() - casaInicial.getLinha() == -1))
			if (casaFinal.getPeca() != null) {
				// entrar na classe de erros e mandar o erro de já tem
				// peça aki!
			} else {
				// chamar metodo mover -> passar casa inicial e final
			}
		else if ((casaFinal.getColuna() - casaInicial.getColuna()) == 2
				&& (casaFinal.getLinha() - casaInicial.getLinha() == -2)) { // Movimento
																			// duplo
																			// para
																			// a
																			// direita
			if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() - 1]
					.getPeca() != null
					&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
							.getColuna() - 1].getPeca().getCor() == Pecas.PRETA) {
				// chamar metodo comer -> passar as 3 casas
			} else {
				// entrar na classe de erros e mandar o erro de
				// movimento inválido
			}
		} else {
			if ((casaFinal.getColuna() - casaInicial.getColuna()) == -2
					&& (casaFinal.getLinha() - casaInicial.getLinha() == -2)) { // Movimento
																				// duplo
																				// para
																				// a
																				// esquerda
				if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() + 1]
						.getPeca() != null
						&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
								.getColuna() + 11].getPeca().getCor() == Pecas.PRETA) {
					// chamar metodo comer -> passar as 3 casas
				} else {
					// entrar na classe de erros e mandar o erro de
					// movimento inválido
				}
			} else {
				// entrar na classe de erros e mandar o erro de
				// movimento inválido
			}
		}
	}

	public void moverPeaoPreta(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal) {
		if ((casaFinal.getColuna() - casaInicial.getColuna() == 1 || casaFinal
				.getColuna()
				- casaInicial.getColuna() == -1)
				&& (casaFinal.getLinha() - casaInicial.getLinha() == 1))
			if (casaFinal.getPeca() != null) {
				// entrar na classe de erros e mandar o erro de já tem
				// peça aki!
			} else {
				// chamar metodo mover -> passar casa inicial e final
			}
		else if ((casaFinal.getColuna() - casaInicial.getColuna()) == 2
				&& (casaFinal.getLinha() - casaInicial.getLinha() == 2)) { // Movimento
																			// duplo
																			// para
																			// a
																			// direita
			if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() - 1]
					.getPeca() != null
					&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
							.getColuna() - 1].getPeca().getCor() == Pecas.BRANCA) {
				// chamar metodo comer -> passar as 3 casas
			} else {
				// entrar na classe de erros e mandar o erro de
				// movimento inválido
			}
		} else {
			if ((casaFinal.getColuna() - casaInicial.getColuna()) == -2
					&& (casaFinal.getLinha() - casaInicial.getLinha() == 2)) { // Movimento
																				// duplo
																				// para
																				// a
																				// esquerda
				if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() + 1]
						.getPeca() != null
						&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
								.getColuna() + 11].getPeca().getCor() == Pecas.BRANCA) {
					// chamar metodo comer -> passar as 3 casas
				} else {
					// entrar na classe de erros e mandar o erro de
					// movimento inválido
				}
			} else {
				// entrar na classe de erros e mandar o erro de
				// movimento inválido
			}
		}
	}

	public void moverDamaBranca(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal) {
	}

	public void moverDamaPreta(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal) {
	}

}