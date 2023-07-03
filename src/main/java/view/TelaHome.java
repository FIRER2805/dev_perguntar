package view;

import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

import controller.PerguntaController;
import model.exception.DevPerguntarException;
import model.vo.Pergunta;

public class TelaHome extends JPanel {

	private JTable table;
	private PerguntaController p = new PerguntaController();
	private JButton btnVizualizar;
	private String[] nomesColunas = { "Titulo ", "DT-Criação", "Status", "Usuario", "Categoria" };
	private ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();

	public JButton getBtnVizualizar() {
		return btnVizualizar;
	}

	/**
	 * Create the panel.
	 */
	public TelaHome() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(350dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow(9)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("40dlu", false), Sizes.constant("50dlu", false)), 1),}));

		JLabel lblTitulo = new JLabel("Duvidas Recentes");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo, "4, 4, center, default");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 6, fill, default");

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		scrollPane.setViewportView(table);

		btnVizualizar = new JButton("Vizualizar");
		add(btnVizualizar, "4, 10, center, center");

		atualizarTable();

	}

	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {,}, nomesColunas));
	}

	public void atualizarTable() {
		limparTabela();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		perguntas = p.busca();

		for (Pergunta p : this.perguntas) {

			Object[] novaLinhaDaTabela = new Object[5];

			novaLinhaDaTabela[0] = p.getTitulo();
			novaLinhaDaTabela[1] = p.getData().format(formatador);
			novaLinhaDaTabela[2] = p.getDataResolucao() == null ? "Em Aberto" : "Resolvido";
			novaLinhaDaTabela[3] = p.getUsuario().getNome();
			novaLinhaDaTabela[4] = p.getCategoria().getNome();

			model.addRow(novaLinhaDaTabela);
		}

	}

	public Pergunta resgatarPergunta() throws DevPerguntarException {

		int linhaSelecionada = table.getSelectedRow();
		if (linhaSelecionada == -1) {
			throw new DevPerguntarException("Selecione uma Pergunta");
		}
		Pergunta pergunta = perguntas.get(linhaSelecionada);
		return pergunta;

	}

}
