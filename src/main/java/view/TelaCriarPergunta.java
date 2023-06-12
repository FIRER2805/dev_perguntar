package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class TelaCriarPergunta extends JPanel {
	private JTextField txtFTitulo;

	/**
	 * Create the panel.
	 */
	public TelaCriarPergunta() {
		setLayout(null);
		
		JLabel lblCriarPergunta = new JLabel("Criar Pergunta");
		lblCriarPergunta.setBounds(267, 49, 70, 23);
		add(lblCriarPergunta);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(133, 109, 59, 14);
		add(lblTitulo);
		
		txtFTitulo = new JTextField();
		txtFTitulo.setBounds(202, 106, 86, 20);
		add(txtFTitulo);
		txtFTitulo.setColumns(10);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(366, 109, 70, 14);
		add(lblCategorias);

		JComboBox cbCategorias = new JComboBox();
		cbCategorias.setToolTipText("");
		cbCategorias.setBounds(446, 105, 95, 22);
		add(cbCategorias);
		
		JLabel lblDuvida = new JLabel("Duvida");
		lblDuvida.setBounds(133, 168, 59, 14);
		add(lblDuvida);
		
		JTextPane txtPDuvida = new JTextPane();
		txtPDuvida.setBounds(213, 168, 218, 186);
		add(txtPDuvida);
		
		JButton btnPostarDuvida = new JButton("Postar");
		btnPostarDuvida.setBounds(241, 376, 89, 23);
		add(btnPostarDuvida);
		
		
	}
}
