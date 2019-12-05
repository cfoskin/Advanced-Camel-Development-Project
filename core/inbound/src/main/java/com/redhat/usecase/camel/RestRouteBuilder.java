package com.redhat.usecase.camel;

import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class RestRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		 
		from("direct:integrateRoute")
       	.log(LoggingLevel.INFO, "com.usecase.camel.RestRouteBuilder", "direct:integrateRoute called")
       	.setExchangePattern(ExchangePattern.InOut)
       	.transform(constant(2))
       	.end()
       ;
		
	}

}
