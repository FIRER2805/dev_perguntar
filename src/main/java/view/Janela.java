package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import controller.UsuarioController;
import model.vo.Pergunta;
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
	private view.TelaLogin telaLogin;
	private JTextField textField;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmLogin;
	private TelaCriarPergunta telaCriacaoDePergunta;
	private TelaPerfil telaPerfil;
	private TelaPesquisa telaPesquisa;
	private TelaHome telaHome = new TelaHome();

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
				telaHome.getBtnVizualizar().addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						int linhaSelecionada = telaHome.getTable().getSelectedRow();
						if(linhaSelecionada == -1)
						return;
						Pergunta pergunta = (Pergunta)telaHome.getTable().getModel().getValueAt(linhaSelecionada, 1);
						TelaPergunta telaPergunta = new TelaPergunta(pergunta);
						telaPergunta.setVisible(true);
						frame.setContentPane(telaPergunta);
						frame.revalidate();
					}
				});
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
				
				telaCadastro.getBtnCadastrar().addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						UsuarioController controller = new UsuarioController();
						Usuario usuario = new Usuario();
						usuario.setNome(telaCadastro.getTextFNome().getText());
						usuario.seteMail(telaCadastro.getTextFEmail().getText());
						usuario.setSenha(telaCadastro.getTextFSenha().getText());
						// quando exception for implementada isso estara num try/catch
						telaCadastro.validaCampos(usuario);
						int cadastrados = controller.cadastrar(usuario);
						if(cadastrados != -1)
						{
							telaLogin = new TelaLogin();
							telaLogin.setVisible(true);
							frame.setContentPane(telaLogin);
							frame.revalidate();
							System.out.println("cadastrado como: " + usuario.getNome());
						}
					}
				});

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
