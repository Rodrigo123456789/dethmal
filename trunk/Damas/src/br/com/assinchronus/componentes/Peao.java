package br.com.assinchronus.componentes;

/**
 * 
 * @author Pilon
 * @version 1.0.2
 */

public class Peao implements Pecas {

	// Cor da peça
	private int cor = 0;

	public Peao() {

	}

	@Override
	public void comer(Casa atual, Casa casa) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * 
	 * @param atual
	 *            - Casa atual da peça
	 * @param proxima
	 *            - Proxima casa da peca
	 */
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
