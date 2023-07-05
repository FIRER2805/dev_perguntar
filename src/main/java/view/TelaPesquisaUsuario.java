package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import model.exception.DevPerguntarException;
import model.vo.PesquisaUsuario;
import model.vo.Usuario;

public class TelaPesquisaUsuario extends JPanel {
	private JTextField txtBarraBusca;
	private String[] tipoPesquisa = { "Nome", "Pergunta", "Resposta", "Solucao" };
	private String[] ordemPesquisa = { "Crescente", "Decrescente" };
	private PesquisaUsuario pesquisaUsuario = new PesquisaUsuario();
	private String[] nomesColunas = { "Nome", "Num. de Pergunta", "Num. de Resposta", "Num. de Solução"};
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private JTable table;
	private JCheckBox chckbxTemPergunta;
	private JCheckBox chckbxTemResposta;
	private JCheckBox chckbxTemSolucao;
	private JComboBox cBTipo;
	private JComboBox cBSentido;
	UsuarioController uCont = new UsuarioController();
	private JButton btnGerarExcel;

	public TelaPesquisaUsuario() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(116dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(116dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(116dlu;min)"),
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		JLabel lblTituloPagina = new JLabel("Pesquisar Por Usuario");
		lblTituloPagina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTituloPagina, "4, 4, 5, 1, center, default");

		txtBarraBusca = new JTextField();
		add(txtBarraBusca, "4, 8, 3, 1, fill, default");
		txtBarraBusca.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTable();
				btnGerarExcel.setVisible(true);
			}
		});
		add(btnBuscar, "8, 8");
		btnBuscar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		
		btnGerarExcel = new JButton("Gerar Excel");
		btnGerarExcel.setVisible(false);
		btnGerarExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para a planilha...");

				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();
					String resultado;
					try {
						resultado = uCont.gerarPlanilha(usuarios, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (DevPerguntarException erro) {
						JOptionPane.showConfirmDialog(null, erro.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}
		});
		add(btnGerarExcel, "8, 10");

		chckbxTemPergunta = new JCheckBox("Tem Perguntas");
		add(chckbxTemPergunta, "4, 14");

		chckbxTemResposta = new JCheckBox("Tem Respostas");
		add(chckbxTemResposta, "6, 14, center, default");

		chckbxTemSolucao = new JCheckBox("Tem Solução");
		add(chckbxTemSolucao, "8, 14, right, default");

		JLabel lblOrdem = new JLabel("Ordenar por :");
		add(lblOrdem, "4, 16, left, default");

		cBTipo = new JComboBox(tipoPesquisa);
		add(cBTipo, "6, 16, fill, default");

		cBSentido = new JComboBox(ordemPesquisa);
		add(cBSentido, "8, 16, fill, default");

		table = new JTable();
		add(table, "4, 20, 5, 1, fill, fill");
		limparTabela();
	}

	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	public void atualizarTable() {
		limparTabela();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		buscar();

		for (Usuario u : usuarios) {

			Object[] novaLinhaDaTabela = new Object[5];

			novaLinhaDaTabela[0] = u.getNome();
			novaLinhaDaTabela[1] = u.getNumPergunta();
			novaLinhaDaTabela[2] = u.getNumResposta();
			novaLinhaDaTabela[3] = u.getNumsolucao();
			
			model.addRow(novaLinhaDaTabela);
		}

	}

	public void buscar() {
		pesquisaUsuario.setBusca(txtBarraBusca.getText());
		pesquisaUsuario.setTemPergunta(chckbxTemPergunta.isSelected());
		pesquisaUsuario.setTemresposta(chckbxTemResposta.isSelected());
		pesquisaUsuario.setTemSolucao(chckbxTemSolucao.isSelected());
		if(cBSentido.getSelectedItem().toString().equals("Crescente")) {
			pesquisaUsuario.setOrdemPesquisa(cBTipo.getSelectedItem().toString() + " asc ");
		}else {
			pesquisaUsuario.setOrdemPesquisa(cBTipo.getSelectedItem().toString() + " desc ");
		}
		
		try 
		{
			usuarios = uCont.pesquisarUsuario(pesquisaUsuario);
			atualizarTable();
		}
		catch(DevPerguntarException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
