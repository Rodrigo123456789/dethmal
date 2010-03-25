/*
 * Tabuleiro.java
 *
 * Created on 22/03/2010, 19:58:42
 */
package br.com.assinchronus.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.componentes.Tabuleiro;
import br.com.assinchronus.negocio.RegraGeral;

public class Jogo extends JFrame implements ActionListener {

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;

	private JButton[][] buttons = new JButton[8][8];
	Map<JButton, Casa> mapaTabuleiro = new HashMap<JButton, Casa>();
	Tabuleiro tabuleiro = new Tabuleiro();

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Jogo().setVisible(true);
			}
		});
	}

	/** Creates new form Tabuleiro */
	public Jogo() {
		initComponents();
	}

	private void initComponents() {
		Color white = new Color(255, 255, 255);
		Color black = new Color(102, 102, 102);
		int coluna = 10;
		int linha = 20;

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				buttons[i][j] = new JButton();
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					buttons[i][j].setBackground(black);
				} else {
					buttons[i][j].setBackground(white);
					buttons[i][j].setEnabled(false);
				}

				getContentPane().add(buttons[i][j]);
				//buttons[i][j].setText(i + " " + j);
				buttons[i][j].setBounds(coluna, linha, 89, 89);
				buttons[i][j].addActionListener(this);

				mapaTabuleiro.put(buttons[i][j], tabuleiro.getTabuleiro()[i][j]);

				coluna += 90;
			}

			linha += 90;
			coluna = 10;
		}

		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(1024, 850));
		setResizable(false);
		getContentPane().setLayout(null);

		jMenu1.setText("File");
		jMenuBar1.add(jMenu1);

		jMenu2.setText("Edit");
		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);
		
		mostrarPecas();

		pack();
	}

	private void mostrarPecas(){
		for(int i = 0; i < tabuleiro.getTabuleiro().length; i++){
    		for (int j = 0; j < tabuleiro.getTabuleiro()[i].length; j++){
    			int cor = 0;
    			if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
    				if(tabuleiro.getTabuleiro()[i][j]!=null && tabuleiro.getTabuleiro()[i][j].getPeca()!=null){
    					cor = tabuleiro.getTabuleiro()[i][j].getPeca().getCor();
    				}
    					
    					buttons[i][j].setText(i + "," + j +"   -   "+ cor);
    				
    			}
    		}
		}
	}
    		
    		
    		
	@Override
	public void actionPerformed(ActionEvent e) {
		if (casaInicial == null) {
			casaInicial = mapaTabuleiro.get(e.getSource());
		} else {
			casaFinal = mapaTabuleiro.get(e.getSource());
			Casa modificada = RegraGeral.validarPeca(false, Pecas.BRANCA,
					tabuleiro.getTabuleiro(), casaInicial, casaFinal);
			buttons[modificada.getLinha()][modificada.getColuna()].setText(modificada.getLinha() + "," + modificada.getColuna() +"   -   "+ 0);
			casaInicial = null;
			casaFinal = null;

		}
	}
	
	Casa casaInicial;
	Casa casaFinal;
	boolean sequencia = false;
	boolean obrigatoria = false;
	int jogada = 1;
	Casa[][] tab = tabuleiro.getTabuleiro();
	
/*
	public void actionPerformed(ActionEvent e, Casa casaInicial, Casa casaFinal, boolean sequencia) {
		while(casaInicial==null | casaFinal==null){
			if(casaInicial==null){
				casaInicial=mapaTabuleiro.get(e.getSource());
			}else{
				if(mapaTabuleiro.get(e.getSource())!=casaInicial){
					casaFinal=mapaTabuleiro.get(e.getSource());
				}else{
					if(sequencia){
						casaInicial=mapaTabuleiro.get(e.getSource());
					}else{
						casaInicial=null;
					}
				}
			}
		}
		RegraGeral.validarPeca(sequencia, jogada, tab, casaInicial, casaFinal);
	}*/
}
