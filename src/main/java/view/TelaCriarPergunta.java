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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.PerguntaController;
import model.dao.CategoriaDAO;
import model.exception.DevPerguntarException;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Usuario;
import java.awt.Color;
import java.awt.SystemColor;

public class TelaCriarPergunta extends JPanel {
	private JTextField textFTitulo;
	private JTextArea textADuvida;
	private JComboBox cbCategoria;
	PerguntaController perguntaController = new PerguntaController();

	CategoriaDAO categoriaDAO = new CategoriaDAO();
	private JButton btnPostarPergunta;

	public JButton getBtnPostarPergunta() {
		return btnPostarPergunta;
	}

	/**
	 * Create the panel.
	 */
	public TelaCriarPergunta() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				RowSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default:grow"),}));

		JLabel lblTituloPagina = new JLabel("Criar Pergunta");
		lblTituloPagina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTituloPagina, "5, 3, 9, 1, center, default");

		JLabel lblTitulo = new JLabel("Titulo :");
		add(lblTitulo, "5, 7, left, default");

		textFTitulo = new JTextField();
		add(textFTitulo, "9, 7, fill, default");
		textFTitulo.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria :");
		add(lblCategoria, "11, 7, right, default");

		ArrayList<Categoria> categorias = categoriaDAO.buscarTodas();
		cbCategoria = new JComboBox(categorias.toArray());
		cbCategoria.setBackground(SystemColor.activeCaption);
		cbCategoria.setSelectedIndex(-1);
		add(cbCategoria, "13, 7, fill, default");

		textADuvida = new JTextArea();
		textADuvida.setLineWrap(true);
		add(textADuvida, "5, 11, 9, 1, fill, fill");


		btnPostarPergunta = new JButton("Postar Pergunta");
		add(btnPostarPergunta, "5, 15, 9, 1, center, default");

	}

	public void cadastrarPergunta(Usuario usuario) {

		Pergunta pergunta = new Pergunta();
		pergunta.setTitulo(textFTitulo.getText());
		pergunta.setConteudo(textADuvida.getText());
		Categoria categoria = new Categoria();
		categoria = (Categoria) cbCategoria.getSelectedItem();
		pergunta.setCategoria(categoria);
		pergunta.setUsuario(usuario);
		int idpergunta = 0;
		try {
			validarCampos();
			idpergunta = perguntaController.perguntar(pergunta);
		} catch (DevPerguntarException erro) {
			JOptionPane.showMessageDialog(null, erro.getMessage(), "falha ao postar pergunta",
					JOptionPane.WARNING_MESSAGE);
		}

		if (idpergunta > 0) {
			atualizarCampos();
			JOptionPane.showMessageDialog(null, "Pergunta feita com Sucesso");

		}

	}

	public void atualizarCampos() {

		textFTitulo.setText("");
		cbCategoria.setSelectedIndex(-1);
		textADuvida.setText("");
	}

	public void validarCampos() throws DevPerguntarException {

		String alerta = "";
		if (textFTitulo.getText().trim().isEmpty()) {
			alerta += "Insira um Titulo\n";
		}
		if (textADuvida.getText().trim().isEmpty()) {
			alerta += "Insira uma Duvida\n";
		}
		if (cbCategoria.getSelectedIndex() == -1) {
			alerta += "Selecione uma Categoria\n";
		}

		if (!alerta.isEmpty()) {
			// lança exception
			alerta = "Causa : \n" + alerta;
			throw new DevPerguntarException(alerta);
		}

	}

}
