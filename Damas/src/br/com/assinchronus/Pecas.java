package br.com.assinchronus;

public class Pecas {
	
	public static final int BRANCA = 1;
	
	public static final int PRETA = 2;
	
	//Cor da pe�a
	private int cor = 0;
	
	//Casa da pe�a
	private int casa = 0;
	
	//Tipo da pe�a
	private String tipo = null;
	
	public Pecas() {
		
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

	public int getCasa() {
		return casa;
	}

	public void setCasa(int casa) {
		this.casa = casa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
