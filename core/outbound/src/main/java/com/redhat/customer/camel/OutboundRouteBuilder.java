package com.redhat.customer.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class OutboundRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		onException(IllegalArgumentException.class)
       	.log(LoggingLevel.INFO, "com.redhat.customer.camel.OutboundRouteBuilder", "Exception: ${body}")
        .handled(true);
		
		
		from("activemq:queue:q.empi.nextgate.out")
       	.log(LoggingLevel.INFO, "com.redhat.customer.camel.OutboundRouteBuilder", "Retrieved from the queue : ${body}")
       	.to("direct:unmarshallXml")
		.log(LoggingLevel.INFO, "com.redhat.customer.camel.OutboundRouteBuilder", "Unmarshalled from XML to: ${body}");
	}

}
