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

import controller.RespostaController;
import model.dao.RespostaDAO;
import model.exception.DevPerguntarException;
import model.vo.Pergunta;
import model.vo.Resposta;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class TelaPergunta extends JPanel {
	
	private int idPergunta;
	private Pergunta pergunta;
	private JLabel lblAutor;
	private JLabel lblCategoria;
	private JLabel lblTitulo;
	private JTextArea textADescricao;
	private JLabel lblDtCriacao;
	private JLabel lblDtResolucao;
	private JLabel lblSuaResposta;
	private JButton btnPublicar;
	private JTable table;
	private ArrayList<Resposta> respostas;
	private String[] nomesColunas = {"respostas"};
	private RespostaDAO respostaDAO;
	private JTextArea txtAResposta;
	private JButton btnMarcarResolucao;
	private JScrollPane scrollPane;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public TelaPergunta() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(175dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(175dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("17px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(53dlu;default)"),}));

		lblAutor = new JLabel("Autor :");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAutor, "3, 3, left, top");

		lblCategoria = new JLabel("Categoria : ");
		add(lblCategoria, "5, 3, right, fill");

		lblTitulo = new JLabel("Titulo : ");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo, "3, 5, fill, top");
		
				textADescricao = new JTextArea();
				textADescricao.setEditable(false);
				add(textADescricao, "3, 7, 3, 1");

		lblDtCriacao = new JLabel("Data de Criação :");
		add(lblDtCriacao, "3, 9, fill, fill");

		lblDtResolucao = new JLabel("data resolucao");
		add(lblDtResolucao, "5, 9, right, fill");
		
		
		lblSuaResposta = new JLabel("Sua Resposta:");
		add(lblSuaResposta, "3, 11, left, fill");
		
		txtAResposta = new JTextArea();
		txtAResposta.setLineWrap(true);
		add(txtAResposta, "3, 13, 3, 1, fill, fill");
		
		btnPublicar = new JButton("Publicar");
		add(btnPublicar, "3, 15, 3, 1, center, fill");
		
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RespostaController respostaController = new RespostaController();
				Resposta resposta = new Resposta();
				resposta.setConteudo(txtAResposta.getText());
				resposta.setIdPergunta(pergunta.getId());
				resposta.setSolucao(false);
				try {
					respostaController.inserir(resposta);
				} catch (DevPerguntarException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage(), "Erro:",JOptionPane.ERROR_MESSAGE);
				}
				preencherTabela();
		}});
		
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		
		add(table, "3, 17, 3, 1, fill, fill");
		
		btnMarcarResolucao = new JButton("Marcar Resposta Como Resolução");
		add(btnMarcarResolucao, "3, 19, 3, 1, center, default");
		
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
		lblTitulo.setText("Titulo : "+pergunta.getTitulo());
		textADescricao.setText(pergunta.getConteudo());
		lblDtCriacao.setText("Data de Criação : " + pergunta.getData().toString());
		if(pergunta.getDataResolucao() == null) {
			lblDtResolucao.setText("");
		}else {
			lblDtResolucao.setText("Data de Resolução : " + pergunta.getDataResolucao());
		}
	}
	
	public void preencherTabela() {
		respostas = respostaDAO.buscarTodos(idPergunta);
		
		limparTabela();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();


		for (Resposta r : respostas) {

			Object[] novaLinhaDaTabela = new Object[1];

			novaLinhaDaTabela[0] = r.getConteudo();

			model.addRow(novaLinhaDaTabela);
		}
		
	}
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {,}, nomesColunas));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
