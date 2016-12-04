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

import br.com.apmp.ccompras.domain.entities.FileCovenant;
import br.com.apmp.ccompras.domain.entities.Period;
import br.com.apmp.ccompras.domain.entities.PurchaseTicket;
import br.com.apmp.ccompras.domain.repository.FileCovenantRepository;
import br.com.apmp.ccompras.service.FileCovenantService;
import br.com.apmp.ccompras.service.PeriodService;
import br.com.apmp.ccompras.service.PurchaseTicketService;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class FileCovenantServiceImpl implements FileCovenantService {

	private static final long serialVersionUID = 3175158112059892738L;

	@Inject
	private FileCovenantRepository fileCovenantRepository;

	@Inject
	private PeriodService periodService;

	@Inject
	private PurchaseTicketService purchaseTicketService;

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void generate( Period period ) {
		if ( period.getHasFileCovenant() ) {
			FileCovenant fileCovenant = this.fileCovenantRepository.findByPeriodId( period.getId() );
			delete( fileCovenant );
		}
		FileCovenant newFileCovenant = new FileCovenant();
		newFileCovenant.setPeriod( period );
		writeFile( newFileCovenant );
		this.fileCovenantRepository.save( newFileCovenant );

	}

	@Override
	@TransactionAttribute( TransactionAttributeType.NEVER )
	public List<FileCovenant> findByPeriod( Period period ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@TransactionAttribute( TransactionAttributeType.REQUIRED )
	public void delete( FileCovenant entity ) {
		Period period = entity.getPeriod();
		this.fileCovenantRepository.delete( entity );
		period.setHasFileCovenant( false );
		this.periodService.save( period );
	}

	private void writeFile( FileCovenant newFileCovenant ) {
		String nameFile = "apmp_ccompra_" + newFileCovenant.getPeriod().nameForPath();
		newFileCovenant.setName( nameFile );
		Path path = Paths.get( "/tmp/" + nameFile );

		List<PurchaseTicket> purchaseTickets = purchaseTicketService.findByPeriodId( newFileCovenant.getPeriod().getId() );

		try (BufferedWriter writer = Files.newBufferedWriter( path )) {
			boolean isBegin = true;
			for ( PurchaseTicket purchaseTicket : purchaseTickets ) {
				if ( !isBegin )
					writer.newLine();
				else
					isBegin = false;

				writer.write( formatField( purchaseTicket.getAssociate().getEnrollment(), String.class, 5, SideFit.RIGHT ) );
				writer.write( formatField( purchaseTicket.getTicketValue(), BigDecimal.class, 13, SideFit.LEFT ) );
			}
			writer.close();
			newFileCovenant.setFile( Files.readAllBytes( path )  );
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
