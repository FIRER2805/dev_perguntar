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
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

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
	private String[] nomesColunas = { "respostas" };
	private RespostaDAO respostaDAO;
	private ArrayList<DefaultMutableTreeNode> arvoresResposta;
	private JScrollPane scrollPaneArvores;
	private JTextArea textResposta;
	private JScrollPane scrollPane = new JScrollPane();
	private JTree tree = new JTree();
	private JButton btnRespondeResposta;
	private JButton btnResolucao;
	private JTextArea txtResponderResposta;
	private Resposta ultimaRespostaSelecionada;
	private RespostaController rCont = new RespostaController();

	/**
	 * Create the panel.
	 */
	public TelaPergunta() {
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(175dlu;min)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(175dlu;min)"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("17px"),
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("14px"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER,
								Sizes.bounded(Sizes.MINIMUM, Sizes.constant("100dlu", false),
										Sizes.constant("200dlu", false)),
								0),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		add(scrollPane, "3, 17, 3, 1, fill, fill");
		scrollPane.setViewportView(tree);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getNewLeadSelectionPath();

				if (path != null) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
					if (!selectedNode.getUserObject().equals("Respostas")) {
						ultimaRespostaSelecionada = (Resposta) selectedNode.getUserObject();

					}

				}
			}
		});

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

		btnResolucao = new JButton("Marcar Como Resolução");
		btnResolucao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = 0;
				try {
					validarSelecao();
					num = rCont.marcarRespostaComoSolucao(ultimaRespostaSelecionada);
					if (num > 0) {
						JOptionPane.showMessageDialog(null, "Resposta Marcada Como Resolucao");
					} else {
						JOptionPane.showMessageDialog(null, "Falha");
					}
					mostrarPergutas();
				} catch (DevPerguntarException erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Falha", JOptionPane.WARNING_MESSAGE);

				}

			}
		});
		add(btnResolucao, "3, 19, 3, 1, center, default");

		txtResponderResposta = new JTextArea();
		txtResponderResposta.setLineWrap(true);
		add(txtResponderResposta, "3, 23, 3, 1, fill, fill");

		btnRespondeResposta = new JButton("Responde a Resposta");
		btnRespondeResposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validarSelecao();
					Resposta resposta = responderResposta();
					validarCamposParaResponderResposta();
					if (rCont.inserirNaResposta(resposta, ultimaRespostaSelecionada.getId()) > 0) {
						JOptionPane.showMessageDialog(null, "Resposta Efetuada!");
						ultimaRespostaSelecionada = null;
						mostrarPergutas();
					}
				} catch (DevPerguntarException erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		add(btnRespondeResposta, "3, 25, 3, 1, center, default");

		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RespostaController respostaController = new RespostaController();
				Resposta resposta = new Resposta();
				try {
					resposta.setConteudo(textResposta.getText());
					resposta.setIdPergunta(pergunta.getId());
					resposta.setSolucao(false);
					respostaController.inserir(resposta);
					textResposta.setText("");
					JOptionPane.showMessageDialog(null, "Resposta Efetuada!");
					mostrarPergutas();
				} catch (DevPerguntarException erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

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
		textADescricao.setText(pergunta.getConteudo());
		lblDtCriacao.setText("Data de Criação : " + pergunta.getData().format(formatador));
		if (pergunta.getDataResolucao() == null) {
			lblDtResolucao.setText("");
		} else {
			lblDtResolucao.setText("Data de Resolução : " + pergunta.getDataResolucao());
		}
	}

	private void limparTabela() {
		scrollPaneArvores.removeAll();
	}

	public void mostrarPergutas() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Respostas");
		arvoresResposta = (ArrayList<DefaultMutableTreeNode>) respostaDAO.montaArvoresResposta(idPergunta);
		for (DefaultMutableTreeNode arvore : arvoresResposta) {
			root.add(arvore);
		}

		tree.setModel(new DefaultTreeModel(root));

	}

	public Resposta responderResposta() throws DevPerguntarException {
		if (txtResponderResposta.getText().isBlank()) {
			throw new DevPerguntarException("Preencha o Campo de Resposta");
		}
		Resposta r = new Resposta();
		r.setConteudo(txtResponderResposta.getText());
		r.setIdPergunta(idPergunta);

		return r;
	}

	public void validarCamposParaResponderResposta() {

	}

	public void validarSelecao() throws DevPerguntarException {

		if (ultimaRespostaSelecionada == null) {
			throw new DevPerguntarException("Selecione uma Resposta");
		}

	}

}
