package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.UsuarioController;
import model.exception.DevPerguntarException;
import model.vo.Usuario;

public class TelaPerfil extends JPanel {
	private Usuario userLogado = new Usuario();
	private UsuarioController uController = new UsuarioController();
	private JButton btnSalvarEdicao;
	private JButton btnExcluirConta;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField pFSenha;
	private boolean edicaoComSenha = false;

	/**
	 * Create the panel.
	 */
	public TelaPerfil() {
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("right:default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("left:default"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("center:default"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("left:default:grow"), },
				new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("top:default:grow"), }));

		JLabel lblNewLabel = new JLabel("Perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel, "3, 3, 7, 1, center, default");

		JLabel lblNewLabel_1 = new JLabel("Nome : ");
		add(lblNewLabel_1, "3, 7");

		txtNome = new JTextField();
		add(txtNome, "7, 7, 5, 1, fill, default");
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("E-mail :");
		add(lblNewLabel_2, "3, 9");

		txtEmail = new JTextField();
		add(txtEmail, "7, 9, 5, 1, fill, default");
		txtEmail.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha :");
		add(lblNewLabel_3, "3, 11");

		btnSalvarEdicao = new JButton("Salvar Edição");
		btnSalvarEdicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarEdicao();
			}
		});

		pFSenha = new JPasswordField();
		add(pFSenha, "7, 11, 5, 1, fill, default");
		add(btnSalvarEdicao, "3, 15, 3, 1, left, fill");

		btnExcluirConta = new JButton("Excluir Conta");

		add(btnExcluirConta, "11, 15, right, default");

	}

	public JButton getBtnExcluirConta() {
		return btnExcluirConta;
	}

	public Usuario getUserLogado() {
		return userLogado;
	}

	public void setUserLogado(Usuario userLogado) {
		this.userLogado = userLogado;
	}

	public UsuarioController getuController() {
		return uController;
	}

	public void salvarEdicao() {
		try {
			validarCampos();
			Usuario userEditado = new Usuario();
			userEditado.setId(userLogado.getId());
			userEditado.setNome(txtNome.getText());
			userEditado.seteMail(txtEmail.getText());
			userEditado.setSenha(new String(pFSenha.getPassword()));
			if (validarEdicao(userEditado)) {
				// TODO editar usuario no Banco
				// o usuarioEditado ja tem o Id e
				// as imformações editadas ou anteriores
				if (uController.editarUsuario(userEditado, userLogado.geteMail()) > 0) {
					atualizarCampos(userEditado);
					JOptionPane.showMessageDialog(null, "Edição Executada Com Sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao Conectar ao Server");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Nenhum Campo Foi Editado");
			}
		} catch (DevPerguntarException erro) {
			JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		}

	}

	public boolean validarEdicao(Usuario userEditado) {
		boolean usuarioFoiEditado = false;

		if (!userEditado.getNome().equals(userLogado.getNome())) {
			usuarioFoiEditado = true;
		}
		if (!userEditado.geteMail().equals(userLogado.geteMail())) {
			usuarioFoiEditado = true;
		}
		if (!userEditado.getSenha().equals(userLogado.getSenha())) {
			usuarioFoiEditado = true;
		}

		return usuarioFoiEditado;
	}

	public void validarCampos() throws DevPerguntarException {
		String alerta = "";
		if (txtNome.getText().trim().isEmpty()) {
			alerta += "Insira um Usuario Desejado\n";
		}
		if (txtEmail.getText().trim().isEmpty()) {
			alerta += "Insira Seu E-mail Desejado\n";
		}
		if (pFSenha.getPassword().toString().trim().isEmpty()) {
			alerta += "Insira Sua Senha Atual ou Umas Nova\n";
		}

		if (!alerta.isEmpty()) {
			alerta = "Causa : \n" + alerta;
			throw new DevPerguntarException(alerta);
		}
	}

	public void atualizarCampos(Usuario user) {
		userLogado = user;

		txtNome.setText(user.getNome());
		txtEmail.setText(user.geteMail());
		pFSenha.setText(user.getSenha());

	}
}
