package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
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
				RowSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default:grow"),}));
		
		JLabel lblTituloPagina = new JLabel("Criar Pergunta");
		lblTituloPagina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTituloPagina, "6, 6, 7, 1, center, default");
		
		JLabel lblTitulo = new JLabel("Titulo :");
		add(lblTitulo, "6, 10, right, default");
		
		textFTitulo = new JTextField();
		add(textFTitulo, "8, 10, left, default");
		textFTitulo.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria :");
		add(lblCategoria, "10, 10, right, default");
		
		ArrayList<Categoria> categorias = categoriaDAO.buscarTodas();
		
		cbCategoria = new JComboBox(categorias.toArray());
		cbCategoria.setBackground(SystemColor.activeCaption);
		cbCategoria.setSelectedIndex(-1);
		add(cbCategoria, "12, 10, fill, default");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "6, 14, 7, 1, fill, fill");
		
		textADuvida = new JTextArea();
		scrollPane.setViewportView(textADuvida);
		
		 btnPostarPergunta = new JButton("Postar Pergunta");
		add(btnPostarPergunta, "6, 18, 7, 1, center, default");
		

	}
	
	
	
	public void cadastrarPergunta(Usuario usuario) {
		
		Pergunta pergunta = new Pergunta();
		pergunta.setTitulo(textFTitulo.getText());
		pergunta.setConteudo(textADuvida.getText());
		Categoria categoria = new Categoria();
		categoria = (Categoria)cbCategoria.getSelectedItem();
		pergunta.setCategoria(categoria);
		pergunta.setUsuario(usuario);
		
		
		perguntaController.perguntar(pergunta);
		
	}



	
	
	
	
	
	
	
	
	
	
	
}
