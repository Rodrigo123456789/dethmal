package br.com.assinchronus.negocio;
//erro svn
import java.util.ArrayList;
import java.util.List;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.exception.JogadaInvalida;

/**
 * 
 * @author Vinicius
 * 
 */

public class RegraPeao {

	/**
	 * 
	 * @param obrigatoria
	 *            Jogada Obrigatoria
	 * @param tabuleiro
	 *            Tabuleiro do Jogo
	 * @param casaInicial
	 *            Casa Inicial
	 * @param casaFinal
	 *            Casa Final
	 * @throws JogadaInvalida
	 *             Caso uma jogada invalida ocorra
	 */
	public List<Casa[]> verificaJogadaPBranco(List<Casa[]> obrigatoria, Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) throws JogadaInvalida {
		List<Casa[]> sequencia = new ArrayList<Casa[]>();
		if (Math.abs(casaFinal.getColuna() - casaInicial.getColuna()) == 1 && (casaFinal.getLinha() - casaInicial.getLinha() == -1)) {
			if (!obrigatoria.isEmpty()) {
				throw new JogadaInvalida("Voce deve capturar uma peca");
			} else {
				casaInicial.getPeca().mover(casaInicial, casaFinal);
				sequencia = null;
			}
		} else if ((casaFinal.getColuna() - casaInicial.getColuna()) == 2 && (casaFinal.getLinha() - casaInicial.getLinha() == -2)) {
			Casa adversaria = tabuleiro[casaInicial.getLinha() - 1][casaFinal.getColuna() - 1];
			// Movimento duplo para a direita
			if (adversaria.getPeca() != null && adversaria.getPeca().getCor() == Pecas.PRETA) {
				casaInicial.getPeca().comer(casaInicial, adversaria, casaFinal);
				sequencia = verificaSequenciaPeao(tabuleiro, casaFinal);
			} else {
				throw new JogadaInvalida("Jogada invalida");
			}
		} else if ((casaFinal.getColuna() - casaInicial.getColuna()) == -2 && (casaFinal.getLinha() - casaInicial.getLinha() == -2)) {
			// Movimento duplo para a esquerda
			Casa adversaria = tabuleiro[casaInicial.getLinha() - 1][casaFinal.getColuna() + 1];
			if (adversaria.getPeca() != null && adversaria.getPeca().getCor() == Pecas.PRETA) {
				casaInicial.getPeca().comer(casaInicial, adversaria, casaFinal);
				sequencia = verificaSequenciaPeao(tabuleiro, casaFinal);
			} else {
				throw new JogadaInvalida("Jogada invalida");
			}
		} else {
			throw new JogadaInvalida("Jogada invalida");
		}
		return sequencia;

	}

	/**
	 * 
	 * @param obrigatoria
	 *            Jogada Obrigatoria
	 * @param tabuleiro
	 *            Tabuleiro do Jogo
	 * @param casaInicial
	 *            Casa Inicial
	 * @param casaFinal
	 *            Casa Final
	 * @throws JogadaInvalida
	 *             Caso uma jogada invalida ocorra
	 */
	public List<Casa[]> verificaJogadaPPreto(List<Casa[]> obrigatoria, Casa[][] tabuleiro, Casa casaInicial, Casa casaFinal) throws JogadaInvalida {
		List<Casa[]> sequencia = new ArrayList<Casa[]>();
		if (Math.abs(casaFinal.getColuna() - casaInicial.getColuna()) == 1 && (casaFinal.getLinha() - casaInicial.getLinha() == 1)) {
			if (!obrigatoria.isEmpty()) {
				throw new JogadaInvalida("Voce deve capturar uma peca");
			} else {
				casaInicial.getPeca().mover(casaInicial, casaFinal);
				sequencia = null;
			}
		} else if ((casaFinal.getColuna() - casaInicial.getColuna()) == 2 && (casaFinal.getLinha() - casaInicial.getLinha() == 2)) {
			// Movimento duplo para a direita
			Casa adversaria = tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() - 1];
			if (adversaria.getPeca() != null && adversaria.getPeca().getCor() == Pecas.BRANCA) {
				casaInicial.getPeca().comer(casaInicial, adversaria, casaFinal);
				sequencia = verificaSequenciaPeao(tabuleiro, casaFinal);
			} else {
				throw new JogadaInvalida("Jogada inv‡lida");
			}
		} else if ((casaFinal.getColuna() - casaInicial.getColuna()) == -2 && (casaFinal.getLinha() - casaInicial.getLinha() == 2)) {
			// Movimento duplo para a esquerda
			Casa adversaria = tabuleiro[casaInicial.getLinha() + 1][casaFinal.getColuna() + 1];
			if (adversaria.getPeca() != null && adversaria.getPeca().getCor() == Pecas.BRANCA) {
				casaInicial.getPeca().comer(casaInicial, adversaria, casaFinal);
				sequencia = verificaSequenciaPeao(tabuleiro, casaFinal);
			} else {
				throw new JogadaInvalida("Jogada invalida");
			}
		} else {
			throw new JogadaInvalida("Jogada invalida");
		}
		return sequencia;
	}

	public List<Casa[]> verificaSequenciaPeao(Casa[][] tabuleiro, Casa casaInicial) {

		Casa[] acao = new Casa[2];
		List<Casa[]> jogadaspossiveis = new ArrayList<Casa[]>();

		if (casaInicial.getLinha() + 2 < 8 && casaInicial.getColuna() + 2 < 8) {
			// se pode comer diagonal inferior direita
			if (tabuleiro[casaInicial.getLinha() + 1][casaInicial.getColuna() + 1].getPeca() != null
					&& tabuleiro[casaInicial.getLinha() + 1][casaInicial.getColuna() + 1].getPeca().getCor() != casaInicial.getPeca().getCor()
					&& tabuleiro[casaInicial.getLinha() + 2][casaInicial.getColuna() + 2].getPeca() == null) {
				acao[0] = casaInicial;
				acao[1] = tabuleiro[casaInicial.getLinha() + 2][casaInicial.getColuna() + 2];
				jogadaspossiveis.add(acao);
			}
		}
		if (casaInicial.getLinha() + 2 < 8 && casaInicial.getColuna() - 2 > -1) {
			// se pode comer diagonal inferior esquerda
			if (tabuleiro[casaInicial.getLinha() + 1][casaInicial.getColuna() - 1].getPeca() != null
					&& tabuleiro[casaInicial.getLinha() + 1][casaInicial.getColuna() - 1].getPeca().getCor() != casaInicial.getPeca().getCor()
					&& tabuleiro[casaInicial.getLinha() + 2][casaInicial.getColuna() - 2].getPeca() == null) {
				acao[0] = casaInicial;
				acao[1] = tabuleiro[casaInicial.getLinha() + 2][casaInicial.getColuna() - 2];
				jogadaspossiveis.add(acao);
			}
		}
		if (casaInicial.getLinha() - 2 > -1 && casaInicial.getColuna() + 2 < 8) {
			// se pode comer diagonal superior direita
			if (tabuleiro[casaInicial.getLinha() - 1][casaInicial.getColuna() + 1].getPeca() != null
					&& tabuleiro[casaInicial.getLinha() - 1][casaInicial.getColuna() + 1].getPeca().getCor() != casaInicial.getPeca().getCor()
					&& tabuleiro[casaInicial.getLinha() - 2][casaInicial.getColuna() + 2].getPeca() == null) {
				acao[0] = casaInicial;
				acao[1] = tabuleiro[casaInicial.getLinha() - 2][casaInicial.getColuna() + 2];
				jogadaspossiveis.add(acao);
			}
		}
		if (casaInicial.getLinha() - 2 > -1 && casaInicial.getColuna() - 2 > -1) {
			// se pode comer diagonal superior esquerda
			if (tabuleiro[casaInicial.getLinha() - 1][casaInicial.getColuna() - 1].getPeca() != null
					&& tabuleiro[casaInicial.getLinha() - 1][casaInicial.getColuna() - 1].getPeca().getCor() != casaInicial.getPeca().getCor()
					&& tabuleiro[casaInicial.getLinha() - 2][casaInicial.getColuna() - 2].getPeca() == null) {
				acao[0] = casaInicial;
				acao[1] = tabuleiro[casaInicial.getLinha() - 2][casaInicial.getColuna() - 2];
				jogadaspossiveis.add(acao);
			}
		}
		return jogadaspossiveis;
	}

}
