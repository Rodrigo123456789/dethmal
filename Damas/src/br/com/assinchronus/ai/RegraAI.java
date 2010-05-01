package br.com.assinchronus.ai;

import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.componentes.Tabuleiro;
import br.com.assinchronus.gui.Jogo;
import br.com.assinchronus.negocio.RegraGeral;

public class RegraAI {

	int jogada = 2;

	private Casa[][] tabuleiro;

	public void setJogada(int jogada) {
		this.jogada = jogada;
	}

	public RegraAI(Casa[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public void verificaCasas() {
		List<Casa[]> jogadaObrigatoria = RegraGeral.verificaCapturaObrigatoria(jogada, tabuleiro);
		List<Casa> casasFinais = new ArrayList<Casa>();
		if (!jogadaObrigatoria.isEmpty()) {
			// Regra de obrigatoria
		} else {
			for (int i = 0; i < tabuleiro.length; i++) {
				for (int j = 0; j < tabuleiro.length; j++) {
					if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
						Pecas peca = tabuleiro[i][j].getPeca();
						if (peca != null && peca.getCor() == jogada) {
							casasFinais = verificaJogada(tabuleiro[i][j]);
							if(!casasFinais.isEmpty()){
								criaJogada(tabuleiro[i][j], casasFinais);
							}
						}
					}
				}
			}
		}
	}

	private void criaJogada(Casa casaInicial, List<Casa> casasFinais) {
		Arvore no;
		Casa[][] taux = tabuleiro;
		for (Casa casaFinal : casasFinais) {
			taux = tabuleiro;
			no = new Arvore();
			no.setTabuleiro(taux);
			no.getTabuleiro()[casaInicial.getLinha()][casaInicial.getColuna()].getPeca().mover(casaInicial, casaFinal);

	//		no.addNode(no);
		}
		
	}

	private List<Casa> verificaJogada(Casa casaInicial) {
		List<Casa> casasFinais = new ArrayList<Casa>();

		Pecas peca = casaInicial.getPeca();

		if (peca instanceof Peao) {
			if (jogada == 1) {
				// movimento branco
				int colunaInicial = casaInicial.getColuna();
				int linhaInicial = casaInicial.getLinha();

				if (colunaInicial == 0 && (tabuleiro[linhaInicial - 1][colunaInicial + 1].getPeca() == null)) {
					casasFinais.add(tabuleiro[linhaInicial - 1][colunaInicial + 1]);
				} else if (colunaInicial == 7 && (tabuleiro[linhaInicial - 1][colunaInicial - 1].getPeca() == null)) {
					casasFinais.add(tabuleiro[linhaInicial - 1][colunaInicial - 1]);
				} else {
					if (tabuleiro[linhaInicial - 1][colunaInicial + 1].getPeca() == null) {
						casasFinais.add(tabuleiro[linhaInicial - 1][colunaInicial + 1]);
					}
					if (tabuleiro[linhaInicial - 1][colunaInicial - 1].getPeca() == null) {
						casasFinais.add(tabuleiro[linhaInicial - 1][colunaInicial - 1]);
					}
				}

			} else {
				// movimento preto
				int colunaInicial = casaInicial.getColuna();
				int linhaInicial = casaInicial.getLinha();

				if (colunaInicial == 0 && (tabuleiro[linhaInicial + 1][colunaInicial + 1].getPeca() == null)) {
					casasFinais.add(tabuleiro[linhaInicial + 1][colunaInicial + 1]);
				} else if (colunaInicial == 7 && (tabuleiro[linhaInicial + 1][colunaInicial - 1].getPeca() == null)) {
					casasFinais.add(tabuleiro[linhaInicial + 1][colunaInicial - 1]);
				} else if (colunaInicial > 0 && colunaInicial < 7){
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
	
	public static void main(String[] args) {
		Tabuleiro t= new Tabuleiro();
		RegraAI regraAI = new RegraAI(t.getTabuleiro());
		
		regraAI.verificaCasas();
		System.out.println(" rodo");
		
	}
}
