package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.PerguntaController;
import model.dao.CategoriaDAO;
import model.exception.DevPerguntarException;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Pesquisa;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class TelaPesquisa extends JPanel {

	private Pesquisa pesquisa = new Pesquisa();
	private PerguntaController perguntaController = new PerguntaController();
	private JButton btnBusca;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private String[] nomesColunas = { "Titulo ", "DT-Criação", "Status", "Usuario", "Categoria" };
	public JButton getBtnVisualizar() {
		return btnVisualizar;
	}

	private JTextField textFDuvida;
	private JTable table;
	private ArrayList<Pergunta> perguntas;
	private JButton btnVisualizar;
	private JRadioButton cbxResolvido;
	private JComboBox cbxCategorias;
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */
	public TelaPesquisa() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(118dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("150dlu", false), Sizes.constant("180dlu", false)), 0),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default:grow"),}));

		ArrayList<Categoria> categorias = categoriaDAO.buscarTodas();

		JLabel lblTituloPagina = new JLabel("Consultar Oraculo");
		add(lblTituloPagina, "5, 3, 5, 1, center, top");

		JLabel lblNewLabel = new JLabel("Duvida : ");
		add(lblNewLabel, "3, 5, right, center");

		textFDuvida = new JTextField();
		add(textFDuvida, "5, 5, 5, 1, fill, fill");
		textFDuvida.setColumns(10);
		
				btnBusca = new JButton("Buscar");
				add(btnBusca, "11, 5, fill, fill");
				
						btnBusca.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Categoria categoriaSelecionada = (Categoria)cbxCategorias.getSelectedItem();
								perguntas = perguntaController.busca(textFDuvida.getText(), categoriaSelecionada.getNome(), cbxResolvido.isSelected());
								
								limparTabela();
								
								DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				
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
						});
		
		cbxResolvido = new JRadioButton("Resolvido");
		add(cbxResolvido, "5, 7, left, default");
		
		lblNewLabel_1 = new JLabel("Categoria:");
		add(lblNewLabel_1, "9, 7, right, default");
		
		cbxCategorias = new JComboBox(categoriaDAO.buscarTodas().toArray());
		add(cbxCategorias, "11, 7, left, default");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "3, 9, 9, 1, fill, fill");

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		scrollPane.setViewportView(table);
								
										btnVisualizar = new JButton("visualizar");
										add(btnVisualizar, "7, 11, fill, center");
		atualizarCampos();
	}

	public void pesquisar() {
		Categoria categoria = (Categoria)cbxCategorias.getSelectedItem();
		perguntaController.busca(textFDuvida.getText(), categoria.getNome(), cbxResolvido.isSelected());
	}

	public void atualizarCampos() {
		pesquisa = new Pesquisa();
	}

	public Pergunta resgatarPergunta() throws DevPerguntarException {

		int linhaSelecionada = table.getSelectedRow();
		if (linhaSelecionada == -1) {
			throw new DevPerguntarException("Selecione uma Pergunta");
		}
		Pergunta pergunta = perguntas.get(linhaSelecionada);
		return pergunta;

	}
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {,}, nomesColunas));
	}

}
