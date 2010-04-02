package br.com.assinchronus.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.exception.JogadaInvalida;

/**
 * 
 * @author vinicius
 * 
 */
public class RegraGeral {

	static RegraPeao rp = new RegraPeao();
	static RegraDama rd = new RegraDama();
	private static boolean sequencia = false;
	private static boolean forcacaptura = false;

	/**
	 * 
	 * @param jogada
	 *            Indica de quem eh a vez
	 * @param tabuleiro
	 *            Tabuleiro atual do jogo
	 * @return
	 */
	public static List<Casa[]> verificaCapturaObrigatoria(int jogada, Casa[][] tabuleiro) {
		int z, l, c;
		Casa[] casa = new Casa[2];
		List<Casa[]> obrigatoria = new ArrayList<Casa[]>();
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && tabuleiro[i][j].getPeca() != null) {
					if (jogada == Pecas.BRANCA) {
						if (tabuleiro[i][j].getPeca().getCor() == Pecas.BRANCA && (tabuleiro[i][j].getPeca() instanceof Peao)) {
							// Se for um peao branco
							if (i - 2 > -1 && j + 2 < 8) {
								if (tabuleiro[i - 1][j + 1].getPeca() != null && tabuleiro[i - 1][j + 1].getPeca().getCor() == Pecas.PRETA
										&& tabuleiro[i - 2][j + 2].getPeca() == null) {
									casa[0] = tabuleiro[i][j];
									casa[1] = tabuleiro[i - 2][j + 2];
									obrigatoria.add(casa);
								}
							}
							if (i - 2 > -1 && j - 2 > -1) {
								if (tabuleiro[i - 1][j - 1].getPeca() != null && tabuleiro[i - 1][j - 1].getPeca().getCor() == Pecas.PRETA
										&& tabuleiro[i - 2][j - 2].getPeca() == null) {
									casa[0] = tabuleiro[i][j];
									casa[1] = tabuleiro[i - 2][j - 2];
									obrigatoria.add(casa);
								}
							}
						} else if (tabuleiro[i][j].getPeca() != null && tabuleiro[i][j].getPeca().getCor() == Pecas.BRANCA
								&& (tabuleiro[i][j].getPeca() instanceof Dama)) {
							// Se for uma dama branca
							for (l = -1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal superior esquerda
								if ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) > -1) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										break;
									} else if ((tabuleiro[i + l * z][j + c * z].getPeca() != null)
											&& (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null)) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										if (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null) {
											casa[0] = tabuleiro[i][j];
											casa[1] = tabuleiro[i + l * (z + 1)][j + c * (z + 1)];
											obrigatoria.add(casa);
										}
									}
								}
							}
							for (l = -1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal superior direita
								if ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) < 8) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										if (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null) {
											casa[0] = tabuleiro[i][j];
											casa[1] = tabuleiro[i + l * (z + 1)][j + c * (z + 1)];
											obrigatoria.add(casa);
										}
									}
								}
							}
							for (l = +1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior esquerda
								if ((i + l * (z + 1)) < 8 && (j + c * (z + 1)) > -1) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										if (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null) {
											casa[0] = tabuleiro[i][j];
											casa[1] = tabuleiro[i + l * (z + 1)][j + c * (z + 1)];
											obrigatoria.add(casa);
										}
									}
								}
							}
							for (l = +1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior direita
								if ((i + l * (z + 1)) < 8 && (j + c * (z + 1)) < 8) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										if (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null) {
											casa[0] = tabuleiro[i][j];
											casa[1] = tabuleiro[i + l * (z + 1)][j + c * (z + 1)];
											obrigatoria.add(casa);
										}
									}
								}
							}
						}
					} else {
						if (tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA && (tabuleiro[i][j].getPeca() instanceof Peao)) {
							// Se for um peao preto
							if (i + 2 < 8 && j + 2 < 8) {
								if (tabuleiro[i + 1][j + 1].getPeca() != null && tabuleiro[i + 1][j + 1].getPeca().getCor() == Pecas.BRANCA
										&& tabuleiro[i + 2][j + 2].getPeca() == null) {
									casa[0] = tabuleiro[i][j];
									casa[1] = tabuleiro[i + 2][j + 2];
									obrigatoria.add(casa);
								}
							}
							if (i + 2 < 8 && j - 2 > -1) {
								if (tabuleiro[i + 1][j - 1].getPeca() != null && tabuleiro[i + 1][j - 1].getPeca().getCor() == Pecas.BRANCA
										&& tabuleiro[i + 2][j - 2].getPeca() == null) {
									casa[0] = tabuleiro[i][j];
									casa[1] = tabuleiro[i + 2][j - 2];
									obrigatoria.add(casa);
								}
							}
						} else if (tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA && (tabuleiro[i][j].getPeca() instanceof Dama)) {
							// Se for uma dama preta
							for (l = -1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal superior esquerda
								if ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) > -1) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										break;
									} else if ((tabuleiro[i + l * z][j + c * z].getPeca() != null)
											&& (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null)) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										if (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null) {
											casa[0] = tabuleiro[i][j];
											casa[1] = tabuleiro[i + l * (z + 1)][j + c * (z + 1)];
											obrigatoria.add(casa);
										}
									}
								}
							}
							for (l = -1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal superior direita
								if ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) < 8) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										break;
									} else if ((tabuleiro[i + l * z][j + c * z].getPeca() != null)
											&& (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null)) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										if (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null) {
											casa[0] = tabuleiro[i][j];
											casa[1] = tabuleiro[i + l * (z + 1)][j + c * (z + 1)];
											obrigatoria.add(casa);
										}
									}
								}
							}
							for (l = +1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior esquerda
								if ((i + l * (z + 1)) < 8 && (j + c * (z + 1)) > -1) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										break;
									} else if ((tabuleiro[i + l * z][j + c * z].getPeca() != null)
											&& (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null)) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										if (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null) {
											casa[0] = tabuleiro[i][j];
											casa[1] = tabuleiro[i + l * (z + 1)][j + c * (z + 1)];
											obrigatoria.add(casa);
										}
									}
								}
							}
							for (l = +1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior direita
								if ((i + l * (z + 1)) > -1 && (i + l * (z + 1)) < 8 && (j + c * (z + 1)) > -1 && (j + c * (z + 1)) < 8) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										break;
									} else if ((tabuleiro[i + l * z][j + c * z].getPeca() != null)
											&& (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null)) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										if (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null) {
											casa[0] = tabuleiro[i][j];
											casa[1] = tabuleiro[i + l * (z + 1)][j + c * (z + 1)];
											obrigatoria.add(casa);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return obrigatoria;
	}

	/**
	 * 
	 * @param jogada
	 *            Indica de quem eh a vez
	 * @param tabuleiro
	 *            Tabuleiro atual do jogo
	 * @param casaInicial
	 *            Casa com a peca do jogador
	 * @param casaFinal
	 *            Casa de destino final
	 * @return Retorna a lista de jogadas obrigatorias (para a IA)
	 * @throws JogadaInvalida
	 */
	public static void validarPeca(int jogada, Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) throws JogadaInvalida {

		List<Casa[]> obrigatoria = new ArrayList<Casa[]>();
		obrigatoria.clear();
		// se primeira jogada, testa pra ver se tem captura obrigatoria
		// se nao for a primeira, vai direto com obrigatoria true.
		if (!getSequencia()) {
			obrigatoria = verificaCapturaObrigatoria(jogada, tabuleiro);
			System.out.println("Captura obrigatoria: " + obrigatoria);
		}

		if ((casaInicial.getPeca() instanceof Peao) && (casaInicial.getPeca().getCor() == Pecas.BRANCA)) {
			System.out.println("Analisando movimento do peao branco");
			obrigatoria = rp.verificaJogadaPBranco(obrigatoria, tabuleiro, casaInicial, casaFinal);
			forcacaptura = !obrigatoria.isEmpty();
			return;
		} else if ((casaInicial.getPeca() instanceof Peao) && (casaInicial.getPeca().getCor() == Pecas.PRETA)) {
			System.out.println("Analisando movimento do peao preto");
			obrigatoria = rp.verificaJogadaPPreto(obrigatoria, tabuleiro, casaInicial, casaFinal);
			forcacaptura = !obrigatoria.isEmpty();
			return;
		} else if (casaInicial.getPeca() instanceof Dama) {
			System.out.println("Analisando Diagonal da Dama");
			obrigatoria = rd.verificaDiagonalDama(obrigatoria, tabuleiro, casaInicial, casaFinal);
			forcacaptura = !obrigatoria.isEmpty();
			return;
		}
		return;
	}

	public void setSequencia(boolean sequencia) {
		RegraGeral.sequencia = sequencia;
	}

	public static boolean getSequencia() {
		return sequencia;
	}

	public boolean isForcacaptura() {
		return forcacaptura;
	}

	public static void setForcacaptura(boolean forcacaptura) {
		RegraGeral.forcacaptura = forcacaptura;
	}

}