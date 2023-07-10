package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.UsuarioController;
import controller.Validador;
import model.exception.DevPerguntarException;
import model.vo.Usuario;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JPasswordField;
import java.awt.Font;

public class TelaLogin extends JPanel {
	private JTextField textEmail;
	private JButton btnLogin;
	private JPasswordField pFSenha;

	public JButton getBtnLogin() {
		return btnLogin;
	}

	/**
	 * Create the panel.
	 */
	public TelaLogin() {
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("right:default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(150dlu;min)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("left:default:grow"), },
				new RowSpec[] { RowSpec.decode("bottom:default:grow"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("top:default:grow"), }));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblEmail, "3, 3, center, center");

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(textEmail, "3, 5, fill, center");
		textEmail.setColumns(15);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblSenha, "3, 7, center, center");

		pFSenha = new JPasswordField();
		pFSenha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(pFSenha, "3, 9, fill, default");

		btnLogin = new JButton("Login");
		add(btnLogin, "3, 13, center, center");

	}

	public Usuario logarUsuario() throws DevPerguntarException {
		UsuarioController controller = new UsuarioController();
		Usuario usuario = new Usuario();
		usuario.seteMail(textEmail.getText());
		char[] passwordChars = pFSenha.getPassword();
		String password = new String(passwordChars);
		usuario.setSenha(password);
		validaCampos(usuario);
		usuario = controller.login(usuario);

		if (usuario == null || usuario.getId() < 0) {
			throw new DevPerguntarException("Usuario ou Senha Invalido(s)");
		}

		return usuario;
	}

	public void limparCampos() {
		textEmail.setText(null);
		pFSenha.setText("");
	}

	private void validaCampos(Usuario u) throws DevPerguntarException {
		String alerta = "";
		if (u.geteMail().trim().isEmpty()) {
			alerta += "Campo e-mail é obrigatório\n";
		} else if (!Validador.validaEmail(u.geteMail())) {
			alerta += "email inválido\n";
		}

		if (u.getSenha().trim().isEmpty()) {
			alerta += "Campo senha é obrigatório\n";
		} else if (!Validador.validaSenha(u.getSenha())) {
			alerta += "senha inválida\n";
		}

		// lançar a exception
		if (!alerta.isEmpty()) {
			// lança exception
			alerta = "Causa : \n" + alerta;
			throw new DevPerguntarException(alerta);
		}
	}

}
