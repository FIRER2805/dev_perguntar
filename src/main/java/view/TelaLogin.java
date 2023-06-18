package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.UsuarioController;
import model.vo.Usuario;

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
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				Usuario usuario = new Usuario();
				usuario.seteMail(textEmail.getText());
				usuario.setSenha(textSenha.getText());
				// quando exception for implementada isso estara num try/catch
				validaCampos(usuario);
				usuario = controller.login(usuario);
				if(usuario != null)
				{
					// Lucas
					// da um jeito de trocar para a tela incial enviando o objeto usuario para ela
					// eu estava pensando em criar um construtor para a tela inicial e deixar o objeto usuario
					// como atributo, para o controle de sessão, ai caso esteja null quer dizer q ele
					// esta deslogado, ai ele não consegue fazer pergunta nem responder, ai tu
					// fica redirecionando ele pra tela de login ou cadastro quando ele clicasse no botão
					// de perguntas, respostas, ect...
					System.out.println("Logado como: " + usuario.getNome());
				}
			}
		});
	}
	
	private void validaCampos(Usuario u)
	{
		String alerta = "";
		if(u.geteMail().trim().isEmpty())
		{
			alerta += "Campo e-mail é obrigatório\n";
		}
		if(u.getSenha().trim().isEmpty())
		{
			alerta += "Campo senha é obrigatório";
		}
		
		//lançar a exception
		if(alerta.isEmpty())
		{
			//lança exception
		}
	}
}
