package com.redhat.customer.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class XlateRoutebuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activemq:queue:q.empi.deim.in")
       	.log(LoggingLevel.INFO, "com.redhat.customer.camel.XlateRoutebuilder", "Retrieved from the queue : ${body}")
		.to("direct:unmarshallXml")
		.log(LoggingLevel.INFO, "com.redhat.customer.camel.XlateRoutebuilder", "Unmarshalled from XML to: ${body}");
	}

}
