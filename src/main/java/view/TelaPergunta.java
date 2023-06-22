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
	
	/**
	 * Create the panel.
	 */
	public TelaPergunta(Pergunta pergunta) {
		
		this.pergunta = pergunta;
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
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
				RowSpec.decode("max(75dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblAutor = new JLabel(pergunta.getUsuario().getNome());
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAutor, "4, 4, left, default");
		
		JLabel lblCategoria = new JLabel(pergunta.getCategoria().getNome());
		add(lblCategoria, "6, 4, right, default");
		
		JLabel lblTitulo = new JLabel(pergunta.getTitulo());
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo, "4, 6");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 8, 3, 1, fill, fill");
		
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JTextArea textADescricao = new JTextArea(pergunta.getConteudo());
		textADescricao.setEnabled(false);
		scrollPane.setViewportView(textADescricao);
		
		JLabel lblDtCriacao = new JLabel(pergunta.getData().toString());
		add(lblDtCriacao, "4, 10");
		
		JLabel lblDtResolucao = new JLabel("data resolucao");
		add(lblDtResolucao, "6, 10, right, default");
	}
}
