package com.redhat.customer.translate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;

import com.customer.app.Person;
import com.sun.mdm.index.webservice.CallerInfo;
import com.sun.mdm.index.webservice.ExecuteMatchUpdate;
import com.sun.mdm.index.webservice.PersonBean;
import com.sun.mdm.index.webservice.SystemPerson;

@Converter
public class TransformToExecuteMatch {

	@Converter
	public Object[] convertTo(Object value, Exchange exchange) throws TypeConversionException {
		
		Object objArray[] = new Object[2];
		ExecuteMatchUpdate executeMatchUpdate = (ExecuteMatchUpdate) value;
		
		// These only show up in the logs and can be anything
		// We set the user to Xlate here to know the ESB was used
//		ArrayList<Serializable> payload = new ArrayList<Serializable>();

		objArray[0] = executeMatchUpdate.getCallerInfo();
		objArray[1] = executeMatchUpdate.getSysObjBean();
//		.add(executeMatchUpdate.getCallerInfo());
//		obj.add(executeMatchUpdate.getSysObjBean());

		if (exchange != null) {
			exchange.getOut().setBody(objArray);
		}
		return objArray;
	}

}
