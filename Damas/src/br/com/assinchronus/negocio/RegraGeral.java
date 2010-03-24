package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;

public class RegraGeral {

	RegraPeao rp = new RegraPeao();
	RegraDama rd = new RegraDama();

	public boolean verificaCapturaObrigatoria(int jogada, Casa[][] tabuleiro) {
		int z, l, c;
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						&& tabuleiro[i][j].getPeca() != null) {
					if (jogada == Pecas.BRANCA) {
						// Se for jogada do branco
						if (tabuleiro[i][j].getPeca().getCor() == Pecas.BRANCA
								&& (tabuleiro[i][j].getPeca() instanceof Peao)) {
							// Se for um peao branco
							if (tabuleiro[i - 1][j + 1].getPeca().getCor() == Pecas.PRETA
									&& tabuleiro[i - 2][j + 2].getPeca() == null) {
								// se é possível comer para a diagonal superior
								// direita
								return true;
							}
							if (tabuleiro[i - 1][j - 1].getPeca().getCor() == Pecas.PRETA
									&& tabuleiro[i - 2][j - 2].getPeca() == null) {
								// se é possível comer para a diagonal superior
								// esquerda
								return true;
							}
						} else if (tabuleiro[i][j].getPeca().getCor() == Pecas.BRANCA
								&& (tabuleiro[i][j].getPeca() instanceof Dama)) {
							// Se for uma dama branca
							for (l = -1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal superior esquerda
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.BRANCA) {
									break;
								}
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.PRETA) {
									if (tabuleiro[i + l * (z + 1)][j + c
											* (z + 1)].getPeca() == null) {
										return true;
									} else {
										break;
									}
								}
							}
							for (l = -1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal superior direita
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.BRANCA) {
									break;
								}
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.PRETA) {
									if (tabuleiro[i + l * (z + 1)][j + c
											* (z + 1)].getPeca() == null) {
										return true;
									} else {
										break;
									}
								}
							}
							for (l = +1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior esquerda
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.BRANCA) {
									break;
								}
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.PRETA) {
									if (tabuleiro[i + l * (z + 1)][j + c
											* (z + 1)].getPeca() == null) {
										return true;
									} else {
										break;
									}
								}
							}
							for (l = +1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior direita
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.BRANCA) {
									break;
								}
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.PRETA) {
									if (tabuleiro[i + l * (z + 1)][j + c
											* (z + 1)].getPeca() == null) {
										return true;
									} else {
										break;
									}
								}
							}
						}
					} else {
						// Se for jogada do preto
						if (tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA
								&& (tabuleiro[i][j].getPeca() instanceof Peao)) {
							// Se for um peao preto
							if (tabuleiro[i + 1][j + 1].getPeca().getCor() == Pecas.BRANCA
									&& tabuleiro[i - 2][j + 2].getPeca() == null) {
								// se é possível comer para a diagonal inferior
								// direita
								return true;
								// parar a analise e chamar o validar
							}
							if (tabuleiro[i + 1][j - 1].getPeca().getCor() == Pecas.BRANCA
									&& tabuleiro[i - 2][j - 2].getPeca() == null) {
								// se é possível comer para a diagonal inferior
								// esquerda
								return true;
								// parar a analise e chamar o validar
							}
						} else if (tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA
								&& (tabuleiro[i][j].getPeca() instanceof Dama)) {
							// Se for uma dama preta
							for (l = -1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal superior esquerda
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.PRETA) {
									break;
								}
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.BRANCA) {
									if (tabuleiro[i + l * (z + 1)][j + c
											* (z + 1)].getPeca() == null) {
										return true;
									} else {
										break;
									}
								}
							}
							for (l = -1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal superior direita
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.PRETA) {
									break;
								}
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.BRANCA) {
									if (tabuleiro[i + l * (z + 1)][j + c
											* (z + 1)].getPeca() == null) {
										return true;
									} else {
										break;
									}
								}
							}
							for (l = +1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior esquerda
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.PRETA) {
									break;
								}
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.BRANCA) {
									if (tabuleiro[i + l * (z + 1)][j + c
											* (z + 1)].getPeca() == null) {
										return true;
									} else {
										break;
									}
								}
							}
							for (l = +1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior direita
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.PRETA) {
									break;
								}
								if (tabuleiro[i + l * z][j + c * z].getPeca() != null
										&& tabuleiro[i + l * z][j + c * z]
												.getPeca().getCor() == Pecas.BRANCA) {
									if (tabuleiro[i + l * (z + 1)][j + c
											* (z + 1)].getPeca() == null) {
										return true;
									} else {
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public void validarPeca(boolean sequencia, int jogada, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal) {

		boolean obrigatoria = true;
		// se primeira jogada, testa pra ver se tem captura obrigatoria
		// se nao for a primeira, vai direto com obrigatoria true.
		if (!sequencia) {
			obrigatoria = verificaCapturaObrigatoria(jogada, tabuleiro);
		}

		if ((casaInicial.getPeca() instanceof Peao)
				&& (casaInicial.getPeca().getCor() == Pecas.BRANCA)) {
			rp.verificaJogadaPBranco(obrigatoria, tabuleiro, casaInicial,
					casaFinal);
		} else if ((casaInicial.getPeca() instanceof Peao)
				&& (casaInicial.getPeca().getCor() == Pecas.PRETA)) {
			rp.verificaJogadaPPreto(obrigatoria, tabuleiro, casaInicial,
					casaFinal);
		} else if (casaInicial.getPeca() instanceof Dama) {
			rd.verificaDiagonalDama(obrigatoria, tabuleiro, casaInicial,
					casaFinal);
		}
	}

}