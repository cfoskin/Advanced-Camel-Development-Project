package com.redhat.usecase;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelContextXmlTest extends CamelSpringTestSupport {

	// TODO Create test message bodies that work for the route(s) being tested
	// Expected message bodies
	protected Object[] expectedBodies = { "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
			"<p:Person xmlns:p=\"http://www.app.customer.com\"\n" + 
			"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + 
			"  xsi:schemaLocation=\"http://www.app.customer.com PatientDemographics.xsd \">\n" + 
			"\n" + 
			"  <p:age>30</p:age>\n" + 
			"  <p:legalname>\n" + 
			"    <p:given>First</p:given>\n" + 
			"    <p:family>Last</p:family>\n" + 
			"  </p:legalname>\n" + 
			"  <p:fathername>Dad</p:fathername>\n" + 
			"  <p:mothername>Mom</p:mothername>\n" + 
			"  <p:gender xsi:type=\"p:Code\">\n" + 
			"    <p:code>Male</p:code>\n" + 
			"  </p:gender>\n" + 
			"</p:Person>"};
	// Templates to send to input endpoints

	@Produce(uri = "direct:integrateRoute")
	protected ProducerTemplate integrateEndpoint;
	
	@Test
	public void testCamelRoute() throws Exception {

		for (Object expectedBody : expectedBodies) {
			integrateEndpoint.sendBody(expectedBody);
		}

		// Validate our expectations
		assertMockEndpointsSatisfied();
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camelContext.xml");
	}

}
