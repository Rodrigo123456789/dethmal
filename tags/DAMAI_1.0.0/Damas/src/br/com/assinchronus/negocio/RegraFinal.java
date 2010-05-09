package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.gui.Jogo;

/**
 * 
 * @author vinicius
 * 
 */
public class RegraFinal {

	public static final int JOGAGAS_EMPATE = 5;

	RegraGeral rg = new RegraGeral();

	/**
	 * @param tabuleiro
	 *            Tabuleiro atual do jogo
	 * @return Retorna a false se o jogador estiver IMOBILIZADO = fim de jogo
	 */
	public static boolean verificaPecasBranca(Casa[][] tabuleiro) {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				Casa casa = tabuleiro[i][j];
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && casa.getPeca() != null
						&& casa.getPeca().getCor() == Pecas.BRANCA) {
					if (casa.getPeca() instanceof Peao) {
						if ((i > 0 && j < 7 && tabuleiro[i - 1][j + 1].getPeca() == null) || (i > 0 && j > 0 && tabuleiro[i - 1][j - 1].getPeca() == null)) {
							return true;
						}
						if (i - 2 > -1 && j + 2 < 8 && tabuleiro[i - 1][j + 1].getPeca() != null && tabuleiro[i - 1][j + 1].getPeca().getCor() == Pecas.PRETA
								&& tabuleiro[i - 2][j + 2].getPeca() == null) {
							return true;
						}
						if (i - 2 > -1 && j - 2 > -1 && tabuleiro[i - 1][j - 1].getPeca() != null && tabuleiro[i - 1][j - 1].getPeca().getCor() == Pecas.PRETA
								&& tabuleiro[i - 2][j - 2].getPeca() == null) {
							return true;
						}
						boolean sequencia = RegraGeral.getSequencia();
						if (sequencia == true && i + 2 < 8 && j + 2 < 8 && tabuleiro[i + 1][j + 1].getPeca() != null
								&& tabuleiro[i + 1][j + 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i + 2][j + 2].getPeca() == null) {
							return true;
						}
						if (sequencia == true && i + 2 < 8 && j - 2 > -1 && tabuleiro[i + 1][j - 1].getPeca() != null
								&& tabuleiro[i + 1][j - 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i + 2][j - 2].getPeca() == null) {
							return true;
						}
					} else if (casa.getPeca() instanceof Dama) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param tabuleiro
	 *            Tabuleiro atual do jogo
	 * @return Retorna a false se o jogador estiver IMOBILIZADO = fim de jogo
	 */
	public static boolean verificaPecasPreta(Casa[][] tabuleiro) {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && tabuleiro[i][j].getPeca() != null
						&& tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA) {
					if (tabuleiro[i][j].getPeca() instanceof Peao) {
						if ((i < 7 && j < 7 && tabuleiro[i + 1][j + 1].getPeca() == null) || (i < 7 && j > 0 && tabuleiro[i + 1][j - 1].getPeca() == null)) {
							return true;
						}
						boolean sequencia = RegraGeral.getSequencia();
						if (sequencia == true && i - 2 > -1 && j + 2 < 8 && tabuleiro[i - 1][j + 1].getPeca() != null
								&& tabuleiro[i - 1][j + 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i - 2][j + 2].getPeca() == null) {
							return true;
						}
						if (sequencia == true && i - 2 > -1 && j - 2 > -1 && tabuleiro[i - 1][j - 1].getPeca() != null
								&& tabuleiro[i - 1][j - 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i - 2][j - 2].getPeca() == null) {
							return true;
						}
						if (i + 2 < 8 && j + 2 < 8 && tabuleiro[i + 1][j + 1].getPeca() != null && tabuleiro[i + 1][j + 1].getPeca().getCor() == Pecas.PRETA
								&& tabuleiro[i + 2][j + 2].getPeca() == null) {
							return true;
						}
						if (i + 2 < 8 && j - 2 > -1 && tabuleiro[i + 1][j - 1].getPeca() != null && tabuleiro[i + 1][j - 1].getPeca().getCor() == Pecas.PRETA
								&& tabuleiro[i + 2][j - 2].getPeca() == null) {
							return true;
						}
					} else if (tabuleiro[i][j].getPeca() instanceof Dama) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param qtdPeaoBranco
	 * @param qtdPeaoPreto
	 * @param qtdDamaBranco
	 * @param qtdDamaPreto
	 * @return 0 para condicao de empate 1 para vitoria branca -1 para vitoria
	 *         preta 5 para condicao de nao-final
	 */
	public static int analisaFinal() {

		int totalpecas = Jogo.peoesBrancos + Jogo.peoesPretos + Jogo.damasBrancas + Jogo.damasPretas;
		
		if (Jogo.peoesBrancos + Jogo.damasBrancas == 0) {
			Jogo.setMSG("Acabaram as pecas brancas");
			return Jogo.VITORIA_PRETA;
		} else if (Jogo.peoesPretos + Jogo.damasPretas == 0) {
			Jogo.setMSG("Acabaram as pecas pretas");
			return Jogo.VITORIA_BRANCA;
		} else if (totalpecas < 5) {
			if ((Jogo.damasBrancas < 3 || Jogo.damasPretas < 3) && (Jogo.peoesPretos + Jogo.peoesBrancos == 0)) {
				Jogo.setMSG("Duas damas ou menos de cada lado");
			} else if (Jogo.peoesBrancos == 1 && Jogo.damasBrancas == 1 && Jogo.damasPretas == 1 && Jogo.peoesPretos == 0) {
				Jogo.setMSG("Um PB, uma DB e uma DP");
			} else if (Jogo.peoesPretos == 1 && Jogo.damasBrancas == 1 && Jogo.damasPretas == 1 && Jogo.peoesBrancos == 0) {
				Jogo.setMSG("Um PP, uma DP e uma DB");
			} else if (Jogo.peoesBrancos == 1 && Jogo.damasBrancas == 1 && Jogo.damasPretas == 2 && Jogo.peoesPretos == 0) {
				Jogo.setMSG("Um PB, uma DB e duas DP");
			} else if (Jogo.peoesPretos == 1 && Jogo.damasBrancas == 2 && Jogo.damasPretas == 1 && Jogo.peoesBrancos == 0) {
				Jogo.setMSG("Um PP, uma DP e duas DB");
			}
			return Jogo.EMPATE;
		}
		return Jogo.CONTINUA;
	}
}