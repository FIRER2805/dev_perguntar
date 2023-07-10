package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.PerguntaController;
import controller.UsuarioController;
import model.exception.DevPerguntarException;
import model.vo.Pergunta;
import model.vo.Usuario;

public class TelaPerfil extends JPanel {
	private Usuario userLogado = new Usuario();
	private UsuarioController uController = new UsuarioController();
	private JButton btnSalvarEdicao;
	private JButton btnExcluirConta;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField pFSenha;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnExcluir;
	private JButton btnVizualizar;
	private String[] nomesColunas = { "Titulo ", "DT-Criação", "Status", "Usuario", "Categoria" };
	private List<Pergunta> perguntas;
	private PerguntaController pCont = new PerguntaController();
	private JSeparator separator;
	private JLabel lblNewLabel_4;
	private JButton btnEditar;

	/**
	 * Create the panel.
	 */
	public TelaPerfil() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(80dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(135dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(135dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
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
				RowSpec.decode("top:max(120dlu;min)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		JLabel lblNewLabel = new JLabel("Perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel, "3, 3, 5, 1, center, default");

		JLabel lblNewLabel_1 = new JLabel("Nome : ");
		add(lblNewLabel_1, "3, 7");

		txtNome = new JTextField();
		add(txtNome, "5, 7, 3, 1, fill, default");
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("E-mail :");
		add(lblNewLabel_2, "3, 9");

		txtEmail = new JTextField();
		add(txtEmail, "5, 9, 3, 1, fill, default");
		txtEmail.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha :");
		add(lblNewLabel_3, "3, 11");

		pFSenha = new JPasswordField();
		add(pFSenha, "5, 11, 3, 1, fill, default");

		btnExcluirConta = new JButton("Excluir Conta");
		add(btnExcluirConta, "3, 13, 3, 1, left, default");

		btnSalvarEdicao = new JButton("Salvar Edição");
		add(btnSalvarEdicao, "7, 13, right, fill");

		separator = new JSeparator();
		add(separator, "1, 17, 9, 1");

		lblNewLabel_4 = new JLabel("Minhas Perguntas");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel_4, "3, 19, 5, 1, center, default");

		scrollPane = new JScrollPane();
		add(scrollPane, "3, 23, 5, 1, fill, fill");

		table = new JTable();
		scrollPane.setViewportView(table);

		btnExcluir = new JButton("Excluir Pergunta");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					excluirPerguntaButao(resgatarPergunta());
				} catch (DevPerguntarException erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		add(btnExcluir, "3, 25, left, default");
		
		btnEditar = new JButton("Editar Pergunta");
		add(btnEditar, "5, 25, right, default");

		btnVizualizar = new JButton("Vizualizar");
		add(btnVizualizar, "7, 25, right, default");
		limparTabela();
	}
	
	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnVizualizar() {
		return btnVizualizar;
	}

	public JButton getBtnSalvarEdicao() {
		return btnSalvarEdicao;
	}

	public JButton getBtnExcluirConta() {
		return btnExcluirConta;
	}

	public boolean excluirConta() {
		boolean deuBom = false;
		if (uController.excluirUsuario(userLogado) > 0) {
			deuBom = true;
		}
		return deuBom;
	}

	public Usuario salvarEdicao() {
		Usuario userRetornado = new Usuario();
		try {
			validarCampos();
			Usuario userEditado = new Usuario();
			userEditado.setId(userLogado.getId());
			userEditado.setNome(txtNome.getText());
			userEditado.seteMail(txtEmail.getText());
			userEditado.setSenha(new String(pFSenha.getPassword()));
			if (validarEdicao(userEditado)) {
				if (uController.editarUsuario(userEditado, userLogado.geteMail()) > 0) {
					atualizarCampos(userEditado);
					JOptionPane.showMessageDialog(null, "Edição Executada Com Sucesso");
					userRetornado = userEditado;
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao Conectar ao Server");
					userRetornado = userLogado;
				}

			} else {
				JOptionPane.showMessageDialog(null, "Nenhum Campo Foi Editado");
			}
		} catch (DevPerguntarException erro) {
			JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		}
		return userRetornado;
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

		atualizarTable(user.getId());
	}

	

	public void atualizarTable(int id) {
		limparTabela();

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		perguntas = pCont.busca(id);

		for (Pergunta p : perguntas) {

			Object[] novaLinhaDaTabela = new Object[5];

			novaLinhaDaTabela[0] = p.getTitulo();
			novaLinhaDaTabela[1] = p.getData();
			novaLinhaDaTabela[2] = p.getDataResolucao() == null ? "Em Aberto" : "Resolvido";
			novaLinhaDaTabela[3] = p.getUsuario().getNome();
			novaLinhaDaTabela[4] = p.getCategoria().getNome();

			model.addRow(novaLinhaDaTabela);
		}

	}
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {,}, nomesColunas));
	}

	public Pergunta resgatarPergunta() throws DevPerguntarException {

		int linhaSelecionada = table.getSelectedRow();
		if (linhaSelecionada == -1) {
			throw new DevPerguntarException("Selecione uma Pergunta");
		}
		Pergunta pergunta = perguntas.get(linhaSelecionada);
		return pergunta;

	}

	public void excluirPerguntaButao(Pergunta pergunta) {
		PerguntaController pCont = new PerguntaController();	
		if(pCont.excluir(pergunta) > 0) {
			JOptionPane.showMessageDialog(null, "Pergunta Excluida com Sucesso");
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao tentar excluir pergunta");
		}
	}

}
