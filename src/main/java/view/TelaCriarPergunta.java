package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;

public class TelaCriarPergunta extends JPanel {
	private JTextField textFTitulo;

	/**
	 * Create the panel.
	 */
	public TelaCriarPergunta() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("bottom:default:grow"),
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
				RowSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default:grow"),}));
		
		JLabel lblTituloPagina = new JLabel("Criar Pergunta");
		lblTituloPagina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTituloPagina, "6, 6, 7, 1, center, default");
		
		JLabel lblTitulo = new JLabel("Titulo :");
		add(lblTitulo, "6, 10, right, default");
		
		textFTitulo = new JTextField();
		add(textFTitulo, "8, 10, left, default");
		textFTitulo.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria :");
		add(lblCategoria, "10, 10, right, default");
		
		JComboBox cbCategoria = new JComboBox();
		add(cbCategoria, "12, 10, left, default");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "6, 14, 7, 1, fill, fill");
		
		JTextArea textADuvida = new JTextArea();
		scrollPane.setViewportView(textADuvida);
		
		JButton btnPostarPergunta = new JButton("Postar Pergunta");
		add(btnPostarPergunta, "6, 18, 7, 1, center, default");
		
		
	}
}
