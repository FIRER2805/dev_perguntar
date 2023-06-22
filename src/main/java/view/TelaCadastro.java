package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.PerguntaController;
import controller.UsuarioController;
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

public class TelaCadastro extends JPanel {
	private JLabel lblTitulo;
	private JLabel lblNome;
	private JLabel lblEmail_1;
	private JLabel lblSenha;
	private JTextField textFNome;
	private JTextField textFEmail;
	private JTextField textFSenha;
	private JButton btnCadastrar;


	/**
	 * Create the panel.
	 */
	public TelaCadastro() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:max(45dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(124dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
				RowSpec.decode("bottom:default:grow"),
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
				RowSpec.decode("top:default:grow"),}));
		 
		 lblTitulo = new JLabel("Cadastrar Usuario");
		 lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		 add(lblTitulo, "5, 3");
		 
		 lblNome = new JLabel(" Nome : ");
		 add(lblNome, "3, 7");
		 
		 textFNome = new JTextField();
		 textFNome.setColumns(15);
		 add(textFNome, "5, 7");
		 
		 lblEmail_1 = new JLabel("E-Mail : ");
		 add(lblEmail_1, "3, 9");
		 
		 textFEmail = new JTextField();
		 textFEmail.setColumns(15);
		 add(textFEmail, "5, 9");
		 
		 lblSenha = new JLabel("Senha : ");
		 add(lblSenha, "3, 11");
		 
		 textFSenha = new JTextField();
		 textFSenha.setColumns(15);
		 add(textFSenha, "5, 11");
		 
		 btnCadastrar = new JButton("Cadastrar");
		 add(btnCadastrar, "5, 13");
		 
	}
	
	public JTextField getTextFNome() {
		return textFNome;
	}


	public void setTextFNome(JTextField textFNome) {
		this.textFNome = textFNome;
	}


	public JTextField getTextFEmail() {
		return textFEmail;
	}


	public void setTextFEmail(JTextField textFEmail) {
		this.textFEmail = textFEmail;
	}


	public JTextField getTextFSenha() {
		return textFSenha;
	}


	public void setTextFSenha(JTextField textFSenha) {
		this.textFSenha = textFSenha;
	}


	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}
	
	public void validaCampos(Usuario u)
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
