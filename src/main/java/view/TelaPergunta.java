package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.RespostaController;
import model.dao.RespostaDAO;
import model.exception.DevPerguntarException;
import model.vo.Pergunta;
import model.vo.Resposta;

public class TelaPergunta extends JPanel {
	
	private int idPergunta;
	private Pergunta pergunta;
	private JLabel lblAutor;
	private JLabel lblCategoria;
	private JLabel lblTitulo;
	private JTextArea textADescricao;
	private JLabel lblDtCriacao;
	private JLabel lblDtResolucao;
	private JTextField textResposta;
	private JLabel lblSuaResposta;
	private JButton btnPublicar;
	private JLabel lblNewLabel;
	private JTable table;
	private ArrayList<Resposta> respostas;
	private String[] nomesColunas = {"respostas"};
	private RespostaDAO respostaDAO;
	private ArrayList<DefaultMutableTreeNode> arvoresResposta;

	/**
	 * Create the panel.
	 */
	public TelaPergunta() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("84px"),
				ColumnSpec.decode("300px"),
				ColumnSpec.decode("140px"),
				ColumnSpec.decode("160px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("31px"),
				RowSpec.decode("17px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("113px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("82px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("34px"),
				RowSpec.decode("136px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		lblAutor = new JLabel("Autor :");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAutor, "2, 2, left, top");

		lblCategoria = new JLabel("Categoria : ");
		add(lblCategoria, "4, 2, fill, center");

		lblTitulo = new JLabel("Titulo : ");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo, "2, 4, fill, top");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 6, 3, 1, fill, fill");

		textADescricao = new JTextArea();
		textADescricao.setEnabled(false);
		scrollPane.setViewportView(textADescricao);

		lblDtCriacao = new JLabel("Data de Criação :");
		add(lblDtCriacao, "2, 8, fill, fill");

		lblDtResolucao = new JLabel("data resolucao");
		add(lblDtResolucao, "4, 8, fill, fill");
		
		textResposta = new JTextField();
		add(textResposta, "2, 12, 3, 1, fill, fill");
		textResposta.setColumns(10);
		
		
		
		lblSuaResposta = new JLabel("Sua Resposta:");
		add(lblSuaResposta, "2, 10, left, fill");
		
		btnPublicar = new JButton("Publicar");
		add(btnPublicar, "4, 14, left, top");
		
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RespostaController respostaController = new RespostaController();
				Resposta resposta = new Resposta();
				resposta.setConteudo(textResposta.getText());
				resposta.setIdPergunta(pergunta.getId());
				resposta.setSolucao(false);
				try {
					respostaController.inserir(resposta);
				} catch (DevPerguntarException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "Erro:",JOptionPane.ERROR_MESSAGE);
				}
				//preencherTabela();
		}});
		
		lblNewLabel = new JLabel("Respostas");
		add(lblNewLabel, "2, 14, left, bottom");
		
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		table.setRowHeight(50);
		add(table, "2, 15, 3, 1, fill, fill");
		
		limparTabela();
		
		 respostaDAO = new RespostaDAO();
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public void atualizarCampos(Pergunta pergunta) {
		idPergunta = pergunta.getId();
		
		// autor categoria titulo descricao data
		lblAutor.setText("Autor : " + pergunta.getUsuario().getNome());
		lblCategoria.setText("Categoria : " + pergunta.getCategoria().getNome());
		lblTitulo.setText(pergunta.getTitulo());
		textADescricao.setText(pergunta.getConteudo());
		lblDtCriacao.setText("Data de Criação : " + pergunta.getData().toString());
		if(pergunta.getDataResolucao() == null) {
			lblDtResolucao.setText("");
		}else {
			lblDtResolucao.setText("Data de Resolução : " + pergunta.getDataResolucao());
		}
	}
	
//	public void preencherTabela() {
//		respostas = respostaDAO.buscarTodos(idPergunta);
//		
//		limparTabela();
//		
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//
//		for (Resposta r : respostas) {
//
//			Object[] novaLinhaDaTabela = new Object[1];
//
//			novaLinhaDaTabela[0] = r.getConteudo();
//
//			model.addRow(novaLinhaDaTabela);
//		}
//		
//	}
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {,}, nomesColunas));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
