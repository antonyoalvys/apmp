package br.com.apmp.ccompras.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.enums.ReportType;
import br.com.apmp.ccompras.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class ReportServiceImpl implements ReportService {

	private static final long serialVersionUID = 3493164922698014118L;

	@PersistenceContext
	private EntityManager em;

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public void generate( ReportType reportType, Period period, OutputStream responseStream ) {
		try {

			// prepara parâmetros

			// abre conexão com o banco
			Session session = em.unwrap( Session.class );
			session.doWork( new Work() {

				@Override
				public void execute( Connection con ) throws SQLException {
					// TODO Auto-generated method stub
					try {
						InputStream jrxml = getClass().getClassLoader().getResourceAsStream( "reports/extDescConvFornReport.jrxml" );
						Map<String, Object> parameters = new HashMap<>();
						parameters.put( "PERIOD_ID", period.getId() );
						parameters.put( "SUB_REPORT_DIR", "reports/" );
						parameters.put( "PERIOD_DESC", period.getDescription() );

						JasperReport jasper = JasperCompileManager.compileReport( jrxml );

						// preenche relatorio
						JasperPrint print = JasperFillManager.fillReport( jasper, parameters, con );

						// exporta para pdf
						JRPdfExporter exporter = new JRPdfExporter();
						exporter.setExporterInput( new SimpleExporterInput( print ) );

						exporter.setExporterOutput( new SimpleOutputStreamExporterOutput( responseStream ) );

						exporter.exportReport();
					} catch ( JRException e ) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} );

		} catch ( Exception e ) {
			throw new RuntimeException( "Erro ao gerar relatório", e );
		}
	}

}
