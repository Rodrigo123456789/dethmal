package br.com.assinchronus.componentes;

import br.com.assinchronus.negocio.RegraGeral;
import br.com.assinchronus.negocio.RegraPeao;

public class Peao implements Pecas {

	private int cor = 0;

	public Peao() {

	}

	@Override
	public void comer(Casa[][] tabuleiro, Casa atual, Casa adversaria,
			Casa proxima) {
		mover(atual, proxima);
		adversaria.setPeca(null);
		RegraGeral rg = new RegraGeral();
		RegraPeao rp = new RegraPeao();
		boolean finaliza = rp.verificaSequenciaPeao(tabuleiro, proxima);
		if (finaliza) {
			// abrir metodo de troca de jogador
		} else {
			boolean sequencia = true;
			Casa c = new Casa(); // teste
			// devemos chamar um metodo de analise de clique para recebermos a
			// casa final (pois a casa inicial eh fixa)
			rg.validarPeca(sequencia, atual.getPeca().getCor(), tabuleiro,
					proxima, c);
		}
	}

	@Override
	public void mover(Casa atual, Casa proxima) {
		Pecas peca = atual.getPeca();
		atual.setPeca(null);
		proxima.setPeca(peca);
		// abrir metodo de troca de jogador

	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}
}
