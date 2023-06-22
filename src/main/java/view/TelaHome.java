package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import com.jgoodies.forms.layout.Sizes;

import controller.PerguntaController;
import model.vo.Categoria;
import model.vo.Pergunta;
import model.vo.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaHome extends JPanel {
	private JTable table;
	private PerguntaController p = new PerguntaController();
	private JButton btnVizualizar;
	private String[] nomesColunas = {  "Titulo ", "DT-Criação", "Usuario", "Categoria" };
	private ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();

	
	public JButton getBtnVizualizar() {
		return btnVizualizar;
	}
	
	
	/**
	 * Create the panel.
	 */
	public TelaHome() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(340dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("60dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:max(229dlu;min)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("top:default:grow"),}));
		
		JLabel lblTitulo = new JLabel("Duvidas Recentes");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblTitulo, "4, 4, center, default");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 6, fill, default");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		 btnVizualizar = new JButton("Vizualizar");
		add(btnVizualizar, "6, 6, default, center");
		
		
		
		perguntas = p.busca();
		
		atualizarTable();
		

	}
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] { , }, nomesColunas));
	}
	
	public void atualizarTable() {
		limparTabela();
		
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		
		for (Pergunta p : this.perguntas) {
			
			
			Object[] novaLinhaDaTabela = new Object[4];
			
			
			novaLinhaDaTabela[0] = p.getTitulo();
			novaLinhaDaTabela[1] = p.getData();
			novaLinhaDaTabela[2] = p.getUsuario();
			novaLinhaDaTabela[3] = p.getCategoria();
			
			
			
			model.addRow(novaLinhaDaTabela);
		}
		
	}
	
	
	
	
	

	

}
