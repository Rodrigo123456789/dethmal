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

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

import br.com.assinchronus.ai.Arvore;
import br.com.assinchronus.ai.Inteligencia;
import br.com.assinchronus.componentes.Casa;
import br.com.assinchronus.componentes.Dama;
import br.com.assinchronus.componentes.Peao;
import br.com.assinchronus.componentes.Pecas;
import br.com.assinchronus.componentes.Tabuleiro;
import br.com.assinchronus.exception.JogadaInvalida;
import br.com.assinchronus.negocio.RegraFinal;
import br.com.assinchronus.negocio.RegraGeral;

public class Jogo extends JFrame implements ActionListener {

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	private JMenu menu;
	private JMenuBar menuBar;
	private JButton[][] buttons = new JButton[8][8];
	private JScrollPane panel = new JScrollPane();
	private JList jogadas = new JList();
	private static DefaultListModel model = new DefaultListModel();

	private ImageIcon iconWhite = new ImageIcon(getClass().getResource("/images/peca.png"));
	private ImageIcon iconBlack = new ImageIcon(getClass().getResource("/images/pecapreta.png"));
	private ImageIcon iconKingWhite = new ImageIcon(getClass().getResource("/images/dama.png"));
	private ImageIcon iconKingBlack = new ImageIcon(getClass().getResource("/images/damapreta.png"));

	private Map<JButton, Casa> mapaTabuleiro = new HashMap<JButton, Casa>();
	private Tabuleiro tabuleiro = new Tabuleiro();
	private Casa casaInicial = null;

	public static int jogada = Pecas.BRANCA;

	public static final int EMPATE = 0;
	public static final int VITORIA_BRANCA = 1;
	public static final int VITORIA_PRETA = -1;
	public static final int CONTINUA = 5;

	public static int peoesBrancos = 12;
	public static int peoesPretos = 12;
	public static int damasBrancas = 0;
	public static int damasPretas = 0;

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
				buttons[i][j].setBounds(coluna, linha, 89, 89);
				buttons[i][j].addActionListener(this);
				mapaTabuleiro.put(buttons[i][j], tabuleiro.getTabuleiro()[i][j]);

				coluna += 90;
			}

			linha += 90;
			coluna = 10;
		}

		jogadas.setModel(model);

		panel.setViewportView(jogadas);
		panel.setBounds(740, 10, 275, 300);
		getContentPane().add(panel);

		menuBar = new javax.swing.JMenuBar();
		menu = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(1024, 850));
		setResizable(false);
		getContentPane().setLayout(null);

		menu.setText("Novo Jogo");
		menuBar.add(menu);

		setJMenuBar(menuBar);

		pack();
	}

	public void actionPerformed(ActionEvent e) {
		boolean passavez = false;
		Casa casaFinal;

		if (casaInicial == null) {
			if (mapaTabuleiro.get(e.getSource()).getPeca() != null) {
				if (jogada == mapaTabuleiro.get(e.getSource()).getPeca().getCor()) {
					casaInicial = mapaTabuleiro.get(e.getSource());
				} else {
					model.add(model.getSize(), "Quem joga agora é o: " + jogada);
				}

			} else
				model.add(model.getSize(), "Casa vazia selecione outra");
		} else {
			if (mapaTabuleiro.get(e.getSource()).getPeca() == null) {
				casaFinal = mapaTabuleiro.get(e.getSource());
				model.clear();
				try {
					passavez = true;
					RegraGeral.validarPeca(jogada, tabuleiro.getTabuleiro(), casaInicial, casaFinal);
					atualizaTabuleiro(tabuleiro.getTabuleiro());

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
					if (jogada == Pecas.BRANCA && RegraGeral.getSequencia() == false && passavez) {
						jogada = Pecas.PRETA;

						Arvore arvore = new Arvore(tabuleiro.getTabuleiro());
						Inteligencia i = new Inteligencia(arvore);
						Casa[][] novoTabuleiro = i.jogar().getTabuleiro();
						merge(tabuleiro.getTabuleiro(), novoTabuleiro);
						atualizaTabuleiro(tabuleiro.getTabuleiro());
						jogada =
								Pecas.BRANCA;

					} else if (jogada == Pecas.PRETA && RegraGeral.getSequencia() == false && passavez) {
						jogada = Pecas.BRANCA;
					}
				}
			} else {
				if (mapaTabuleiro.get(e.getSource()).getPeca().getCor() == jogada && RegraGeral.getSequencia() == false)
					casaInicial = mapaTabuleiro.get(e.getSource());
				else if (mapaTabuleiro.get(e.getSource()).getPeca().getCor() != jogada
						&& RegraGeral.getSequencia() == false)
					casaInicial = null;
			}

			// Analise do fim do jogo
			if (passavez) {
				System.out.println("No Jogo         PB:" + peoesBrancos + "  DB: " + damasBrancas + "    =/=/=  PP: " + peoesPretos + "  DP: " + damasPretas);
				int fim = RegraFinal.analisaFinal();
				switch (fim) {
				case 0: {
					model.add(model.getSize(), "Empate");
					jogada = 0;
					break;
				}
				case VITORIA_BRANCA: {
					model.add(model.getSize(), "Branca ganhou");
					jogada = 0;
					break;
				}
				case VITORIA_PRETA: {
					model.add(model.getSize(), "Preta ganhou");
					jogada = 0;
					break;
				}
				}

				// Analise da imobilizacao
				if (jogada == Pecas.BRANCA) {
					if (!RegraFinal.verificaPecasBranca(tabuleiro.getTabuleiro())) {
						model.add(model.getSize(), "Preta ganhou por imobilizacao");
						jogada = 0;
					}
				} else if (jogada == Pecas.PRETA) {
					if (!RegraFinal.verificaPecasPreta(tabuleiro.getTabuleiro())) {
						model.add(model.getSize(), "Branca ganhou por imobilizacao");
						jogada = 0;
					}
				}
			}
		}
		jogadas.setModel(model);
		jogadas.setSelectedIndex(model.getSize() - 1);

	}

	/**
	 * imprimi tabuleiro na tela
	 */
	public void atualizaTabuleiro(Casa[][] tab) {

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
									damasBrancas++;
									peoesBrancos--;
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
									damasPretas++;
									peoesPretos--;
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
	}

	private void merge(Casa[][] tabAtual, Casa[][] tabNovo) {
		for (int i = 0; i < tabAtual.length; i++) {
			for (int j = 0; j < tabAtual.length; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
					if (tabAtual[i][j].getPeca() != null && tabNovo[i][j].getPeca() == null) {
						tabAtual[i][j].setPeca(null);
					} else if (tabAtual[i][j].getPeca() == null && tabNovo[i][j].getPeca() != null) {
						tabAtual[i][j].setPeca(tabNovo[i][j].getPeca());
					}
				}
			}
		}
	}

	public static void setMSG(String msg) {
		model.add(model.getSize(), msg);
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Jogo().setVisible(true);
			}
		});
	}
}
