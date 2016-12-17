package br.com.apmp.ccompras.service.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.querydsl.core.Tuple;

import br.com.apmp.ccompras.domain.entities.FileAgreement;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.repository.FileAgreementRepository;
import br.com.apmp.ccompras.service.FileAgreementService;
import br.com.apmp.ccompras.service.PeriodService;
import br.com.apmp.ccompras.service.PurchaseTicketService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class FileAgreementServiceImpl implements FileAgreementService {

	private static final long serialVersionUID = 3175158112059892738L;

	@Inject
	private FileAgreementRepository fileAgreementRepository;

	@Inject
	private PeriodService periodService;

	@Inject
	private PurchaseTicketService purchaseTicketService;

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void generate( Period period ) {
		if ( period.getHasFileCovenant() ) {
			FileAgreement fileCovenant = this.fileAgreementRepository.findByPeriodId( period.getId() );
			delete( fileCovenant );
		}
		FileAgreement newFileCovenant = new FileAgreement();
		newFileCovenant.setPeriod( period );
		writeFile( newFileCovenant );
		this.fileAgreementRepository.save( newFileCovenant );

	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<FileAgreement> findByPeriod( Period period ) {
		return fileAgreementRepository.findByPeriod( period );
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void delete( FileAgreement entity ) {
		Period period = entity.getPeriod();
		this.fileAgreementRepository.delete( entity );
		period.setHasFileCovenant( false );
		this.periodService.save( period );
	}

	private void writeFile( FileAgreement newFileCovenant ) {
		String nameFile = "apmp_ccompra_" + newFileCovenant.getPeriod().nameForPath();
		newFileCovenant.setName( nameFile );
		Path path = Paths.get( "/tmp/" + nameFile );

		List<Tuple> tuples = purchaseTicketService.findForFile( newFileCovenant.getPeriod().getId() );

		try (BufferedWriter writer = Files.newBufferedWriter( path )) {
			boolean isBegin = true;
			for ( Tuple tuple : tuples ) {
				if ( !isBegin )
					writer.write( "\r\n" );
				else
					isBegin = false;

				writer.write( formatField( tuple.get( 0, String.class ), String.class, 5, SideFit.RIGHT ) );
				writer.write( formatField( tuple.get( 1, BigDecimal.class ), BigDecimal.class, 13, SideFit.LEFT ) );
			}
			writer.close();
			newFileCovenant.setFile( Files.readAllBytes( path ) );
		} catch ( IOException e ) {
			e.printStackTrace();
			throw new ServiceException( "Falha de entrada/saída ao gerar tentar gerar o arquivo." );
		}
	}

	public static <T> String formatField( T field, Class<T> type, int size, SideFit sideFit ) {
		if ( type == String.class ) {
			String formatedField = (String) field;
			if ( formatedField.length() > size )
				throw new ServiceException( "Falha ao gerar o arquivo. Tamanho de campo inválido." );
			while ( formatedField.length() < size ) {
				if ( sideFit.equals( SideFit.LEFT ) )
					formatedField = "0" + formatedField;
				else if ( sideFit.equals( SideFit.RIGHT ) )
					formatedField = formatedField + "0";
			}
			return formatedField;

		} else if ( type == BigDecimal.class ) {
			BigDecimal bigDecimalField = (BigDecimal) field;
			String formatedField = bigDecimalField.toPlainString().replaceAll( "\\.", "" );

			if ( formatedField.length() > size )
				throw new ServiceException( "Falha ao gerar o arquivo. Tamanho de campo inválido." );
			while ( formatedField.length() < size ) {
				if ( sideFit.equals( SideFit.LEFT ) )
					formatedField = "0" + formatedField;
				else if ( sideFit.equals( SideFit.RIGHT ) )
					formatedField = formatedField + "0";
			}
			return formatedField;

		}
		throw new ServiceException( "Falha ao gerar o arquivo. Tipo " + type + " desconhecido" );
	}

}
