package br.com.assinchronus.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;

public class RegraDama {

	public void verificaDiagonalDama(List<Casa[]> obrigatoria, Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) {
		int x; // salva a direcao horizontal do movimento
		int y; // salva a direcao vertical do movimento
		if (Math.abs(casaFinal.getLinha() - casaInicial.getLinha()) == Math.abs(casaFinal.getColuna() - casaInicial.getColuna())) {
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
			verificaJogadaDama(obrigatoria, tabuleiro, casaInicial, casaFinal, x, y);
		} else {
			// chamar classe de erros: Movimento invalido
		}
	}

	public void verificaJogadaDama(List<Casa[]> obrigatoria, Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal, int x, int y) {
		int linha = casaInicial.getLinha();
		int coluna = casaInicial.getColuna();
		int z = 1; // incrementa para varrer toda a diagonal escolhida
		int adversaria = 0; // conta as pecas adversarias no caminho
		while (adversaria < 2 && (linha + y * z) != (casaFinal.getLinha())) {
			// ATENCAO: ESSE WHILE PODE DAR ERRO SE O PLAYER NAO CLICAR EM
			// DIAGONAL!!
			// enquanto a linha analisada (comeca uma casa na frente da inicial)
			// nao for igual a final e houver menos de 2 adversarios no caminho
			if (tabuleiro[linha + y * z][coluna + x * z].getPeca() != null)
			// Se houver peca
			{
				if (tabuleiro[linha + y * z][coluna + x * z].getPeca().getCor() == casaInicial.getPeca().getCor()) {
					// Se ha peca branca na casa analisada
					// chamar erro "nao pode pular a propria peca"
				} else { // se ha peca preta, incrementar contador de peca
					// adversaria
					adversaria++;
				}
			}
			z++;
		}
		if (adversaria == 0) {
			if (!obrigatoria.isEmpty()) {
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

	public List<Casa[]> verificaSequenciaDama(Casa[][] tabuleiro, Casa casaInicial) {
		int z, l, c;
		Casa[] acao = new Casa[2];
		List<Casa[]> jogadaspossiveis = new ArrayList<Casa[]>();

		for (l = -1, c = -1, z = 1; z <= 6; z++) {
			// analisando diagonal superior esquerda
			if (casaInicial.getLinha() + l * (z + 1) > -1 && casaInicial.getColuna() + c * (z + 1) > -1) {
				if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca() != null) {
					if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() == casaInicial.getPeca().getCor()
							|| tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() != null) {
						break;
					}
					if ((tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() != casaInicial.getPeca().getCor())
							&& (tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() == null)) {
						acao[0] = tabuleiro[casaInicial.getLinha()][casaInicial.getColuna()];
						acao[1] = tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)];
						jogadaspossiveis.add(acao);
					}
				}
			}
		}
		for (l = -1, c = +1, z = 1; z <= 6; z++) {
			// analisando diagonal superior direita
			if (casaInicial.getLinha() + l * (z + 1) > -1 && casaInicial.getColuna() + c * (z + 1) < 8) {
				if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca() != null) {
					if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() == casaInicial.getPeca().getCor()
							|| tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() != null) {
						break;
					}
					if ((tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() != casaInicial.getPeca().getCor())
							&& (tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() == null)) {
						acao[0] = tabuleiro[casaInicial.getLinha()][casaInicial.getColuna()];
						acao[1] = tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)];
						jogadaspossiveis.add(acao);
					}
				}
			}
		}
		for (l = +1, c = -1, z = 1; z <= 6; z++) {
			// analisando diagonal inferior esquerda
			if (casaInicial.getLinha() + l * (z + 1) < 8 && casaInicial.getColuna() + c * (z + 1) > -1) {
				if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca() != null) {
					if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() == casaInicial.getPeca().getCor()
							|| tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() != null) {
						break;
					}
					if ((tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() != casaInicial.getPeca().getCor())
							&& (tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() == null)) {
						acao[0] = tabuleiro[casaInicial.getLinha()][casaInicial.getColuna()];
						acao[1] = tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)];
						jogadaspossiveis.add(acao);
					}
				}
			}
		}
		for (l = +1, c = +1, z = 1; z <= 6; z++) {
			// analisando diagonal inferior direita
			if (casaInicial.getLinha() + l * (z + 1) < 8 && casaInicial.getColuna() + c * (z + 1) < 8) {
				if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca() != null) {
					if (tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() == casaInicial.getPeca().getCor()
							|| tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() != null) {
						break;
					}
					if ((tabuleiro[casaInicial.getLinha() + l * z][casaInicial.getColuna() + c * z].getPeca().getCor() != casaInicial.getPeca().getCor())
							&& (tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)].getPeca() == null)) {
						acao[0] = tabuleiro[casaInicial.getLinha()][casaInicial.getColuna()];
						acao[1] = tabuleiro[casaInicial.getLinha() + l * (z + 1)][casaInicial.getColuna() + c * (z + 1)];
						jogadaspossiveis.add(acao);
					}
				}
			}
		}
		return jogadaspossiveis;
	}
}
