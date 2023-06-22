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

import model.vo.Usuario;

public class Janela {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem mntmHome;
	private JMenuItem mntmPesquisa;
	private JMenuItem mntmCriarPesquisa;
	private JMenu mnConfig;
	private JMenuItem mntmSair;
	private view.TelaCadastro telaCadastro;
	private view.TelaLogin telaLogin = new TelaLogin();
	private JTextField textField;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmLogin;
	private TelaCriarPergunta telaCriacaoDePergunta;
	private TelaPerfil telaPerfil;
	private TelaPesquisa telaPesquisa;
	private TelaHome telaHome = new TelaHome();
	private Usuario usuarioLogado;
	private JMenuItem mntmPerfil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
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
	public Janela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setContentPane(telaHome);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setVisible(true);

		mntmHome = new JMenuItem("Home");
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaHome = new TelaHome();
				telaHome.setVisible(true);
				frame.setContentPane(telaHome);
				frame.revalidate();

			}
		});
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
		mntmCriarPesquisa.setVisible(false);
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

				telaLogin.limparCampos();;
				telaLogin.setVisible(true);
				frame.setContentPane(telaLogin);
				frame.revalidate();

			}
		});
		mnConfig.add(mntmLogin);
		
		telaLogin.getBtnLogin().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				usuarioLogado = telaLogin.logarUsuario();
				mntmCriarPesquisa.setVisible(true);
				mntmPerfil.setVisible(true);
				mntmCadastrar.setVisible(false);
				mntmLogin.setVisible(false);
				
				telaHome.setVisible(true);
				frame.setContentPane(telaHome);
				frame.revalidate();
				
			}
		});
		
		
		

		 mntmPerfil = new JMenuItem("Perfil");
		 mntmPerfil.setVisible(false);
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
