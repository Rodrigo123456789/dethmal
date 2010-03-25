package br.com.assinchronus.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Pecas;

public class RegraPeao {

	public Casa verificaJogadaPBranco(List<Casa[]> obrigatoria,
			Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) {
		if (casaFinal.getPeca() != null) {
			// mandar o erro de já tem peça aki!
		} else {
			if (Math.abs(casaFinal.getColuna() - casaInicial.getColuna()) == 1
					&& (casaFinal.getLinha() - casaInicial.getLinha() == -1)) {
				if (!obrigatoria.isEmpty()) {
					// Mensagem de jogada invalida
				} else {
					casaInicial.getPeca().mover(casaInicial, casaFinal);
					return casaInicial;
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

		return null;
	}

	public Casa verificaJogadaPPreto(List<Casa[]> obrigatoria,
			Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) {
		if (casaFinal.getPeca() != null) {
			// mandar o erro de já tem peça aki!
		} else {
			if (Math.abs(casaFinal.getColuna() - casaInicial.getColuna()) == 1
					&& (casaFinal.getLinha() - casaInicial.getLinha() == 1)) {
				if (!obrigatoria.isEmpty()) {
					// Mensagem de jogada invalida
				} else {
					casaInicial.getPeca().mover(casaInicial, casaFinal);
					return casaInicial;
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
		return null;
	}

	public List<Casa[]> verificaSequenciaPeao(Casa[][] tabuleiro,
			Casa casaInicial) {

		Casa[] acao = new Casa[2];
		List<Casa[]> jogadaspossiveis = new ArrayList<Casa[]>();

		if (casaInicial.getLinha() + 2 < 8 && casaInicial.getColuna() + 2 < 8) {
			// se pode comer diagonal inferior direita
			if (tabuleiro[casaInicial.getLinha() + 1][casaInicial.getColuna() + 1]
					.getPeca() != null
					&& tabuleiro[casaInicial.getLinha() + 1][casaInicial
							.getColuna() + 1].getPeca().getCor() != casaInicial
							.getPeca().getCor()
					&& tabuleiro[casaInicial.getLinha() + 2][casaInicial
							.getColuna() + 2].getPeca() == null) {
				acao[0] = casaInicial;
				acao[1] = tabuleiro[casaInicial.getLinha() + 2][casaInicial
						.getColuna() + 2];
				jogadaspossiveis.add(acao);
			}
		}
		if (casaInicial.getLinha() + 2 < 8 && casaInicial.getColuna() - 2 > -1) {
			// se pode comer diagonal inferior esquerda
			if (tabuleiro[casaInicial.getLinha() + 1][casaInicial.getColuna() - 1]
					.getPeca() != null
					&& tabuleiro[casaInicial.getLinha() + 1][casaInicial
							.getColuna() - 1].getPeca().getCor() != casaInicial
							.getPeca().getCor()
					&& tabuleiro[casaInicial.getLinha() + 2][casaInicial
							.getColuna() - 2].getPeca() == null) {
				acao[0] = casaInicial;
				acao[1] = tabuleiro[casaInicial.getLinha() + 2][casaInicial
						.getColuna() - 2];
				jogadaspossiveis.add(acao);
			}
		}
		if (casaInicial.getLinha() - 2 > -1 && casaInicial.getColuna() + 2 < 8) {
			// se pode comer diagonal superior direita
			if (tabuleiro[casaInicial.getLinha() - 1][casaInicial.getColuna() + 1]
					.getPeca() != null
					&& tabuleiro[casaInicial.getLinha() - 1][casaInicial
							.getColuna() + 1].getPeca().getCor() != casaInicial
							.getPeca().getCor()
					&& tabuleiro[casaInicial.getLinha() - 2][casaInicial
							.getColuna() + 2].getPeca() == null) {
				acao[0] = casaInicial;
				acao[1] = tabuleiro[casaInicial.getLinha() - 2][casaInicial
						.getColuna() + 2];
				jogadaspossiveis.add(acao);
			}
		}
		if (casaInicial.getLinha() - 2 > -1 && casaInicial.getColuna() - 2 > -1) {
			// se pode comer diagonal superior esquerda
			if (tabuleiro[casaInicial.getLinha() - 1][casaInicial.getColuna() - 1]
					.getPeca() != null
					&& tabuleiro[casaInicial.getLinha() - 1][casaInicial
							.getColuna() - 1].getPeca().getCor() != casaInicial
							.getPeca().getCor()
					&& tabuleiro[casaInicial.getLinha() - 2][casaInicial
							.getColuna() - 2].getPeca() == null) {
				acao[0] = casaInicial;
				acao[1] = tabuleiro[casaInicial.getLinha() - 2][casaInicial
						.getColuna() - 2];
				jogadaspossiveis.add(acao);
			}
		}
		return jogadaspossiveis;
	}

}
