package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.PerguntaController;
import model.dao.CategoriaDAO;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Pesquisa;

public class TelaPesquisa extends JPanel {

	private Pesquisa pesquisa = new Pesquisa();
	private PerguntaController perguntaController = new PerguntaController();
	private JTable table;
	private JButton btnBusca;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private String[] nomesColunas = { "Titulo ", "DT-Criação", "Usuario", "Categoria" };
	private JTextField textDuvida;

	/**
	 * Create the panel.
	 */
	public TelaPesquisa() {
		setLayout(null);

		JLabel lblTituloPagina = new JLabel("Consultar Oraculo");
		lblTituloPagina.setBounds(185, 31, 86, 14);
		add(lblTituloPagina);
		
		ArrayList<Categoria> categorias = categoriaDAO.buscarTodas();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 87, 330, 200);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		textDuvida = new JTextField();
		textDuvida.setBounds(112, 56, 234, 20);
		add(textDuvida);
		textDuvida.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Duvida:");
		lblNewLabel.setBounds(56, 59, 46, 14);
		add(lblNewLabel);
		
		btnBusca = new JButton("Buscar");
		btnBusca.setBounds(351, 55, 89, 23);
		add(btnBusca);
		
		JButton btnVisualizar = new JButton("visualizar");
		btnVisualizar.setBounds(356, 171, 94, 23);
		add(btnVisualizar);
		atualizarCampos();
		
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(new Object[][] {,}, nomesColunas));

				DefaultTableModel model = (DefaultTableModel) table.getModel();

				ArrayList<Pergunta> perguntas = perguntaController.busca(textDuvida.getText());

				for (Pergunta pergunta : perguntas) {

					Object[] novaLinhaDaTabela = new Object[4];

					novaLinhaDaTabela[0] = pergunta.getTitulo();
					novaLinhaDaTabela[1] = pergunta.getData();
					novaLinhaDaTabela[2] = pergunta.getUsuario().getNome();
					novaLinhaDaTabela[3] = pergunta.getCategoria().getNome();

					model.addRow(novaLinhaDaTabela);
				}
			}
		});
	}

	public void pesquisar() {
		perguntaController.busca(textDuvida.getText());
	}

	public void atualizarCampos() {
		pesquisa = new Pesquisa() ;
	}
}
