package br.com.assinchronus.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.gui.Jogo;
import br.com.assinchronus.negocio.RegraGeral;
import br.com.assinchronus.util.Utility;

public class RegraAI {

	private int jogada;
	private int nivelArvore;
	private Casa[][] tabuleiro;
	Map<Casa, List<Casa>> jogadaObrigatoria;

	/**
	 * 
	 * @param nivelArvore
	 *            Nivel da Arvore que sera gerada
	 */
	public RegraAI(int nivelArvore) {
		jogada = Jogo.jogada;
		this.nivelArvore = nivelArvore;
	}

	public void verificaCasas(Arvore node, int nivel) {
		jogada = nivel % 2 == nivelArvore % 2 ? Pecas.PRETA : Pecas.BRANCA;

		if (nivel != 0) {
			tabuleiro = node.getTabuleiro();
			jogadaObrigatoria = RegraGeral.verificaCapturaObrigatoria(tabuleiro, jogada);
			List<Casa> casasFinais = new ArrayList<Casa>();
			// Jogadas Obrigatorias
			if (!jogadaObrigatoria.isEmpty()) {
				System.out.println("obrigatoria");
				Set<Casa> listaCasasIniciais = jogadaObrigatoria.keySet();
				for (Casa casaInicial : listaCasasIniciais) {
					System.out.println("Chamando criaJogada para:" +casaInicial.getLinha()+","+casaInicial.getColuna() );
					casasFinais = jogadaObrigatoria.get(casaInicial);
					criaJogada(node, casaInicial, casasFinais, nivel, true);
				}
				// Jogadas comuns
			} else {
				for (int i = 0; i < tabuleiro.length; i++) {
					for (int j = 0; j < tabuleiro.length; j++) {
						if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
							Pecas peca = node.getTabuleiro()[i][j].getPeca();
							if (peca != null && peca.getCor() == jogada) {
								casasFinais = verificaJogada(node.getTabuleiro()[i][j]);
								if (!casasFinais.isEmpty()) {
									criaJogada(node, node.getTabuleiro()[i][j], casasFinais, nivel, false);
									node.setTabuleiro(tabuleiro);
								}
							}
						}
					}
				}

				for (Arvore no : node.getArvores()) {
					verificaCasas(no, nivel - 1);
				}
			}
		}
	}

	private List<Casa> verificaJogada(Casa casaInicial) {
		List<Casa> casasFinais = new ArrayList<Casa>();

		Pecas peca = casaInicial.getPeca();

		// movimento branco
		int colunaInicial = casaInicial.getColuna();
		int linhaInicial = casaInicial.getLinha();

		if (peca instanceof Peao) {
			if (jogada == 1) {
				if (colunaInicial == 0 && (tabuleiro[linhaInicial - 1][colunaInicial + 1].getPeca() == null)) {
					casasFinais.add(tabuleiro[linhaInicial - 1][colunaInicial + 1]);
				} else if (colunaInicial == 7 && (tabuleiro[linhaInicial - 1][colunaInicial - 1].getPeca() == null)) {
					casasFinais.add(tabuleiro[linhaInicial - 1][colunaInicial - 1]);
				} else if (colunaInicial > 0 && colunaInicial < 7) {
					if (tabuleiro[linhaInicial - 1][colunaInicial + 1].getPeca() == null) {
						casasFinais.add(tabuleiro[linhaInicial - 1][colunaInicial + 1]);
					}
					if (tabuleiro[linhaInicial - 1][colunaInicial - 1].getPeca() == null) {
						casasFinais.add(tabuleiro[linhaInicial - 1][colunaInicial - 1]);
					}
				}

			} else {
				if (colunaInicial == 0 && (tabuleiro[linhaInicial + 1][colunaInicial + 1].getPeca() == null)) {
					casasFinais.add(tabuleiro[linhaInicial + 1][colunaInicial + 1]);
				} else if (colunaInicial == 7 && (tabuleiro[linhaInicial + 1][colunaInicial - 1].getPeca() == null)) {
					casasFinais.add(tabuleiro[linhaInicial + 1][colunaInicial - 1]);
				} else if (colunaInicial > 0 && colunaInicial < 7) {
					if (tabuleiro[linhaInicial + 1][colunaInicial + 1].getPeca() == null) {
						casasFinais.add(tabuleiro[linhaInicial + 1][colunaInicial + 1]);
					}
					if (tabuleiro[linhaInicial + 1][colunaInicial - 1].getPeca() == null) {
						casasFinais.add(tabuleiro[linhaInicial + 1][colunaInicial - 1]);
					}
				}
			}
		} else {
			// movimento dama
		}
		return casasFinais;
	}

	private void criaJogada(Arvore root, Casa casaInicial, List<Casa> casasFinais, int nivel, boolean obrigatoria) {
		Arvore node = null;

		for (Casa casaFinal : casasFinais) {
			Casa[][] clone = Utility.copy(root.getTabuleiro());
			node = new Arvore(clone);

			Casa casaInicialNova = node.getTabuleiro()[casaInicial.getLinha()][casaInicial.getColuna()];
			Casa casaFinalNova = node.getTabuleiro()[casaFinal.getLinha()][casaFinal.getColuna()];

			if (obrigatoria) {
				// chamar metodo que procura o adversario de acordo com casa
				// inicial e final e retorna uma casa adversaria
				Casa adversaria = acharAdversario(root.getTabuleiro(), casaInicialNova, casaFinalNova);
				System.out.println("Adversario em: " +adversaria.getLinha() +","+adversaria.getColuna());
				// chamar o metodo comer
				System.out.println("Chamando comer para:" +casaInicialNova.getLinha()+","+casaInicialNova.getColuna()+" até "+casaFinalNova.getLinha()+","+casaFinalNova.getColuna() );
				node.getTabuleiro()[casaInicialNova.getLinha()][casaInicialNova.getColuna()].getPeca().comer(casaInicialNova, adversaria, casaFinalNova);
			} else {
				node.getTabuleiro()[casaInicialNova.getLinha()][casaInicialNova.getColuna()].getPeca().mover(casaInicialNova, casaFinalNova);
			}
			node.setValor(calculaValorPosicional(node.getTabuleiro()));
//			System.out.println("Peca : " + casaInicialNova + " Para: " + casaFinalNova + " nivel : " + nivel + " valor: "
//					+ String.valueOf(calculaValorPosicional(node.getTabuleiro())));

			root.addNode(node);
		}
	}

	private Casa acharAdversario(Casa[][] tab, Casa casaInicial, Casa casaFinal) {
		// encontrar a direcao do movimento
		Casa adversaria = new Casa();
		int linha = casaInicial.getLinha();
		int coluna = casaInicial.getColuna();
		int z = 1; // incrementa para varrer toda a diagonal escolhida
		int x; // salva a direcao horizontal do movimento
		int y; // salva a direcao vertical do movimento

		x = casaFinal.getColuna() - coluna;
		y = casaFinal.getLinha() - linha;

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

		// incrementar a partir da origem até o destino

		while ((linha + y * z) != casaFinal.getLinha()) {
			if (tabuleiro[linha + y * z][coluna + x * z].getPeca() != null
					&& tabuleiro[linha + y * z][coluna + x * z].getPeca().getCor() != casaInicial.getPeca().getCor()) {
				adversaria = tabuleiro[linha + y * z][coluna + x * z];
			}
			z++;
		}

		// quando encontrar a adversaria, retornar

		return adversaria;
	}

	private int calculaValorPosicional(Casa[][] tab) {
		int valorb = 0;
		int valorp = 0;

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					// calcular valor branco
					if (tab[i][j].getPeca() != null && tab[i][j].getPeca().getCor() == Pecas.BRANCA) {
						if (tab[i][j].getPeca() instanceof Dama) {
							valorb += tab[i][j].getValor() * 10;
						} else if (i == 1) {
							valorb += +tab[i][j].getValor() * 7;
						} else {
							valorb += +tab[i][j].getValor() * 5;
						}
					}
					// calcular valor preto
					if (tab[i][j].getPeca() != null && tab[i][j].getPeca().getCor() == Pecas.PRETA) {
						if (tab[i][j].getPeca() instanceof Dama) {
							valorp += tab[i][j].getValor() * 10;
						} else if (i == 6) {
							valorp += tab[i][j].getValor() * 7;
						} else {
							valorp += tab[i][j].getValor() * 5;
						}
					}
				}
			}
		}

		// calculo do valor absoluto (computador = preto)
		return valorp - valorb;
	}

	@SuppressWarnings("unused")
	private float calculaValorTrinagulo(Casa[][] tab) {
		float valortotal;
		float valorb = 0;
		float valorp = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				if ((i % 2 == 0
						&& j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					// calcular valor branco
					if (tab[i][j].getPeca() != null && tab[i][j].getPeca().getCor() == 1) {
						// calculo de caracteres defensivos
						if (i == 7) {
							valorb = valorb + 1;
						} else if (i == 6 && (j == 2 || j == 4)) {
							valorb = valorb + 1;
						} else if (i == 5 && j == 3) {
							valorb = valorb + 1;
						} // calculo de caracteres materiais
						if (tab[i][j].getPeca() instanceof Dama) {
							valorb = valorb + 3;
						} else {
							valorb = valorb + 1;
						}
					} // calcular valor preto
					if (tab[i][j].getPeca() != null && tab[i][j].getPeca().getCor() == 2) {
						// calculo de caracteres defensivos
						if (i == 0) {
							valorp = valorp + 1;
						} else if (i == 1 && (j == 3 || j == 5)) {
							valorp = valorp + 1;
						} else if (i == 2 && j == 4) {
							valorp = valorp + 1;
						}
						// calculo de caracteres materiais
						if (tab[i][j].getPeca() instanceof Dama) {
							valorp = valorp + 3;
						} else {
							valorp = valorp + 1;
						}
					}
				}
			}
		}
		// calculo do valor absoluto (computador = preto)
		valortotal = (valorp - valorb) / (valorp + valorb);

		return valortotal;
	}
	
}
