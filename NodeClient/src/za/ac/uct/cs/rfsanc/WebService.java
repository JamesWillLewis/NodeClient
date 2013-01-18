package za.ac.uct.cs.rfsanc;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import javax.ws.rs.core.MediaType;

/**
 * REST resource wrapper - provides interface for performing standard HTTP
 * operations upon a specified REST resource on a server. Implemented with the
 * Jersey REST API.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
public class WebService {

    /**
     * The REST web resource context upon which HTTP methods are invoked.
     */
    private WebResource webResource;
    /**
     * The client which utilizes the web resource.
     */
    private Client client;
    /**
     * Base URI of the server (full URI excluding relative path of web resource)
     */
    private final String BASE_URI;
    /**
     * Relative path of the web resource.
     */
    private final String RESOURCE_PATH;

    /**
     * Construct a new Web Service.
     *
     * @param baseURI Base URI of the server (full host URI excluding relative
     * path of web resource)
     * @param resourcePath Relative path of the web resource.
     */
    public WebService(String baseURI, String resourcePath) {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        this.BASE_URI = baseURI;
        this.RESOURCE_PATH = resourcePath;
        webResource = client.resource(BASE_URI).path(RESOURCE_PATH);
    }

    /**
     * Perform HTTP GET method.
     * 
     * @param <T> Type of the response entity.
     * @param responseType Class type of response entity.
     * @param path Relative URI 
     * @return 
     */
    public <T> T doGet(Class<T> responseType, String path) {
        return webResource.path(path).type(MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T doPut(Class<T> responseType, String path, Object requestEntity) {
        return webResource.path(path).type(MediaType.APPLICATION_JSON).put(responseType, requestEntity);
    }

    public <T> T doPost(Class<T> responseType, String path, Object requestEntity) {
        return webResource.path(path).type(MediaType.APPLICATION_JSON).post(responseType, requestEntity);
    }

    public void close() {
        client.destroy();
    }

    public void setUsernamePassword(String username, String password) {
        client.addFilter(new HTTPBasicAuthFilter(username, password));
    }
}
