package br.com.assinchronus.componentes;

import br.com.assinchronus.negocio.RegraFinal;

public class Peao implements Pecas {

	RegraFinal rf = new RegraFinal();
	private int cor = 0;
	boolean capturada = false;

	@Override
	public void comer(Casa atual, Casa adversaria, Casa proxima) {
		if (adversaria.getPeca().getCor() == 1) {
			if (adversaria.getPeca() instanceof Peao) {
				RegraFinal.setQtdPeaoBranco(rf.getQtdPeaoBranco() - 1);
			} else {
				RegraFinal.setQtdDamaBranco(rf.getQtdDamaBranco() - 1);
			}
		} else {
			if (adversaria.getPeca() instanceof Peao) {
				RegraFinal.setQtdPeaoPreto(rf.getQtdPeaoPreto() - 1);
			} else {
				RegraFinal.setQtdDamaPreto(rf.getQtdDamaPreto() - 1);
			}
		}
		mover(atual, proxima);
		adversaria.getPeca().setCapturada(true);
	}

	@Override
	public void mover(Casa atual, Casa proxima) {
		Pecas peca = atual.getPeca();
		atual.setPeca(null);
		proxima.setPeca(peca);
		
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}
	
	public boolean isCapturada() {
		return capturada;
	}

	public void setCapturada(boolean capturada) {
		this.capturada = capturada;
	}
}
