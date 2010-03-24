package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Pecas;

public class RegraDama {
	
	public void verificaDiagonalDama(boolean obrigatoria, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal) {
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
			if (casaInicial.getPeca().getCor() == Pecas.BRANCA) {
				// Se a dama for Branca
				verificaJogadaDBranca(obrigatoria, tabuleiro, casaInicial,
						casaFinal, x, y);
			} else {
				// Se a dama for preta
				verificaJogadaDPreta(obrigatoria, tabuleiro, casaInicial,
						casaFinal, x, y);
			}
		} else {
			// chamar classe de erros: Movimento invalido
		}
	}

	public void verificaJogadaDBranca(boolean obrigatoria, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal, int x, int y) {
		int linha = casaInicial.getLinha();
		int coluna = casaInicial.getColuna();
		int z = 1; // incrementa para varrer toda a diagonal escolhida
		int adversaria = 0; // conta as pecas adversarias no caminho
		while (adversaria < 2 && (linha + y * z) != (casaFinal.getLinha())) {
			// enquanto a linha analisada (comeca uma casa na frente da inicial)
			// nao for igual a final e houver menos de 2 adversarios no caminho
			if (tabuleiro[linha + y * z][coluna + x * z].getPeca() != null)
			// Se houver peca
			{
				if (tabuleiro[linha + y * z][coluna + x * z].getPeca().getCor() == Pecas.BRANCA) {
					// Se ha peca branca na casa analisada
					// chamar erro "nao pode pular a propria peca"
				} else { // se ha peca preta, incrementar contador de peca
					// adversaria
					adversaria++;
				}
			} else {
				// se nao houver peca
			}
			z++;
		}
		if (adversaria == 0) {
			if (obrigatoria) {
				// Mensagem de jogada invalida
			} else {
				// chamar metodo mover
			}
		} else if (adversaria == 1) {
			// chamar metodo comer
		} else if (adversaria == 2) {
			// chamar msg de movimento invalido
		}

	}

	public void verificaJogadaDPreta(boolean obrigatoria, Casa[][] tabuleiro,
			Casa casaInicial, Casa casaFinal, int x, int y) {
		int linha = casaInicial.getLinha();
		int coluna = casaInicial.getColuna();
		int z = 1; // incrementa para varrer toda a diagonal escolhida
		int adversaria = 0; // conta as pecas adversarias no caminho
		while (adversaria < 2 && (linha + y * z) != (casaFinal.getLinha())) {
			// enquanto a linha analisada (comeca uma casa na frente da inicial)
			// nao for igual a final e houver menos de 2 adversarios no caminho
			if (tabuleiro[linha + y * z][coluna + x * z].getPeca() != null) // Se
			// houver
			// peca
			{
				if (tabuleiro[linha + y * z][coluna + x * z].getPeca().getCor() == Pecas.PRETA) {
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
		if (adversaria == 0) {
			if (obrigatoria) {
				// Mensagem de jogada invalida
			} else {
				// chamar metodo mover
			}
		} else if (adversaria == 1) {
			// chamar metodo comer
		} else if (adversaria == 2) {
			// chamar msg de movimento invalido
		}
	}

	
}
