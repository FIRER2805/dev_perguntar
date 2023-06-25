package view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.dao.RespostaDAO;
import model.vo.Pergunta;
import model.vo.Resposta;

public class TelaPergunta extends JPanel {

	private Pergunta pergunta;
	private JLabel lblAutor;
	private JLabel lblCategoria;
	private JLabel lblTitulo;
	private JTextArea textADescricao;
	private JLabel lblDtCriacao;
	private JLabel lblDtResolucao;
	private JTextField textField;
	private JLabel lblSuaResposta;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JTable table;
	private String[] nomesColunas = {"respostas"};

	/**
	 * Create the panel.
	 */
	public TelaPergunta() {
		setLayout(null);

		lblAutor = new JLabel("Autor :");
		lblAutor.setBounds(87, 31, 136, 17);
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAutor);

		lblCategoria = new JLabel("Categoria : ");
		lblCategoria.setBounds(556, 32, 128, 14);
		add(lblCategoria);

		lblTitulo = new JLabel("Titulo : ");
		lblTitulo.setBounds(87, 54, 297, 20);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 80, 526, 113);
		add(scrollPane);

//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		textADescricao = new JTextArea();
		textADescricao.setEnabled(false);
		scrollPane.setViewportView(textADescricao);

		lblDtCriacao = new JLabel("Data de Criação :");
		lblDtCriacao.setBounds(87, 199, 297, 14);
		add(lblDtCriacao);

		lblDtResolucao = new JLabel("data resolucao");
		lblDtResolucao.setBounds(542, 199, 142, 14);
		add(lblDtResolucao);
		
		textField = new JTextField();
		textField.setBounds(87, 245, 526, 82);
		add(textField);
		textField.setColumns(10);
		
		lblSuaResposta = new JLabel("Sua Resposta:");
		lblSuaResposta.setBounds(84, 224, 89, 14);
		add(lblSuaResposta);
		
		btnNewButton = new JButton("Publicar");
		btnNewButton.setBounds(524, 338, 89, 23);
		add(btnNewButton);
		
		lblNewLabel = new JLabel("Respostas");
		lblNewLabel.setBounds(87, 358, 86, 14);
		add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(87, 372, 527, 136);
		add(table);
		
		limparTabela();
		
		RespostaDAO respostaDAO = new RespostaDAO();
	}

	public void atualizarCampos(Pergunta pergunta) {

		// autor categoria titulo descricao data
		lblAutor.setText("Autor : " + pergunta.getUsuario().getNome());
		lblCategoria.setText("Categoria : " + pergunta.getCategoria().getNome());
		lblTitulo.setText(" " + pergunta.getTitulo());
		textADescricao.setText(pergunta.getConteudo());
		lblDtCriacao.setText("Data de Criação : " + pergunta.getData().toString());
		if(pergunta.getDataResolucao() == null) {
			lblDtResolucao.setText("");
		}else {
			lblDtResolucao.setText("Data de Resolução : " + pergunta.getDataResolucao());
		}
	}
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {,}, nomesColunas));
	}
}
