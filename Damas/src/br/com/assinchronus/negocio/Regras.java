package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;

public class Regras {

	public void validar(Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) {

		if ((casaInicial.getPeca() instanceof Peao)
				&& (casaInicial.getPeca().getCor() == Pecas.BRANCA)) {
			moverPeaoBranca(tabuleiro, casaInicial, casaFinal);
		} else if ((casaInicial.getPeca() instanceof Peao)
				&& (casaInicial.getPeca().getCor() == Pecas.PRETA)) {
			moverPeaoPreta(tabuleiro, casaInicial, casaFinal);
		} else if (casaInicial.getPeca() instanceof Dama) {
			verificaDama(tabuleiro, casaInicial, casaFinal);
		}
	}

	public void moverPeaoBranca(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal) {
		if ((casaFinal.getColuna() - casaInicial.getColuna() == 1 || casaFinal
				.getColuna()
				- casaInicial.getColuna() == -1)
				&& (casaFinal.getLinha() - casaInicial.getLinha() == -1))
			if (casaFinal.getPeca() != null) {
				// entrar na classe de erros e mandar o erro de j� tem
				// pe�a aki!
			} else {
				// chamar metodo mover -> passar casa inicial e final
			}
		else if ((casaFinal.getColuna() - casaInicial.getColuna()) == 2
				&& (casaFinal.getLinha() - casaInicial.getLinha() == -2)) { // Movimento
			// duplo
			// para
			// a
			// direita
			if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() - 1]
					.getPeca() != null
					&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
							.getColuna() - 1].getPeca().getCor() == Pecas.PRETA) {
				// chamar metodo comer -> passar as 3 casas
			} else {
				// entrar na classe de erros e mandar o erro de
				// movimento inv�lido
			}
		} else {
			if ((casaFinal.getColuna() - casaInicial.getColuna()) == -2
					&& (casaFinal.getLinha() - casaInicial.getLinha() == -2)) { // Movimento
				// duplo
				// para
				// a
				// esquerda
				if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() + 1]
						.getPeca() != null
						&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
								.getColuna() + 11].getPeca().getCor() == Pecas.PRETA) {
					// chamar metodo comer -> passar as 3 casas
				} else {
					// entrar na classe de erros e mandar o erro de
					// movimento inv�lido
				}
			} else {
				// entrar na classe de erros e mandar o erro de
				// movimento inv�lido
			}
		}
	}

	public void moverPeaoPreta(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal) {
		if ((casaFinal.getColuna() - casaInicial.getColuna() == 1 || casaFinal
				.getColuna()
				- casaInicial.getColuna() == -1)
				&& (casaFinal.getLinha() - casaInicial.getLinha() == 1))
			if (casaFinal.getPeca() != null) {
				// entrar na classe de erros e mandar o erro de j� tem
				// pe�a aki!
			} else {
				// chamar metodo mover -> passar casa inicial e final
			}
		else if ((casaFinal.getColuna() - casaInicial.getColuna()) == 2
				&& (casaFinal.getLinha() - casaInicial.getLinha() == 2)) { // Movimento
			// duplo
			// para
			// a
			// direita
			if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() - 1]
					.getPeca() != null
					&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
							.getColuna() - 1].getPeca().getCor() == Pecas.BRANCA) {
				// chamar metodo comer -> passar as 3 casas
			} else {
				// entrar na classe de erros e mandar o erro de
				// movimento inv�lido
			}
		} else {
			if ((casaFinal.getColuna() - casaInicial.getColuna()) == -2
					&& (casaFinal.getLinha() - casaInicial.getLinha() == 2)) { // Movimento
				// duplo
				// para
				// a
				// esquerda
				if (tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() + 1]
						.getPeca() != null
						&& tabuleiro[casaInicial.getLinha() + 1][casaFinal
								.getColuna() + 11].getPeca().getCor() == Pecas.BRANCA) {
					// chamar metodo comer -> passar as 3 casas
				} else {
					// entrar na classe de erros e mandar o erro de
					// movimento inv�lido
				}
			} else {
				// entrar na classe de erros e mandar o erro de
				// movimento inv�lido
			}
		}
	}

	public void verificaDama(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal) {
		int x; // salva a direcao horizontal do movimento
		int y; // salva a direcao vertical do movimento
		if (Math.abs(casaFinal.getLinha() - casaInicial.getLinha()) == Math
				.abs(casaFinal.getColuna() - casaInicial.getColuna())) {
			// caso o movimento tenha sido dentro de uma diagonal permitida
			x = casaFinal.getColuna() - casaInicial.getColuna();
			y = casaFinal.getLinha() - casaInicial.getLinha();
			if (x > 0 && y > 0) // movimento para baixo direita
			{
				x = 1;
				y = 1;
			}
			if (x > 0 && y < 0) // movimento para baixo esquerda
			{
				x = 1;
				y = -1;
			}
			if (x < 0 && y > 0) // movimento para cima direita
			{
				x = -1;
				y = 1;
			}
			if (x < 0 && y < 0) // movimento para cima esquerda
			{
				x = -1;
				y = -1;
			}
			if (casaInicial.getPeca().getCor() == Pecas.BRANCA) { // Se a dama
				// for
				// Branca
				moverDamaBranca(tabuleiro, casaInicial, casaFinal, x, y);
			} else { // Se a dama for preta
				moverDamaBranca(tabuleiro, casaInicial, casaFinal, x, y);
			}
		} else {
			// chamar classe de erros: Movimento invalido
		}
	}

	public void moverDamaBranca(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal, int x, int y) {
		int l = casaInicial.getLinha();
		int c = casaInicial.getColuna();
		int z = 1; // incrementa para varrer toda a diagonal escolhida
		int adversaria = 0; // conta as pecas adversarias no caminho
		while (adversaria < 2 && (l + y * z) != (casaFinal.getLinha())) { 
			// enquanto a linha analisada (comeca uma casa na frente da inicial)
			//nao for igual a final e houver menos de 2 adversarios no caminho
			if (tabuleiro[l + y * z][c + x * z].getPeca() != null) // Se houver
																	// peca
			{
				if (tabuleiro[l + y * z][c + x * z].getPeca().getCor() == Pecas.BRANCA) {
					// Se ha peca branca na casa analisada
					// chamar erro "nao pode pular a propria peca"
				} else { // se ha peca preta, incrementar contador de peca
							// adversaria
					adversaria++;
				}
			} else {
				// se nao houver peca
			}
		}
		if(adversaria==0)
		{
			//chamar metodo mover
		}else if(adversaria==1)
		{
			//chamar metodo comer
		}else if(adversaria==2)
		{
			//chamar msg de movimento invalido
		}

	}

	public void moverDamaPreta(Casa[][] tabuleiro, Casa casaInicial,
			Casa casaFinal, int x, int y) {
	}

}