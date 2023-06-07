package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaCadastro extends JPanel {
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textSenha;
	private JButton btnCadastrar;
	private JButton btnLogin;
	private JButton btnSair;

	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}
	public JButton getBtnVoltar() {
		return btnLogin;
	}

	/**
	 * Create the panel.
	 */
	public TelaCadastro() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(114, 43, 46, 14);
		add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(196, 40, 86, 20);
		add(textNome);
		textNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(114, 84, 46, 14);
		add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(196, 81, 86, 20);
		add(textEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(114, 126, 46, 14);
		add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(196, 123, 86, 20);
		add(textSenha);
		
		 btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(188, 167, 89, 23);
		add(btnCadastrar);
		
		 btnLogin = new JButton("Login");
		btnLogin.setBounds(81, 220, 89, 23);
		add(btnLogin);
		
		btnSair = new JButton("Sair");
		btnSair.setBounds(264, 220, 89, 23);
		add(btnSair);

	}
}
