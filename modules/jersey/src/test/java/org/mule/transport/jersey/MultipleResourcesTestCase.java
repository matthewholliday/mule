package org.mule.transport.jersey;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;
import org.mule.transport.http.HttpConnector;
import org.mule.transport.http.HttpConstants;

/**
 * Tests that the jersey:resources component can handle multiple components correctly.
 */
public class MultipleResourcesTestCase extends FunctionalTestCase {
    
    public void testParams() throws Exception
    {
        MuleClient client = new MuleClient(muleContext);

        Map<String, String> props = new HashMap<String, String>();
        props.put(HttpConnector.HTTP_METHOD_PROPERTY, HttpConstants.METHOD_GET);
        MuleMessage result = client.send("http://localhost:63081/helloworld/sayHelloWithUri/Dan", "", props);
        assertEquals((Integer)200, result.getInboundProperty(HttpConnector.HTTP_STATUS_PROPERTY, 0));
        assertEquals("Hello Dan", result.getPayloadAsString());
        
        result = client.send("http://localhost:63081/anotherworld/sayHelloWithUri/Dan", "", props);
        assertEquals((Integer)200, result.getInboundProperty(HttpConnector.HTTP_STATUS_PROPERTY, 0));
        assertEquals("Bonjour Dan", result.getPayloadAsString());
    }
    
    @Override
    protected String getConfigResources() 
    {
        return "multiple-resources-conf.xml";
    }

}
