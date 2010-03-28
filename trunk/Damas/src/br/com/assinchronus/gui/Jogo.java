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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.componentes.Tabuleiro;
import br.com.assinchronus.exception.JogadaInvalida;
import br.com.assinchronus.negocio.RegraGeral;
import br.com.assinchronus.negocio.RegraFinal;

public class Jogo extends JFrame implements ActionListener {

	/**
	 * SerialVersion
	 */
	//erro svn
	private static final long serialVersionUID = 1L;

	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;

	private JButton[][] buttons = new JButton[8][8];
	Map<JButton, Casa> mapaTabuleiro = new HashMap<JButton, Casa>();
	Tabuleiro tabuleiro = new Tabuleiro();

	Casa casaInicial;
	Casa casaFinal;
	int jogada = 1;

	ImageIcon iconWhite = new ImageIcon(getClass().getResource("/images/peca.png"));
	ImageIcon iconBlack = new ImageIcon(getClass().getResource("/images/pecapreta.png"));
	ImageIcon iconKingWhite = new ImageIcon(getClass().getResource("/images/damapreta.png"));
	ImageIcon iconKingBlack = new ImageIcon(getClass().getResource("/images/dama.png"));

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

					if (i >= 0 && i <= 2) {
						buttons[i][j].setIcon(iconBlack);
					} else if (i >= 5 && i <= 7) {
						buttons[i][j].setIcon(iconWhite);
					}
				} else {
					buttons[i][j].setBackground(white);
					buttons[i][j].setEnabled(false);
				}

				getContentPane().add(buttons[i][j]);
				// buttons[i][j].setText(i + " " + j);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (casaInicial == null) {
			if (mapaTabuleiro.get(e.getSource()).getPeca() != null) {
				if (jogada == mapaTabuleiro.get(e.getSource()).getPeca().getCor())
				{
					casaInicial = mapaTabuleiro.get(e.getSource());
				} else {
					System.out.println("Esta não é sua peça");
				}

			} else
				System.out.println("Casa vazia selecione outra");
		} else {
			if (mapaTabuleiro.get(e.getSource()).getPeca() == null)
			{
				casaFinal = mapaTabuleiro.get(e.getSource());
				try {
					RegraGeral.validarPeca(Pecas.BRANCA, tabuleiro.getTabuleiro(),
							casaInicial, casaFinal);
	
					atualizaTabuleiro();
					if (RegraGeral.getSequencia()) {
						casaInicial = casaFinal;
						casaFinal = null;
						return;
					}
				} catch (JogadaInvalida e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					casaInicial = null;
					casaFinal = null;
					if (jogada == 1)
						jogada = 2;
					else
						jogada = 1;
				}
			}
			else
			{
				casaInicial = mapaTabuleiro.get(e.getSource());
			}
		}
	}

	public void atualizaTabuleiro() {

		Casa[][] tab = tabuleiro.getTabuleiro();

		for (int i = 0; i < tab.length; i++) {

			for (int j = 0; j < tab.length; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {

					if (tab[i][j].getPeca() != null) {

						if (tab[i][j].getPeca().getCor() == 1) {
							if (tab[i][j].getPeca() instanceof Peao) {
								buttons[i][j].setIcon(iconWhite);
							} else {
								buttons[i][j].setIcon(iconKingWhite);
							}
						} else if (tab[i][j].getPeca().getCor() == 2) {
							if (tab[i][j].getPeca() instanceof Peao) {
								buttons[i][j].setIcon(iconBlack);
							} else {
								buttons[i][j].setIcon(iconKingBlack);
							}
						}
					} else {
						buttons[i][j].setIcon(null);
					}
				}
			}
		}
	}
}
