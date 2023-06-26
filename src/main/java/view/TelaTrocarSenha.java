package view;

import java.awt.Font;
import java.util.Arrays;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaTrocarSenha extends JPanel {
	private JButton btnSalvar;
	Usuario u = new Usuario();
	UsuarioController uCont = new UsuarioController();
	private JPasswordField pFSenhaAtual;
	private JPasswordField pFNovaSenha;
	private JPasswordField pFComfirmNovaSenha;
	private String novaSenha;
	
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
		
		pFSenhaAtual = new JPasswordField();
		add(pFSenhaAtual, "4, 10, fill, default");
		
		JLabel lblNewLabel_2 = new JLabel("Nova Senha");
		add(lblNewLabel_2, "4, 12");
		
		pFNovaSenha = new JPasswordField();
		add(pFNovaSenha, "4, 14, fill, default");
		
		JLabel lblNewLabel_3 = new JLabel("Comfirmar Nova Senha");
		add(lblNewLabel_3, "4, 16");
		 
		 pFComfirmNovaSenha = new JPasswordField();
		 add(pFComfirmNovaSenha, "4, 18, fill, default");
		
		 btnSalvar = new JButton("Salvar");
		 btnSalvar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		try {
					SalvarNovaSenha();
				} catch (DevPerguntarException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Atenção",
							JOptionPane.WARNING_MESSAGE);
				}
		 		
		 	}
		 });
		add(btnSalvar, "4, 22");
		
		

	}

	
	
	public void SalvarNovaSenha() throws DevPerguntarException {
		
		validarCampos();
		u.setSenha(novaSenha);
//		TODO criar metodos DAO ou DTO para trocar senha do usuario
//		if(uCont.validarSenhaAtual(u)) {
//			uCont.trocarSenha(u);
		JOptionPane.showMessageDialog(null, "Senha Alterada com Sucessos");
//		}else {
			JOptionPane.showMessageDialog(null, "Senha Atual Invalida");
//		}
		
		
		
		
	}
	
	public void validarCampos() throws DevPerguntarException {
		
		char[] cSenhaAtual = pFSenhaAtual.getPassword();
		String senhaAtual = new String(cSenhaAtual);
		 
		char[] cNovaSenha = pFNovaSenha.getPassword();
		 novaSenha = new String(cNovaSenha);
		
		char[] cComfirmNovaSenha = pFComfirmNovaSenha.getPassword();
		String comfirmNovaSenha = new String(cComfirmNovaSenha);
		
		String alerta = "";
		
		if (senhaAtual.trim().isEmpty()) {
			alerta += "Insira a Senha Atual\n";
		}
		
		if (novaSenha.trim().isEmpty()) {
			alerta += "Insira a Nova Senha\n";
		}
		
		if (comfirmNovaSenha.trim().isEmpty()) {
			alerta += "Insira a Comfirmação da Nova Senha\n";
		}
		
		if (!novaSenha.equals(comfirmNovaSenha)) {
			alerta += "Campos de nova Senha não Coincidem";
		}

		if (!alerta.isEmpty()) {
			alerta = "Causa : \n" + alerta;
			throw new DevPerguntarException(alerta);
		}
		
	}
	
	public void pega() {
		// Limpando o array de caracteres da senha
		char[] passwordChars = pFSenhaAtual.getPassword();
		String password = new String(passwordChars);
		Arrays.fill(passwordChars, '\0');

	}
	
	
	
	
	
	
	
}
