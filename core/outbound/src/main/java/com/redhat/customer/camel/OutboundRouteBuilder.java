package com.redhat.customer.camel;

import java.net.ConnectException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class OutboundRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		onException(ConnectException.class)
		 .maximumRedeliveries(3)
       	.log(LoggingLevel.INFO, "com.redhat.customer.camel.OutboundRouteBuilder", "Exception:" + simple("${body}"))
		.to("activemq:queue:q.empi.nextgate.dlq")
        .handled(true);
		
		from("activemq:queue:q.empi.nextgate.out")
       	.log(LoggingLevel.INFO, "com.redhat.customer.camel.OutboundRouteBuilder", "Retrieved from the queue : ${body}")
       	.to("direct:unmarshallXml")
		.log(LoggingLevel.INFO, "com.redhat.customer.camel.OutboundRouteBuilder", "Unmarshalled from XML to: ${body}")
       	.beanRef("transformToExecuteMatch", "convertTo")
		.log(LoggingLevel.INFO, "com.redhat.customer.camel.OutboundRouteBuilder", "Converted to: ${body}")
		.to("cxf:bean:nextgateService?defaultOperationName=executeMatchUpdate");
	}

}
