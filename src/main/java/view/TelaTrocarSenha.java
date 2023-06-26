package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.UsuarioController;
import model.exception.DevPerguntarException;
import model.vo.Usuario;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class TelaTrocarSenha extends JPanel {

	private JTextField textFSenhaAtual;
	private JTextField textFNovaSenha;
	private JTextField textFComfirmacaoNovaSenha;
	private JButton btnSalvar;
	Usuario u = new Usuario();
	UsuarioController uCont = new UsuarioController();
	
	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	/**
	 * Create the panel.
	 */
	public TelaTrocarSenha() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Trocar Senha");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel, "4, 4");
		
		JLabel lblNewLabel_1 = new JLabel("Senha Atual");
		add(lblNewLabel_1, "4, 8");
		
		textFSenhaAtual = new JTextField();
		add(textFSenhaAtual, "4, 10, fill, default");
		textFSenhaAtual.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nova Senha");
		add(lblNewLabel_2, "4, 12");
		
		textFNovaSenha = new JTextField();
		add(textFNovaSenha, "4, 14, fill, default");
		textFNovaSenha.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Comfirmar Nova Senha");
		add(lblNewLabel_3, "4, 16");
		
		textFComfirmacaoNovaSenha = new JTextField();
		add(textFComfirmacaoNovaSenha, "4, 18, fill, default");
		textFComfirmacaoNovaSenha.setColumns(10);
		
		 btnSalvar = new JButton("Salvar");
		add(btnSalvar, "4, 22");
		
		

	}

	
	
	public void SalvarNovaSenha() throws DevPerguntarException {
		
		validarCampos();
		u.setSenha(textFNovaSenha.getText());
//		TODO criar metodos DAO ou DTO para trocar senha do usuario
//		if(uCont.validarSenhaAtual(u)) {
//			uCont.trocarSenha(u);
		JOptionPane.showMessageDialog(null, "Senha Alterada com Sucessos");
//		}else {
			JOptionPane.showMessageDialog(null, "Senha Atual Invalida");
//		}
		
		
		
		
	}
	
	public void validarCampos() throws DevPerguntarException {
		String alerta = "";
		
		if (textFSenhaAtual.getText().trim().isEmpty()) {
			alerta += "Insira a Senha Atual";
		}
		
		if (textFNovaSenha.getText().trim().isEmpty()) {
			alerta += "Insira a Nova Senha";
		}
		
		if (textFComfirmacaoNovaSenha.getText().trim().isEmpty()) {
			alerta += "Insira a Comfirmação da Nova Senha";
		}
		
		if (textFNovaSenha.getText().equals(textFComfirmacaoNovaSenha.getText())) {
			alerta += "Campos de nova Senha não Coincidem";
		}

		if (!alerta.isEmpty()) {
			alerta = "Causa : \n" + alerta;
			throw new DevPerguntarException(alerta);
		}
		
	}
	
	
	
	
	
	
	
}
