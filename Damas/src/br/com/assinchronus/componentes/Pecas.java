package br.com.assinchronus.componentes;

import java.io.Serializable;

/**
 * 
 * @author Pilon
 * @version 1.0.0
 */

public interface Pecas extends Serializable{

	public static final int BRANCA = 1;
	public static final int PRETA = 2;

	void mover(Casa atual, Casa proxima);

	void comer(Casa atual, Casa adversaria, Casa proxima);

	void setCor(int cor);

	int getCor();
	
	public boolean isCapturada();

	public void setCapturada(boolean capturada);
}
