
package nodeclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.text.MessageFormat;
import javax.ws.rs.core.MediaType;

/**
 * REST resource client.
 *
 * @author James
 */
public class WebService {
    
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SpectrumAnalyzer/webresources";

    public WebService(String resourcePath) {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path(resourcePath);
    }

    public <T> T queryNode(Class<T> responseType, String nodeid) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}", new Object[]{nodeid}));
        return resource.accept(MediaType.APPLICATION_JSON).get(responseType);
    }

    public String submitAllocation(Object requestEntity, String nodeid) throws UniformInterfaceException {
        return webResource.path(MessageFormat.format("{0}/submit_allocation", new Object[]{nodeid})).type(MediaType.APPLICATION_JSON).put(String.class, requestEntity);
    }

    public void close() {
        client.destroy();
    }

    public void setUsernamePassword(String username, String password) {
        client.addFilter(new HTTPBasicAuthFilter(username, password));
    }
    
}
