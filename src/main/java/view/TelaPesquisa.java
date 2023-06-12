package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TelaPesquisa extends JPanel {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	


	/**
	 * Create the panel.
	 */
	public TelaPesquisa() {
		setLayout(null);
		
		JComboBox cbCategorias = new JComboBox();
		cbCategorias.setToolTipText("");
		cbCategorias.setBounds(393, 31, 130, 22);
		add(cbCategorias);
		
		JLabel lblNewLabel = new JLabel("Status");
		lblNewLabel.setBounds(107, 35, 46, 14);
		add(lblNewLabel);
		
		JLabel lblTitulo = new JLabel("titulo");
		lblTitulo.setBounds(107, 79, 46, 14);
		add(lblTitulo);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(170, 76, 86, 20);
		add(textField_1);
		
		JLabel lblDescricao = new JLabel("descricao");
		lblDescricao.setBounds(107, 127, 46, 14);
		add(lblDescricao);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(170, 124, 86, 20);
		add(textField_2);
		
		JLabel lblDataInicial = new JLabel("De");
		lblDataInicial.setBounds(107, 184, 46, 14);
		add(lblDataInicial);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(170, 181, 86, 20);
		add(textField_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Até");
		lblNewLabel_3_1.setBounds(327, 187, 46, 14);
		add(lblNewLabel_3_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(390, 184, 86, 20);
		add(textField_4);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Intervalo de Datas de Criação");
		lblNewLabel_3_1_1.setBounds(249, 155, 168, 14);
		add(lblNewLabel_3_1_1);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(266, 235, 89, 23);
		add(btnPesquisar);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(314, 35, 69, 14);
		add(lblCategorias);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Resolvido");
		chckbxNewCheckBox.setBounds(170, 31, 97, 23);
		add(chckbxNewCheckBox);
		
		table = new JTable();
		table.setBounds(107, 283, 444, 161);
		add(table);
		
		
		
		
	}
}
