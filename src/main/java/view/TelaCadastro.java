package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.UsuarioController;
import model.vo.Usuario;

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
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				Usuario usuario = new Usuario();
				usuario.setNome(textNome.getText());
				usuario.seteMail(textEmail.getText());
				usuario.setSenha(textSenha.getText());
				// quando exception for implementada isso estara num try/catch
				validaCampos(usuario);
				int cadastrados = controller.cadastrar(usuario);
				if(cadastrados != -1)
				{
					// Lucas
					// da um jeito de trocar para a tela incial enviando o objeto usuario para ela
					// eu estava pensando em criar um construtor para a tela inicial e deixar o objeto usuario
					// como atributo, para o controle de sessão, ai caso esteja null quer dizer q ele
					// esta deslogado, ai ele não consegue fazer pergunta nem responder, ai tu
					// fica redirecionando ele pra tela de login ou cadastro quando ele clicasse no botão
					// de perguntas, respostas, ect...
					System.out.println("cadastrado como: " + usuario.getNome());
				}
			}
		});
	}
	
	private void validaCampos(Usuario u)
	{
		String alerta = "";
		if(u.getNome().trim().isEmpty())
		{
			alerta += "Campo nome é obrigatório\n";
		}
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
