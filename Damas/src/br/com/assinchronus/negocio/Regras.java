package br.com.assinchronus.negocio;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Pecas;

public class Regras {

	public void validar(Casa[][] tabuleiro, Casa CasaInicial, Casa CasaFinal) {

		if (CasaInicial.getPeca() != null){
			if (CasaInicial.getPeca().getCor() == Pecas.BRANCA){
				if ((CasaFinal.getColuna() - CasaInicial.getColuna() == 1 
					|| CasaFinal.getColuna() - CasaInicial.getColuna() == -1)
					&& (CasaFinal.getLinha() - CasaInicial.getLinha() == -1))
					if (CasaFinal.getPeca() != null) {
						// entrar na classe de erros e mandar o erro de já tem
						// peça aki!
					} else {
						// chamar metodo mover -> passar casa inicial e final
					}
				else if ((CasaFinal.getColuna() - CasaInicial.getColuna()) == 2
						&& (CasaFinal.getLinha() - CasaInicial.getLinha() == -2)) { //Movimento duplo para a direita
					if (tabuleiro[CasaInicial.getLinha() + 1][CasaFinal.getColuna() - 1].getPeca() != null
						&& tabuleiro[CasaInicial.getLinha() + 1][CasaFinal.getColuna() - 1].getPeca().getCor() == Pecas.PRETA) {
						// chamar metodo comer -> passar as 3 casas
					} else {
						// entrar na classe de erros e mandar o erro de
						// movimento inválido
					}
				} else {
					if ((CasaFinal.getColuna() - CasaInicial.getColuna()) == -2
							&& (CasaFinal.getLinha() - CasaInicial.getLinha() == -2)) { //Movimento duplo para a esquerda
						if (tabuleiro[CasaInicial.getLinha() + 1][CasaFinal.getColuna() + 1].getPeca() != null
							&& tabuleiro[CasaInicial.getLinha() + 1][CasaFinal.getColuna() + 11].getPeca().getCor() == Pecas.PRETA) {
							// chamar metodo comer -> passar as 3 casas
						} else {
							// entrar na classe de erros e mandar o erro de
							// movimento inválido
						}
					} else {
						// entrar na classe de erros e mandar o erro de
						// movimento inválido
					}
				}
			}
			else	//Peca branca
			{
				if ((CasaFinal.getColuna() - CasaInicial.getColuna() == 1 
					|| CasaFinal.getColuna() - CasaInicial.getColuna() == -1)
					&& (CasaFinal.getLinha() - CasaInicial.getLinha() == 1))
					if (CasaFinal.getPeca() != null) {
						// entrar na classe de erros e mandar o erro de já tem
						// peça aki!
					} else {
						// chamar metodo mover -> passar casa inicial e final
					}
				else if ((CasaFinal.getColuna() - CasaInicial.getColuna()) == 2
						&& (CasaFinal.getLinha() - CasaInicial.getLinha() == 2)) { //Movimento duplo para a direita
					if (tabuleiro[CasaInicial.getLinha() + 1][CasaFinal.getColuna() - 1].getPeca() != null
						&& tabuleiro[CasaInicial.getLinha() + 1][CasaFinal.getColuna() - 1].getPeca().getCor() == Pecas.BRANCA) {
						// chamar metodo comer -> passar as 3 casas
					} else {
						// entrar na classe de erros e mandar o erro de
						// movimento inválido
					}
				} else {
					if ((CasaFinal.getColuna() - CasaInicial.getColuna()) == -2
							&& (CasaFinal.getLinha() - CasaInicial.getLinha() == 2)) { //Movimento duplo para a esquerda
						if (tabuleiro[CasaInicial.getLinha() + 1][CasaFinal.getColuna() + 1].getPeca() != null
							&& tabuleiro[CasaInicial.getLinha() + 1][CasaFinal.getColuna() + 11].getPeca().getCor() == Pecas.BRANCA) {
							// chamar metodo comer -> passar as 3 casas
						} else {
							// entrar na classe de erros e mandar o erro de
							// movimento inválido
						}
					} else {
						// entrar na classe de erros e mandar o erro de
						// movimento inválido
					}
				}
			}
		}
		else	//Nao tem peca na casa que o cara clicou
		{
			
		}

	}
}
