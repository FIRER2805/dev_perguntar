package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class TelaMinhasPerguntas extends JPanel {
	private JTable table;
	private JButton btnVizualizar;
	private String[] nomesColunas = { "Titulo ", "DT-Criação", "Status", "Usuario", "Categoria" };
	private ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
	private PerguntaController pCont = new PerguntaController();

	/**
	 * Create the panel.
	 */
	public TelaMinhasPerguntas() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("100dlu", false), Sizes.constant("120dlu", false)), 0),
				FormSpecs.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("100dlu", false), Sizes.constant("120dlu", false)), 0),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		JLabel lblNewLabel = new JLabel("Minhas Perguntas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel, "4, 4, center, default");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 8, 1, 3, fill, fill");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnVizualizar = new JButton("Vizualizar");
		add(btnVizualizar, "6, 8");
		
		JButton btnExcluir = new JButton("Excluir");
		add(btnExcluir, "6, 10");

	}
	
	
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {,}, nomesColunas));
	}

	public void atualizarTable() {
		limparTabela();

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		perguntas = pCont.busca();

		for (Pergunta p : this.perguntas) {

			Object[] novaLinhaDaTabela = new Object[5];

			novaLinhaDaTabela[0] = p.getTitulo();
			novaLinhaDaTabela[1] = p.getData();
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
	
	
	public void excluirPerguntaButao() {
		
//		PerguntaController pCont = new PerguntaController();	
//		Pergunta p = new Pergunta();
//		//TODO criar metodo Excluir
////		pCont.excluir(p);
	}
	
	
	
	
	
	
	
	
	
	

}
