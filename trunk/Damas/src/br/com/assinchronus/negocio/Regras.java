package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;

public class Regras {

	public boolean analisaTabuleiro(String jogada, Casa[][] tabuleiro) {
		int z, l, c;
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
						&& tabuleiro[i][j].getPeca() != null) {
					if (jogada.equals('b')) {
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

	public void validarPeca(String jogada, Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal) {

		boolean obrigatoria = analisaTabuleiro(jogada, tabuleiro);

		if ((casaInicial.getPeca() instanceof Peao)
				&& (casaInicial.getPeca().getCor() == Pecas.BRANCA)) {
			moverPeaoBranca(obrigatoria, tabuleiro, casaInicial, casaFinal);
		} else if ((casaInicial.getPeca() instanceof Peao)
				&& (casaInicial.getPeca().getCor() == Pecas.PRETA)) {
			moverPeaoPreta(obrigatoria, tabuleiro, casaInicial, casaFinal);
		} else if (casaInicial.getPeca() instanceof Dama) {
			verificaDama(obrigatoria, tabuleiro, casaInicial, casaFinal);
		}
	}

	public void moverPeaoBranca(boolean obrigatoria, Casa[][] tabuleiro,
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

	public void moverPeaoPreta(boolean obrigatoria, Casa[][] tabuleiro,
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

	public void verificaDama(boolean obrigatoria, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal) {
		int x; // salva a direcao horizontal do movimento
		int y; // salva a direcao vertical do movimento
		if (Math.abs(casaFinal.getLinha() - casaInicial.getLinha()) == Math
				.abs(casaFinal.getColuna() - casaInicial.getColuna())) {
			// caso o movimento tenha sido dentro de uma diagonal permitida
			x = casaFinal.getColuna() - casaInicial.getColuna();
			y = casaFinal.getLinha() - casaInicial.getLinha();
			if (x > 0 && y > 0) // movimento para baixo direita
			{
				x = 1;
				y = 1;
			}
			if (x > 0 && y < 0) // movimento para baixo esquerda
			{
				x = 1;
				y = -1;
			}
			if (x < 0 && y > 0) // movimento para cima direita
			{
				x = -1;
				y = 1;
			}
			if (x < 0 && y < 0) // movimento para cima esquerda
			{
				x = -1;
				y = -1;
			}
			if (casaInicial.getPeca().getCor() == Pecas.BRANCA) {
				// Se a dama for Branca
				moverDamaBranca(obrigatoria, tabuleiro, casaInicial, casaFinal,
						x, y);
			} else {
				// Se a dama for preta
				moverDamaPreta(obrigatoria, tabuleiro, casaInicial, casaFinal,
						x, y);
			}
		} else {
			// chamar classe de erros: Movimento invalido
		}
	}

	public void moverDamaBranca(boolean obrigatoria, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal, int x, int y) {
		int linha = casaInicial.getLinha();
		int coluna = casaInicial.getColuna();
		int z = 1; // incrementa para varrer toda a diagonal escolhida
		int adversaria = 0; // conta as pecas adversarias no caminho
		while (adversaria < 2 && (linha + y * z) != (casaFinal.getLinha())) {
			// enquanto a linha analisada (comeca uma casa na frente da inicial)
			// nao for igual a final e houver menos de 2 adversarios no caminho
			if (tabuleiro[linha + y * z][coluna + x * z].getPeca() != null)
			// Se houver peca
			{
				if (tabuleiro[linha + y * z][coluna + x * z].getPeca().getCor() == Pecas.BRANCA) {
					// Se ha peca branca na casa analisada
					// chamar erro "nao pode pular a propria peca"
				} else { // se ha peca preta, incrementar contador de peca
					// adversaria
					adversaria++;
				}
			} else {
				// se nao houver peca
			}
			z++;
		}
		if (adversaria == 0) {
			if (obrigatoria) {
				// Mensagem de jogada invalida
			} else {
				// chamar metodo mover
			}
		} else if (adversaria == 1) {
			// chamar metodo comer
		} else if (adversaria == 2) {
			// chamar msg de movimento invalido
		}

	}

	public void moverDamaPreta(boolean obrigatoria, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal, int x, int y) {
		int linha = casaInicial.getLinha();
		int coluna = casaInicial.getColuna();
		int z = 1; // incrementa para varrer toda a diagonal escolhida
		int adversaria = 0; // conta as pecas adversarias no caminho
		while (adversaria < 2 && (linha + y * z) != (casaFinal.getLinha())) {
			// enquanto a linha analisada (comeca uma casa na frente da inicial)
			// nao for igual a final e houver menos de 2 adversarios no caminho
			if (tabuleiro[linha + y * z][coluna + x * z].getPeca() != null) // Se
			// houver
			// peca
			{
				if (tabuleiro[linha + y * z][coluna + x * z].getPeca().getCor() == Pecas.PRETA) {
					// Se ha peca branca na casa analisada
					// chamar erro "nao pode pular a propria peca"
				} else { // se ha peca preta, incrementar contador de peca
					// adversaria
					adversaria++;
				}
			} else {
				// se nao houver peca
			}
		}
		if (adversaria == 0) {
			if (obrigatoria) {
				// Mensagem de jogada invalida
			} else {
				// chamar metodo mover
			}
		} else if (adversaria == 1) {
			// chamar metodo comer
		} else if (adversaria == 2) {
			// chamar msg de movimento invalido
		}
	}

	public boolean peaoComerSeq(Casa[][] tabuleiro, Casa casaInicial){
		if(tabuleiro[casaInicial.getLinha()+1][casaInicial.getColuna()+1].getPeca()!=null && tabuleiro[casaInicial.getLinha()+1][casaInicial.getColuna()+1].getPeca().getCor()!=casaInicial.getPeca().getCor() && tabuleiro[casaInicial.getLinha()+2][casaInicial.getColuna()+2].getPeca()==null){
			return true;
		}
		if(tabuleiro[casaInicial.getLinha()+1][casaInicial.getColuna()-1].getPeca()!=null && tabuleiro[casaInicial.getLinha()+1][casaInicial.getColuna()-1].getPeca().getCor()!=casaInicial.getPeca().getCor() && tabuleiro[casaInicial.getLinha()+2][casaInicial.getColuna()-2].getPeca()==null){
			return true;
		}
		if(tabuleiro[casaInicial.getLinha()-1][casaInicial.getColuna()+1].getPeca()!=null && tabuleiro[casaInicial.getLinha()-1][casaInicial.getColuna()+1].getPeca().getCor()!=casaInicial.getPeca().getCor() && tabuleiro[casaInicial.getLinha()-2][casaInicial.getColuna()+2].getPeca()==null){
			return true;
		}
		if(tabuleiro[casaInicial.getLinha()-1][casaInicial.getColuna()-1].getPeca()!=null && tabuleiro[casaInicial.getLinha()-1][casaInicial.getColuna()-1].getPeca().getCor()!=casaInicial.getPeca().getCor() && tabuleiro[casaInicial.getLinha()-2][casaInicial.getColuna()-2].getPeca()==null){
			return true;
		}
		return false;
	}
	
}