package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicial {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem mntmHome;
	private JMenuItem mntmPesquisa;
	private JMenuItem mntmCriarPesquisa;
	private JMenu mnConfig;
	private JMenuItem mntmSair;
	private view.TelaCadastro telaCadastro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setVisible(false);

		mntmHome = new JMenuItem("Home");
		menuBar.add(mntmHome);

		mntmPesquisa = new JMenuItem("Pesquisa");
		menuBar.add(mntmPesquisa);

		mntmCriarPesquisa = new JMenuItem("Criar pergunta");
		menuBar.add(mntmCriarPesquisa);

		mnConfig = new JMenu("Config");
		menuBar.add(mnConfig);

		mntmSair = new JMenuItem("Sair");
		mnConfig.add(mntmSair);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(251, 155, 89, 23);
		frame.getContentPane().add(btnLogin);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
				frame.setContentPane(telaCadastro);
				frame.revalidate();
				
				telaCadastro.getBtnVoltar().addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
					
						
					}
				});

			}
		});
		btnCadastrar.setBounds(251, 209, 89, 23);
		frame.getContentPane().add(btnCadastrar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnSair.setBounds(251, 268, 89, 23);
		frame.getContentPane().add(btnSair);

	}
}
