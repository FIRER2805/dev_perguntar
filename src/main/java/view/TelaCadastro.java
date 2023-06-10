package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JPanel {
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textSenha;
	private JButton btnCadastrar;

	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}


	/**
	 * Create the panel.
	 */
	public TelaCadastro() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(131, 86, 46, 14);
		add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(213, 83, 86, 20);
		add(textNome);
		textNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(131, 127, 46, 14);
		add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(213, 124, 86, 20);
		add(textEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(131, 169, 46, 14);
		add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(213, 166, 86, 20);
		add(textSenha);
		
		 btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(205, 210, 89, 23);
		add(btnCadastrar);

	}
}
