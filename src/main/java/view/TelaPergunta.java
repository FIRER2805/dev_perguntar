package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.RespostaController;
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
	private JTextField textResposta;
	private JLabel lblSuaResposta;
	private JButton btnPublicar;
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
		
		textResposta = new JTextField();
		textResposta.setBounds(87, 245, 526, 82);
		add(textResposta);
		textResposta.setColumns(10);
		
		
		
		lblSuaResposta = new JLabel("Sua Resposta:");
		lblSuaResposta.setBounds(84, 224, 89, 14);
		add(lblSuaResposta);
		
		btnPublicar = new JButton("Publicar");
		btnPublicar.setBounds(524, 338, 89, 23);
		add(btnPublicar);
		
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RespostaController respostaController = new RespostaController();
				Resposta resposta = new Resposta();
				resposta.setConteudo(textResposta.getText());
				resposta.setIdPergunta(pergunta.getId());
				resposta.setSolucao(false);
				respostaController.inserir(resposta);
		}});
		
		lblNewLabel = new JLabel("Respostas");
		lblNewLabel.setBounds(87, 358, 86, 14);
		add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(87, 372, 527, 136);
		add(table);
		
		limparTabela();
		
		RespostaDAO respostaDAO = new RespostaDAO();
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
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
