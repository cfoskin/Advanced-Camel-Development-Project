package com.redhat.customer.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class XlateRoutebuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activemq:queue:q.empi.deim.in")
       	.log(LoggingLevel.INFO, "com.redhat.customer.camel.XlateRoutebuilder", "Retrieved from the queue : ${body}")
		.to("direct:unmarshallXml")
		.log(LoggingLevel.INFO, "com.redhat.customer.camel.XlateRoutebuilder", "Unmarshalled from XML to: ${body}")
       	.beanRef("transformToExecuteMatch", "convertTo")
		.log(LoggingLevel.INFO, "com.redhat.customer.camel.XlateRoutebuilder", "Converted to: ${body}")
		.to("direct:marshallXml")
		.log(LoggingLevel.INFO, "com.redhat.customer.camel.XlateRoutebuilder", "Marshalled to: ${body}")
		.to("activemq:queue:q.empi.nextgate.out")
		.log(LoggingLevel.INFO, "com.redhat.customer.camel.XlateRoutebuilder", "Sent to Queue nextgate.out");

	}

}
