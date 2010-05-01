/*
 * Tabuleiro.java
 *
 * Created on 22/03/2010, 19:58:42
 */
package br.com.assinchronus.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Tabuleiro;
import br.com.assinchronus.exception.JogadaInvalida;
import br.com.assinchronus.negocio.RegraFinal;
import br.com.assinchronus.negocio.RegraGeral;

public class Jogo extends JFrame implements ActionListener {

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenuBar jMenuBar1;

	ImageIcon iconWhite = new ImageIcon(getClass().getResource("/images/peca.png"));
	ImageIcon iconBlack = new ImageIcon(getClass().getResource("/images/pecapreta.png"));
	ImageIcon iconKingWhite = new ImageIcon(getClass().getResource("/images/dama.png"));
	ImageIcon iconKingBlack = new ImageIcon(getClass().getResource("/images/damapreta.png"));

	private JButton[][] buttons = new JButton[8][8];
	Map<JButton, Casa> mapaTabuleiro = new HashMap<JButton, Casa>();
	Tabuleiro tabuleiro = new Tabuleiro();

	private RegraFinal rf = new RegraFinal();
	
	private int tabelaPeso[][] = {{4,0,4,0,4,0,4,0},{0,3,0,3,0,3,0,4},{4,0,2,0,2,0,3,0},{0,3,0,1,0,2,0,4},{4,0,2,0,1,0,3,0},{0,3,0,2,0,2,0,4},{4,0,3,0,3,0,3,0},{0,4,0,4,0,4,0,4}};
	
	Casa casaInicial;
	Casa casaFinal;
	int jogada = 1;
	boolean passavez;

	javax.swing.JScrollPane panel = new JScrollPane();
	javax.swing.JList jogadas = new JList();

	static javax.swing.DefaultListModel model = new javax.swing.DefaultListModel();

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

		RegraFinal.setQtdPeaoBranco(12);
		RegraFinal.setQtdPeaoPreto(12);
		RegraFinal.setQtdDamaBranco(0);
		RegraFinal.setQtdDamaPreto(0);

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
				buttons[i][j].setBounds(coluna, linha, 89, 89);
				//buttons[i][j].addActionListener(this);
			//	buttons[i][j].addActionListener(this);
				buttons[i][j].addActionListener( this);
				mapaTabuleiro.put(buttons[i][j], tabuleiro.getTabuleiro()[i][j]);

				coluna += 90;
			}

			linha += 90;
			coluna = 10;
		}
		// jogadas.setSize(100, 100);

		jogadas.setModel(model);

		panel.setViewportView(jogadas);
		panel.setBounds(740, 10, 275, 300);
		getContentPane().add(panel);

		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(1024, 850));
		setResizable(false);
		getContentPane().setLayout(null);

		jMenu1.setText("Novo Jogo");
		jMenuBar1.add(jMenu1);

    	jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				menuActionPerformed(evt);
			}
		});
		setJMenuBar(jMenuBar1);

		pack();
	}

	public void menuActionPerformed(MouseEvent e) {
	/*	tabuleiro = new Tabuleiro();
		casaFinal = null;
		casaInicial = null;
		jogada = 1;
		model.clear();
		jogadas.setModel(model);
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				mapaTabuleiro.put(buttons[i][j], tabuleiro.getTabuleiro()[i][j]);
			}
		}
		RegraFinal.setQtdPeaoBranco(12);
		RegraFinal.setQtdPeaoPreto(12);
		RegraFinal.setQtdDamaBranco(0);
		RegraFinal.setQtdDamaPreto(0);
		RegraGeral.setSequencia(false);
		atualizaTabuleiro();*/
	}

	public void actionPerformed(ActionEvent e) {

		Jogo.setMSG("Quem joga: " + jogada + ", tem sequencia: " + RegraGeral.getSequencia());
		if (casaInicial == null) {
			if (mapaTabuleiro.get(e.getSource()).getPeca() != null) {
				if (jogada == mapaTabuleiro.get(e.getSource()).getPeca().getCor()) {
					casaInicial = mapaTabuleiro.get(e.getSource());
				} else {
					model.add(model.getSize(), "Esta não é sua peça");
				}

			} else
				model.add(model.getSize(), "Casa vazia selecione outra");
		} else {
			if (mapaTabuleiro.get(e.getSource()).getPeca() == null) {
				casaFinal = mapaTabuleiro.get(e.getSource());
				try {
					passavez = true;
					RegraGeral.validarPeca(jogada, tabuleiro.getTabuleiro(), casaInicial, casaFinal);
					atualizaTabuleiro();

					if (RegraGeral.getSequencia()) {
						casaInicial = casaFinal;
						casaFinal = null;
						RegraGeral.setForcacaptura(true);
						return;
					}
				} catch (JogadaInvalida e1) {
					passavez = false;
					setMSG(e1.getMessage());
				} finally {
					casaInicial = null;
					casaFinal = null;
					if (jogada == 1 && RegraGeral.getSequencia() == false && passavez) {
						jogada = 2;
					} else if (jogada == 2 && RegraGeral.getSequencia() == false && passavez) {
						jogada = 1;
					}
				}
			} else {
				if (mapaTabuleiro.get(e.getSource()).getPeca().getCor() == jogada && RegraGeral.getSequencia() == false)
					casaInicial = mapaTabuleiro.get(e.getSource());
				else if (mapaTabuleiro.get(e.getSource()).getPeca().getCor() != jogada && RegraGeral.getSequencia() == false)
					casaInicial = null;
			}

			// Analise do fim do jogo
			int fim = rf.analisaFinal();
			model.add(model.getSize(), "Retorno do analisaFinal: " + fim + " // Jogadas para empate: " + rf.getJogadasempate() + " // Passa vez: " + passavez);
			switch (fim) {
			case 0: {
				if (rf.getJogadasempate() == 0 && passavez) {
					model.add(model.getSize(), "Empate"); // vai na GUI
					jogada = 0;
				}
				break;
			}
			case 1: {
				model.add(model.getSize(), "Branca ganhou"); // vai na
				// GUI
				jogada = 0;
				break;
			}
			case -1: {
				model.add(model.getSize(), "Preta ganhou"); // vai na
				// GUI
				jogada = 0;
				break;
			}
			}

			// Analise da imobilizacao
			if (jogada == 1) {
				if (!rf.verificaPecasBranca(tabuleiro.getTabuleiro())) {
					model.add(model.getSize(), "Preta ganhou por imobilizacao"); // vai
					// na
					// GUI
					jogada = 0;
				}
			} else if (jogada == 2) {
				if (!rf.verificaPecasPreta(tabuleiro.getTabuleiro())) {
					model.add(model.getSize(), "Branca ganhou por imobilizacao"); // vai
					// na
					// GUI
					jogada = 0;
				}
			}
		}
		jogadas.setModel(model);
		jogadas.setSelectedIndex(model.getSize() - 1);

	}

	/**
	 * imprimi tabuleiro na tela
	 */
	public void atualizaTabuleiro() {

		Casa[][] tab = tabuleiro.getTabuleiro();

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					if (tab[i][j].getPeca() != null && !tab[i][j].getPeca().isCapturada()) {
						if (tab[i][j].getPeca().getCor() == 1) {
							if (tab[i][j].getPeca() instanceof Peao) {
								if (i == 0) {
									tab[i][j].setPeca(new Dama());
									tab[i][j].getPeca().setCor(1);
									buttons[i][j].setIcon(iconKingWhite);
									RegraFinal.setQtdDamaBranco(rf.getQtdDamaBranco() + 1);
									RegraFinal.setQtdPeaoBranco(rf.getQtdPeaoBranco() - 1);
								} else
									buttons[i][j].setIcon(iconWhite);
							} else {
								buttons[i][j].setIcon(iconKingWhite);
							}
						} else if (tab[i][j].getPeca().getCor() == 2) {
							if (tab[i][j].getPeca() instanceof Peao) {
								if (i == 7) {
									tab[i][j].setPeca(new Dama());
									tab[i][j].getPeca().setCor(2);
									buttons[i][j].setIcon(iconKingBlack);
									RegraFinal.setQtdDamaPreto(rf.getQtdDamaPreto() + 1);
									RegraFinal.setQtdPeaoPreto(rf.getQtdPeaoPreto() - 1);
								} else
									buttons[i][j].setIcon(iconBlack);
							} else {
								buttons[i][j].setIcon(iconKingBlack);
							}
						}
					} else {
						buttons[i][j].setIcon(null);
						tab[i][j].setPeca(null);
						
					}
				}
			}
		}
		setMSG("valor tabuleiro= " + String.valueOf( calcularValor()));
	}
	

	public static void setMSG(String msg) {
		model.add(model.getSize(), msg);
	}
	
	int calcularValor()
	{
		int pecaB=0;
		int pecaP=0;
		int vB=0;
		int vP=0;
		Casa[][] tab = tabuleiro.getTabuleiro();
		
		for (int i= 0; i<tab.length ; i++)
		{
			for(int j=0 ; j<tab.length; j++)
			{
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
				{
					if (tab[i][j].getPeca() != null)
					{
						if(tab[i][j].getPeca().getCor()==1)
						{
							if (tab[i][j].getPeca() instanceof Peao)
							{
								if(i==6)
									pecaB = 7;
								else
									pecaB = 5;
							}
							else
								pecaB=10;
							
							vB += pecaB*tabelaPeso[i][j];
						}
						else if(tab[i][j].getPeca().getCor()==2)
						{
							if (tab[i][j].getPeca() instanceof Peao)
							{
								if (i==1)
									pecaP =7;
								else
									pecaP =5;
							}
							else
								pecaP=10;
							
							vP += pecaP*tabelaPeso[i][j];
						}
						
					}
				}
			}
		}

		return vB - vP;
	}
	
}
