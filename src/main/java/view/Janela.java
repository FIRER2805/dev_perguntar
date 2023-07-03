package view;

import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.exception.DevPerguntarException;
import model.vo.Usuario;

public class Janela {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem mntmHome;
	private JMenuItem mntmPesquisa;
	private JMenuItem mntmCriarPergunta;
	private JMenu mnConfig;
	private JMenuItem mntmSair;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmLogin;
	private Usuario usuarioLogado;
	private JMenuItem mntmPerfil;
	private view.TelaCadastro telaCadastro = new TelaCadastro();
	private TelaCriarPergunta telaCriacaoDePergunta = new TelaCriarPergunta();
	private TelaHome telaHome = new TelaHome();
	private view.TelaLogin telaLogin = new TelaLogin();
	private TelaPerfil telaPerfil = new TelaPerfil();
	private TelaPergunta telaPergunta = new TelaPergunta();
	private TelaPesquisa telaPesquisa = new TelaPesquisa();

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
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setContentPane(telaHome);
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setVisible(true);

		mntmHome = new JMenuItem("Home");
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaHome.atualizarTable();
				telaHome.setVisible(true);
				frame.setContentPane(telaHome);
				frame.revalidate();

			}
		});
		menuBar.add(mntmHome);

		telaHome.getBtnVizualizar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					telaPergunta.atualizarCampos(telaHome.resgatarPergunta(), usuarioLogado);
					telaPergunta.setPergunta(telaHome.resgatarPergunta());
					telaPergunta.preencherTabela();
					telaPergunta.setVisible(true);
					frame.setContentPane(new JScrollPane(telaPergunta));
					frame.revalidate();
				} catch (DevPerguntarException erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		mntmPesquisa = new JMenuItem("Pesquisa");
		mntmPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaPesquisa.atualizarCampos();
				telaPesquisa.setVisible(true);
				frame.setContentPane(telaPesquisa);
				frame.revalidate();

			}
		});
		menuBar.add(mntmPesquisa);

		telaPesquisa.getBtnVisualizar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					telaPergunta.atualizarCampos(telaPesquisa.resgatarPergunta(), usuarioLogado);
					telaPergunta.setVisible(true);
					frame.setContentPane(telaPergunta);
					frame.revalidate();
				} catch (DevPerguntarException erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		mntmCriarPergunta = new JMenuItem("Criar pergunta");
		mntmCriarPergunta.setVisible(false);
		mntmCriarPergunta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				telaCriacaoDePergunta.atualizarCampos();
				telaCriacaoDePergunta.setVisible(true);
				frame.setContentPane(new JScrollPane(telaCriacaoDePergunta));
				frame.revalidate();

			}
		});
		menuBar.add(mntmCriarPergunta);

		telaCriacaoDePergunta.getBtnPostarPergunta().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				telaCriacaoDePergunta.cadastrarPergunta(usuarioLogado);

			}
		});

		mnConfig = new JMenu("Usuario");
		menuBar.add(mnConfig);

		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaLogin.limparCampos();
				telaLogin.setVisible(true);
				frame.setContentPane(telaLogin);
				frame.revalidate();

			}
		});
		mnConfig.add(mntmLogin);

		telaLogin.getBtnLogin().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					usuarioLogado = telaLogin.logarUsuario();
					mntmCriarPergunta.setVisible(true);
					mntmPerfil.setVisible(true);
					mntmSair.setVisible(true);
					mntmCadastrar.setVisible(false);
					mntmLogin.setVisible(false);

					telaHome.setVisible(true);
					frame.setContentPane(telaHome);
					frame.revalidate();
				} catch (DevPerguntarException erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Falha ao Efetuar Login",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		mntmPerfil = new JMenuItem("Perfil");
		mntmPerfil.setVisible(false);
		mntmPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaPerfil.atualizarCampos(usuarioLogado);
				telaPerfil.setVisible(true);
				frame.setContentPane(telaPerfil);
				frame.revalidate();

			}
		});
		mnConfig.add(mntmPerfil);

		telaPerfil.getBtnSalvarEdicao().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				usuarioLogado = telaPerfil.salvarEdicao();

			}
		});

		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaCadastro.setVisible(true);
				frame.setContentPane(telaCadastro);
				frame.revalidate();

			}
		});
		mnConfig.add(mntmCadastrar);

		telaCadastro.getBtnCadastrar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					telaCadastro.cadastrarUsuario();
					telaLogin.limparCampos();
					telaLogin.setVisible(true);
					frame.setContentPane(telaLogin);
					frame.revalidate();
				} catch (DevPerguntarException erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Falha ao Efetuar Cadastro",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		mntmSair = new JMenuItem("Sair");
		mntmSair.setVisible(false);
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				trocarTelasAoSair();

			}
		});
		mnConfig.add(mntmSair);

		telaPerfil.getBtnExcluirConta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (telaPerfil.excluirConta()) {
					JOptionPane.showMessageDialog(null, "Exclusão Executada Com Sucesso");
					trocarTelasAoSair();
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao excluir usuario");
				}
			}
		});
	}

	public void trocarTelasAoSair() {
		usuarioLogado = null;
		mntmCriarPergunta.setVisible(false);
		mntmPerfil.setVisible(false);
		mntmSair.setVisible(false);
		mntmCadastrar.setVisible(true);
		mntmLogin.setVisible(true);
		telaLogin.limparCampos();
		telaLogin.setVisible(true);
		frame.setContentPane(telaLogin);
		frame.revalidate();
	}

}
