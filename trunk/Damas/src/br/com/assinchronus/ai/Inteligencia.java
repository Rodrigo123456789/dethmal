package br.com.assinchronus.ai;

import br.com.assinchronus.componentes.Tabuleiro;

public class Inteligencia {
	
	private static final int NIVEL = 2;
	
	Arvore arvore;
	
	RegraAI regraAI; 
	
	public Inteligencia(Arvore arvore) {
		regraAI = new RegraAI(NIVEL);
		this.arvore = arvore;
	}
	
	public void jogar() {
		regraAI.verificaCasas(arvore, NIVEL);
		
		for(Arvore arvore : this.arvore.getArvores()){
			miniMax(arvore);
		}
	}
	
	private Arvore miniMax(Arvore root) {
		return null;
	}
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		
		Arvore arvore = new Arvore(new Tabuleiro().getTabuleiro());
		Inteligencia inteligencia = new Inteligencia(arvore);
		inteligencia.jogar();
		
		time = System.currentTimeMillis() - time;
		
		System.out.println(time);
	}
}
