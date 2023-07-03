package view;

import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.UsuarioController;
import model.vo.PesquisaUsuario;
import model.vo.Usuario;

public class TelaPesquisaUsuario extends JPanel {
	private JTextField txtBarraBusca;
	private String[] tipoPesquisa = { "Nome", "Pergunta", "Resposta", "Solução" };
	private String[] ordemPesquisa = { "Crescente", "Decrescente" };
	private PesquisaUsuario pesquisaUsuario = new PesquisaUsuario();
	private String[] nomesColunas = { "Titulo ", "DT-Criação", "Status", "Usuario", "Categoria" };
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private JTable table;
	private JCheckBox chckbxTemPergunta;
	private JCheckBox chckbxTemResposta;
	private JCheckBox chckbxTemSolucao;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	UsuarioController uCont = new UsuarioController();

	public TelaPesquisaUsuario() {
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblTituloPagina = new JLabel("Pesquisar Por Usuario");
		lblTituloPagina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTituloPagina, "4, 4, 5, 1, center, default");

		txtBarraBusca = new JTextField();
		add(txtBarraBusca, "4, 8, 3, 1, fill, default");
		txtBarraBusca.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		add(btnBuscar, "8, 8");

		chckbxTemPergunta = new JCheckBox("Tem Perguntas");
		add(chckbxTemPergunta, "4, 12");

		chckbxTemResposta = new JCheckBox("Tem Respostas");
		add(chckbxTemResposta, "6, 12");

		chckbxTemSolucao = new JCheckBox("Tem Solução");
		add(chckbxTemSolucao, "8, 12");

		JLabel lblOrdem = new JLabel("Ordenar por :");
		add(lblOrdem, "4, 14, left, default");

		comboBox_1 = new JComboBox(tipoPesquisa);
		add(comboBox_1, "6, 14, fill, default");

		comboBox = new JComboBox(ordemPesquisa);
		add(comboBox, "8, 14, fill, default");

		table = new JTable();
		add(table, "4, 18, 5, 1, fill, fill");
		limparTabela();
	}

	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	public void atualizarTable() {
		limparTabela();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		usuarios = p.busca();

		for (Usuario p : usuarios) {

			Object[] novaLinhaDaTabela = new Object[5];

//			novaLinhaDaTabela[0] = p.getTitulo();
//			novaLinhaDaTabela[1] = p.getData().format(formatador);
//			novaLinhaDaTabela[2] = p.getDataResolucao() == null ? "Em Aberto" : "Resolvido";
//			novaLinhaDaTabela[3] = p.getUsuario().getNome();
//			novaLinhaDaTabela[4] = p.getCategoria().getNome();

			model.addRow(novaLinhaDaTabela);
		}

	}

	public void buscar() {
		pesquisaUsuario.setBusca(txtBarraBusca.getText());
		pesquisaUsuario.setTemPergunta(chckbxTemPergunta.isSelected());
		pesquisaUsuario.setTemresposta(chckbxTemResposta.isSelected());
		pesquisaUsuario.setTemSolucao(chckbxTemSolucao.isSelected());
		pesquisaUsuario.setOrdemPesquisa(comboBox.getSelectedItem().toString() +""+ comboBox_1.getSelectedItem().toString());
		
		if(!uCont.pesquisarUsuario(usuarios, pesquisaUsuario)) {
			JOptionPane.showMessageDialog(null, "Falha ao Conectar Com o Servidor");
		}

	}

}
