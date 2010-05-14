package br.com.assinchronus.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.com.assinchronus.componentes.Casa;

public class Utility {

	/**
	 * Clona o Tabuleiro de Casas
	 * 
	 * @param orig
	 *            Tabuleiro original
	 * @return Retorna o Tabuleiro clonado
	 */
	public static Casa[][] clone(Object orig) {
		Object obj = null;
		try {
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(orig);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			obj = in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return (Casa[][]) obj;
	}
	
	/**
	 * Retorna o objeto Casa correto para o tabuleiro informado.
	 * 
	 * @param tabuleiro Tabuleiro novo
	 * @param casa Casa
	 * @return Retorna a casa na posição do Tabuleiro passado
	 */
	public Casa getCasaCerta(Casa[][] tabuleiro, Casa casa) {
		Casa casaNova = null;
		casaNova = tabuleiro[casa.getLinha()][casa.getColuna()];
		
		return casaNova;
	}
}
