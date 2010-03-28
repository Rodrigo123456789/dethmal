package br.com.assinchronus.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;

//erro svn
public class RegraFinal {

	int qtdPeaoBranco;
	int qtdPeaoPreto;
	int qtdDamaBranco;
	int qtdDamaPreto;

	public List<Casa> verificaPecas(Casa[][] tabuleiro) {
		List<Casa> casa = new ArrayList<Casa>();
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && tabuleiro[i][j].getPeca() != null) {
					casa.add(tabuleiro[i][j]); 
					//lista de todas as casas com peca
				}
			}
		}
		return casa;
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

}