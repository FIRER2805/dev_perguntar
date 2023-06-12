package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaPesquisa extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	


	/**
	 * Create the panel.
	 */
	public TelaPesquisa() {
		setLayout(null);
		
		JComboBox cbCategorias = new JComboBox();
		cbCategorias.setToolTipText("");
		cbCategorias.setBounds(393, 66, 130, 22);
		add(cbCategorias);
		
		JLabel lblNewLabel = new JLabel("autor");
		lblNewLabel.setBounds(107, 70, 46, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(170, 67, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblTitulo = new JLabel("titulo");
		lblTitulo.setBounds(107, 114, 46, 14);
		add(lblTitulo);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(170, 111, 86, 20);
		add(textField_1);
		
		JLabel lblDescricao = new JLabel("descricao");
		lblDescricao.setBounds(107, 162, 46, 14);
		add(lblDescricao);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(170, 159, 86, 20);
		add(textField_2);
		
		JLabel lblDataInicial = new JLabel("De");
		lblDataInicial.setBounds(107, 219, 46, 14);
		add(lblDataInicial);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(170, 216, 86, 20);
		add(textField_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Até");
		lblNewLabel_3_1.setBounds(327, 222, 46, 14);
		add(lblNewLabel_3_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(390, 219, 86, 20);
		add(textField_4);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Intervalo de Datas");
		lblNewLabel_3_1_1.setBounds(249, 190, 124, 14);
		add(lblNewLabel_3_1_1);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(266, 270, 89, 23);
		add(btnPesquisar);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(314, 70, 69, 14);
		add(lblCategorias);
		
		
		
		
	}
}
