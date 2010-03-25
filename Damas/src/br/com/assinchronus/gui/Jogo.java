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
import javax.swing.JOptionPane;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Tabuleiro;

/**
 * 
 * @author Matheus
 */
public class Jogo extends JFrame implements ActionListener{

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton[][] buttons = new JButton[8][8];
	
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	
	Map<JButton, Casa> mapaTabuleiro = new HashMap<JButton, Casa>();
	
	Tabuleiro tabuleiro = new Tabuleiro();
	
	/** Creates new form Tabuleiro */
	public Jogo() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {
    	Color white = new Color(255, 255, 255);
    	Color black = new Color(102, 102, 102);
    	int coluna = 10;
    	int linha = 20;
    	
    	for(int i = 0; i < buttons.length; i++){
    		for (int j = 0; j < buttons.length; j++){
    			buttons[i][j] = new JButton();
    			if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)){
    				buttons[i][j].setBackground(black);
    			}else{
    				buttons[i][j].setBackground(white);
    				buttons[i][j].setEnabled(false);
    			}
    			
    			getContentPane().add(buttons[i][j]);
		        buttons[i][j].setText(i+" "+j);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Jogo().setVisible(true);
			}
		});
	}
	
	int x, y;
	int x1, y1;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Casa casa = mapaTabuleiro.get(e.getSource());
		JOptionPane.showMessageDialog(null, casa.getPeca().getCor());
		
	}
}
