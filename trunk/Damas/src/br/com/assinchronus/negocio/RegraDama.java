package br.com.assinchronus.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.exception.JogadaInvalida;

public class RegraDama {

	RegraGeral rg = new RegraGeral();

	/**
	 * 
	 * @param obrigatoria
	 *            lista das jogadas obrigatorias
	 * @param tabuleiro
	 *            tabuleiro atual do jogo
	 * @param casaInicial
	 *            casa clicada para jogar
	 * @param casaFinal
	 *            casa de destino final
	 * @throws JogadaInvalida
	 */
	public List<Casa[]> verificaDiagonalDama(List<Casa[]> obrigatoria, Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) throws JogadaInvalida {
		int x; // salva a direcao horizontal do movimento
		int y; // salva a direcao vertical do movimento
		if (Math.abs(casaFinal.getLinha() - casaInicial.getLinha()) == Math.abs(casaFinal.getColuna() - casaInicial.getColuna())) {
			// caso o movimento tenha sido dentro de uma diagonal permitida
			x = casaFinal.getColuna() - casaInicial.getColuna();
			y = casaFinal.getLinha() - casaInicial.getLinha();
			if (x > 0 && y > 0) {
				x = 1;
				y = 1;
			}
			if (x > 0 && y < 0) {
				x = 1;
				y = -1;
			}
			if (x < 0 && y > 0) {
				x = -1;
				y = 1;
			}
			if (x < 0 && y < 0) {
				x = -1;
				y = -1;
			}
			obrigatoria = verificaJogadaDama(obrigatoria, tabuleiro, casaInicial, casaFinal, x, y);
		} else {
			throw new JogadaInvalida("Dama: movimento invalido");
		}
		return obrigatoria;
	}

	/**
	 * 
	 * @param obrigatoria
	 *            lista das jogadas obrigatorias
	 * @param tabuleiro
	 *            tabuleiro atual do jogo
	 * @param casaInicial
	 *            casa clicada para jogar
	 * @param casaFinal
	 *            casa de destino final
	 * @param x
	 *            direcao horizontal do movimento da dama
	 * @param y
	 *            direcao vertical do movimento da dama
	 * @throws JogadaInvalida
	 */

	public List<Casa[]> verificaJogadaDama(List<Casa[]> obrigatoria, Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal, int x, int y) throws JogadaInvalida {
		int linha = casaInicial.getLinha();
		int coluna = casaInicial.getColuna();
		int z = 1; // incrementa para varrer toda a diagonal escolhida
		int adversaria = 0;
		// conta as pecas adversarias no caminho
		Casa capturada = new Casa();
		while (adversaria < 2 && (linha + y * z) != (casaFinal.getLinha())) {
			if (tabuleiro[linha + y * z][coluna + x * z].getPeca() != null) {
				if (tabuleiro[linha + y * z][coluna + x * z].getPeca().getCor() == casaInicial.getPeca().getCor()) {
					throw new JogadaInvalida("Nao pode pular a propria peca");
				} else { // se ha peca preta, incrementar contador de peca
					// adversaria
					adversaria++;
					if (adversaria == 1) {
						capturada = tabuleiro[linha + y * z][coluna + x * z];
					}
				}
			}
			z++;
		}
		if (adversaria == 0) {
			if (!obrigatoria.isEmpty()) {
				throw new JogadaInvalida("Voce deve capturar uma peca");
			} else if (RegraGeral.getSequencia()) {
				throw new JogadaInvalida("Voce deve capturar uma peca");
			} else {
				casaInicial.getPeca().mover(casaInicial, casaFinal);
			}
		} else if (adversaria == 1) {
			casaInicial.getPeca().comer(casaInicial, capturada, casaFinal);
			obrigatoria = verificaSequenciaDama(tabuleiro, casaFinal);
			if (obrigatoria.isEmpty()) {
				rg.setSequencia(false);
			} else {
				rg.setSequencia(true);
			}

		} else if (adversaria == 2) {
			throw new JogadaInvalida("A dama nao pode pular 2 pecas");
		}
		return obrigatoria;
	}

	/**
	 * 
	 * @param tabuleiro
	 *            Tabuleiro atual do jogo
	 * @param casaInicial
	 *            Casa em que a peca parou
	 * @return Lista de jogadas possiveis para sequencia
	 */
	public List<Casa[]> verificaSequenciaDama(Casa[][] tabuleiro, Casa casaInicial) {
		int z, l, c;
		Casa[] acao = new Casa[2];
		List<Casa[]> jogadaspossiveis = new ArrayList<Casa[]>();

		for (l = -1, c = -1, z = 1; z <= 6; z++) {
			// analisando diagonal superior esquerda
			if (casaInicial.getLinha() + l * (z + 1) > -1 && casaInicial.getColuna() + c * (z + 1) > -1) {
				if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca() != null) {
					if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() == casaInicial.getPeca().getCor()
							|| tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().isCapturada() || tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() != null) {
						break;
					}
					if ((tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() != casaInicial.getPeca().getCor())
							&& (tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() == null)) {
						acao[0] = tabuleiro[casaInicial.getLinha()][casaInicial.getColuna()];
						acao[1] = tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)];
						jogadaspossiveis.add(acao);
					}
				}
			}
		}
		for (l = -1, c = +1, z = 1; z <= 6; z++) {
			// analisando diagonal superior direita
			if (casaInicial.getLinha() + l * (z + 1) > -1 && casaInicial.getColuna() + c * (z + 1) < 8) {
				if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca() != null) {
					if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() == casaInicial.getPeca().getCor()
							|| tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().isCapturada() || tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() != null) {
						break;
					}
					if ((tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() != casaInicial.getPeca().getCor())
							&& (tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() == null)) {
						acao[0] = tabuleiro[casaInicial.getLinha()][casaInicial.getColuna()];
						acao[1] = tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)];
						jogadaspossiveis.add(acao);
					}
				}
			}
		}
		for (l = +1, c = -1, z = 1; z <= 6; z++) {
			// analisando diagonal inferior esquerda
			if (casaInicial.getLinha() + l * (z + 1) < 8 && casaInicial.getColuna() + c * (z + 1) > -1) {
				if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca() != null) {
					if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() == casaInicial.getPeca().getCor()
							|| tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().isCapturada() || tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() != null) {
						break;
					}
					if ((tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() != casaInicial.getPeca().getCor())
							&& (tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() == null)) {
						acao[0] = tabuleiro[casaInicial.getLinha()][casaInicial.getColuna()];
						acao[1] = tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)];
						jogadaspossiveis.add(acao);
					}
				}
			}
		}
		for (l = +1, c = +1, z = 1; z <= 6; z++) {
			// analisando diagonal inferior direita
			if (casaInicial.getLinha() + l * (z + 1) < 8 && casaInicial.getColuna() + c * (z + 1) < 8) {
				if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca() != null) {
					if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() == casaInicial.getPeca().getCor()
							|| tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().isCapturada() || tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() != null) {
						break;
					}
					if ((tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() != casaInicial.getPeca().getCor())
							&& (tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() == null)) {
						acao[0] = tabuleiro[casaInicial.getLinha()][casaInicial.getColuna()];
						acao[1] = tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)];
						jogadaspossiveis.add(acao);
					}
				}
			}
		}
		return jogadaspossiveis;
	}
}
