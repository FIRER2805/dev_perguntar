package model.gerador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.vo.Pergunta;
import model.vo.Usuario;

public class GeradorPlanilha {
	
	public String gerarPlanilhaPerguntas(List<Pergunta> perguntas, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Perguntas");
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Titulo");
		linhaCabecalho.createCell(1).setCellValue("DT-Criação");
		linhaCabecalho.createCell(2).setCellValue("Status");
		linhaCabecalho.createCell(3).setCellValue("Usuario");
		linhaCabecalho.createCell(4).setCellValue("Categoria");
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		int contadorLinhas = 1;
		for(Pergunta p: perguntas) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(p.getTitulo());
			novaLinha.createCell(1).setCellValue(p.getData().format(formatador));
			novaLinha.createCell(2).setCellValue(p.getDataResolucao() == null ? "Em Aberto" : "Resolvido");
			novaLinha.createCell(3).setCellValue(p.getUsuario().getNome());
			novaLinha.createCell(4).setCellValue(p.getCategoria().getNome());
			contadorLinhas++;
		}
		
		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}
	
	public String gerarPlanilhaUsuarios(List<Usuario> usuarios, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Perguntas");
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("nome");
		linhaCabecalho.createCell(1).setCellValue("num.Perguntas");
		linhaCabecalho.createCell(2).setCellValue("num.Respostas");
		linhaCabecalho.createCell(3).setCellValue("num.Soluções");
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		int contadorLinhas = 1;
		for(Usuario u: usuarios) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(u.getNome());
			novaLinha.createCell(1).setCellValue(u.getNumPergunta());
			novaLinha.createCell(2).setCellValue(u.getNumResposta());
			novaLinha.createCell(3).setCellValue(u.getNumsolucao());
			contadorLinhas++;
		}
		
		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}
	
	
	
	private String salvarNoDisco(HSSFWorkbook planilha, String caminhoArquivo) {
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso): " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}
