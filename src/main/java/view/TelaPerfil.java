package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class TelaPerfil extends JPanel {
	private JTextField txtFSenhaAtual;
	private JTextField txtFNovaSenha1;
	private JTextField txtFNovaSenha2;

	/**
	 * Create the panel.
	 */
	public TelaPerfil() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default"),
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default:grow"),}));
		
		JLabel lblSenhaAtual = new JLabel("senha atual :");
		add(lblSenhaAtual, "3, 3, right, fill");
		
		txtFSenhaAtual = new JTextField();
		add(txtFSenhaAtual, "5, 3, fill, fill");
		txtFSenhaAtual.setColumns(15);
		
		JLabel lblNovaSenha1 = new JLabel("Nova Senha :");
		add(lblNovaSenha1, "3, 7, right, fill");
		
		txtFNovaSenha1 = new JTextField();
		txtFNovaSenha1.setColumns(15);
		add(txtFNovaSenha1, "5, 7, fill, fill");
		
		JLabel lblNovaSenha2 = new JLabel("Comfirmar Nova Senha :");
		add(lblNovaSenha2, "3, 11, right, fill");
		
		txtFNovaSenha2 = new JTextField();
		txtFNovaSenha2.setColumns(15);
		add(txtFNovaSenha2, "5, 11, fill, fill");
		
		JButton btnTrocarSenha = new JButton("Trocar senha");
		add(btnTrocarSenha, "5, 15, default, fill");

	}
}
