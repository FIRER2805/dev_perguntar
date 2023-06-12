package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaPerfil extends JPanel {
	private JTextField textSenhaAtual;
	private JTextField textNovaSenha1;
	private JTextField textNovaSenha2;

	/**
	 * Create the panel.
	 */
	public TelaPerfil() {
		setLayout(null);
		
		JLabel lblSenharAtual = new JLabel("senha atual :");
		lblSenharAtual.setBounds(88, 72, 76, 14);
		add(lblSenharAtual);
		
		textSenhaAtual = new JTextField();
		textSenhaAtual.setBounds(174, 69, 86, 20);
		add(textSenhaAtual);
		textSenhaAtual.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("nova Senha :");
		lblNewLabel_1.setBounds(88, 108, 76, 14);
		add(lblNewLabel_1);
		
		textNovaSenha1 = new JTextField();
		textNovaSenha1.setColumns(10);
		textNovaSenha1.setBounds(174, 105, 86, 20);
		add(textNovaSenha1);
		
		JLabel lblNewLabel_2 = new JLabel("nova Senha :");
		lblNewLabel_2.setBounds(88, 148, 76, 14);
		add(lblNewLabel_2);
		
		textNovaSenha2 = new JTextField();
		textNovaSenha2.setColumns(10);
		textNovaSenha2.setBounds(174, 145, 86, 20);
		add(textNovaSenha2);
		
		JButton btnTrocarSenha = new JButton("Trocar senha");
		btnTrocarSenha.setBounds(143, 202, 117, 23);
		add(btnTrocarSenha);

	}
}
