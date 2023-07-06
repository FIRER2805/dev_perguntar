package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.CategoriaController;
import controller.PerguntaController;
import model.exception.DevPerguntarException;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Usuario;

public class TelaEdicaoPergunta extends JPanel {
	private JTextField textFieldTitulo;
	private Pergunta pergunta;
	private Usuario usuarioLogado;
	private PerguntaController controller;
	private JComboBox comboBoxCategoria;
	private JTextArea textAreaConteudo;
	private JButton btnSalvarEdicao;

	/**
	 * Create the panel.
	 */
	public TelaEdicaoPergunta() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(80dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(230dlu;min)"),
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
				FormSpecs.DEFAULT_ROWSPEC,}));
				
						JLabel lblNewLabel = new JLabel("Edição de pergunta");
						lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
						add(lblNewLabel, "4, 4, 5, 1, center, default");
		
				JLabel lblNewLabel_1 = new JLabel("Título:");
				add(lblNewLabel_1, "4, 8, left, default");

		textFieldTitulo = new JTextField();
		add(textFieldTitulo, "6, 8, 3, 1, fill, default");
		textFieldTitulo.setColumns(10);

		CategoriaController categoriaController = new CategoriaController();
		ArrayList<Categoria> categorias = categoriaController.buscaTodas();
		
				JLabel lblNewLabel_2 = new JLabel("Conteudo:");
				add(lblNewLabel_2, "4, 10, left, default");

		textAreaConteudo = new JTextArea();
		textAreaConteudo.setLineWrap(true);
		add(textAreaConteudo, "6, 10, 3, 1, fill, fill");
		
				JLabel lblNewLabel_3 = new JLabel("Categoria:");
				add(lblNewLabel_3, "4, 12, left, default");
		
				btnSalvarEdicao = new JButton("Salvar Edição");
				btnSalvarEdicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnSalvarEdicao.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						atualizaPergunta();
					}
				});
				comboBoxCategoria = new JComboBox(categorias.toArray());
				add(comboBoxCategoria, "6, 12, fill, default");
				add(btnSalvarEdicao, "4, 16, 5, 1, center, center");

		controller = new PerguntaController();

	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void atualizaPergunta() {
		try {
			Pergunta novaPergunta = geraPerguntaAtualizada();
			validaCampos(novaPergunta);
			if (controller.atualizar(novaPergunta) > 0) {
				JOptionPane.showMessageDialog(null, "Pergunta atualizada com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Ocorreu algum erro ao atualizar a pergunta");
			}
		} catch (DevPerguntarException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void setaCampos() {
		textFieldTitulo.setText(this.pergunta.getTitulo());
		textAreaConteudo.setText(this.pergunta.getConteudo());
	}

	private void validaCampos(Pergunta pergunta) throws DevPerguntarException {
		String mensagem = "";
		if (pergunta.getTitulo().isBlank())
			mensagem += "O título é obrigatório!\n";
		if (pergunta.getConteudo().isBlank())
			mensagem += "O conteudo é obrigatório!\n";
		if (!mensagem.isBlank())
			throw new DevPerguntarException(mensagem);
	}

	private Pergunta geraPerguntaAtualizada() {
		Pergunta novaPergunta = new Pergunta();
		novaPergunta.setTitulo(textFieldTitulo.getText());
		novaPergunta.setConteudo(textAreaConteudo.getText());
		novaPergunta.setCategoria((Categoria) comboBoxCategoria.getSelectedItem());
		novaPergunta.setId(this.pergunta.getId());
		return novaPergunta;
	}
}
