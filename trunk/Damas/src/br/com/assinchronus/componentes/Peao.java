package br.com.assinchronus.componentes;
//erro svn
public class Peao implements Pecas {

	private int cor = 0;

	public Peao() {

	}

	@Override
	public void comer(Casa atual, Casa adversaria, Casa proxima) {
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
