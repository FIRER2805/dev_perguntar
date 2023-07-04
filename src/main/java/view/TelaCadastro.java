package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.PerguntaController;
import controller.UsuarioController;
import model.exception.DevPerguntarException;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Font;
import javax.swing.JPasswordField;

public class TelaCadastro extends JPanel {
	private JTextField textFNome;
	private JTextField textFEmail;
	private JButton btnCadastrar;
	private JPasswordField pFSenha;

	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}
	

	/**
	 * Create the panel.
	 */
	public TelaCadastro() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(150dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		JLabel lblTitulo = new JLabel("Cadastrar Usuario");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo, "3, 3");
		
				JLabel lblNome = new JLabel(" Nome : ");
				lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
				add(lblNome, "3, 7");

		textFNome = new JTextField();
		textFNome.setColumns(15);
		add(textFNome, "3, 9, fill, default");
		
				JLabel lblEmail = new JLabel("E-Mail : ");
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
				add(lblEmail, "3, 13");

		textFEmail = new JTextField();
		textFEmail.setColumns(15);
		add(textFEmail, "3, 15, fill, default");
		
				JLabel lblSenha = new JLabel("Senha : ");
				lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
				add(lblSenha, "3, 19");
		
		pFSenha = new JPasswordField();
		pFSenha.setColumns(15);
		add(pFSenha, "3, 21, fill, default");

		btnCadastrar = new JButton("Cadastrar");
		add(btnCadastrar, "3, 27");

	}

	public void cadastrarUsuario() throws DevPerguntarException {

		UsuarioController controller = new UsuarioController();
		Usuario usuario = new Usuario();
		usuario.setNome(textFNome.getText());
		usuario.seteMail(textFEmail.getText());
		char[] passwordChars = pFSenha.getPassword();
		String password = new String(passwordChars);
		usuario.setSenha(password);
		validaCampos(usuario);
		int cadastrados = controller.cadastrar(usuario);
		if (cadastrados != -1) {

			System.out.println("cadastrado como: " + usuario.getNome());
			JOptionPane.showMessageDialog(null, "Cadastro Efetuado");
		}

	}

	public void validaCampos(Usuario u) throws DevPerguntarException {
		String alerta = "";
		if (u.getNome().trim().isEmpty()) {
			alerta += "Campo nome é obrigatório\n";
		}
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
