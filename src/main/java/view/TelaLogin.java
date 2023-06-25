package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.UsuarioController;
import model.exception.DevPerguntarException;
import model.vo.Usuario;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

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
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("right:default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(48dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(108dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("left:default:grow"), },
				new RowSpec[] { RowSpec.decode("bottom:default:grow"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("top:default:grow"), }));

		JLabel lblEmail = new JLabel("Email");
		add(lblEmail, "3, 3, right, center");

		textEmail = new JTextField();
		add(textEmail, "5, 3, left, center");
		textEmail.setColumns(15);

		JLabel lblSenha = new JLabel("Senha");
		add(lblSenha, "3, 7, right, center");

		textSenha = new JTextField();
		textSenha.setColumns(15);
		add(textSenha, "5, 7, left, center");

		btnLogin = new JButton("Login");
		add(btnLogin, "5, 11, left, center");

	}

	public Usuario logarUsuario() throws DevPerguntarException {
		UsuarioController controller = new UsuarioController();
		Usuario usuario = new Usuario();
		usuario.seteMail(textEmail.getText());
		usuario.setSenha(textSenha.getText());
		validaCampos(usuario);
		usuario = controller.login(usuario);

		return usuario;
	}

	public void limparCampos() {
		textEmail.setText(null);
		textSenha.setText("");
	}

	private void validaCampos(Usuario u) throws DevPerguntarException {
		String alerta = "";
		if (u.geteMail().trim().isEmpty()) {
			alerta += "Campo e-mail é obrigatório\n";
		}
		if (u.getSenha().trim().isEmpty()) {
			alerta += "Campo senha é obrigatório";
		}

		// lançar a exception
		if (!alerta.isEmpty()) {
			// lança exception
			alerta = "Causa : \n" + alerta;
			throw new DevPerguntarException(alerta);
		}
	}
}
