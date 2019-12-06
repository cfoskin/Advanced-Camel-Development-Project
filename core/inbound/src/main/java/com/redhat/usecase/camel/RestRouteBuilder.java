package com.redhat.usecase.camel;

import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class RestRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		//exception handling
		onException(IllegalArgumentException.class)
        .to("log:fail")
        .handled(true);
		
		//the inbound route
		from("direct:integrateRoute")
       	.log(LoggingLevel.INFO, "com.usecase.camel.RestRouteBuilder", "Body before the call : ${body}")
       	.setExchangePattern(ExchangePattern.InOnly)
       	.to("direct:marshallXml")//this is a route defined in spring DSL
       	.to("activemq:queue:q.empi.deim.in")
       	.log(LoggingLevel.INFO, "com.usecase.camel.RestRouteBuilder", "After marshalling: ${body}")
       	.transform(constant(2))
       	.end()
       ;
	}
}
