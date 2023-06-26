package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.Sizes;
import javax.swing.JTable;

public class TelaMinhasPerguntas extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TelaMinhasPerguntas() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("100dlu", false), Sizes.constant("120dlu", false)), 0),
				FormSpecs.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("100dlu", false), Sizes.constant("120dlu", false)), 0),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		JLabel lblNewLabel = new JLabel("Minhas Perguntas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNewLabel, "4, 4, center, default");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 8, 1, 3, fill, fill");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVizualizar = new JButton("Vizualizar");
		add(btnVizualizar, "6, 8");
		
		JButton btnExcluir = new JButton("Excluir");
		add(btnExcluir, "6, 10");

	}

}
