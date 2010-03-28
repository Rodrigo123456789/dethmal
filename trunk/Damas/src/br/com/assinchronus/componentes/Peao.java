package br.com.assinchronus.componentes;

import br.com.assinchronus.negocio.RegraFinal;

public class Peao implements Pecas {

	private int cor = 0;
	RegraFinal rf = new RegraFinal();

	@Override
	public void comer(Casa atual, Casa adversaria, Casa proxima) {
		if(atual.getPeca().getCor()==1){
			rf.setQtdDamaBranco(rf.getQtdPeaoBranco()-1);
		}else{
			rf.setQtdDamaPreto(rf.getQtdPeaoPreto()-1);
		}
		mover(atual, proxima);
		adversaria.setPeca(null);
		
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
}
