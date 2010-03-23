package br.com.assinchronus.componentes;

/**
 * 
 * @author Pilon
 * @version 1.0.3
 */

public class Peao implements Pecas {

	// Cor da peca
	private int cor = 0;

	public Peao() {

	}
	
	/**
	 * Nome: comer
	 * 
	 * @param atual
	 *            - Casa atual da peca
	 * @param proxima
	 *            - Proxima casa da peca
	 */
	@Override
	public void comer(Casa[][] tabuleiro, Casa atual, Casa adversaria, Casa proxima) {
		mover(atual, proxima);
		adversaria.setPeca(null);
		boolean finaliza;;
		do{
			finaliza = peaoComerSeq(tabuleiro, proxima);
		}while(!finaliza);
	}


	/**
	 * Nome: mover
	 * 
	 * @param atual
	 *            - Casa atual da peca
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
