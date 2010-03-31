package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.*;

/**
 * 
 * @author vinicius
 * 
 */
public class RegraFinal {

	private int qtdPeaoBranco;
	private int qtdPeaoPreto;
	private int qtdDamaBranco;
	private int qtdDamaPreto;
	private int jogadasempate;

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
						// se a peca for um peao branco
						if (tabuleiro[i - 1][j + 1].getPeca() == null || tabuleiro[i - 1][j - 1].getPeca() == null) {
							// se pode se mover
							return true;
						}
						if (i - 2 > -1 && j + 2 < 8 && tabuleiro[i - 1][j + 1].getPeca() != null && tabuleiro[i - 1][j + 1].getPeca().getCor() == Pecas.PRETA
								&& tabuleiro[i - 2][j + 2].getPeca() == null) {
							// se pode comer diagonal superior direita
							return true;
						}
						if (i - 2 > -1 && j - 2 > -1 && tabuleiro[i - 1][j - 1].getPeca() != null && tabuleiro[i - 1][j - 1].getPeca().getCor() == Pecas.PRETA
								&& tabuleiro[i - 2][j - 2].getPeca() == null) {
							// se pode comer diagonal superior esquerda
							return true;
						}
						if (RegraGeral.getSequencia() == true && i + 2 < 8 && j + 2 < 8 && tabuleiro[i + 1][j + 1].getPeca() != null
								&& tabuleiro[i + 1][j + 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i + 2][j + 2].getPeca() == null) {
							// se pode comer diagonal inferior direita
							return true;
						}
						if (RegraGeral.getSequencia() == true && i + 2 < 8 && j - 2 > -1 && tabuleiro[i + 1][j - 1].getPeca() != null
								&& tabuleiro[i + 1][j - 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i + 2][j - 2].getPeca() == null) {
							// se pode comer diagonal inferior esquerda
							return true;
						}
					} else if (tabuleiro[i][j].getPeca() instanceof Dama) {
						// se ha uma dama, o jogador nao esta imobilizado
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
						// se a peca for um peao preto
						if (tabuleiro[i + 1][j + 1].getPeca() == null || tabuleiro[i + 1][j - 1].getPeca() == null) {
							// se pode se mover
							return true;
						}
						if (RegraGeral.getSequencia() == true && i - 2 > -1 && j + 2 < 8 && tabuleiro[i - 1][j + 1].getPeca() != null
								&& tabuleiro[i - 1][j + 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i - 2][j + 2].getPeca() == null) {
							// se pode comer diagonal superior direita
							return true;
						}
						if (RegraGeral.getSequencia() == true && i - 2 > -1 && j - 2 > -1 && tabuleiro[i - 1][j - 1].getPeca() != null
								&& tabuleiro[i - 1][j - 1].getPeca().getCor() == Pecas.PRETA && tabuleiro[i - 2][j - 2].getPeca() == null) {
							// se pode comer diagonal superior esquerda
							return true;
						}
						if (i + 2 < 8 && j + 2 < 8 && tabuleiro[i + 1][j + 1].getPeca() != null && tabuleiro[i + 1][j + 1].getPeca().getCor() == Pecas.PRETA
								&& tabuleiro[i + 2][j + 2].getPeca() == null) {
							// se pode comer diagonal inferior direita
							return true;
						}
						if (i + 2 < 8 && j - 2 > -1 && tabuleiro[i + 1][j - 1].getPeca() != null && tabuleiro[i + 1][j - 1].getPeca().getCor() == Pecas.PRETA
								&& tabuleiro[i + 2][j - 2].getPeca() == null) {
							// se pode comer diagonal inferior esquerda
							return true;
						}
					} else if (tabuleiro[i][j].getPeca() instanceof Dama) {
						// se ha uma dama, o jogador nao esta imobilizado
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
	 * @return	0 para condicao de empate
	 * 			1 para vitoria branca
	 * 		   -1 para vitoria preta
	 * 			5 para condicao de nao-final
	 */
	public int analisaFinal() {

		int totalpecas = qtdPeaoBranco + qtdPeaoPreto + qtdDamaBranco + qtdDamaPreto;

		if (totalpecas < 5) {
			if (qtdPeaoBranco + qtdDamaBranco == 0) {
				// Preto vence
				return -1;
			} else if (qtdPeaoPreto + qtdDamaPreto == 0) {
				// Branco vence
				return 1;
			} else if (qtdPeaoPreto + qtdPeaoBranco == 0) {
				// Se nao tem peoes no tabuleiro
				if (qtdDamaBranco < 3 || qtdDamaPreto < 3) {
					setJogadasempate(getJogadasempate() - 1);
					return 0;
				} else if (qtdPeaoBranco == 1 && qtdDamaBranco == 1 && qtdDamaPreto == 1) {
					setJogadasempate(getJogadasempate() - 1);
					return 0;
				} else if (qtdPeaoPreto == 1 && qtdDamaBranco == 1 && qtdDamaPreto == 1) {
					setJogadasempate(getJogadasempate() - 1);
					return 0;
				} else if (qtdPeaoBranco == 1 && qtdDamaBranco == 1 && qtdDamaPreto == 2) {
					setJogadasempate(getJogadasempate() - 1);
					return 0;
				} else if (qtdPeaoPreto == 1 && qtdDamaBranco == 2 && qtdDamaPreto == 1) {
					setJogadasempate(getJogadasempate() - 1);
					return 0;
				}
			}
		}
		//jogadasempate = 10;
		return 5; // indica que o jogo nao terminou
	}

	public int getQtdPeaoBranco() {
		return qtdPeaoBranco;
	}

	public void setQtdPeaoBranco(int qtdPeaoBranco) {
		this.qtdPeaoBranco = qtdPeaoBranco;
	}

	public int getQtdPeaoPreto() {
		return qtdPeaoPreto;
	}

	public void setQtdPeaoPreto(int qtdPeaoPreto) {
		this.qtdPeaoPreto = qtdPeaoPreto;
	}

	public int getQtdDamaBranco() {
		return qtdDamaBranco;
	}

	public void setQtdDamaBranco(int qtdDamaBranco) {
		this.qtdDamaBranco = qtdDamaBranco;
	}

	public int getQtdDamaPreto() {
		return qtdDamaPreto;
	}

	public void setQtdDamaPreto(int qtdDamaPreto) {
		this.qtdDamaPreto = qtdDamaPreto;
	}

	public void setJogadasempate(int jogadasempate) {
		this.jogadasempate = jogadasempate;
	}

	public int getJogadasempate() {
		return jogadasempate;
	}

}