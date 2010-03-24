package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Pecas;

public class RegraPeao {

	public void verificaJogadaPBranco(boolean obrigatoria, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal) {
		if (casaFinal.getPeca() != null) {
			// mandar o erro de já tem peça aki!
		} else {
			if (Math.abs(casaFinal.getColuna() - casaInicial.getColuna()) == 1
					&& (casaFinal.getLinha() - casaInicial.getLinha() == -1)) {
				if (obrigatoria) {
					// Mensagem de jogada invalida
				} else {
					// chamar metodo mover -> passar casa inicial e final
				}
			} else if ((casaFinal.getColuna() - casaInicial.getColuna()) == 2
					&& (casaFinal.getLinha() - casaInicial.getLinha() == -2)) {
				// Movimento duplo para a direita
				if (tabuleiro[casaInicial.getLinha() - 1][casaFinal.getColuna() - 1]
						.getPeca() != null
						&& tabuleiro[casaInicial.getLinha() - 1][casaFinal
								.getColuna() - 1].getPeca().getCor() == Pecas.PRETA) {
					// chamar metodo comer -> passar as 3 casas
				} else {
					// entrar na classe de erros e mandar o erro de movimento
					// inválido
				}
			} else if ((casaFinal.getColuna() - casaInicial.getColuna()) == -2
					&& (casaFinal.getLinha() - casaInicial.getLinha() == -2)) {
				// Movimento duplo para a esquerda
				if (tabuleiro[casaInicial.getLinha() - 1][casaFinal.getColuna() + 1]
						.getPeca() != null
						&& tabuleiro[casaInicial.getLinha() - 1][casaFinal
								.getColuna() + 1].getPeca().getCor() == Pecas.PRETA) {
					// chamar metodo comer -> passar as 3 casas
				} else {
					// entrar na classe de erros e mandar o erro de
					// movimento inválido
				}
			} else {
				// entrar na classe de erros e mandar o erro de movimento
				// inválido
			}
		}
	}

	public void verificaJogadaPPreto(boolean obrigatoria, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal) {
		if (casaFinal.getPeca() != null) {
			// mandar o erro de já tem peça aki!
		} else {
			if (Math.abs(casaFinal.getColuna() - casaInicial.getColuna()) == 1
					&& (casaFinal.getLinha() - casaInicial.getLinha() == 1)) {
				if (obrigatoria) {
					// Mensagem de jogada invalida
				} else {
					// chamar metodo mover -> passar casa inicial e final
				}
			} else if ((casaFinal.getColuna() - casaInicial.getColuna()) == 2
					&& (casaFinal.getLinha() - casaInicial.getLinha() == 2)) {
				// Movimento duplo para a direita
				if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() - 1]
						.getPeca() != null
						&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
								.getColuna() - 1].getPeca().getCor() == Pecas.BRANCA) {
					// chamar metodo comer -> passar as 3 casas
				} else {
					// entrar na classe de erros e mandar o erro de movimento
					// inválido
				}
			} else if ((casaFinal.getColuna() - casaInicial.getColuna()) == -2
					&& (casaFinal.getLinha() - casaInicial.getLinha() == -2)) {
				// Movimento duplo para a esquerda
				if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() + 1]
						.getPeca() != null
						&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
								.getColuna() + 1].getPeca().getCor() == Pecas.BRANCA) {
					// chamar metodo comer -> passar as 3 casas
				} else {
					// entrar na classe de erros e mandar o erro de
					// moviment inválido
				}
			} else {
				// entrar na classe de erros e mandar o erro de movimento
				// inválido
			}
		}
	}

	//mudar este metodo para retornar uma lista com casas I e F.
	public boolean verificaSequenciaPeao(Casa[][] tabuleiro, Casa casaInicial) {
		if (tabuleiro[casaInicial.getLinha() + 1][casaInicial.getColuna() + 1]
				.getPeca() != null
				&& tabuleiro[casaInicial.getLinha() + 1][casaInicial
						.getColuna() + 1].getPeca().getCor() != casaInicial
						.getPeca().getCor()
				&& tabuleiro[casaInicial.getLinha() + 2][casaInicial
						.getColuna() + 2].getPeca() == null) {
			return true;
		}
		if (tabuleiro[casaInicial.getLinha() + 1][casaInicial.getColuna() - 1]
				.getPeca() != null
				&& tabuleiro[casaInicial.getLinha() + 1][casaInicial
						.getColuna() - 1].getPeca().getCor() != casaInicial
						.getPeca().getCor()
				&& tabuleiro[casaInicial.getLinha() + 2][casaInicial
						.getColuna() - 2].getPeca() == null) {
			return true;
		}
		if (tabuleiro[casaInicial.getLinha() - 1][casaInicial.getColuna() + 1]
				.getPeca() != null
				&& tabuleiro[casaInicial.getLinha() - 1][casaInicial
						.getColuna() + 1].getPeca().getCor() != casaInicial
						.getPeca().getCor()
				&& tabuleiro[casaInicial.getLinha() - 2][casaInicial
						.getColuna() + 2].getPeca() == null) {
			return true;
		}
		if (tabuleiro[casaInicial.getLinha() - 1][casaInicial.getColuna() - 1]
				.getPeca() != null
				&& tabuleiro[casaInicial.getLinha() - 1][casaInicial
						.getColuna() - 1].getPeca().getCor() != casaInicial
						.getPeca().getCor()
				&& tabuleiro[casaInicial.getLinha() - 2][casaInicial
						.getColuna() - 2].getPeca() == null) {
			return true;
		}
		return false;
	}

}
