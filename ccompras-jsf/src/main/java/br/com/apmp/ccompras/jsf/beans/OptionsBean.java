package br.com.apmp.ccompras.jsf.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.apmp.ccompras.domain.enums.PhoneType;
import br.com.apmp.ccompras.jsf.enums.MaskOptions;

@Named
@ApplicationScoped
public class OptionsBean implements Serializable {

	private static final long serialVersionUID = 4303916924249640860L;

	public String phoneMask( PhoneType phoneType ) {
		if ( phoneType.equals( PhoneType.LANDLINE ) )
			return MaskOptions.LANDLINE_PHONE_MASK.getDescription();
		if ( phoneType.equals( PhoneType.MOBILE ) )
			return MaskOptions.MOBILE_PHONE_MASK.getDescription();
		return "";
	}

}
