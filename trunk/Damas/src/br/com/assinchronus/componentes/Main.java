package br.com.assinchronus.componentes;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args){
		Tabuleiro tab = new Tabuleiro();
		
		tab.printTabuleiro();
		
		int x1 = Integer.parseInt(JOptionPane.showInputDialog("Origem x:"));
		int y1 = Integer.parseInt(JOptionPane.showInputDialog("Origem y:"));
		
		int x2 = Integer.parseInt(JOptionPane.showInputDialog("Destino x:"));
		int y2 = Integer.parseInt(JOptionPane.showInputDialog("Destino y:"));
		
		Casa casaAtual = tab.getTabuleiro()[x1][y1];
		Casa casaProxima = tab.getTabuleiro()[x2][y2];
		
		casaAtual.getPeca().mover(casaAtual, casaProxima);
		
		System.out.println("\n");
		
		tab.printTabuleiro();
	}
}
