package br.com.assinchronus.ai;

import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.componentes.Tabuleiro;
import br.com.assinchronus.negocio.RegraGeral;
import br.com.assinchronus.util.Utility;

public class RegraAI {

	int jogada = 2;

	private Casa[][] tabuleiro;

	public RegraAI(Casa[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public void verificaCasas(Arvore node) {
		List<Casa[]> jogadaObrigatoria = RegraGeral.verificaCapturaObrigatoria(jogada, tabuleiro);
		List<Casa> casasFinais = new ArrayList<Casa>();
		if (!jogadaObrigatoria.isEmpty()) {
			if(jogadaObrigatoria.size() == 1){
				// Regra de obrigatoria
			}else{
				// Regra de obrigatoria
			}
		} else {
			for (int i = 0; i < tabuleiro.length; i++) {
				for (int j = 0; j < tabuleiro.length; j++) {
					if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
						Pecas peca = tabuleiro[i][j].getPeca();
						if (peca != null && peca.getCor() == jogada) {
							casasFinais = verificaJogada(tabuleiro[i][j]);
							if(!casasFinais.isEmpty()){
								criaJogada(node, tabuleiro[i][j], casasFinais);
							}
						}
					}
				}
			}
		}
	}

	private void criaJogada(Arvore root, Casa casaInicial, List<Casa> casasFinais) {
		Arvore node;
		for (Casa casaFinal : casasFinais) {
			node = new Arvore();
			node.setValor(calculaValorPosicional());
			Casa[][] clone = Utility.copy(tabuleiro);
			node.setTabuleiro(clone);
			Casa casaInicialNova = node.getTabuleiro()[casaInicial.getLinha()][casaInicial.getColuna()];
			Casa casaFinalNova = node.getTabuleiro()[casaFinal.getLinha()][casaFinal.getColuna()];
			node.getTabuleiro()[casaInicial.getLinha()][casaInicial.getColuna()].getPeca().mover(casaInicialNova, casaFinalNova);

			root.addNode(node);
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
	
	public int calculaValorPosicional(){
		Casa[][] tab = tabuleiro;
		int valortotal;
		int valorb = 0;
		int valorp = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					//calcular valor branco
					if (tab[i][j].getPeca() != null && tab[i][j].getPeca().getCor()==1){
						if(tab[i][j].getPeca() instanceof Dama){
							valorb = valorb + tab[i][j].getValor()*10;
						}else if(i == 1){
							valorb = valorb + tab[i][j].getValor()*7;
						}else{
							valorb = valorb + tab[i][j].getValor()*5;
						}
					}
					//calcular valor preto
					if (tab[i][j].getPeca() != null && tab[i][j].getPeca().getCor()==2){
						if(tab[i][j].getPeca() instanceof Dama){
							valorp = valorp + tab[i][j].getValor()*10;
						}else if(i == 6){
							valorp = valorp + tab[i][j].getValor()*7;
						}else{
							valorp = valorp + tab[i][j].getValor()*5;
						}
					}
				}
			}
		}
		
		//calculo do valor absoluto (computador = preto)
		valortotal = valorp - valorb;
		
		return valortotal;
	}	
	
	public float calculaValorTrinagulo(){
		Casa[][] tab = tabuleiro;
		float valortotal;
		float valorb = 0;
		float valorp = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					//calcular valor branco
					if (tab[i][j].getPeca() != null && tab[i][j].getPeca().getCor()==1){
						//calculo de caracteres defensivos
						if(i==7){
							valorb = valorb + 1;
						}else if(i==6 && (j==2 || j==4)){
							valorb = valorb + 1;
						}else if(i==5 && j==3){
							valorb = valorb + 1;
						}
							//calculo de caracteres materiais
						if(tab[i][j].getPeca() instanceof Dama){
							valorb = valorb + 3;
						}else{
							valorb = valorb + 1;
						}
					}
					//calcular valor preto
					if (tab[i][j].getPeca() != null && tab[i][j].getPeca().getCor()==2){
						//calculo de caracteres defensivos
						if(i==0){
							valorp = valorp + 1;
						}else if(i==1 && (j==3 || j==5)){
							valorp = valorp + 1;
						}else if(i==2 && j==4){
							valorp = valorp + 1;
						}
						//calculo de caracteres materiais
						if(tab[i][j].getPeca() instanceof Dama){
							valorp = valorp + 3;
						}else{
							valorp = valorp + 1;
						}
					}
				}
			}
		}
		//calculo do valor absoluto (computador = preto)
		valortotal = (valorp - valorb)/(valorp + valorb);
		
		return valortotal;
	}
	
	public static void main(String[] args) {
		Tabuleiro tabuleiro2 = new Tabuleiro();
		
		RegraAI regraAI = new RegraAI(tabuleiro2.getTabuleiro());
		
		Arvore node = new Arvore();
		node.setTabuleiro(tabuleiro2.getTabuleiro());
		regraAI.verificaCasas(node);
		
	}
}
