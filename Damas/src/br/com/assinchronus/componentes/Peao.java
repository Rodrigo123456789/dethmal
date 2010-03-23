package br.com.assinchronus.componentes;
import br.com.assinchronus.negocio.Regras;

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
		Regras r = new Regras();
		boolean finaliza;
		do{
			finaliza = r.peaoComerSeq(tabuleiro, proxima);
		}while(!finaliza);
		if(finaliza){
			//abrir metodo de troca de jogador
		}
		else
		{
			boolean sequencia = true;
			Casa c = new Casa();	//teste
			r.validarPeca(sequencia, atual.getPeca().getCor(), tabuleiro, proxima, c);
		}
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
