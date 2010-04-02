package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.*;

/**
 * 
 * @author vinicius
 * 
 */
public class RegraFinal {

	static int qtdPeaoBranco;
	static int qtdPeaoPreto;
	static int qtdDamaBranco;
	static int qtdDamaPreto;
	static int jogadasempate = 5;

	RegraGeral rg = new RegraGeral();

	/**
	 * 
	 * @param tabuleiro
	 *            Tabuleiro atual do jogo
	 * @return Retorna a false se o jogador estiver IMOBILIZADO = fim de jogo
	 */
	public boolean verificaPecasBranca(Casa[][] tabuleiro) {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && tabuleiro[i][j].getPeca() != null
						&& tabuleiro[i][j].getPeca().getCor() == Pecas.BRANCA) {
					if (tabuleiro[i][j].getPeca() instanceof Peao) {
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
						if (RegraGeral.getSequencia() == true && i + 2 < 8 && j + 2 < 8 && tabuleiro[i + 1][j + 1].getPeca() != null
								&& tabuleiro[i + 1][j + 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i + 2][j + 2].getPeca() == null) {
							return true;
						}
						if (RegraGeral.getSequencia() == true && i + 2 < 8 && j - 2 > -1 && tabuleiro[i + 1][j - 1].getPeca() != null
								&& tabuleiro[i + 1][j - 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i + 2][j - 2].getPeca() == null) {
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
	 * @param tabuleiro
	 *            Tabuleiro atual do jogo
	 * @return Retorna a false se o jogador estiver IMOBILIZADO = fim de jogo
	 */
	public boolean verificaPecasPreta(Casa[][] tabuleiro) {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && tabuleiro[i][j].getPeca() != null
						&& tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA) {
					if (tabuleiro[i][j].getPeca() instanceof Peao) {
						if ((i < 7 && j < 7 && tabuleiro[i + 1][j + 1].getPeca() == null) || (i < 7 && j > 0 && tabuleiro[i + 1][j - 1].getPeca() == null)) {
							return true;
						}
						if (RegraGeral.getSequencia() == true && i - 2 > -1 && j + 2 < 8 && tabuleiro[i - 1][j + 1].getPeca() != null
								&& tabuleiro[i - 1][j + 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i - 2][j + 2].getPeca() == null) {
							return true;
						}
						if (RegraGeral.getSequencia() == true && i - 2 > -1 && j - 2 > -1 && tabuleiro[i - 1][j - 1].getPeca() != null
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
	public int analisaFinal() {

		int totalpecas = qtdPeaoBranco + qtdPeaoPreto + qtdDamaBranco + qtdDamaPreto;
		if (qtdPeaoBranco + qtdDamaBranco == 0) {
			System.out.println("Acabaram as pecas brancas");
			return -1;
		} else if (qtdPeaoPreto + qtdDamaPreto == 0) {
			System.out.println("Acabaram as pecas pretas");
			return 1;
		} else if (totalpecas < 5) {
			if ((qtdDamaBranco < 3 || qtdDamaPreto < 3) && (qtdPeaoPreto + qtdPeaoBranco == 0)) {
				System.out.println("Duas damas ou menos de cada lado");
				setJogadasempate(getJogadasempate() - 1);
				return 0;
			} else if (qtdPeaoBranco == 1 && qtdDamaBranco == 1 && qtdDamaPreto == 1 && qtdPeaoPreto == 0) {
				System.out.println("Um PB, uma DB e uma DP");
				setJogadasempate(getJogadasempate() - 1);
				return 0;
			} else if (qtdPeaoPreto == 1 && qtdDamaBranco == 1 && qtdDamaPreto == 1 && qtdPeaoBranco == 0) {
				System.out.println("Um PP, uma DP e uma DB");
				setJogadasempate(getJogadasempate() - 1);
				return 0;
			} else if (qtdPeaoBranco == 1 && qtdDamaBranco == 1 && qtdDamaPreto == 2 && qtdPeaoPreto == 0) {
				System.out.println("Um PB, uma DB e duas DP");
				setJogadasempate(getJogadasempate() - 1);
				return 0;
			} else if (qtdPeaoPreto == 1 && qtdDamaBranco == 2 && qtdDamaPreto == 1 && qtdPeaoBranco == 0) {
				System.out.println("Um PP, uma DP e duas DB");
				setJogadasempate(getJogadasempate() - 1);
				return 0;
			}
		}
		return 5;
	}

	public int getQtdPeaoBranco() {
		return qtdPeaoBranco;
	}

	public static void setQtdPeaoBranco(int qtdPeaoBranco) {
		RegraFinal.qtdPeaoBranco = qtdPeaoBranco;
	}

	public int getQtdPeaoPreto() {
		return qtdPeaoPreto;
	}

	public static void setQtdPeaoPreto(int qtdPeaoPreto) {
		RegraFinal.qtdPeaoPreto = qtdPeaoPreto;
	}

	public int getQtdDamaBranco() {
		return qtdDamaBranco;
	}

	public static void setQtdDamaBranco(int qtdDamaBranco) {
		RegraFinal.qtdDamaBranco = qtdDamaBranco;
	}

	public int getQtdDamaPreto() {
		return qtdDamaPreto;
	}

	public static void setQtdDamaPreto(int qtdDamaPreto) {
		RegraFinal.qtdDamaPreto = qtdDamaPreto;
	}

	public void setJogadasempate(int jogadasempate) {
		RegraFinal.jogadasempate = jogadasempate;
	}

	public int getJogadasempate() {
		return jogadasempate;
	}

}