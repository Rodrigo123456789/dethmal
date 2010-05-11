package br.com.assinchronus.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.exception.JogadaInvalida;
import br.com.assinchronus.gui.Jogo;

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
	 *            Indica de quem é a vez
	 * @param tabuleiro
	 *            Tabuleiro atual do jogo
	 * @return
	 */
	public static Map<Casa, List<Casa>> verificaCapturaObrigatoria(Casa[][] tabuleiro, int jogada) {
		int z, l, c;
		List<Casa> casasfinais;
		Map<Casa, List<Casa>> obrigatoria = new HashMap<Casa, List<Casa>>();
				
		// List<Casa[]> obrigatoria = new ArrayList<Casa[]>();
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				casasfinais = new ArrayList<Casa>();
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && tabuleiro[i][j].getPeca() != null) {
					if (jogada == Pecas.BRANCA) {
						if (tabuleiro[i][j].getPeca().getCor() == Pecas.BRANCA && (tabuleiro[i][j].getPeca() instanceof Peao)) {
							// Se for um peao branco
							if (i - 2 > -1 && j + 2 < 8) {
								if (tabuleiro[i - 1][j + 1].getPeca() != null && tabuleiro[i - 1][j + 1].getPeca().getCor() == Pecas.PRETA
										&& tabuleiro[i - 2][j + 2].getPeca() == null) {

									casasfinais.add(tabuleiro[i - 2][j + 2]);
								}
							}
							if (i - 2 > -1 && j - 2 > -1) {
								if (tabuleiro[i - 1][j - 1].getPeca() != null && tabuleiro[i - 1][j - 1].getPeca().getCor() == Pecas.PRETA
										&& tabuleiro[i - 2][j - 2].getPeca() == null) {

									casasfinais.add(tabuleiro[i - 2][j - 2]);
								}
							}
							if(!casasfinais.isEmpty()){
								obrigatoria.put(tabuleiro[i][j], casasfinais);
								casasfinais.clear();
							}

						} else if (tabuleiro[i][j].getPeca() != null && tabuleiro[i][j].getPeca().getCor() == Pecas.BRANCA
								&& (tabuleiro[i][j].getPeca() instanceof Dama)) {
							// Se for uma dama branca
							DamaBrancaSuperiorEsquerda: for (l = -1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal superior esquerda
								if ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) > -1) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										break;
									} else if ((tabuleiro[i + l * z][j + c * z].getPeca() != null)
											&& (tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null)) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										do {
											casasfinais.add(tabuleiro[i + l * (z + 1)][j + c * (z + 1)]);
											z++;
										}while ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) > -1	&& tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null);
										break DamaBrancaSuperiorEsquerda;
									}
								}
							}
							DamaBrancaSuperiorDireita: for (l = -1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal superior direita
								if ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) < 8) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										do {
											casasfinais.add(tabuleiro[i + l * (z + 1)][j + c * (z + 1)]);
											z++;
										}while ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) < 8 && tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null);
										break DamaBrancaSuperiorDireita;
									}
								}
							}
							DamaBrancaInferiorEsquerda: for (l = +1, c = -1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior esquerda
								if ((i + l * (z + 1)) < 8 && (j + c * (z + 1)) > -1) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										do {
											casasfinais.add(tabuleiro[i + l * (z + 1)][j + c * (z + 1)]);
											z++;
										}while ((i + l * (z + 1)) < 8 && (j + c * (z + 1)) > -1 && tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null);
										break DamaBrancaInferiorEsquerda;
									}
								}
							}
							DamaBrancaInferiorDireita: for (l = +1, c = +1, z = 1; z <= 6; z++) {
								// analisando diagonal inferior direita
								if ((i + l * (z + 1)) < 8 && (j + c * (z + 1)) < 8) {
									if (tabuleiro[i + l * z][j + c * z].getPeca() != null && tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.BRANCA) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() != null) {
										break;
									} else if (tabuleiro[i + l * z][j + c * z].getPeca() != null
											&& tabuleiro[i + l * z][j + c * z].getPeca().getCor() == Pecas.PRETA) {
										do {
											casasfinais.add(tabuleiro[i + l * (z + 1)][j + c * (z + 1)]);
											z++;
										}while ((i + l * (z + 1)) < 8 && (j + c * (z + 1)) < 8 && tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null);
										break DamaBrancaInferiorDireita;
									}
								}
							}

							if(!casasfinais.isEmpty()){
								System.out.println("na verificacao. De: " + i + "," + j + " Para: "+ casasfinais.get(0).getLinha()+","+ casasfinais.get(0).getColuna());
								obrigatoria.put(tabuleiro[i][j], casasfinais);
							}

						}
					} else {
						if (tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA && (tabuleiro[i][j].getPeca() instanceof Peao)) {
							// Se for um peao preto
							if (i + 2 < 8 && j + 2 < 8) {
								if (tabuleiro[i + 1][j + 1].getPeca() != null && tabuleiro[i + 1][j + 1].getPeca().getCor() == Pecas.BRANCA
										&& tabuleiro[i + 2][j + 2].getPeca() == null) {

									casasfinais.add(tabuleiro[i + 2][j + 2]);
								}
							}
							if (i + 2 < 8 && j - 2 > -1) {
								if (tabuleiro[i + 1][j - 1].getPeca() != null && tabuleiro[i + 1][j - 1].getPeca().getCor() == Pecas.BRANCA
										&& tabuleiro[i + 2][j - 2].getPeca() == null) {

									casasfinais.add(tabuleiro[i + 2][j - 2]);
								}
							}

							if(!casasfinais.isEmpty()){
								obrigatoria.put(tabuleiro[i][j], casasfinais);
							}

						} else if (tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA && (tabuleiro[i][j].getPeca() instanceof Dama)) {
							// Se for uma dama preta
							DamaPretaSuperiorEsquerda:
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
										do {
											casasfinais.add(tabuleiro[i + l * (z + 1)][j + c * (z + 1)]);
											z++;
										}while ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) > -1 && tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null);
										break DamaPretaSuperiorEsquerda;
									}
								}
							}
							DamaPretaSuperiorDireita:
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
										do {
											casasfinais.add(tabuleiro[i + l * (z + 1)][j + c * (z + 1)]);
											z++;
										}while ((i + l * (z + 1)) > -1 && (j + c * (z + 1)) < 8 && tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null);
										break DamaPretaSuperiorDireita;
									}
								}
							}
							DamaPretaInferiorEsquerda:
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
										do {
											casasfinais.add(tabuleiro[i + l * (z + 1)][j + c * (z + 1)]);
											z++;
										}while ((i + l * (z + 1)) < 8 && (j + c * (z + 1)) > -1 && tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null);
										break DamaPretaInferiorEsquerda;
									}
								}
							}
							DamaPretaInferiorDireita:
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
										do {
											casasfinais.add(tabuleiro[i + l * (z + 1)][j + c * (z + 1)]);
											z++;
										}while ((i + l * (z + 1)) > -1 && (i + l * (z + 1)) < 8 && tabuleiro[i + l * (z + 1)][j + c * (z + 1)].getPeca() == null);
										break DamaPretaInferiorDireita;
									}
								}
							}
							if(!casasfinais.isEmpty()){
								System.out.println("na verificacao. De: " + i + "," + j + " Para: "+ casasfinais.get(0).getLinha()+","+ casasfinais.get(0).getColuna());
								obrigatoria.put(tabuleiro[i][j], casasfinais);
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

		Map<Casa, List<Casa>> obrigatoria = new HashMap<Casa, List<Casa>>();
		Map<Casa, List<Casa>> sequencia = new HashMap<Casa, List<Casa>>();
		sequencia.clear();
		obrigatoria.clear();
		// se primeira jogada, testa pra ver se tem captura obrigatoria
		// se nao for a primeira, vai direto com obrigatoria true.
		if (!getSequencia()) {
			obrigatoria = verificaCapturaObrigatoria(tabuleiro, Jogo.jogada);
			Jogo.setMSG("Captura obrigatoria: " + obrigatoria);
		}

		if ((casaInicial.getPeca() instanceof Peao) && (casaInicial.getPeca().getCor() == Pecas.BRANCA)) {
			Jogo.setMSG("Analisando movimento do peao branco");
			sequencia = rp.verificaJogadaPBranco(obrigatoria, tabuleiro, casaInicial, casaFinal);
			forcacaptura = !sequencia.isEmpty();
			return;
		} else if ((casaInicial.getPeca() instanceof Peao) && (casaInicial.getPeca().getCor() == Pecas.PRETA)) {
			Jogo.setMSG("Analisando movimento do peao preto");
			sequencia = rp.verificaJogadaPPreto(obrigatoria, tabuleiro, casaInicial, casaFinal);
			forcacaptura = !sequencia.isEmpty();
			return;
		} else if (casaInicial.getPeca() instanceof Dama) {
			Jogo.setMSG("Analisando Diagonal da Dama");
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

	public boolean isForcaCaptura() {
		return forcacaptura;
	}

	public static void setForcacaptura(boolean forcacaptura) {
		RegraGeral.forcacaptura = forcacaptura;
	}

}