package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
	private JLabel lblSuaResposta;
	private JButton btnPublicar;
	private ArrayList<Resposta> respostas;
	private String[] nomesColunas = {"respostas"};
	private RespostaDAO respostaDAO;
	private ArrayList<DefaultMutableTreeNode> arvoresResposta;
	private JScrollPane scrollPaneArvores;
	private JTextArea textResposta;
	private JScrollPane scrollPane;
	private JTree tree;

	/**
	 * Create the panel.
	 */
	public TelaPergunta() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(175dlu;min):grow"),
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
				RowSpec.decode("136px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		lblAutor = new JLabel("Autor :");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAutor, "3, 3, left, top");

		lblCategoria = new JLabel("Categoria : ");
		add(lblCategoria, "5, 3, right, center");

		lblTitulo = new JLabel("Titulo : ");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo, "3, 5, fill, top");

		textADescricao = new JTextArea();
		textADescricao.setEditable(false);
		add(textADescricao, "3, 7, 3, 1, fill, fill");

		lblDtCriacao = new JLabel("Data de Criação :");
		add(lblDtCriacao, "3, 9, fill, fill");

		lblDtResolucao = new JLabel("data resolucao");
		add(lblDtResolucao, "5, 9, right, fill");
		
		lblSuaResposta = new JLabel("Sua Resposta:");
		add(lblSuaResposta, "3, 11, left, fill");
		
		textResposta = new JTextArea();
		add(textResposta, "3, 13, 3, 1, fill, fill");
		
		btnPublicar = new JButton("Publicar");
		add(btnPublicar, "3, 15, 3, 1, center, center");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "3, 17, 3, 1, fill, fill");
		
		tree = new JTree();
		scrollPane.setViewportView(tree);
		
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
		}});
		
		scrollPaneArvores = new JScrollPane();
		
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
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		idPergunta = pergunta.getId();
		
		lblAutor.setText("Autor : " + pergunta.getUsuario().getNome());
		lblCategoria.setText("Categoria : " + pergunta.getCategoria().getNome());
		lblTitulo.setText(pergunta.getTitulo());
		textADescricao.setText(pergunta.getData().format(formatador));
		lblDtCriacao.setText("Data de Criação : " + pergunta.getData().format(formatador));
		if(pergunta.getDataResolucao() == null) {
			lblDtResolucao.setText("");
		}else {
			lblDtResolucao.setText("Data de Resolução : " + pergunta.getDataResolucao());
		}
	}
	
	
	private void limparTabela() {
		scrollPaneArvores.removeAll();
	}
	
	public void mostrarPergutas()
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Respostas");
		arvoresResposta = (ArrayList<DefaultMutableTreeNode>) respostaDAO.montaArvoresResposta(idPergunta);
		 for(DefaultMutableTreeNode arvore : arvoresResposta)
		 {
			 root.add(arvore);
		 }
		 

			tree.setModel(new DefaultTreeModel(root));
			
		 
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
