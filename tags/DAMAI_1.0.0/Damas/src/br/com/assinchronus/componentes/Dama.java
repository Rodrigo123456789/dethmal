package br.com.assinchronus.componentes;

import br.com.assinchronus.gui.Jogo;

public class Dama implements Pecas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cor = 0;
	boolean capturada = false;

	@Override
	public void comer(Casa atual, Casa adversaria, Casa proxima) {
		if (adversaria.getPeca().getCor() == 1) {
			if (adversaria.getPeca() instanceof Peao) {
				Jogo.peoesBrancos--;
			} else {
				Jogo.damasBrancas--;
			}
		} else {
			if (adversaria.getPeca() instanceof Peao) {
				Jogo.peoesPretos--;
			} else {
				Jogo.damasPretas--;
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
