package view;

import java.awt.Font;

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

	/**
	 * Create the panel.
	 */
	public TelaEdicaoPergunta() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(14dlu;default)"),},
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Edição de pergunta");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel, "12, 4, center, default");
		
		JLabel lblNewLabel_1 = new JLabel("Título:");
		add(lblNewLabel_1, "8, 8");
		
		textFieldTitulo = new JTextField();
		add(textFieldTitulo, "12, 8, fill, default");
		textFieldTitulo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Categoria:");
		add(lblNewLabel_3, "16, 8, left, default");
		
		comboBoxCategoria = new JComboBox();
		add(comboBoxCategoria, "18, 8, 7, 1, fill, default");
		
		JLabel lblNewLabel_2 = new JLabel("Conteudo:");
		add(lblNewLabel_2, "8, 10");
		
		textAreaConteudo = new JTextArea();
		add(textAreaConteudo, "12, 10, fill, fill");
		
		JButton btnNewButton = new JButton("Salvar Edição");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(btnNewButton, "12, 14, default, center");
		
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
	
	public void atualizaPergunta()
	{
		try {
			Pergunta novaPergunta = geraPerguntaAtualizada();
			validaCampos(novaPergunta);
			if(controller.atualizar(geraPerguntaAtualizada()) > 0)
			{
				JOptionPane.showMessageDialog(null, "Pergunta atualizada com sucesso!");
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Ocorreu algum erro ao atualizar a pergunta");
			}
		} catch (DevPerguntarException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void validaCampos(Pergunta pergunta) throws DevPerguntarException
	{
		String mensagem = "";
		if(pergunta.getTitulo().isBlank())
			mensagem += "O título é obrigatório!\n";
		if(pergunta.getConteudo().trim().isEmpty())
			mensagem += "O conteudo é obrigatório!\n";
		if(mensagem.isBlank())
			throw new DevPerguntarException(mensagem);
	}
	
	private Pergunta geraPerguntaAtualizada()
	{
		Pergunta novaPergunta = new Pergunta();
		novaPergunta.setTitulo(textFieldTitulo.getText());
		novaPergunta.setConteudo(textAreaConteudo.getText());
		novaPergunta.setCategoria((Categoria) comboBoxCategoria.getSelectedItem());
		return novaPergunta;
	}
}
