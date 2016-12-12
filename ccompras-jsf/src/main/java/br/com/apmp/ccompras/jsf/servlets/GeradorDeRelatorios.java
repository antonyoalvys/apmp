package br.com.apmp.ccompras.jsf.servlets;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class GeradorDeRelatorios {
	
	private Connection conexao;

	public GeradorDeRelatorios(Connection conexao) {
		this.conexao = conexao;
	}

	public void geraPdf(InputStream jrxml, Map<String, Object> parametros, OutputStream saida) {

		try {
			
		//	InputStream jrxml_sub = getClass().getClassLoader().getResourceAsStream("relatorios/sub_rep_proj_tar.jrxml");
			//JasperReport sub_jasper = JasperCompileManager.compileReport(jrxml_sub);
			// compila jrxml em memoria
			JasperReport jasper = JasperCompileManager.compileReport(jrxml);

			// preenche relatorio
			JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);

			// exporta para pdf
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(saida));

			exporter.exportReport();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar relatório", e);
		}
	}	

}
