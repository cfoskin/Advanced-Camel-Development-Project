package com.redhat.customer.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class XlateRoutebuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activemq:queue:q.empi.deim.in")
       	.log(LoggingLevel.INFO, "com.usecase.camel.RestRouteBuilder", "Retrieved from the queue : ${body}");
		
	}

}
