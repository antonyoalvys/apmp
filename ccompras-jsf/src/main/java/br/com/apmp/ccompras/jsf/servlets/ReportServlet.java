package br.com.apmp.ccompras.jsf.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

@WebServlet( "/relatorios/extratoDesconto" )
public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = -1359022739246835497L;
	
	
	@PersistenceContext
	private EntityManager em;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Long periodId = new Long( req.getParameter( "periodoId" ) );
		String periodDesc = req.getParameter( "periodoDesc" );
		InputStream jrxml = getClass().getClassLoader().getResourceAsStream( "reports/extDescConvFornReport.jrxml" );
		Map<String, Object> parameters = new HashMap<>();
		parameters.put( "PERIOD_ID", periodId );
		parameters.put( "SUB_REPORT_DIR", "reports/" );
		parameters.put( "PERIOD_DESC", periodDesc );		
		
		// abre conexão com o banco
		Session session = em.unwrap(Session.class);
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				try {
					GeradorDeRelatorios gerador = new GeradorDeRelatorios(con);
					gerador.geraPdf(jrxml, parameters, resp.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
	}
	

}
