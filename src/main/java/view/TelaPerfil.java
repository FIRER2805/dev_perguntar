package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.UsuarioController;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPerfil extends JPanel {
	
	UsuarioController uController = new UsuarioController();
	private JButton btnMinhasPerguntas;
	private JButton btnTrocarSenha;

	public JButton getBtnMinhasPerguntas() {
		return btnMinhasPerguntas;
	}

	public JButton getBtnTrocarSenha() {
		return btnTrocarSenha;
	}

	/**
	 * Create the panel.
	 */
	public TelaPerfil() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				RowSpec.decode("top:default:grow"),}));
		
		JLabel lblNewLabel = new JLabel("Perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel, "3, 3, center, default");

		btnMinhasPerguntas = new JButton("Minhas Perguntas");
		add(btnMinhasPerguntas, "3, 7");

		btnTrocarSenha = new JButton("Trocar senha");
		add(btnTrocarSenha, "3, 11, default, fill");

		JButton btnExcluirConta = new JButton("Excluir Conta");
		
		add(btnExcluirConta, "3, 15");

	}
	
	private void ExcluirContaBUtao() {
		//TODO criar metodo para Excluir
//		uController.excluirUsuario();
	}
}
