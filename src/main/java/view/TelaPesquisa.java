package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import model.dao.CategoriaDAO;
import model.vo.Categoria;
import model.vo.Pesquisa;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextPane;

public class TelaPesquisa extends JPanel {

	private Pesquisa pesquisa = new Pesquisa();
	private JTextField textFDataInicial;
	private JTextField textFDataFinal;
	private JTable table;
	private JCheckBox chckbxResolvido;
	private JComboBox cbCategoria;
	private JTextPane textPDuvida;
	private JButton btnPesquisar;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private String[] nomesColunas = { "Titulo ", "DT-Criação", "Usuario", "Categoria" };

	/**
	 * Create the panel.
	 */
	public TelaPesquisa() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
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
				RowSpec.decode("max(50dlu;default):grow"),
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
				RowSpec.decode("max(133dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default:grow"),}));

		JLabel lblTituloPagina = new JLabel("Consultar Oraculo");
		add(lblTituloPagina, "4, 4, 9, 1, center, fill");

		JLabel lblStatus = new JLabel("Status");
		add(lblStatus, "4, 8, default, fill");

		 chckbxResolvido = new JCheckBox("Resolvido");
		add(chckbxResolvido, "6, 8");

		JLabel lblCategoria = new JLabel("Categoria");
		add(lblCategoria, "10, 8");
		
		ArrayList<Categoria> categorias = categoriaDAO.buscarTodas();

		 cbCategoria = new JComboBox(categorias.toArray());
		add(cbCategoria, "12, 8, fill, default");

		JLabel lblTitulo = new JLabel("Duvida");
		add(lblTitulo, "4, 12, default, top");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "6, 12, 7, 1, fill, fill");
		
				 textPDuvida = new JTextPane();
				 scrollPane_1.setViewportView(textPDuvida);

		JLabel lblIntervaloDtCriacao = new JLabel("Intevalo de Data de Criação");
		add(lblIntervaloDtCriacao, "4, 16, 9, 1, center, fill");

		JLabel lblDataInicial = new JLabel("De :");
		add(lblDataInicial, "4, 18, default, fill");

		textFDataInicial = new JTextField();
		add(textFDataInicial, "6, 18, fill, default");
		textFDataInicial.setColumns(10);

		JLabel lblDataFinal = new JLabel("Até :");
		add(lblDataFinal, "10, 18");

		textFDataFinal = new JTextField();
		add(textFDataFinal, "12, 18, fill, default");
		textFDataFinal.setColumns(10);

		 btnPesquisar = new JButton("Pesquisar");
		add(btnPesquisar, "4, 22, 9, 1, center, fill");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 26, 9, 1, fill, fill");

		table = new JTable();
		scrollPane.setViewportView(table);
		atualizarCampos();

	}
	
	public JButton getBtnPesquisar() {
		return btnPesquisar;
	}



	public void pesquisar() {
		
		pesquisa.setCategoria((Categoria)cbCategoria.getSelectedItem());
		//TODO colocar campos de data com Mascara
		pesquisa.setDataFinal(null);
		pesquisa.setDataInicial(null);
		pesquisa.setDuvida(textPDuvida.getText());
		pesquisa.setResolvido(chckbxResolvido.isSelected());
//		perguntaController.busca(pesquisa);

	}

	public void atualizarCampos() {

		pesquisa = new Pesquisa() ;
		
//		chckbxResolvido.setContentAreaFilled(false);
		chckbxResolvido.setSelected(false);
		cbCategoria.setSelectedIndex(-1);
		textPDuvida.setText("");
		textFDataInicial.setText("");
		textFDataFinal.setText("");
		
		

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
