package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JPanel {
	private JTextField textEmail;
	private JTextField textSenha;
	private JButton btnLogin;

	public JButton getBtnLogin() {
		return btnLogin;
	}


	/**
	 * Create the panel.
	 */
	public TelaLogin() {
		setLayout(null);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(143, 87, 46, 14);
		add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(210, 84, 86, 20);
		add(textEmail);
		textEmail.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(143, 135, 46, 14);
		add(lblSenha);

		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(210, 132, 86, 20);
		add(textSenha);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(196, 192, 89, 23);
		add(btnLogin);

	}
}
