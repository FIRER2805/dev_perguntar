package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TelaInicial {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem mntmHome;
	private JMenuItem mntmPesquisa;
	private JMenuItem mntmCriarPesquisa;
	private JMenu mnConfig;
	private JMenuItem mntmSair;
	private view.TelaCadastro telaCadastro;
	private view.TelaLogin telaLogin;
	private JTextField textField;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmLogin;
	private TelaCriarPergunta telaCriacaoDePergunta;
	private TelaPerfil telaPerfil;
	private TelaPesquisa telaPesquisa;

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
		
		textField = new JTextField();
		textField.setBounds(150, 235, 295, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(251, 277, 89, 23);
		frame.getContentPane().add(btnPesquisar);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setVisible(true);

		mntmHome = new JMenuItem("Home");
		menuBar.add(mntmHome);

		mntmPesquisa = new JMenuItem("Pesquisa");
		mntmPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				telaPesquisa = new TelaPesquisa();
				telaPesquisa.setVisible(true);
				frame.setContentPane(telaPesquisa);
				frame.revalidate();
				
			}
		});
		menuBar.add(mntmPesquisa);

		mntmCriarPesquisa = new JMenuItem("Criar pergunta");
		mntmCriarPesquisa.setVisible(true);
		mntmCriarPesquisa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				telaCriacaoDePergunta = new TelaCriarPergunta();
				telaCriacaoDePergunta.setVisible(true);
				frame.setContentPane(telaCriacaoDePergunta);
				frame.revalidate();
				
			}
		});
		menuBar.add(mntmCriarPesquisa);

		mnConfig = new JMenu("Config");
		menuBar.add(mnConfig);
		
		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
				frame.setContentPane(telaLogin);
				frame.revalidate();
				
			}
		});
		mnConfig.add(mntmLogin);
		
		JMenuItem mntmPerfil = new JMenuItem("Perfil");
		mntmPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				telaPerfil = new TelaPerfil();
				telaPerfil.setVisible(true);
				frame.setContentPane(telaPerfil);
				frame.revalidate();
				
			}
		});
		mnConfig.add(mntmPerfil);
		
		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
				frame.setContentPane(telaCadastro);
				frame.revalidate();
				
			}
		});
		mnConfig.add(mntmCadastrar);

		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		mnConfig.add(mntmSair);
		
		

	}
}
