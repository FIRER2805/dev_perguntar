package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaPerfil extends JPanel {
	private JTextField txtFSenhaAtual;
	private JTextField txtFNovaSenha1;
	private JTextField txtFNovaSenha2;

	/**
	 * Create the panel.
	 */
	public TelaPerfil() {
		setLayout(null);
		
		JLabel lblSenhaAtual = new JLabel("senha atual :");
		lblSenhaAtual.setBounds(88, 72, 76, 14);
		add(lblSenhaAtual);
		
		txtFSenhaAtual = new JTextField();
		txtFSenhaAtual.setBounds(174, 69, 86, 20);
		add(txtFSenhaAtual);
		txtFSenhaAtual.setColumns(10);
		
		JLabel lblNovaSenha1 = new JLabel("Nova Senha :");
		lblNovaSenha1.setBounds(88, 108, 76, 14);
		add(lblNovaSenha1);
		
		txtFNovaSenha1 = new JTextField();
		txtFNovaSenha1.setColumns(10);
		txtFNovaSenha1.setBounds(174, 105, 86, 20);
		add(txtFNovaSenha1);
		
		JLabel lblNovaSenha2 = new JLabel("Nova Senha :");
		lblNovaSenha2.setBounds(88, 148, 76, 14);
		add(lblNovaSenha2);
		
		txtFNovaSenha2 = new JTextField();
		txtFNovaSenha2.setColumns(10);
		txtFNovaSenha2.setBounds(174, 145, 86, 20);
		add(txtFNovaSenha2);
		
		JButton btnTrocarSenha = new JButton("Trocar senha");
		btnTrocarSenha.setBounds(143, 202, 117, 23);
		add(btnTrocarSenha);

	}
}
