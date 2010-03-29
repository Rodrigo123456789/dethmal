package br.com.assinchronus.componentes;

/**
 * 
 * @author Pilon
 * @version 1.0.0
 */

public class Casa {

	private int linha = 0;

	private int coluna = 0;

	private Pecas peca = null;

	public Casa() {
		// TODO Auto-generated constructor stub
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public Pecas getPeca() {
		return peca;
	}

	public void setPeca(Pecas peca) {
		this.peca = peca;
	}

	@Override
	public String toString() {
		return String.valueOf(getLinha()).concat(",").concat(String.valueOf(getColuna()));
	}

}
