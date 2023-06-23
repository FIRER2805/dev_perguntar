package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import model.vo.Pergunta;

public class TelaPergunta extends JPanel {

	Pergunta pergunta;
	private JLabel lblAutor;
	private JLabel lblCategoria;
	private JLabel lblTitulo;
	private JTextArea textADescricao;
	private JLabel lblDtCriacao;
	private JLabel lblDtResolucao;

	/**
	 * Create the panel.
	 */
	public TelaPergunta() {


		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(75dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		lblAutor = new JLabel("Autor :");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAutor, "4, 4, left, default");

		lblCategoria = new JLabel("Categoria : ");
		add(lblCategoria, "6, 4, right, default");

		lblTitulo = new JLabel("Titulo : ");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo, "4, 6");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 8, 3, 1, fill, fill");

//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		textADescricao = new JTextArea();
		textADescricao.setEnabled(false);
		scrollPane.setViewportView(textADescricao);

		lblDtCriacao = new JLabel("Data de Criação :");
		add(lblDtCriacao, "4, 10");

		lblDtResolucao = new JLabel("data resolucao");
		add(lblDtResolucao, "6, 10, right, default");
	}

	public void atualizarCampos(Pergunta pergunta) {

		// autor catehoria titulo descricao data
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

}
