package br.com.assinchronus;

public class Casa {

	private int numero = 0;

	private Pecas peca = null;

	public Casa() {
		// TODO Auto-generated constructor stub
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Pecas getPeca() {
		return peca;
	}

	public void setPeca(Pecas peca) {
		this.peca = peca;
	}

	@Override
	public String toString() {
		return String.valueOf(getNumero());
	}
}
