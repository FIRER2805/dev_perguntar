package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaLogin extends JPanel {
	private JTextField textEmail;
	private JTextField textSenha;

	/**
	 * Create the panel.
	 */
	public TelaLogin() {
		setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(117, 71, 46, 14);
		add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(184, 68, 86, 20);
		add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(117, 119, 46, 14);
		add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(184, 116, 86, 20);
		add(textSenha);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(170, 176, 89, 23);
		add(btnLogin);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(248, 240, 89, 23);
		add(btnSair);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(85, 240, 89, 23);
		add(btnCadastrar);

	}
}
