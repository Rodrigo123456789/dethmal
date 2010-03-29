package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.*;

public class RegraFinal {

	int qtdPeaoBranco;
	int qtdPeaoPreto;
	int qtdDamaBranco;
	int qtdDamaPreto;
	
	RegraGeral rg = new RegraGeral();

	public boolean verificaPecasBranca(Casa[][] tabuleiro) {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && tabuleiro[i][j].getPeca() != null && tabuleiro[i][j].getPeca().getCor() == Pecas.BRANCA) {
					if(tabuleiro[i][j].getPeca() instanceof Peao){
						//se a peca for um peao branco
						if(tabuleiro[i-1][j+1].getPeca()==null || tabuleiro[i-1][j-1].getPeca()==null){
							//se pode se mover
							return true;
						}
						if(i - 2 > -1 && j + 2 < 8 && tabuleiro[i-1][j+1].getPeca()!=null && tabuleiro[i-1][j+1].getPeca().getCor()==Pecas.PRETA && tabuleiro[i-2][j+2].getPeca()==null){
							//se pode comer diagonal superior direita
							return true;
						}
						if(i - 2 > -1 && j - 2 > -1 && tabuleiro[i-1][j-1].getPeca()!=null && tabuleiro[i-1][j-1].getPeca().getCor()==Pecas.PRETA && tabuleiro[i-2][j-2].getPeca()==null){
							//se pode comer diagonal superior esquerda
							return true;
						}
						if(RegraGeral.getSequencia()==true && i + 2 < 8 && j + 2 < 8 && tabuleiro[i+1][j+1].getPeca()!=null && tabuleiro[i+1][j+1].getPeca().getCor()==Pecas.PRETA && tabuleiro[i+2][j+2].getPeca()==null){
							//se pode comer diagonal inferior direita
							return true;
						}
						if(RegraGeral.getSequencia()==true && i + 2 < 8 && j - 2 > -1 && tabuleiro[i+1][j-1].getPeca()!=null && tabuleiro[i+1][j-1].getPeca().getCor()==Pecas.PRETA && tabuleiro[i+2][j-2].getPeca()==null){
							//se pode comer diagonal inferior esquerda
							return true;
						}
					}else if(tabuleiro[i][j].getPeca() instanceof Dama){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean verificaPecasPreta(Casa[][] tabuleiro) {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && tabuleiro[i][j].getPeca() != null && tabuleiro[i][j].getPeca().getCor() == Pecas.PRETA) {
					if(tabuleiro[i][j].getPeca() instanceof Peao){
						//se a peca for um peao preto
						if(tabuleiro[i+1][j+1].getPeca()==null || tabuleiro[i+1][j-1].getPeca()==null){
							//se pode se mover
							return true;
						}
						if(RegraGeral.getSequencia()==true && i - 2 > -1 && j + 2 < 8 && tabuleiro[i-1][j+1].getPeca()!=null && tabuleiro[i-1][j+1].getPeca().getCor()==Pecas.PRETA && tabuleiro[i-2][j+2].getPeca()==null){
							//se pode comer diagonal superior direita
							return true;
						}
						if(RegraGeral.getSequencia()==true && i - 2 > -1 && j - 2 > -1 && tabuleiro[i-1][j-1].getPeca()!=null && tabuleiro[i-1][j-1].getPeca().getCor()==Pecas.PRETA && tabuleiro[i-2][j-2].getPeca()==null){
							//se pode comer diagonal superior esquerda
							return true;
						}
						if(i + 2 < 8 && j + 2 < 8 && tabuleiro[i+1][j+1].getPeca()!=null && tabuleiro[i+1][j+1].getPeca().getCor()==Pecas.PRETA && tabuleiro[i+2][j+2].getPeca()==null){
							//se pode comer diagonal inferior direita
							return true;
						}
						if(i + 2 < 8 && j - 2 > -1 && tabuleiro[i+1][j-1].getPeca()!=null && tabuleiro[i+1][j-1].getPeca().getCor()==Pecas.PRETA && tabuleiro[i+2][j-2].getPeca()==null){
							//se pode comer diagonal inferior esquerda
							return true;
						}
					}else if(tabuleiro[i][j].getPeca() instanceof Dama){
						return true;
					}
				}
			}
		}
		return false;
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